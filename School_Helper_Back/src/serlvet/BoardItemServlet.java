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

/**
 * Servlet implementation class BoardItemServlet
 */
@WebServlet("/BoardItemServlet")
public class BoardItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/**
		 * 姓名：尚一飞
		 * 日期：2018.12.18
		 * 简介：向Board页传递数据库中的赏金任务
		 */
		RewardDao rewardDao = new RewardDao();
		List<RewardBean> rewardList = rewardDao.selectBoardReward();
		JSONArray array = new JSONArray();
		for(RewardBean reward:rewardList) {
			JSONObject object = new JSONObject();
			String rewardTime = "20"+reward.getRewardTime().substring(0, 2)+"-"+reward.getRewardTime().substring(3,5)+"-"+reward.getRewardTime().substring(6,8);
			String endTime = "20"+reward.getRewardDeadline().substring(0, 2)+"-"+reward.getRewardDeadline().substring(3,5)+"-"+reward.getRewardDeadline().substring(6,8);
			
			object.put("userId", reward.getPosterId());
			object.put("rewardId", reward.getRewardId());
			object.put("name", getName(reward));
			object.put("sex", getSex(reward));
			object.put("title", reward.getRewardTitle());
			object.put("content", reward.getRewardContent());
			object.put("rewardTime", rewardTime);
			object.put("endTime", endTime);
			object.put("money", reward.getRewardMoney());
			array.put(object);
		}
		response.getWriter().append(array.toString()).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * 
	 * @author 尚一飞
	 * 日期：2018.12.18
	 * 简介：根据用户ID获取用户名
	 */
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
