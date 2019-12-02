package movie2019.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		NoticeDAO noticedao = new NoticeDAO();
		NoticeVO noticevo = new NoticeVO();

		//�۹�ȣ �Ķ���� ���� num������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//���� ������ DAO���� ���� �� ���� ����� boarddata ��ü�� �����մϴ�.
		noticevo=noticedao.getDetail(num);
		
		ActionForward forward = new ActionForward();
				
		//DAO���� ���� ������ ���� ������ ��� null�� ��ȯ�մϴ�.
		if(noticevo==null) {
			System.out.println("�󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� �󼼺��� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return null;
		}
		System.out.println("�󼼺��� ����");
		
		//boarddata ��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("noticedata",noticevo);
		forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("Page/AdminPage/Notice/NoticeView.jsp");
		return forward;
	}

}
