package movie2019.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.board.action.Action;
import movie2019.board.action.ActionForward;

public class BoardReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		BoardVO boarddata = new BoardVO();
		
		//�Ķ���ͷ� ���޹��� �亯�� �� ��ȣ�� num������ �����Ѵ�.
		int num = Integer.parseInt(request.getParameter("num"));
		
		//�� ��ȣ num�� �ش��ϴ� ������ �����ͼ� boarddata ��ü�� �����Ѵ�.
		boarddata = boarddao.getDetail(num);
		
		//�� ������ ���� ���
		if(boarddata==null) {
			System.out.println("���� ������ �̵� ����");
			forward.setRedirect(false);
			request.setAttribute("message","���� ������ �󼼺��� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("���� ������ �̵� �Ϸ�");
		//�亯 �� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		//boarddata��ü�� Request��ü�� �����Ѵ�.
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		//�� �亯 ������ ��� �����Ѵ�.
		forward.setPath("Page/Board/board/board_reply.jsp");
		return forward;
		
	}
}