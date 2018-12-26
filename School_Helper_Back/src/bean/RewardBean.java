package bean;

import java.sql.Time;

public class RewardBean {
	/**
	 * @author 尚一飞
	 * @date 2018年12月18日上午10:46:12
	 * 
	 */
	
	private int rewardId;
	private int posterId;
	private String rewardContent;
	private String rewardTitle;
	private double rewardMoney;
	private String rewardTime;
	private String rewardDeadline;
	private String rewardState;
	private String rewardImage;
	public RewardBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public RewardBean(int rewardId, int posterId, String rewardContent, String rewardTitle, double rewardMoney,
			String rewardTime, String rewardDeadline, String rewardState, String rewardImage) {
		super();
		this.rewardId = rewardId;
		this.posterId = posterId;
		this.rewardContent = rewardContent;
		this.rewardTitle = rewardTitle;
		this.rewardMoney = rewardMoney;
		this.rewardTime = rewardTime;
		this.rewardDeadline = rewardDeadline;
		this.rewardState = rewardState;
		this.rewardImage = rewardImage;
	}


	public RewardBean(int posterId, String rewardContent, String rewardTitle, double rewardMoney, String rewardTime,
			String rewardDeadline, String rewardState, String rewardImage) {
		super();
		this.posterId = posterId;
		this.rewardContent = rewardContent;
		this.rewardTitle = rewardTitle;
		this.rewardMoney = rewardMoney;
		this.rewardTime = rewardTime;
		this.rewardDeadline = rewardDeadline;
		this.rewardState = rewardState;
		this.rewardImage = rewardImage;
	}


	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}
	public int getPosterId() {
		return posterId;
	}
	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}
	public String getRewardContent() {
		return rewardContent;
	}
	public void setRewardContent(String rewardContent) {
		this.rewardContent = rewardContent;
	}
	public String getRewardTitle() {
		return rewardTitle;
	}
	public void setRewardTitle(String rewardTitle) {
		this.rewardTitle = rewardTitle;
	}
	public double getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public String getRewardTime() {
		return rewardTime;
	}
	public void setRewardTime(String rewardTime) {
		this.rewardTime = rewardTime;
	}
	public String getRewardDeadline() {
		return rewardDeadline;
	}
	public void setRewardDeadline(String rewardDeadline) {
		this.rewardDeadline = rewardDeadline;
	}
	public String getRewardState() {
		return rewardState;
	}
	public void setRewardState(String rewardState) {
		this.rewardState = rewardState;
	}
	public String getRewardImage() {
		return rewardImage;
	}
	public void setRewardImage(String rewardImage) {
		this.rewardImage = rewardImage;
	}
	
	
}
