package serlvet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.ConnectionBean;
import bean.RewardBean;
import bean.UserBean;
import dao.ConnectionDao;
import dao.RewardDao;
import dao.UserDao;
/*
 * 功能：DeleteRewardServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.28
 * */
/**
 * Servlet implementation class DeleteRewardServlet
 */
@WebServlet("/DeleteRewardServlet")
public class DeleteRewardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRewardServlet() {
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
		int rewardId=Integer.parseInt(request.getParameter("rewardId"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		int posterId=Integer.parseInt(request.getParameter("posterId"));
		int receiverId=Integer.parseInt(request.getParameter("receiverId"));
		UserDao userdao=new UserDao();
		UserBean user=new UserBean();
		user=userdao.checkUser(userId);
		ConnectionBean con=new ConnectionBean();
		ConnectionDao condao=new ConnectionDao();
		RewardBean reward=new RewardBean();
		JSONObject json19 = new JSONObject();
		RewardDao redao=new RewardDao();
		if(userId==posterId) {
			List<RewardBean> rewardList=redao.MyPublishone(rewardId);
			for(RewardBean rewardone:rewardList) {
				if(rewardone.getRewardState().equals("2")) {
					condao.delete(rewardone.getRewardId());
					redao.delete(rewardId);
					try {
						Double money1=user.getUserMoney();
						Double money2=rewardone.getRewardMoney();
						Double money=money1+money2;
						user.setUserMoney(money);
						userdao.reviseUser(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(rewardone.getRewardState().equals("1")) {
					redao.delete(rewardId);
					try {
						Double money1=user.getUserMoney();
						Double money2=rewardone.getRewardMoney();
						Double money=money1+money2;
						user.setUserMoney(money);
						userdao.reviseUser(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else if(userId==receiverId) {
			List<RewardBean> rewardList=redao.MyPublishone(rewardId);
			for(RewardBean rewardtwo:rewardList) {
//				System.err.println(rewardtwo.getRewardId());
				condao.delete(rewardtwo.getRewardId());
				rewardtwo.setRewardState("1");
				try {
					redao.reviseState(rewardtwo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		json19.put("money", user.getUserMoney());
		System.err.println(user.getUserMoney());
		json19.put("response", "success");
		response.getWriter().append(json19.toString()).append(request.getContentType());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
