package movie2019.gong.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.gong.model.GongDAO;
import movie2019.gong.model.GongVO;

public class GListCommand implements GCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GongDAO dao = new GongDAO();
		int curPage =Integer.parseInt(request.getParameter("curPage"));
		int start =(curPage-1)*5+1; //���۹�ȣ = (���������� -1) * ��������Խù��� +1
		int end=start+(5-1); //����ȣ = ���������� +(�������� �Խù��� -1)
		List<GongVO> list = dao.GongList(start,end);
		request.setAttribute("list", list);
		
		
		//���������� ��� ex 991/10 =>99.1 = > �ø� => 100 count����
		int rows =dao.GongCount();
		int total_page=(int)(Math.ceil(rows/5.0));//ceil�ø��Լ�
		request.setAttribute("total_page", total_page);
		
		//��ϴ� ǥ�� ������ ���� 10��������ϰڴ�
		int page_list_size=5;
		//������ ����� ����(��ü ���������� / ��ϴ� ������ ����)
		int total_block=(int)Math.ceil(total_page*1.0/page_list_size);
		//���� �������� ���° ������� ��� ����=(����������-1)/��������ϴ���+1
		int current_block=(int)Math.ceil((curPage-1)/page_list_size)+1;
		//n��° ����� ������������ȣ, �������� ��ȣ ���
		int block_start=(current_block-1)*page_list_size+1;
		int block_end=block_start+page_list_size-1; //���۹�ȣ�� ������ +9�� 
		//���������� ��ȣ, ���������� ��ȣ
		int prev_page = current_block ==1 ? 1:(current_block-1)*page_list_size;
		int next_page =current_block > total_block ? (current_block*page_list_size) : (current_block*page_list_size)+1;
		
		//������ ����� ����ȣ�� ���� �ʰ� ����
		if(block_end > total_page) {
			block_end = total_page;
		}
		//������ �������� ���� �ʰ� ����
		if(next_page >=total_page) {
			next_page = total_page;
		}
		
		//������ ���̰��̼� ��������
		request.setAttribute("total_block", total_block);//��ϰ���
		request.setAttribute("current_block", current_block);//������
		request.setAttribute("block_start", block_start);//������������ȣ
		request.setAttribute("block_end", block_end);//����������ȣ
		request.setAttribute("prev_page", prev_page);//������������ȣ
		request.setAttribute("next_page", next_page);//������������ȣ
		request.setAttribute("current_page", curPage);	//������������ȣ
	}

}
