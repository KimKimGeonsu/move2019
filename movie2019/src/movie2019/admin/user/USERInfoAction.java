package movie2019.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class USERInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDAO userdao = new UserDAO();
		Users user = new Users();

		String id=request.getParameter("id");

		user=userdao.user_info(id);
		
		ActionForward forward = new ActionForward();
		
		if(user==null) {
			System.out.println("������������ ����");
			forward.setRedirect(false);
			request.setAttribute("message", "������������ �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return null;
		}
		System.out.println("�󼼺��� ����");
		
		request.setAttribute("userinfo",user);
		forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Page/AdminPage/UserList/UserInfo.jsp");
		return forward;
	}


}
