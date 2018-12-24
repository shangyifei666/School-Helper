package serlvet;
/*
 * 功能：UpdateServlet
 * 开发人：杨旭辉
 * 开发时间：2018.12.24
 * 
 * */
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

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		int id=Integer.parseInt(request.getParameter("id"));
		System.err.println(id);
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String signature=request.getParameter("write");
//		String password=request.getParameter("password");
		String realname=request.getParameter("realname");
		String sex=request.getParameter("sex");
		String identification=request.getParameter("identification");
		JSONObject json7 = new JSONObject();
		JSONObject json9 = new JSONObject();
		JSONArray array = new JSONArray();
		UserBean user=new UserBean();
		UserDao userdao=new UserDao();
		int flag=0;
		List<UserBean> userList = userdao.getAllUser(); 
		for(UserBean thisUser:userList) {
			if(thisUser.getUserPhone().equals(phone)) {
				if(thisUser.getUserId()==id) {
					user=thisUser;
					flag=1;
				}else {
					json9.put("success", "success");
					json9.put("error", "该手机号已被注册");
					json9.put("userId", "id");
					json9.put("phone", "phone");
					json9.put("userName", "name");
					json9.put("identification", "identification");
					json9.put("signature", "signature");
					json9.put("realname", "real");
					json9.put("sex", "sex");
					array.put(json9);
					flag=0;
				}
			}else {
				if(thisUser.getUserId()==id) {
					user=thisUser;
					flag=1;
				}
			}
		}
		if(flag==1) {
		user.setUserName(name);
		user.setUserPhone(phone);
		user.setUserSignature(signature);
		user.setUserRealname(realname);
		user.setUserIdentification(identification);
		user.setUserSex(sex);
		try {
			userdao.reviseUser(user);
			json7.put("success", "修改成功");
			json7.put("error", "error");
			json7.put("phone", user.getUserPhone());
			json7.put("userId", user.getUserId());
			json7.put("userName", user.getUserName());
			json7.put("identification", user.getUserIdentification());
			json7.put("signature", user.getUserSignature());
			json7.put("realname", user.getUserRealname());
			json7.put("sex", user.getUserSex());
			array.put(json7);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
//		user.setUserPassword(password);
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
