package movie2019.mypage.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie2019.admin.user.UserDAO;
import movie2019.admin.user.Users;

public class USERInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDAO userdao = new UserDAO();
		Users user = new Users();
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
	
		user=userdao.user_info(id);
		ActionForward forward = new ActionForward();
		
		System.out.println("�󼼺��� ����");
		
		request.setAttribute("userinfo",user);
		forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Page/MyPage/MyInfo/MyInfo.jsp");
		return forward;
	}


}
