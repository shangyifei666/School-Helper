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

/**
 * Servlet implementation class UpdateServletone
 */
@WebServlet("/UpdateServletone")
public class UpdateServletone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServletone() {
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
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		UserBean user=new UserBean();
		UserDao userdao=new UserDao();
		JSONObject json8 = new JSONObject();
		JSONArray array = new JSONArray();
		List<UserBean> userList = userdao.getAllUser(); 
		for(UserBean thisUser:userList) {
			if(thisUser.getUserPhone().equals(phone)) {
				user=thisUser;
			}
		}
		user.setUserPassword(password);
		try {
			userdao.reviseUser(user);
			json8.put("success", "修改成功");
			json8.put("error", "error");
			json8.put("password", user.getUserPassword());
			array.put(json8);
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
