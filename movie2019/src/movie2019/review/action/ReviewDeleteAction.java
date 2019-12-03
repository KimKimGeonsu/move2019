package movie2019.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.login.action.ActionForward;
import movie2019.review.db.ReviewDAO;

public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		boolean result = false;
		boolean usercheck = false;

		int num = Integer.parseInt(request.getParameter("num"));

		ReviewDAO reviewdao = new ReviewDAO();

		// ���� �����Ϸ��� ����ڰ� �۾��̰� �´��� �Ǵ��ϱ� ����
		// �Է��� ���̵�� �۾��� ���̵� ���� ��ġ�ϸ� ����.
		usercheck = reviewdao.isReviewWriter(num, request.getParameter("USER_ID"));

		// ��ġ���� �ʴ� ���

		if (usercheck == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�ٸ� ȸ���� ����� ������ �� �����ϴ�.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}

		// ��й�ȣ ��ġ�ϴ� ��� ���� ���ड��
		result = reviewdao.reviewDelete(num);
		ActionForward forward = new ActionForward();

		// ���� ó�� ������ ���
		if (result == false) {
			System.out.println("���� ���� ����");
			forward.setRedirect(false);
			request.setAttribute("message", "���� ���� ����!");
			forward.setPath("error/error.jsp");
			return forward;
		}

		// ���� ������ ��� - �� ��Ϻ��� ��û ����
		System.out.println("���� ���� ����");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('���� �Ϸ�~ �ȳ��');");
		out.println("location.href='ReviewList.rv';");
		out.println("</script>");
		out.close();
		return null;

	}

}
