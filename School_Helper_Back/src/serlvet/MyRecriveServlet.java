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
 * 功能：MyRecriveServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.26
 * */
/**
 * Servlet implementation class MyRecriveServlet
 */
@WebServlet("/MyRecriveServlet")
public class MyRecriveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyRecriveServlet() {
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
		int receiverId=Integer.parseInt(request.getParameter("userId"));
		ConnectionDao condao=new ConnectionDao();
		JSONArray array=new JSONArray();
		List<ConnectionBean> connectionList =condao.selectConnection(receiverId);
		for(ConnectionBean con:connectionList) {
			RewardDao redao=new RewardDao();
			List<RewardBean> rewardList = redao.MyPublish(con.getPosterId());
			for(RewardBean reward:rewardList) {
				JSONObject json13 = new JSONObject();
				json13.put("userId", reward.getPosterId());
				json13.put("rewardId", reward.getRewardId());
				json13.put("name", getName(con));
				json13.put("sex", getSex(con));
				json13.put("title", reward.getRewardTitle());
				json13.put("content", reward.getRewardContent());
				json13.put("rewardTime", reward.getRewardTime());
				json13.put("endTime", reward.getRewardDeadline());
				json13.put("money", reward.getRewardMoney());
				json13.put("state", reward.getRewardState());
				array.put(json13);
				System.err.println(json13);
//				rewardList=null;
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
