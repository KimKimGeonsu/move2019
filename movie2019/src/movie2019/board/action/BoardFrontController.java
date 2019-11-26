package movie2019.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, 
		       HttpServletResponse response) 
		   throws ServletException, IOException {
		/*
		 * ��û�� ��ü URL�߿��� ��Ʈ ��ȣ �������� ������ ���ڿ����� ��ȯ�ȴ�.
		 * ��)http://localhost:8088/JspProject/login.net�� ���
		 * "/JspProject/login.jsp" ��ȯ�ȴ�.
		 */
		String RequestURI= request.getRequestURI();
		System.out.println("RequestURI"+ RequestURI);
	
		//getContextPath(): ���ؽ�Ʈ ��ΰ� ��ȯ�ȴ�.
		//contextPath�� "/JspProject"�� ��ȯ�ȴ�.
		String contextPath=request.getContextPath();
		System.out.println("contextPath="+contextPath);
	
		//RequestURI���� ���ؽ�Ʈ ��� ���� ���� �ε��� ��ġ�� ���ں���
		//������ ��ġ ���ڱ��� �����Ѵ�.
		//command�� "/login.net" ��ȯ�ȴ�.
		String command=RequestURI.substring(contextPath.length());
	    System.out.println("command="+command);
		
	    //�ʱ�ȭ
	    ActionForward forward=null;
	    Action action=null;
	    
	    if(command.equals("/BoardList.bo")) { 
	    	action=new BoardListAction(); //�������� ���� ��ĳ����
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	 
	    }else if(command.equals("/BoardWrite.bo")) { 
	         forward = new ActionForward();
	         forward.setRedirect(false); //������ ������� �ּҰ� �ٲ��� �ʴ´�
	         forward.setPath("Page/Board/board/board_write.jsp");
	    }  
	    
	    //����� �ǵ�� �ȵ�~
	    if(forward!=null) {
	    	if(forward.isRedirect()) { //�����̷�Ʈ �ȴ�.
	    		response.sendRedirect(forward.getPath());
	    	}else { //������ �ȴ�.
	    		RequestDispatcher dispatcher
	    		= request.getRequestDispatcher(forward.getPath());
	    		dispatcher.forward(request, response);
	    	}
	    }
	}
 
    public BoardFrontController() {
        super();
    }

   //doProcess(request,response)�޼��带 �����Ͽ� ��û�� GET����̵�
    //POST������� ���۵Ǿ� ���� ���� �޼��忡�� ��û�� ó���� �� �ֵ��� �Ѵ�.
	protected void doGet(HttpServletRequest request, 
			       HttpServletResponse response) 
			   throws ServletException, IOException {
	   doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, 
			       HttpServletResponse response) 
			  throws ServletException, IOException {
		 doProcess(request,response);
	}


}
