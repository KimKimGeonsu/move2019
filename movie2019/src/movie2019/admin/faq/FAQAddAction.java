package movie2019.admin.faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FAQAddAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, 
      HttpServletResponse response) throws Exception {
      
      FAQDAO faqdao = new FAQDAO();
      FAQVO faqvo = new FAQVO();
      ActionForward forward = new ActionForward();
    
       boolean result = false;
       
       try {
         
          //FAQBean ��ü�� �� ��� ������ �Է� ���� �������� �����Ѵ�.
    	   faqvo.setFAQ_NAME(
                request.getParameter("FAQ_NAME"));
    	   faqvo.setFAQ_SUBJECT(
                replaceParameter(request.getParameter("FAQ_SUBJECT")));
    	   faqvo.setFAQ_CONTENT(
                replaceParameter(request.getParameter("FAQ_CONTENT")));
          
          
          //�� ��� ������ �Է��� ������ ����Ǿ� �ִ� FAQdata ��ü�� �����Ѵ�.
          result = faqdao.FAQInsert(faqvo);
          
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
          forward.setPath("FAQList.fa");
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