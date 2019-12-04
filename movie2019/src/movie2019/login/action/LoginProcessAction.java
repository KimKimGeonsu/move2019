package movie2019.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie2019.login.db.MemberDAO;

public class LoginProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		System.out.println("check");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		MemberDAO mdao = new MemberDAO();

		int result = mdao.isId(id, password);

		System.out.println("����� = " + result);

		if (result == 1) {

			HttpSession session = request.getSession();
			// �α��� ����
			session.setAttribute("id", id);
			forward.setRedirect(true);
			forward.setPath("BoardList.lg");
//			forward.setPath("BoardWrite.bo");
			return forward;

		} else {
			// ��й�ȣ ��ġ X
			String message = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			if (result == -1)
				message = "���̵� �������� �ʽ��ϴ�.";

			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;

		}

	}
}
