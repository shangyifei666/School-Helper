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
 * 功能：登陆验证信息
 * 开发人：尚一飞
 * 开发时间：2018.11.30
 * 
 * 描述：如果有错误返回error，如果没有错误返回userId
 * */

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		JSONObject json3 = new JSONObject();
		JSONObject json4 = new JSONObject();
		JSONObject json5 = new JSONObject();
		JSONArray array = new JSONArray();
		UserDao userDao = new UserDao();
		List<UserBean> userList = userDao.getAllUser();
		UserBean user = new UserBean();
		for(UserBean thisUser:userList) {
			if(thisUser.getUserPhone().equals(phone)) {
				user=thisUser;
			}
		}
		if(user.getUserPhone()==null) {
			json3.put("error", "用户不存在");
			json3.put("success", "success");
			json3.put("userId", "Id");
			json3.put("userName", "name");
			json3.put("password", "pass");
			json3.put("schoolId", "school");
			json3.put("stuNum", "num");
			json3.put("phone", "phone");
			json3.put("image", "image");
			json3.put("money", "money");
			json3.put("value", "value");
			json3.put("took", "took");
			json3.put("publish", "publish");
			json3.put("identification", "identification");
			json3.put("signature", "signature");
			json3.put("realname", "realname");
			json3.put("sex", "sex");
			array.put(json3);
		}else {
			if(!user.getUserPassword().equals(password)) {
				json4.put("success", "success");
				json4.put("error", "密码错误");
				json4.put("userId", "Id");
				json4.put("userName", "name");
				json4.put("password", "pass");
				json4.put("schoolId", "school");
				json4.put("stuNum", "num");
				json4.put("phone", "phone");
				json4.put("image", "image");
				json4.put("money", "money");
				json4.put("value", "value");
				json4.put("took", "took");
				json4.put("publish", "publish");
				json4.put("identification", "identification");
				json4.put("signature", "signature");
				json4.put("realname", "realname");
				json4.put("sex", "sex");
				array.put(json4);
			}else {
				json5.put("success", "登录成功");
				json5.put("error", "error");
				json5.put("userId", user.getUserId());
				json5.put("userName", user.getUserName());
				json5.put("password", user.getUserPassword());
				json5.put("schoolId", user.getSchoolId());
				json5.put("stuNum", user.getUserStudentNum());
				json5.put("phone", user.getUserPhone());
				json5.put("image", user.getImage());
				json5.put("money", user.getUserMoney());
				json5.put("value", user.getUserReputationValue());
				json5.put("took", user.getUserTookCount());
				json5.put("publish", user.getUserPublishCount());
				json5.put("identification", user.getUserIdentification());
				json5.put("signature", user.getUserSignature());
				json5.put("realname", user.getUserRealname());
				json5.put("sex", user.getUserSex());
				array.put(json5);
			}
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
