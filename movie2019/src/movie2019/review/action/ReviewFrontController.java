package movie2019.review.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet {
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

		if (command.equals("/ReviewList.rv")) {
			action = new ReviewListAction(); // �������� ���� ��ĳ����
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/ReviewWrite.rv")) {
			forward = new ActionForward();
			forward.setRedirect(false); // ������ ������� �ּҰ� �ٲ��� ����.
			forward.setPath("review/review_write.jsp");

		} else if (command.equals("/ReviewAddAction.rv")) {
			action = new ReviewAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/ReviewDelete.rv")) {
			forward = new ActionForward();
			forward.setRedirect(false); // ������ ������� �ּ� �ٲ��� ����
			forward.setPath("review/review_delete.jsp");

		} else if (command.equals("/ReviewDeleteAction.rv")) {
			action = new ReviewDeleteAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/ReviewModifyAction.rv")) {
			action = new ReviewModifyAction();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/ReviewModifyView.rv")) {
			action = new ReviewModifyView();
			try {
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/ReviewDetailAction.rv")) {
			action = new ReviewDetailAction();
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
