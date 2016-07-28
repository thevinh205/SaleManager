package sale.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import sale.model.Member;
import sale.model.mapper.MemberMapper;


public class MemberDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private HashMap<String, Member> memberCache = new LinkedHashMap<>();
   
	public void setDataSource(DataSource dataSource) {
	  this.dataSource = dataSource;
	  this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public Member getMember(String userName){
		Member member = memberCache.get(userName);
		if(null == member){
			member = getMemberDB(userName);
			if(null != member){
				memberCache.put(userName, member);
			}
		}
		return member;
	}
	
	public void reloadMember(String userName){
		if(memberCache.containsKey(userName))
			memberCache.remove(userName);
		Member member = getMemberDB(userName);
		if(null != member){
			memberCache.put(userName, member);
		}
	}
	
	public Member getMemberDB(String userName){
		try{
			String sql = "select * from member where username = ?";
			Member member = jdbcTemplateObject.queryForObject(sql, new Object[]{userName}, new MemberMapper());
			return member;
		}catch (Exception e) {
			return null;
		}
	}
	
	public Member getMemberDB(String userName, String pass){
		try{
			String sql = "select * from member where username = ? and password = ?";
			Member member = jdbcTemplateObject.queryForObject(sql, new Object[]{userName, pass}, new MemberMapper());
			if(null != member && !memberCache.containsKey(userName)){
				memberCache.put(userName, member);
			}
			return member;
		}catch (Exception e) {
			return null;
		}
	}
	
	public void registerMember(Member member, String password){
		String sql = "insert into member "
				+ "(username, email, phone_number, name, address, level, state, birthday, create_date, gender, password, role) "
				+ "values (?, ?, ?, ?, ?, ? ,? , ?, ?, ?, ?, ?)";
		jdbcTemplateObject.update( sql, member.getUserName(), 
										member.getEmail(), 
										member.getPhoneNumber(),
										member.getName(),
										member.getAddress(),
										member.getLevel(),
										member.getState(),
										null == member.getBirthDate() ? null : new Timestamp(member.getBirthDate().getTime()),
										new Timestamp(member.getCreateDate().getTime()),
										member.getGender(),
										password,
										member.getRole());
	}
	
	public List<Member> searchCustomer(String name, String email, String phoneNumber){
		String sql = "select * from member where 1=1 ";
		int index = 0;
		if(null != name && name.trim().length() > 0){
			sql += "and name like ? ";
			index++;
		}
		if(null != email && email.trim().length() > 0){
			sql += "and email like ?";
			index++;
		}
		if(null != phoneNumber && phoneNumber.trim().length() > 0){
			sql += "and phone_number like ?";
			index++;
		}
		
		Object[] objects = new Object[index];
		index = 0;
		if(null != name && name.trim().length() > 0){
			objects[index] = "%" + name + "%";
			index++;
		}
		if(null != email && email.trim().length() > 0){
			objects[index] = "%" + email + "%";
			index++;
		}
		if(null != phoneNumber && phoneNumber.trim().length() > 0){
			objects[index] = "%" + phoneNumber + "%";
		}
		List<Member> memberList = jdbcTemplateObject.query(sql, objects, new MemberMapper());
		return memberList;
	}
	
	public void deleteCustomer(String userName){
		String sql = "delete from member where username = '" + userName + "'";
		jdbcTemplateObject.update(sql);
		reloadMember(userName);
	}
	
	public void updateMember(Member member, String password){
		String sql = "update member set ";
		sql += " email = '" + member.getEmail() + "'";
		sql += ", phone_number = '" + member.getPhoneNumber() + "'";
		sql += ", name = '" + member.getName() + "'";
		sql += ", address = '" + member.getAddress() + "'";
		sql += ", birthday = ? ";
		sql += " ,gender = '" + member.getGender() + "'";
		if(null != password && password.length() > 0)
			sql += ", password = '" + password + "'";
		sql += " where username = '" + member.getUserName() + "'" ;
		Timestamp birthDay = (null == member.getBirthDate() ? null : new Timestamp(member.getBirthDate().getTime()));
		jdbcTemplateObject.update(sql, new Object[]{birthDay});
		reloadMember(member.getUserName());
	}
}
