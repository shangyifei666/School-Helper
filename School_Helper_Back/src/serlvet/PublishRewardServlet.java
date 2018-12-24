package serlvet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bean.RewardBean;
import dao.RewardDao;

/**
 * Servlet implementation class PublishRewardServlet
 */
@WebServlet("/PublishRewardServlet")
public class PublishRewardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishRewardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int posterId = Integer.parseInt(request.getParameter("posterId"));
		String rewardTitle = request.getParameter("rewardTitle");
		String rewardContent = request.getParameter("rewardContent");
		String rewardImage = request.getParameter("imgUrl");
		String rewardTime = request.getParameter("publishTime");
		String rewardDeadline = request.getParameter("deadline");
		double rewardMoney = Double.parseDouble(request.getParameter("rewardMoney"));
		//此为创建赏金，状态默认为“1”
		String rewardState = "1";
		
		RewardBean reward = new RewardBean(posterId,rewardContent,rewardTitle,rewardMoney,rewardTime,rewardDeadline,rewardState,rewardImage);
		RewardDao rewardDao = new RewardDao();
		rewardDao.setReward(reward);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("response", "success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
