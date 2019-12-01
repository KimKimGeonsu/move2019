package movie2019.admin.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.fa")
public class FAQFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FAQFrontController() {
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
		 ��) http://localhost:8088/JspProject/login.net�� ���
		 "/JspProject/login.net"��ȯ�˴ϴ�. 
		 */
		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI="+RequestURI);
		
		//getContextPath():���ؽ�Ʈ ��ΰ� ��ȯ�˴ϴ�.
		//contextPath�� "/JspProject"�� ��ȯ�˴ϴ�.
		String contextPath=request.getContextPath();
		System.out.println("contexPath="+contextPath);
		
		//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں��� ������ ��ġ ���ڱ��� �����մϴ�.
		//command�� "/login.net" ��ȯ�˴ϴ�.
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command="+command);
		
		//�ʱ�ȭ
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/FAQList.fa")) {
			action=new FAQListAction();	//�������� ���� ��ĳ����
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FAQWrite.fa")) {
			forward=new ActionForward();
			forward.setRedirect(false);	//������ ������� �ּҰ� �ٲ��� �ʾƿ�
			forward.setPath("Page/AdminPage/FAQ/FAQWrite.jsp");
		}else if(command.equals("/FAQAddAction.fa")) {
			action = new FAQAddAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FAQModifyView.fa")) {
			action = new FAQModifyView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}else if(command.equals("/FAQModifyAction.fa")) {
			action = new FAQModifyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDetailAction.fa")) {
			action = new FAQDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/FAQDetailAction.fa")) {
			action = new FAQDetailAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/FAQDeleteAction.fa")) {
			action = new FAQDeleteAction();
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
