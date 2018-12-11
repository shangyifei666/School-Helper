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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		int school=1;
		String stuNumber=request.getParameter("stuNumber");
		String image="images/geren.png";
		Double money=0.00;
		int value=60;
		int took=0;
		int publish=0;
		UserBean user=new UserBean();
		UserDao userdao=new UserDao();
		List<UserBean> userList = userdao.getAllUser(); 
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONArray array = new JSONArray();
		for(UserBean thisUser:userList) {
			if(thisUser.getUserPhone().equals(phone)){
				user=thisUser;
			}
		}
		if(user.getUserPhone()==null) {
			user.setUserName(name);
			user.setUserPassword(password);
			user.setUserPhone(phone);
			user.setUserStudentNum(stuNumber);
			user.setSchoolId(school);
			user.setImage(image);
			user.setUserMoney(money);
			user.setUserReputationValue(value);
			user.setUserTookCount(took);
			user.setUserPublishCount(publish);
			json1.put("success", "注册成功");
			json1.put("error","error");
			array.put(json1);
			userdao.setUser(user);
		}else {
			json2.put("error", "用户已存在");
			json2.put("success", "success");
			array.put(json2);
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
