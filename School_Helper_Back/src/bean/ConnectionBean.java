package bean;
/*
 * 功能：ConnectionBean
 * 开发人：杨旭辉
 * 开发时间：2018.12.26
 * */
public class ConnectionBean {
	private int connectionId;
	private int posterId;
	private int recriverId;
	private int rewardId;
	public ConnectionBean(int connectionId, int posterId, int recriverId, int rewardId) {
		super();
		this.connectionId = connectionId;
		this.posterId = posterId;
		this.recriverId = recriverId;
		this.rewardId = rewardId;
	}
	public ConnectionBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public int getPosterId() {
		return posterId;
	}
	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}
	public int getRecriverId() {
		return recriverId;
	}
	public void setRecriverId(int recriverId) {
		this.recriverId = recriverId;
	}
	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}
	
}
