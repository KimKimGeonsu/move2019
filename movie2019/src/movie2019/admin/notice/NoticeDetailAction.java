package movie2019.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class NoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();

		//�۹�ȣ �Ķ���� ���� num������ �����մϴ�.
		int num=Integer.parseInt(request.getParameter("num"));
		
		//������ Ȯ���� ���� ��ȸ���� ������ŵ�ϴ�.
		boarddao.setReadCountUpdate(num);
		
		//���� ������ DAO���� ���� �� ���� ����� boarddata ��ü�� �����մϴ�.
		boarddata=boarddao.getDetail(num);
		
		ActionForward forward = new ActionForward();
		
		//boarddata=null;	//�׽�Ʈ �� �� �ȵǳ�..
		
		//DAO���� ���� ������ ���� ������ ��� null�� ��ȯ�մϴ�.
		if(boarddata==null) {
			System.out.println("�󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� �󼼺��� �����Դϴ�.");
			forward.setPath("error/error.jsp");
			return null;
		}
		System.out.println("�󼼺��� ����");
		
		//boarddata ��ü�� Request ��ü�� �����մϴ�.
		request.setAttribute("boarddata",boarddata);
		forward = new ActionForward();
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("board/qna_board_view.jsp");
		return forward;
	}

}
