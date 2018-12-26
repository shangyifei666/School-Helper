package serlvet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.ConnectionBean;
import bean.RewardBean;
import bean.UserBean;
import dao.ConnectionDao;
import dao.RewardDao;
import dao.UserDao;
/*
 * 功能：TaskServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.26
 * */
/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int userId=Integer.parseInt(request.getParameter("userId"));
		JSONArray array=new JSONArray();
		RewardDao rewardDao = new RewardDao();
		List<RewardBean> rewardList =rewardDao.MyPublish(userId);
		for(RewardBean reward:rewardList) {
			if(reward.getRewardState().equals("3")) {
			JSONObject json15=new JSONObject();
			json15.put("userId", reward.getPosterId());
			json15.put("rewardId", reward.getRewardId());
			json15.put("name", getName(reward));
			json15.put("sex", getSex(reward));
			json15.put("title", reward.getRewardTitle());
			json15.put("content", reward.getRewardContent());
			json15.put("rewardTime", reward.getRewardTime());
			json15.put("endTime", reward.getRewardDeadline());
			json15.put("money", reward.getRewardMoney());
			json15.put("state", reward.getRewardState());
			array.put(json15);
			System.err.println(json15);
			}
		}
		ConnectionDao condao=new ConnectionDao();
		List<ConnectionBean> connectionList =condao.selectConnection(userId);
		for(ConnectionBean con:connectionList) {
			RewardDao redao=new RewardDao();
			List<RewardBean> rewardListone = redao.MyPublish(con.getPosterId());
			for(RewardBean reward:rewardListone) {
				if(reward.getRewardState().equals("2")) {
				JSONObject json16 = new JSONObject();
				json16.put("userId", reward.getPosterId());
				json16.put("rewardId", reward.getRewardId());
				json16.put("name", getName(con));
				json16.put("sex", getSex(con));
				json16.put("title", reward.getRewardTitle());
				json16.put("content", reward.getRewardContent());
				json16.put("rewardTime", reward.getRewardTime());
				json16.put("endTime", reward.getRewardDeadline());
				json16.put("money", reward.getRewardMoney());
				json16.put("state", reward.getRewardState());
				array.put(json16);
				System.err.println(json16);
//				rewardList=null;
				}
			}
		}
		response.getWriter().append(array.toString()).append(request.getContentType());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private String getName(RewardBean reward) {
		UserDao userDao = new UserDao();
		UserBean user = userDao.checkUser(reward.getPosterId());
		String name = user.getUserName();
		
		return name;
		
	}
	private String getSex(RewardBean reward) {
		UserDao userDao = new UserDao();
		UserBean user = userDao.checkUser(reward.getPosterId());
		String sex = user.getUserSex();
		return sex;
	}
	private String getName(ConnectionBean con) {
		UserDao userdao=new UserDao();
		UserBean user=userdao.checkUser(con.getPosterId());
		String name=user.getUserName();
		return name;
	}
	private String getSex(ConnectionBean con) {
		UserDao userDao = new UserDao();
		UserBean user = userDao.checkUser(con.getPosterId());
		String sex = user.getUserSex();
		return sex;
	}
}
