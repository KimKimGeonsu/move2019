package movie2019.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.board.comment.CommentAdd;
import movie2019.board.comment.CommentList;

@WebServlet("*.bd")
public class BoardFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, 
		       HttpServletResponse response) 
		   throws ServletException, IOException {
	
		String RequestURI= request.getRequestURI();
		System.out.println("RequestURI"+ RequestURI);
	
		String contextPath=request.getContextPath();
		System.out.println("contextPath="+contextPath);
		
		String command=RequestURI.substring(contextPath.length());
	    System.out.println("command="+command);
		
	    //초기화
	    ActionForward forward=null;
	    Action action=null;
	    
	    
	    if(command.equals("/BoardList.bd")) { 
	   // 	action=new BoardListAction(); //다형성에 의한 업캐스팅
   	 	action=new BoardSearchAction(); //다형성에 의한 업캐스팅
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	    
	    }else if(command.equals("/BoardWrite.bd")) { 
	         forward = new ActionForward();
	         forward.setRedirect(false); //포워딩 방식으로 주소가 바뀌지 않는다
	         forward.setPath("Page/Board/board/board_write.jsp");
	  
	    }else if(command.equals("/BoardAddAction.bd")) { 
	    	action=new BoardAddAction();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	          
	    }else if(command.equals("/BoardDetailAction.bd")) { 
	    	action=new BoardDetailAction();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	          
	    }else if(command.equals("/BoardFileDown.bd")) { 
	    	action=new BoardFileDownAction();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	    
	    }else if(command.equals("/BoardReplyView.bd")) { 
	    	action=new BoardReplyView();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	    
	    }else if(command.equals("/BoardReplyAction.bd")) { 
	    	action=new BoardReplyAction();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	     
	    }else if(command.equals("/BoardModifyView.bd")) { 
	    	action=new BoardModifyView();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	          
	    }else if(command.equals("/BoardModifyAction.bd")) { 
	    	action=new BoardModifyAction();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	    
	    }else if(command.equals("/BoardDelete.bd")) { 
	    	forward = new ActionForward();
	    	forward.setRedirect(false);//포워딩 방식으로 주소가 바뀌지 않음
	    	forward.setPath("Page/Board/board/board_delete.jsp");
	    
	    }else if(command.equals("/BoardDeleteAction.bd")) { 
	    	action=new BoardDeleteAction();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	          
	    }else if(command.equals("/CommentList.bd")) { 
	    	action=new CommentList();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	       
	    }else if(command.equals("/CommentAdd.bd")) { 
	    	//import안할거면 이렇게 패키지주소랑  같이 씀 net.comment.action.CommentAdd
	    	action=new CommentAdd();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	          
	    }else if(command.equals("/CommentDelete.bd")) { 
	    	action=new movie2019.board.comment.CommentDelete();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	          
	    }else if(command.equals("/CommentUpdate.bd")) { 
	    	action=new movie2019.board.comment.CommentUpdate();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	    
	    //신고 메일 보내기
	    }else if(command.equals("/Mail.bd")) { 
	    	action=new MailSend2();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	  /*        
	   //게시물 검색
	    }else if(command.equals("/board_search.bd")) { 
	    	action=new BoardSearchAction();
	          try {
	        	  forward=action.execute(request, response);
	          }catch(Exception e) {
	        	  e.printStackTrace();
	          }
	    }
	    
	    */
	    } //여기는 건들면 안돼~
	    if(forward!=null) {
	    	if(forward.isRedirect()) { //리다이렉트 된다.
	    		response.sendRedirect(forward.getPath());
	    	}else { //포워딩 된다.
	    		RequestDispatcher dispatcher
	    		= request.getRequestDispatcher(forward.getPath());
	    		dispatcher.forward(request, response);
	    	}
	    }
	}
 
    public BoardFrontController() {
        super();
    }

   //doProcess(request,response)메서드를 구현하여 요청이 GET방식이든
    //POST방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 한다.
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
