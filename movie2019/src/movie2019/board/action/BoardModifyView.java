package movie2019.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.board.action.Action;
import movie2019.board.action.ActionForward;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		BoardVO boarddata = new BoardVO();
		
		//�Ķ���ͷ� ���޹��� ������ �� ��ȣ�� num������ �����Ѵ�.
		int num = Integer.parseInt(request.getParameter("num"));
		//�� ������ �ҷ��ͼ� boarddata��ü�� �����Ѵ�.
		boarddata = boarddao.getDetail(num);
		
		//�� ���� �ҷ����� ������ ����̴�.
		if(boarddata==null) {
			System.out.println("(����)�󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� �󼼺��� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("(����)�󼼺��� ����");
		//���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ������ boarddata��ü��
		//request��ü�� �����Ѵ�.
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		//�� ���� �� �������� �̵��ϱ� ���� ��θ� �����Ѵ�.
		forward.setPath("Page/Board/board/board_modify.jsp");
		return forward;

	}
}
