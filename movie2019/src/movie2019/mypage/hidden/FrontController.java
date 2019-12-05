package movie2019.mypage.hidden;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.mh")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

	//doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵�
    //POST������� ���۵Ǿ� ���� ���� �޼��忡�� ��û�� ó���� �� �ֵ��� �Ͽ����ϴ�.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI="+RequestURI);
		
		String contextPath=request.getContextPath();
		System.out.println("contexPath="+contextPath);

		String command=RequestURI.substring(contextPath.length());
		System.out.println("command="+command);
		
		//�ʱ�ȭ
		ActionForward forward=null;
		Action action=null;

		if(command.equals("/hidden_list.mh")) {
			action=new HListAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/hidden_delete.mh")) {
			action=new HDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(forward!=null) {
			if(forward.isRedirect()) {//�����̷�Ʈ �˴ϴ�.
				response.sendRedirect(forward.getPath());
			}else {//�������˴ϴ�.
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}
