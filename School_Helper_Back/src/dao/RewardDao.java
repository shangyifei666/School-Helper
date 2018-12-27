package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.RewardBean;
import bean.UserBean;

public class RewardDao {
	/**
	 * @author 尚一飞
	 * @date 2018年12月18日上午10:44:45
	 * 简介：插入新的赏金任务
	 */
	public static void setReward(RewardBean reward) {
		Connection conn = DataBase.getConnection();
		String sql = "insert into reward(poster_id,reward_title,reward_content,reward_money,reward_time,reward_deadline,reward_state,reward_image) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reward.getPosterId());
			pstmt.setString(2, reward.getRewardTitle());
			pstmt.setString(3, reward.getRewardContent());
			pstmt.setDouble(4, reward.getRewardMoney());
			pstmt.setString(5, reward.getRewardTime());
			pstmt.setString(6, reward.getRewardDeadline());
			pstmt.setString(7, reward.getRewardState());
			pstmt.setString(8, reward.getRewardImage());
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
	
	/**
	 * 姓名：尚一飞
	 * 日期：2018.12.18
	 * 简介：为Board查找状态为"1"的赏金任务
	 */
	public List<RewardBean> selectBoardReward() {
		List<RewardBean> rewardList = new ArrayList<RewardBean>();
		Connection conn = DataBase.getConnection();
		String sql = "select * from reward where reward_state='1'";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RewardBean reward = new RewardBean();
				reward.setPosterId(rs.getInt("poster_id"));
				reward.setRewardId(rs.getInt("reward_id"));
				reward.setRewardTitle(rs.getString("reward_title"));
				reward.setRewardContent(rs.getString("reward_content"));
				reward.setRewardImage(rs.getString("reward_image"));
				reward.setRewardTime(rs.getString("reward_time"));
				reward.setRewardDeadline(rs.getString("reward_deadline"));
				reward.setRewardMoney(rs.getShort("reward_money"));
				reward.setRewardState(rs.getString("reward_state"));
				rewardList.add(reward);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rewardList;
	}
	/**
	 * 姓名：杨旭辉
	 * 日期：2018.12.26
	 * 简介：查找我发布的任务
	 */
	public List<RewardBean> MyPublish(int posterId) {
		List<RewardBean> rewardList = new ArrayList<RewardBean>();
		Connection conn = DataBase.getConnection();
		String sql = "select * from reward where poster_id=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, posterId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RewardBean reward = new RewardBean();
				reward.setPosterId(rs.getInt("poster_id"));
				reward.setRewardId(rs.getInt("reward_id"));
				reward.setRewardTitle(rs.getString("reward_title"));
				reward.setRewardContent(rs.getString("reward_content"));
				reward.setRewardImage(rs.getString("reward_image"));
				reward.setRewardTime(rs.getString("reward_time"));
				reward.setRewardDeadline(rs.getString("reward_deadline"));
				reward.setRewardMoney(rs.getShort("reward_money"));
				reward.setRewardState(rs.getString("reward_state"));
				rewardList.add(reward);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rewardList;
	}
	public List<RewardBean> MyPublishone(int rewardId) {
		List<RewardBean> rewardList = new ArrayList<RewardBean>();
		Connection conn = DataBase.getConnection();
		String sql = "select * from reward where reward_id=?";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rewardId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RewardBean reward = new RewardBean();
				reward.setPosterId(rs.getInt("poster_id"));
				reward.setRewardId(rs.getInt("reward_id"));
				reward.setRewardTitle(rs.getString("reward_title"));
				reward.setRewardContent(rs.getString("reward_content"));
				reward.setRewardImage(rs.getString("reward_image"));
				reward.setRewardTime(rs.getString("reward_time"));
				reward.setRewardDeadline(rs.getString("reward_deadline"));
				reward.setRewardMoney(rs.getShort("reward_money"));
				reward.setRewardState(rs.getString("reward_state"));
				rewardList.add(reward);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rewardList;
	}
	public static int reviseState(RewardBean reward) throws Exception{
		Connection conn=DataBase.getConnection();
		String sql="update reward set reward_state='"+reward.getRewardState()+"'where reward_id="+reward.getRewardId()+"";
		Statement stmt=conn.createStatement();
		int result=stmt.executeUpdate(sql);
		conn.close();
		return result;
	}
	public List<RewardBean> getAllReward() {
		List<RewardBean> rewardList = new ArrayList<RewardBean>();
		Connection conn = DataBase.getConnection();
		String sql = "select * from reward";
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RewardBean reward = new RewardBean();
				reward.setPosterId(rs.getInt("poster_id"));
				reward.setRewardId(rs.getInt("reward_id"));
				reward.setRewardTitle(rs.getString("reward_title"));
				reward.setRewardContent(rs.getString("reward_content"));
				reward.setRewardImage(rs.getString("reward_image"));
				reward.setRewardTime(rs.getString("reward_time"));
				reward.setRewardDeadline(rs.getString("reward_deadline"));
				reward.setRewardMoney(rs.getShort("reward_money"));
				reward.setRewardState(rs.getString("reward_state"));
				rewardList.add(reward);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rewardList;
	}
}
