package movie2019.admin.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NoticeModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//�Ķ���ͷ� ������ �� �ѱ��� ������ �ʵ��� �ϱ� ���� �����Դϴ�.
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		Boolean result = false;
System.out.println("TTTTT");
		//���޹��� �Ķ���� num ������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("NOTICE_NUM"));
		
		NoticeDAO noticedao=new NoticeDAO();
		NoticeVO notice = new NoticeVO();

		//��й�ȣ�� ��ġ�ϴ� ��� ���� ������ �����մϴ�.
		notice.setNOTICE_NUM(num);
		notice.setNOTICE_SUBJECT(request.getParameter("NOTICE_SUBJECT"));
		notice.setNOTICE_CONTENT(request.getParameter("NOTICE_CONTENT"));
		
		//DAO���� ���� �޼��� ȣ���Ͽ� �����մϴ�.
		result = noticedao.NoticeModify(notice);
		//������ ������ ���
		if(result==false) {
			System.out.println("�Խ��� ���� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� ���� �ۼ� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//���� ������ ���
		System.out.println("�Խ��� ���� �Ϸ�");
		
		forward.setRedirect(true);
		//������ �� ������ Ȯ���ϱ� ���� �� ���� ���� �������� ��η� �����մϴ�.
		forward.setPath("BoardDetailAction.bo?num="+notice.getNOTICE_NUM());
		
		return forward;
	}
}
