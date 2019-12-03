package movie2019.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.review.db.ReviewDAO;
import movie2019.review.db.ReviewVO;

public class ReviewModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		ActionForward forward = new ActionForward();

		Boolean result = false;

		ReviewDAO reviewdao = new ReviewDAO();
		ReviewVO reviewvo = new ReviewVO();



		try {
			
			int num = Integer.parseInt(request.getParameter("BOARD_NUM"));
			String id = request.getParameter("USER_ID");

			// �۾��̰� �´��� Ȯ���ϱ� ���� ����� ��� & �Է� ��� ��
			boolean usercheck = reviewdao.isReviewWriter(num, id);

			// �ٸ� ���
			if (usercheck == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('������ �ۼ��� ���丸 ����/���� �����մϴ�.')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}

			// ��ġ�ϴ� ��� ���� ���� �����Ѵ�.
			// BoardBean��ü�� �� ��� ������ �Է¹��� �������� ����.
			reviewvo.setUSER_ID(id);
			reviewvo.setREVIEW_TITLE(request.getParameter("REVIEW_TITLE"));
			reviewvo.setREVIEW_CONTENT(request.getParameter("REVIEW_CONTENT"));

			
			// DAO���� ���� �޼ҵ� ȣ���� �����ϱ�
			result = reviewdao.reviewModify(reviewvo);

			// ���� ������ ���
			if (result == false) {
				System.out.println("���� ���� ����");
				forward.setRedirect(false);
				request.setAttribute("message", "���� ���� ����");
				forward.setPath("error/error.jsp");
				return forward;
			}
			// �Խñ� ������ ����� �� ���
			System.out.println("���� ���� �Ϸ�!");

			forward.setRedirect(true);
			// ������ �� ���� Ȯ���� ���� �� ���뺸�� �������� ��η� ����
			forward.setPath("ReviewDetailAction.rv?num=" + reviewvo.getREVIEW_NUMBER());

			return forward;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
