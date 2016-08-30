package sale.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sale.base.BaseDao;
import sale.table.Member;


public class MemberDao extends BaseDao{
	private HashMap<String, Member> memberCache = new LinkedHashMap<>();
	
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
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select m from " + Member.class.getName() + " m where username = :userName";
			Query query = session.createQuery(sql);
			query.setParameter("userName", userName);
			Member member = (Member)query.uniqueResult();
			tx.commit();
			session.close();
			return member;
		}catch (Exception e) {
			return null;
		}
	}
	
	public Member getMemberDB(String userName, String pass){
		try{
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String sql = "select m from " + Member.class.getName() + " m where username = :userName and password = :pass";
			Query query = session.createQuery(sql);
			query.setParameter("userName", userName);
			query.setParameter("pass", pass);
			Member member = (Member)query.uniqueResult();
			tx.commit();
			session.close();
			if(null != member && !memberCache.containsKey(userName)){
				member.setPassword("");
				memberCache.put(userName, member);
			}
			return member;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void registerMember(Member member, String password){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		member.setPassword(password);
		session.save(member);
		tx.commit();
		session.close();
	}
	
	public List<Member> searchCustomer(String name, String email, String phoneNumber){
		String sql = "select m from " + Member.class.getName() + " m where 1=1 ";
		int index = 0;
		if(null != name && name.trim().length() > 0){
			sql += "and name like :name ";
			index++;
		}
		if(null != email && email.trim().length() > 0){
			sql += "and email like :email";
			index++;
		}
		if(null != phoneNumber && phoneNumber.trim().length() > 0){
			sql += "and phone_number like :phoneNumber";
			index++;
		}
		
		sql += "and role = 'customer'";
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(sql);
		if(null != name && name.trim().length() > 0)
			query.setParameter("name", "%" + name + "%");
		if(null != email && email.trim().length() > 0)
			query.setParameter("email", "%" + email + "%");
		if(null != phoneNumber && phoneNumber.trim().length() > 0)
			query.setParameter("phoneNumber", "%" + phoneNumber + "%");
		List<Member> memberList = query.list();
		tx.commit();
		session.close();
		return memberList;
	}
	
	public List<Member> searchEmployee(String name, String email, String phoneNumber){
		String sql = "select m from " + Member.class.getName() + " m where 1=1 ";
		int index = 0;
		if(null != name && name.trim().length() > 0){
			sql += "and name like :name ";
			index++;
		}
		if(null != email && email.trim().length() > 0){
			sql += "and email like :email";
			index++;
		}
		if(null != phoneNumber && phoneNumber.trim().length() > 0){
			sql += "and phone_number like :phoneNumber";
			index++;
		}
		
		sql += "and role = 'employee'";
		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(sql);
		if(null != name && name.trim().length() > 0)
			query.setParameter("name", "%" + name + "%");
		if(null != email && email.trim().length() > 0)
			query.setParameter("email", "%" + email + "%");
		if(null != phoneNumber && phoneNumber.trim().length() > 0)
			query.setParameter("phoneNumber", "%" + phoneNumber + "%");
		List<Member> memberList = query.list();
		tx.commit();
		session.close();
		return memberList;
	}
	
	public List<Member> getAllEmployee(){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "select m from " + Member.class.getName() + " m where role='employee'";
		List<Member> listEmployee  = session.createQuery(sql).list();
		tx.commit();
		session.close();
		return listEmployee;
	}
	
	public void deleteMember(String userName){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "delete from " + Member.class.getName() + " where username = '" + userName + "'";
		Query query = session.createQuery(sql);
		query.executeUpdate();
		tx.commit();
		session.close();
		reloadMember(userName);
	}
	
	public void updateMember(Member member, String password){
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String sql = "update " + Member.class.getName() + " set ";
		sql += " email = '" + member.getEmail() + "'";
		sql += ", phone_number = '" + member.getPhoneNumber() + "'";
		sql += ", name = '" + member.getName() + "'";
		sql += ", address = '" + member.getAddress() + "'";
		sql += ", birthday = :birthday ";
		sql += " ,gender = '" + member.getGender() + "'";
		if(null != password && password.length() > 0)
			sql += ", password = '" + password + "'";
		sql += " where username = '" + member.getUserName() + "'" ;
		Timestamp birthDay = (null == member.getBirthDate() ? null : new Timestamp(member.getBirthDate().getTime()));
		Query query = session.createQuery(sql);
		query.setParameter("birthday", birthDay);
		query.executeUpdate();
		tx.commit();
		session.close();
		reloadMember(member.getUserName());
	}
}
