package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ConnectionBean;
import bean.RewardBean;
import bean.UserBean;
/*
 * 功能：ConnectionDao
 * 开发人：杨旭辉
 * 开发时间：2018.12.26
 * */
public class ConnectionDao {
	public static void setConnection(ConnectionBean con) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into connection(poster_id,recriver_id,reward_id) values(?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, con.getPosterId());
			pstmt.setInt(2, con.getRecriverId());
			pstmt.setInt(3, con.getRewardId());
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
	
	public List<ConnectionBean> selectConnection(int receiverId) {
		List<ConnectionBean> connectionList = new ArrayList<ConnectionBean>();
		Connection conn = DataBase.getConnection();
		String sql = "select * from connection where receiver_id=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,receiverId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConnectionBean con = new ConnectionBean();
				con.setConnectionId(rs.getInt("connection_id"));
				con.setRecriverId(rs.getInt("receiver_id"));
				con.setPosterId(rs.getInt("poster_id"));
				con.setRewardId(rs.getInt("reward_id"));
				connectionList.add(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connectionList;
	}
}
