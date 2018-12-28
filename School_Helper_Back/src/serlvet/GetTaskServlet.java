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
import dao.ConnectionDao;
import dao.RewardDao;
/*
 * 功能：GetTaskServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.28
 * */
/**
 * Servlet implementation class GetTaskServlet
 */
@WebServlet("/GetTaskServlet")
public class GetTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTaskServlet() {
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
		int posterId=Integer.parseInt(request.getParameter("posterId"));
		int receiverId=Integer.parseInt(request.getParameter("receiverId"));
		int rewardId=Integer.parseInt(request.getParameter("rewardId"));
		ConnectionBean con=new ConnectionBean();
		ConnectionDao condao=new ConnectionDao();
		con.setPosterId(posterId);
		con.setRecriverId(receiverId);
		con.setRewardId(rewardId);
		condao.setConnection(con);
		JSONObject json17 = new JSONObject();
		String state="2";
		RewardBean reward=new RewardBean();
		RewardDao redao=new RewardDao();
		List<RewardBean> rewardList=redao.getAllReward();
		for(RewardBean thisReward:rewardList) {
			if(thisReward.getRewardId()==rewardId) {
				reward=thisReward;
			}
		}
		reward.setRewardState(state);
		try {
			redao.reviseState(reward);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json17.put("response", "success");
		response.getWriter().append(json17.toString()).append(request.getContentType());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
