package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import VO.UserVo;

public class userdao {
	private final String select = "select *from test_user";
	
	UserVo user;

	public List<UserVo> sel() {
		
		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 ResultSet rs = null;
		 PreparedStatement ps = null;
		 Connection conn = null;
		 
		 List<UserVo> users = new ArrayList<UserVo>();	   
		
		 try {
			 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SPRINGUSER","1004");
			 ps = conn.prepareStatement(select);
			 rs = ps.executeQuery();
		     while(rs.next()) {
				user = new UserVo();
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
				users.add(user);
			}
			
		} catch (Exception e) {
			
		}finally { //다쓰면 연결을 해제해야한다. 해제를 안시키면 다른 사용자가 쓸수 없으므로. 닫을때 finally를 사용하여 닫는다.
            // 열렸는지 체크하면서 닫아줘야한다. 값이변경됬는지 보고 열린지 판단 / resultset부터 반대로 확인 > statement > connection 순으로
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }            
        }


		return users;
	}
}

