package movie2019.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class NoticeModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		NoticeDAO noticedao=new NoticeDAO();
		NoticeVO notice = new NoticeVO();

		//�Ķ���ͷ� ���޹��� �亯�� �� ��ȣ�� num ������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
		notice=noticedao.getDetail(num);
		
		//�� ������ ���� ���
		if(notice==null) {
			System.out.println("���� ������ �̵� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "���� ������ �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("���� ������ �̵� �Ϸ�");
		//���� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		//boarddata��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("notice", notice);
		forward.setRedirect(false);
		//���� ������ ��� �����մϴ�.
		forward.setPath("Page/AdminPage/Notice/NoticeModify.jsp");
		
		return forward;
	}

}
