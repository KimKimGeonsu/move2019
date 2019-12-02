package movie2019.admin.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NoticeAddAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, 
      HttpServletResponse response) throws Exception {
	  request.setCharacterEncoding("UTF-8");
  
      NoticeDAO noticedao = new NoticeDAO();
      NoticeVO noticevo = new NoticeVO();
      ActionForward forward = new ActionForward();
    
       boolean result = false;
       
       try {
         
          //NOTICEBean ��ü�� �� ��� ������ �Է� ���� �������� �����Ѵ�.
    	   noticevo.setNOTICE_NAME(
                request.getParameter("NOTICE_NAME"));
    	   noticevo.setNOTICE_SUBJECT(
                replaceParameter(request.getParameter("NOTICE_SUBJECT")));
    	   System.out.println(request.getParameter("NOTICE_NAME"));
    	   System.out.println(request.getParameter("NOTICE_SUBJECT"));
    	   noticevo.setNOTICE_CONTENT(
                replaceParameter(request.getParameter("NOTICE_CONTENT")));
          
          
          //�� ��� ������ �Է��� ������ ����Ǿ� �ִ� NOTICEdata ��ü�� �����Ѵ�.
          result = noticedao.NoticeInsert(noticevo);
          
          //�� ��Ͽ� ������ ��� false�� ��ȯ�Ѵ�.
          if(result==false) {
             System.out.println("�Խ��� ��� ����");
             forward.setRedirect(false);
               request.setAttribute("message", "�Խ��� ��� �����Դϴ�.");
               forward.setPath("error/error.jsp");
             return forward;
          }
          System.out.println("�Խ��� ��� �Ϸ�");
          //�� ����� �Ϸ�Ǹ� �� ����� �ܼ��� �����ֱ⸸ �� ���̹Ƿ�
          //Redirect���θ� true�� �����Ѵ�.
          forward.setRedirect(true);
          //�̵��� ��θ� �����Ѵ�.
          forward.setPath("NoticeList.bo");
          return forward;
       }catch(Exception ex) {
          ex.printStackTrace();
       }
       return null;
         
   }
   
   private String replaceParameter(String param) {
      String result = param;
      if(param!=null) {
         result = result.replaceAll("<","&lt;");
         result = result.replaceAll(">","&gt;");
         result = result.replaceAll("\"", "&quot;");
      }
      return result; 
   }

}