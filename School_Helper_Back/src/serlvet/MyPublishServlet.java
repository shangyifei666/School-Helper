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

import bean.RewardBean;
import bean.UserBean;
import dao.RewardDao;
import dao.UserDao;
/*
 * 功能：MyPublishServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.26
 * */
/**
 * Servlet implementation class MyPublishServlet
 */
@WebServlet("/MyPublishServlet")
public class MyPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPublishServlet() {
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
			JSONObject json12=new JSONObject();
			json12.put("userId", reward.getPosterId());
			json12.put("rewardId", reward.getRewardId());
			json12.put("name", getName(reward));
			json12.put("sex", getSex(reward));
			json12.put("title", reward.getRewardTitle());
			json12.put("content", reward.getRewardContent());
			json12.put("rewardTime", reward.getRewardTime());
			json12.put("endTime", reward.getRewardDeadline());
			json12.put("money", reward.getRewardMoney());
			json12.put("state", reward.getRewardState());
			array.put(json12);
			System.err.println(json12);
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
}
