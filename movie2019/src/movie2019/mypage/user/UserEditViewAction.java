package movie2019.mypage.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.admin.user.UserDAO;
import movie2019.admin.user.Users;

public class UserEditViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		UserDAO udao=new UserDAO();
		Users users = new Users();
			String id = request.getParameter("user_id");
			
			//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
			users=udao.user_info(id);
			
			//�� ������ ���� ���
			if(users==null) {
				System.out.println("���� ������ �̵� ����");
				forward.setRedirect(false);
				request.setAttribute("message", "���� ������ �����Դϴ�.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			System.out.println("���� ������ �̵� �Ϸ�");
			//���� �������� �̵��� �� ���� �� ������ �����ֱ� ����
			//boarddata��ü�� Request ��ü�� �����մϴ�.
			request.setAttribute("userinfo", users);
			forward.setRedirect(false);
			//���� ������ ��� �����մϴ�.
			forward.setPath("Page/MyPage/MyInfo/MyInfoModify.jsp");
			
			return forward;
	}

}
