package movie2019.board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.board.action.Action;
import movie2019.board.action.ActionForward;

public class BoardFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
		HttpServletResponse response) throws Exception {

		String fileName = request.getParameter("filename");
		System.out.println("fileName="+fileName);
		
		String savePath = "boardupload";
		
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		//String sFilePath = sDownloadPath + "\\" + fileName;
		// "\" �߰��ϱ� ���� "\\" ����Ѵ�.
		String sFilePath = sDownloadPath + "/" + fileName;
		System.out.println(sFilePath);
		
		byte b[] = new byte[4096];
		
		//sFilePath�� �ִ� ������ MimeType�� ���ؿ´�.
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>"+sMimeType);
		
		if(sMimeType == null)
			sMimeType = "application/octect-stream";
		
		response.setContentType(sMimeType);
		
		//�� �κ��� �ѱ� ���ϸ��� ������ ���� ������ �ش�.
		String sEncoding = 
				new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		System.out.println(sEncoding);
		
		/*
		 * Content-Disposition: attatchment: �������� �ش� Content��
		 *ó������ �ʰ� �ٿ�ε� �ϰ� �ȴ�.
		 */
		response.setHeader("Content-Disposition",
				"attatchment; filename="+sEncoding);
		
		try (
			//�� ���������� ��� ��Ʈ���� �����Ѵ�. //���۸� �Ἥ ������ ���
			BufferedOutputStream out2 = 
					new BufferedOutputStream(response.getOutputStream());
			//sFilePath�� ������ ���Ͽ� ���� �Է� ��Ʈ���� �����Ѵ�.
			BufferedInputStream in =
					new BufferedInputStream(new FileInputStream(sFilePath));)
	    {
				int numRead;
				//read(b,0,b.length):����Ʈ �迭 b�� 0�� ���� b,length
				//ũ�⸸ŭ �о�´�.
				while((numRead = in.read(b, 0, b.length)) != -1) {
					// ����Ʈ �迭 b�� 0������ numReadũ�⸸ŭ �������� ���
					out2.write(b,0,numRead);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
