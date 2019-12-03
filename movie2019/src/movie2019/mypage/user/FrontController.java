package movie2019.mypage.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.mypage.genres.USERGenresAction;

@WebServlet("*.mu")
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
		/*
		 ��û�� ��ü URL�߿��� ��Ʈ ��ȣ ���� ���� ������ ���ڿ����� ��ȯ�˴ϴ�.
		 ��) http://localhost:8088/JspProject/login.ul�� ���
		 "/JspProject/login.ul"��ȯ�˴ϴ�. 
		 */
		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI="+RequestURI);
		
		//getContextPath():���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		//contextPath�� "/JspProject"�� ��ȯ�˴ϴ�.
		String contextPath=request.getContextPath();
		System.out.println("contexPath="+contextPath);
		
		//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں��� ������ ��ġ ���ڱ��� �����մϴ�.
		//command�� "/login.ul" ��ȯ�˴ϴ�.
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command="+command);
		
		//�ʱ�ȭ
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/user_info.mu")) {
			action=new USERInfoAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(command.equals("/user_genres.mu")) {
			action=new USERGenresAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/user_delete.mu")) {
	//		action=new UserDeleteAction();
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
