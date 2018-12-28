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
 * 功能：ChangeStateServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.28
 * */
/**
 * Servlet implementation class ChangeStateServlet
 */
@WebServlet("/ChangeStateServlet")
public class ChangeStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStateServlet() {
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
		String state=request.getParameter("state");
		ConnectionBean con=new ConnectionBean();
		ConnectionDao condao=new ConnectionDao();
		RewardDao redao=new RewardDao();
		JSONObject json18 = new JSONObject();
		List<RewardBean> rewardList =redao.MyPublishone(rewardId);
		for(RewardBean thisreward:rewardList) {
			if(thisreward.getRewardState().equals("2")) {
			try {
				thisreward.setRewardState(state);
				redao.reviseState(thisreward);
				
//				array.put(json18);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				if(thisreward.getRewardState().equals("3")) {
					try {
						List<ConnectionBean> connectionList=condao.selectConnectiontwo(rewardId);
						for(ConnectionBean conn:connectionList) {
							int receiverId=conn.getRecriverId();
							UserDao userdao=new UserDao();
							UserBean user=new UserBean();
							user=userdao.checkUser(receiverId);
							Double money1=user.getUserMoney();
							Double money2=thisreward.getRewardMoney();
							Double money=money1+money2;
							user.setUserMoney(money);
							userdao.reviseUser(user);
						}
						thisreward.setRewardState(state);
						redao.reviseState(thisreward);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					JSONObject json19 = new JSONObject();
//					json19 .put("response", "success");
//					array.put(json19);
				}
			}
		}
		json18.put("response", "success");
		response.getWriter().append(json18.toString()).append(request.getContentType());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
