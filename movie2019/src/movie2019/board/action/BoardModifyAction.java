package movie2019.board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import movie2019.board.action.Action;
import movie2019.board.action.ActionForward;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ActionForward forward = new ActionForward();
	      BoardDAO boarddao = new BoardDAO();
	      BoardVO boarddata = new BoardVO();
	      
	      String realFolder="";
			
			//WebContent�Ʒ��� ���� ����
			String saveFolder="boardupload";
			
			int fileSize=10*1024*1024; //���ε��� ������ �ִ� ������. 10MB
			
			//���� ���� ��θ� �����Ѵ�.
		    ServletContext sc = request.getServletContext();
		    realFolder = sc.getRealPath(saveFolder);
		    
		    System.out.println("realFolder= "+realFolder);
		    boolean result = false;
		    
		    try {
		    	MultipartRequest multi = 
		    			new MultipartRequest(request,
		    			          realFolder, fileSize,
		    			          "utf-8", new DefaultFileRenamePolicy());
	      
	      //���޹��� �Ķ���� num ������ �����Ѵ�.
	      int num = Integer.parseInt(multi.getParameter("BOARD_NUM"));
	      String pass = multi.getParameter("BOARD_PASS");
	      //�۾������� Ȯ���ϱ� ���� ����� ��й�ȣ�� �Է��� ��й�ȣ�� ���Ѵ�.
	      boolean usercheck = 
	    		  boarddao.isBoardWriter(num,pass);
	      
	      System.out.println("��й�ȣüũ"+usercheck);
	      //��й�ȣ�� �ٸ� ���
	      if(usercheck==false) {
	    	  response.setContentType("text/html;charset=utf-8");
	    	  PrintWriter out = response.getWriter();
	    	  out.println("<script>");
	    	  out.println("alert('��й�ȣ�� �ٸ��ϴ�.');");
	    	  out.println("history.back();");
	    	  out.println("</script>");
	    	  out.close();
	    	  return null;
	      }
         //��й�ȣ�� ��ġ�ϴ� ���
	      //���� ������ �����Ѵ�.
	      boarddata.setBOARD_NUM(num);
	      boarddata.setBOARD_SUBJECT(
	    		  request.getParameter("BOARD_SUBJECT"));
	      boarddata.setBOARD_CONTENT(
	    		  request.getParameter("BOARD_CONTENT"));
	     
	      String check = multi.getParameter("check");
	      System.out.println("check="+check);
	      if(check!=null) {//�������� �״�� ����ϴ� ���
	    	  boarddata.setBOARD_FILE(check);
	      }else {
	    	  //���ε�� ������ �ý��� �� ���ε�� ���� ���ϸ��� ���´�.
	    	  String filename = multi.getFilesystemName("BOARD_FILE");
	    	  boarddata.setBOARD_FILE(filename);
	      }
	   
	      //DAO���� ���� �޼��� ȣ���Ͽ� �����Ѵ�.
	      result = boarddao.boardModify(boarddata);
	      //������ ������ ���
	      if(result==false) {
	    	  System.out.println("�Խ��� ���� ����");
	    	  forward.setRedirect(false);
	    	  request.setAttribute("message","�Խ��� ���� �����Դϴ�.");
	    	  forward.setPath("error/error.jsp");
	    	  return forward;
	      }
	      //���� ������ ���
	      System.out.println("�Խ��� ���� �Ϸ�");

	      forward.setRedirect(true);
	      //������ �� ������ �����ֱ� ���� �� ���� ���� ������������ 
	      //�̵��ϱ� ���� ��θ� �����Ѵ�.
	      forward.setPath("BoardDetailAction.bd?num="+boarddata.getBOARD_NUM());
	      return forward;
	
	 }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
			
	}
}
