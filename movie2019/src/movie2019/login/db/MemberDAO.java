package movie2019.login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
		}
	}

	public int isId(String id) {
		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			String sql = "select id from users where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 0; // DB�� �ش� ID�� �ֽ��ϴ�.
			} else {
				result = -1; // DB�� �ش� ID�� �ֽ��ϴ�.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public int insertMember(Member m) {
		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			pstmt = con.prepareStatement("insert into users values (?,?,?,?,?,?)");
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getName());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getGender());
			pstmt.setString(6, m.getEmail());

			result = pstmt.executeUpdate();

			//
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public int isId(String id, String pass) {
		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			String sql = "select id, password from users where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(2).equals(pass)) {
					result = 1; // ���̵�, ��� �� �� ��ġ�ϴ� ���
				} else {
					result = 0; // ��й�ȣ ��ġ���� �ʴ� ���
				}
			} else {
				result = -1; // ���̵� ����X
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public Member member_info(String id) {
		Member m = null;

		try {
			con = ds.getConnection();
			String sql = "select* from users where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				m = new Member();
				m.setId(rs.getString(1));
				m.setPassword(rs.getString(2));
				m.setName(rs.getString(3));
				m.setAge(rs.getInt(4));
				m.setGender(rs.getString(5));
				m.setEmail(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return m;
	}

	public int update(Member m) {
		int result = 0;
		try {
			con = ds.getConnection();

			String sql = "update users set name=?, " 
					+ "age=?, gender=?, email=? " 
					+ "where id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, m.getGender());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getId());
			result = pstmt.executeUpdate();

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	
	
	public List<Member> getList() {
		List<Member> list = new ArrayList<Member>();
		
		try {
			con = ds.getConnection();
			
			String sql = "select* from users where id != 'admin'";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			/*
			create table member (
			id varchar2(15),
			password varchar2(10),
			name varchar2(15),
			age Number,
			gender varchar2(5),
			email varchar2(30),
			primary key(id)
			);
			 */
			
		while (rs.next()) {
			Member m = new Member();
			m.setId(rs.getString(1));
			m.setPassword(rs.getString(2));
			m.setName(rs.getString(3));
			m.setAge(rs.getInt(4));
			m.setGender(rs.getString(5));
			m.setEmail(rs.getString(6));
			list.add(m);  //����! �����ذ� �ó����� (Arraylist�ε� add�� ����)
		}
		
		} catch (

				Exception e) {
					e.printStackTrace();
				} finally {
					if (rs != null) {
						try {
							pstmt.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
					if (con != null) {
						try {
							con.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				}
				return list;
			}

	public int getListCount() {
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from users where id != 'admin'");

			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount ���� : " + ex);
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return x;
	}

	public List<Member> getList(int page, int limit) {
	
		
			List<Member> list = new ArrayList<Member>();
			String sql = "select * " + "from (select b.*, rownum rnum" 
											+ " from (select* from users "
											+ " where id != 'admin'"
									 		+ " order by id) b"
											+ ")"
											+ "where rnum>=? and rnum<=?";
		
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startrow);
				pstmt.setInt(2, endrow);
				rs = pstmt.executeQuery();
				
			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getString(1));
				m.setPassword(rs.getString(2));
				m.setName(rs.getString(3));
				m.setAge(rs.getInt(4));
				m.setGender(rs.getString(5));
				m.setEmail(rs.getString(6));
				list.add(m); // ���� ���� ��ü�� ����Ʈ�� ����.
			}
			return list; // ���� ���� ��ü�� ������ ����Ʈ�� ȣ���� ������ ������.
		} catch (Exception e) {
			System.out.println("getMemberList() ���� : " + e);
			e.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	
	public int delete(String id) {
		
		result = 0;
		try {
			con = ds.getConnection();
			
			String sql = "delete from users where id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("geMemberList() ���� : " + e);
			e.printStackTrace();

		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	
	
	public int getListCount(String field, String value) {
		int x = 0;
		
		try {
			con = ds.getConnection();
			String sql = "select count(*) from users "
					+ " where id !='admin' "
					+ "and "
					+ field
					+ " like ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + value + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount ���� : " + ex);
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return x;
	}

	
	public List<Member> getList(String field, String value, int page, int limit){
	      List<Member> list = new ArrayList<Member>();
	      try {
	         con = ds.getConnection();
	         
	         String sql = 
	               "select * "
	               +"from (select b.*, rownum rnum"
	                     +" from (select * from users "
	                     +" where id != 'admin' "
	                     +" and "+field + " like ?"
	                     +" order by id) b"
	                     +" )"
	                     +" where rnum between ? and ?";
	         
	         
	         // �� ������ �� 10���� ����� ��� 1������, 2������, 3������, 4������ ...
	         int startrow = (page - 1) * limit + 1;
	         // �б� ������ row ��ȣ(1 11 21 31 ...)
	         int endrow = startrow + limit - 1;
	         // ���� ������ row ��ȣ (10 20 30 40 ...)
	         
	      
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1,"%"+value+"%");
	            pstmt.setInt(2, startrow);
	            pstmt.setInt(3, endrow);
	            rs = pstmt.executeQuery();
	            
	            
	            // DB���� ������ �����͸� VO ��ü�� ����ϴ�.
	            while (rs.next()) {
	               Member member = new Member();
	               member.setId(rs.getString(1));
	               member.setPassword(rs.getString(2));
	               member.setName(rs.getString(3));
	               member.setAge(rs.getInt(4));
	               member.setGender(rs.getString(5));
	               list.add(member); // ���� ���� ��ü�� ����Ʈ�� �����մϴ�.
	            }
	            

	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {

	            if (rs != null)
	               try {
	                  rs.close();
	               } catch (SQLException e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	               }
	            if (pstmt != null)
	               try {
	                  pstmt.close();
	               } catch (SQLException e) {
	                  e.printStackTrace();
	               }
	            if (con != null)
	               try {
	                  con.close();
	               } catch (SQLException e) {
	                  e.printStackTrace();
	               }
	         }
	         return list; // ���� ���� ��ü�� ����Ʈ�� ȣ���� ������ �������ϴ�.

	      }

}
