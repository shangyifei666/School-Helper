package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.UserBean;
import dao.DataBase;

public class UserDao {

	/*
	 * 功能：setUser  注册时向数据库存入用户信息
	 * 开发人：尚一飞
	 * 开发时间：2018.11.27
	 * 
	 * 描述：传入UserBean类型数据将其存入数据库
	 * */
	public static void setUser(UserBean use) {
			
			Connection conn = DataBase.getConnection();
			String sql = "insert into user(user_phone,user_password,user_name,school_id,user_student_num,user_image,user_money,user_reputation_value,user_took_count,user_publish_count,user_identification,user_signature,user_realname,user_sex) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, use.getUserPhone());
				pstmt.setString(2, use.getUserPassword());
				pstmt.setString(3, use.getUserName());
				pstmt.setInt(4, use.getSchoolId());
				pstmt.setString(5, use.getUserStudentNum());
				pstmt.setString(6, use.getImage());
				pstmt.setDouble(7, use.getUserMoney());
				pstmt.setInt(8, use.getUserReputationValue());
				pstmt.setInt(9, use.getUserTookCount());
				pstmt.setInt(10, use.getUserPublishCount());
				pstmt.setInt(11, use.getUserIdentification());
				pstmt.setString(12, use.getUserSignature());
				pstmt.setString(13, use.getUserRealname());
				pstmt.setString(14, use.getUserSex());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	/*
	 * 功能：reviseUser 修改数据库用户信息
	 * 开发人：杨旭辉
	 * 开发时间：2018.11.28
	 * 
	 * 描述：传入UserBean类型信息，通过userId查到数据库中user信息并将其覆盖
	 * */
	public static int reviseUser(UserBean user) throws Exception{
		Connection conn=DataBase.getConnection();
		String sql="update user set user_name='"+user.getUserName()+"',user_password='"+user.getUserPassword()+"',user_phone='"+user.getUserPhone()+"',user_money="+user.getUserMoney()+",user_took_count="+user.getUserTookCount()+",user_publish_count="+user.getUserPublishCount()+",user_identification="+user.getUserIdentification()+",user_signature='"+user.getUserSignature()+"',user_realname='"+user.getUserRealname()+"',user_sex='"+user.getUserSex()+"' where user_id="+user.getUserId()+"";
		Statement stmt=conn.createStatement();
		int result=stmt.executeUpdate(sql);
		conn.close();
		return result;
	}
	
	public List<UserBean> getAllUser(){
		List<UserBean> userList=new ArrayList<UserBean>();
		Connection conn=DataBase.getConnection();
		String sql="select * from user";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				UserBean userbean =new UserBean();
				userbean.setUserId(rs.getInt("user_id"));
				userbean.setUserName(rs.getString("user_name"));
				userbean.setUserPassword(rs.getString("user_password"));
				userbean.setSchoolId(rs.getInt("school_id"));
				userbean.setUserStudentNum(rs.getString("user_student_num"));
				userbean.setUserPhone(rs.getString("user_phone"));
				userbean.setImage(rs.getString("user_image"));
				userbean.setUserMoney(rs.getDouble("user_money"));
				userbean.setUserReputationValue(rs.getInt("user_reputation_value"));
				userbean.setUserTookCount(rs.getInt("user_took_count"));
				userbean.setUserPublishCount(rs.getInt("user_publish_count"));
				userbean.setUserIdentification(rs.getInt("user_identification"));
				userbean.setUserSignature(rs.getString("user_signature"));
				userbean.setUserRealname(rs.getString("user_realname"));
				userbean.setUserSex(rs.getString("user_sex"));
				userList.add(userbean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userList;
	}
	/*
	 * 功能：checkUser 查询数据库该用户信息
	 * 开发人：尚一飞
	 * 开发时间：2018.11.27
	 * 
	 * 描述：通过传入的用户id查找该用户的所有信息
	 * */
	public static UserBean checkUser(int userId) {
		UserBean user = new UserBean();
		Connection conn = DataBase.getConnection();
		String sql = "select user_id,user_name,user_password,school_id,user_student_num,user_phone,user_image,user_money,user_reputation_value,user_took_count,user_publish_count,user_identification,user_signature,user_realname,user_sex from user where user_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setSchoolId(rs.getInt("school_id"));
				user.setUserStudentNum(rs.getString("user_student_num"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setImage(rs.getString("user_image"));
				user.setUserMoney(rs.getShort("user_money"));
				user.setUserReputationValue(rs.getInt("user_reputation_value"));
				user.setUserTookCount(rs.getInt("user_took_count"));
				user.setUserPublishCount(rs.getInt("user_publish_count"));
				user.setUserIdentification(rs.getInt("user_identification"));
				user.setUserSignature(rs.getString("user_signature"));
				user.setUserRealname(rs.getString("user_realname"));
				user.setUserSex(rs.getString("user_sex"));
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
		
	}
}