package movie2019.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import movie2019.login.db.MemberDAO;

public class LoginProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
	//	ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		System.out.println("check");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("USER_ID");
		String pass = request.getParameter("USER_PASS");

		MemberDAO mdao = new MemberDAO();

		int result = mdao.isId(id, pass);

		System.out.println("��� = " + result);
		System.out.println("���̵� = " +id);
		if (result == 1) { //����
				
			
	//		HttpSession session = request.getSession(); //session
	//		session.setAttribute("id", id);		
	//		forward.setRedirect(true);			
	//		forward.setPath("main.ml");
	//		return forward;
			
			HttpSession session = request.getSession(); //session
			session.setAttribute("id", id);		

			out.println("<script>");
			out.println("alert('" + id + "�� ������ ���� ���� ȯ���մϴ�!')");
			out.println("location.href='main.ml'"); 
			out.println("</script>");

		} else {
			String message = "��й�ȣ�� �ٽ� Ȯ�����ּ���.";  //0
			if (result == -1)
				message = "������ ���Ե��� ���� ���̵��Դϴ�.";  //-1

			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;

		}
		return null;

	}
}
