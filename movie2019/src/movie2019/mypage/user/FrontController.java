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

	//doProcess(request,response)메서드를 구현하여 요청이 GET방식이든
    //POST방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다.
		 예) http://localhost:8088/JspProject/login.ul인 경우
		 "/JspProject/login.ul"반환됩니다. 
		 */
		String RequestURI=request.getRequestURI();
		System.out.println("RequestURI="+RequestURI);
		
		//getContextPath():컨텍스트 경로가 반환됩니다.
		//contextPath는 "/JspProject"가 반환됩니다.
		String contextPath=request.getContextPath();
		System.out.println("contexPath="+contextPath);
		
		//RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 마지막 위치 문자까지 추출합니다.
		//command는 "/login.ul" 반환됩니다.
		String command=RequestURI.substring(contextPath.length());
		System.out.println("command="+command);
		
		//초기화
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
		else if(command.equals("/user_editview.mu")) {
			action=new UserEditViewAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/user_editaction.mu")) {
			action=new UserEditAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward!=null) {
			if(forward.isRedirect()) {//리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			}else {//포워딩됩니다.
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}
