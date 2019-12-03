package movie2019.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import movie2019.review.db.ReviewDAO;
import movie2019.review.db.ReviewVO;

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ReviewDAO reviewdao = new ReviewDAO();
		ReviewVO reviewvo = new ReviewVO();

		// �۹�ȣ �Ķ���Ͱ��� num������ ����
		int num = Integer.parseInt(request.getParameter("num"));

		// ���� ������ DAO���� ���� �� ���� ����� boarddata ��ü�� ����
		reviewvo = reviewdao.getDetail(num);

		ActionForward forward = new ActionForward();

		// DAO���� ���� ������ ���� ������ ��� null ��ȯ
		if (reviewvo == null) {
			System.out.println("�󼼺��� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "�Խ��� �󼼺��� ���д�.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("�󼼺��� ����");

		// boarddata��ü�� Request��ü�� ����
		request.setAttribute("reviewvo", reviewvo);
		forward.setRedirect(false);

		// �� ���� ���� �������� �̵��ϱ� ���� ��θ� ����review�մϴ�.
		forward.setPath("review/review_view.jsp");
		return forward;
	}

}
