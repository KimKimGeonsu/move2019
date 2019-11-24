package movie2019.movielist.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieListController
 */
@WebServlet("*.ml")
public class MovieListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		// getContextPath() : ���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		// contextPath�� "/JspProject" �� ��ȯ�մϴ�.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		// RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
		// ������ ��ġ ���ڱ��� �����մϴ�.
		// command�� 'login.net'�� ��ȯ�մϴ�.

		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		// �ʱ�ȭ
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/main.ml")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("Page/Main/Main.jsp");
		} else if (command.equals("/MainPage.ml")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("Page/MainPage/MainPage.jsp");
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				System.out.println("sendRedirect �̵� :" + forward.getPath());
				response.sendRedirect(forward.getPath());
			} else { // �������˴ϴ�.
				System.out.println("dispather �̵� :" + forward.getPath());
				RequestDispatcher dispather = request.getRequestDispatcher(forward.getPath());
				dispather.forward(request, response);

			}
		}

	}

}
