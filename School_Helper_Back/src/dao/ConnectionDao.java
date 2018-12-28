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
		String sql = "insert into connection(poster_id,receiver_id,reward_id) values(?,?,?)";
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
	public List<ConnectionBean> selectConnectiontwo(int rewardId) {
		List<ConnectionBean> connectionList = new ArrayList<ConnectionBean>();
		Connection conn = DataBase.getConnection();
		String sql = "select * from connection where reward_id=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,rewardId);
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
	public List<ConnectionBean> selectConnectionone(int posterId) {
		List<ConnectionBean> connectionListone = new ArrayList<ConnectionBean>();
		Connection conn = DataBase.getConnection();
		String sql = "select * from connection where poster_id=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,posterId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConnectionBean con = new ConnectionBean();
				con.setConnectionId(rs.getInt("connection_id"));
				con.setRecriverId(rs.getInt("receiver_id"));
				con.setPosterId(rs.getInt("poster_id"));
				con.setRewardId(rs.getInt("reward_id"));
				connectionListone.add(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connectionListone;
	}
	public void delete(int rewardId) {
		Connection conn=DataBase.getConnection();
		PreparedStatement pstmt=null;
		String sql="delete from connection where reward_id=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rewardId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DataBase.close(pstmt);
			DataBase.close(conn);
		}
	}
}
