package movie2019.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.login.action.ActionForward;
import movie2019.review.db.ReviewDAO;
import movie2019.review.db.ReviewVO;


public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		ReviewDAO reviewdao = new ReviewDAO();
		ReviewVO reviewvo = new ReviewVO();
		
		//�۹�ȣ �Ķ���Ͱ��� num������ ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		//������ Ȯ���� ���� ��ȸ���� ������Ų��.
		boarddao.setReadCountUpdate(num);
		
		//���� ������ DAO���� ���� �� ���� ����� boarddata ��ü�� ����
		boarddata = boarddao.getDetail(num);
		
		ActionForward forward = new ActionForward();
		
		//DAO���� ���� ������ ���� ������ ��� null ��ȯ
		if (boarddata == null) {
			System.out.println("�󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� �󼼺��� ���д�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("�󼼺��� ����");
		
		//boarddata��ü�� Request��ü�� ����
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		
		//�� ���� ���� �������� �̵��ϱ� ���� ��θ� �����մϴ�.
		forward.setPath("board/qna_board_view.jsp");
		return forward;
	}

}
