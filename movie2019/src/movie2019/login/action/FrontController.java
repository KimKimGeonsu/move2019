package movie2019.login.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.net")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// doProcess(request, response) �޼ҵ带 ������
	// ���� ��û�� get/post ��� ���� �޼ҵ忡�� ó���� �� �ְ� �ߴ�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	// doProcess
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * ��û�� ��ü url �߿��� ��Ʈ ��ȣ �������� ������ ���ڿ����� ��ȯ.
		 * 
		 * ex) http://localhost:8088/JspProject/login.net �� ��� "/JspProject/login.net"
		 * �κи� ��ȯ.
		 */

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		// getContextPath() : ���ؽ�Ʈ ��� ��ȯ
		// ContextPath�� "JspProject" ��ȯ.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		// RequestURI���� ���ؽ�Ʈ ��� ���̰��� �ε��� ���ں��� ������ ���ڱ��� ����.
		// command�� "/login.net" ��ȯ.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		// �ʱ�ȭ
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/login.net")) {
			forward = new ActionForward();
			forward.setRedirect(false); // �ּ� ���� ���� jsp�������� ������ �����ش�.
			forward.setPath("login/login.jsp");


		} else if (command.equals("/idcheck.net")) {
			action = new IdCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}


		} else if (command.equals("/loginProcess.net")) {
			action = new LoginProcessAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}



		} else if (command.equals("/logout.net")) {
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		}
		
		

		if (forward != null) {
			if (forward.isRedirect()) { // �����̷�Ʈ ��
				response.sendRedirect(forward.getPath());
			} else { // ������ ��
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
