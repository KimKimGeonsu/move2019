package movie2019.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.board.action.Action;
import movie2019.board.action.ActionForward;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
		HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		BoardDAO boarddao = new BoardDAO();
		BoardVO boarddata = new BoardVO();
		
		//�۹�ȣ �Ķ���� ���� num������ �����Ѵ�.
		int num = Integer.parseInt(request.getParameter("num"));
		
		//������ Ȯ���� ���� ��ȸ���� ������Ų��.
		boarddao.setReadCountUpdate(num);
		
		//���� ������ DAO���� ���� �� ���� ����� boarddata ��ü�� �����Ѵ�.
		boarddata = boarddao.getDetail(num);
		
		//boarddata = null;
		//DAO���� ���� ������ ���� ������ ��� null�� ��ȯ�Ѵ�.
		if(boarddata==null) {
			System.out.println("�󼼺��� ����");
		 //error/error.jsp�̵��ϱ�
			forward.setRedirect(false);
			request.setAttribute("message","�Խ��� �󼼺��� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("�󼼺��� ����");
		
		//boarddata ��ü�� Request��ü�� �����Ѵ�.
		request.setAttribute("boarddata", boarddata);
        forward.setRedirect(false);
        
        //�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����Ѵ�.
        forward.setPath("/Page/Board/board/board_view.jsp");
        return forward;
	}


}
