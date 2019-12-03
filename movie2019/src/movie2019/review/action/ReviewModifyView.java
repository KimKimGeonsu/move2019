package movie2019.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.review.db.ReviewDAO;
import movie2019.review.db.ReviewVO;

public class ReviewModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		ActionForward forward = new ActionForward();

		ReviewDAO reviewdao = new ReviewDAO();
		ReviewVO reviewvo = new ReviewVO();

		// �Ķ���ͷ� ���޹��� ������ �� ��ȣ�� num������ ����
		int num = Integer.parseInt(request.getParameter("num"));
		// �� ���� �ҷ��ͼ� boarddata��ü�� ����
		reviewvo = reviewdao.getDetail(num);

		// �� ������ ���� ���
		if (reviewvo == null) {
			System.out.println("���� �� ���� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "���� �󼼺��� ����");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("���� �󼼺��� ����");

		// ���� �� �������� �̵��� �� ���� �� ������ �����ֱ� ����
		// boarddata��ü�� Request��ü�� ����.
		request.setAttribute("reviewvo", reviewvo);
		forward.setRedirect(false);
		// �� ���� ������ ��� ����
		forward.setPath("");
		return forward;
	}

}
