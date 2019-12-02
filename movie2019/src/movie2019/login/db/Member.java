
/* DataBean �����ͺ� Ŭ���� 
 
�Խ��ǿ��� ���Ǵ� �������� �����ͺ��̶�� �ϳ��� ��ü�� �����ϰ�,
�� ��ü�� �����ϸ� ������ �ϳ��� ������ �ʿ� ���� �Ѳ����� ��� ������ ���޵ȴ�.

�̷� Ŭ������ DTO(Data Transfer Object), VO(Value Object)��� �Ѵ�.
DB���� ������� �÷���� �����ϰ� ������Ƽ���� �����ؾ� ��.
*/

package movie2019.login.db;

public class Member {

	private String id;
	private String password;
	private String nickname;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
