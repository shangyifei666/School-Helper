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

import bean.UserBean;
import dao.UserDao;

/*
 * 功能：GetMoneyServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.24
 * 
 * */
/**
 * Servlet implementation class GetMoneyServlet
 */
@WebServlet("/GetMoneyServlet")
public class GetMoneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMoneyServlet() {
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
		double money=Double.parseDouble(request.getParameter("money"));
		String phone=request.getParameter("phone");
		UserBean user=new UserBean();
		UserDao userdao=new UserDao();
		List<UserBean> userList = userdao.getAllUser(); 
		JSONObject json11 = new JSONObject();
		JSONObject json12 = new JSONObject();
		JSONArray array = new JSONArray();
		for(UserBean thisUser:userList) {
			if(thisUser.getUserPhone().equals(phone)){
				user=thisUser;
			}
		}
		double moneyone=user.getUserMoney()-money;
		user.setUserMoney(moneyone);
		try {
			userdao.reviseUser(user);
			json11.put("success", "提现成功");
			json11.put("error", "error");
			json11.put("money", user.getUserMoney());
			array.put(json11);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
