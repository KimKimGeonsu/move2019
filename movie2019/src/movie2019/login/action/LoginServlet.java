package movie2019.login.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Page/Login/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8"); // �߰��ؾ� �ѱ� ��� ����

		PrintWriter out = response.getWriter();

		// �Ķ���� id, passwd �� ��������.
		String inputid = request.getParameter("id");
		String inputpw = request.getParameter("password");

		String id = getServletContext().getInitParameter("id");
		String pw = getServletContext().getInitParameter("password");  
													//�̸��� xml�̶� �Ȱ��� ����Ѵ�.

		if (id.equals(inputid) && pw.equals(inputpw)) {

			HttpSession session = request.getSession(); // ���� ���� //�߿�***
			session.setAttribute("id", id); // ���� ��ü�� id��� �Ӽ����� id�� ����

			out.println("<script>");
			out.println("alert('" + id + "�� ����~')");
			out.println("location.href='index.jsp'");
			out.println("</script>");
		} else if (id.equals(inputid)) {
			out.println("<script>");
			out.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.')");
			out.println("history.back()"); // �ٷ� �� �������� �̵�
			out.println("</script>");
			out.close();
		} else {
			out.println("<script>");
			out.println("alert('���̵� ��ġ���� �ʽ��ϴ�.')");
			out.println("history.back()"); // �ٷ� �� �������� �̵�
			out.println("</script>");
			out.close();
		}
	}
}
