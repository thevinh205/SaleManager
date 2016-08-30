package sale.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import sale.table.Member;

public class MemberMapper implements RowMapper<Member> {
   public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
      Member member = new Member();
      member.setId(rs.getInt("id"));
      member.setUserName(rs.getString("username"));
      member.setEmail(rs.getString("email"));
      member.setPhoneNumber(rs.getString("phone_number"));
      member.setName(rs.getString("name"));
      member.setAddress(rs.getString("address"));
      member.setLevel(rs.getInt("level"));
      member.setState(rs.getString("state"));
      if(null != rs.getTimestamp("birthday"))
    	  member.setBirthDate(new Date(rs.getTimestamp("birthday").getTime()));
      if(null != rs.getTimestamp("create_date"))
    	  member.setCreateDate(new Date(rs.getTimestamp("create_date").getTime()));
      member.setGender(rs.getString("gender"));
      member.setRole(rs.getString("role"));
      return member;
   }
}
