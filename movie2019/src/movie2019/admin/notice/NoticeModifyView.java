package movie2019.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class NoticeModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		BoardDAO boarddao=new BoardDAO();
		BoardBean boarddata = new BoardBean();

		//�Ķ���ͷ� ���޹��� �亯�� �� ��ȣ�� num ������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����մϴ�.
		boarddata=boarddao.getDetail(num);
		
		//�� ������ ���� ���
		if(boarddata==null) {
			System.out.println("���� ������ �̵� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "���� ������ �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("���� ������ �̵� �Ϸ�");
		//���� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		//boarddata��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		//���� ������ ��� �����մϴ�.
		forward.setPath("board/qna_board_modify.jsp");
		
		return forward;
	}

}
