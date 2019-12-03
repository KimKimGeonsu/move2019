package movie2019.board.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
		}
	}

	//�� �ۼ� 
	public boolean boardInsert(BoardVO boarddata) {
		String sql = "";
		boolean result = false;

		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			sql = "insert into MBOARD(" + "BOARD_NUM, BOARD_NAME, BOARD_PASS,"
					+ "BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE," + "BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ,"
					+ "BOARD_READCOUNT, BOARD_DATE) " + "values((select nvl(max(BOARD_NUM),0)+1 from mboard),"
					+ "?,?,?,?," // board_content����
					+ "?,(select nvl(max(BOARD_NUM),0)+1 from mboard)," // board_re_ref����
					+ "?,?,?,sysdate)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boarddata.getBOARD_NAME());
			pstmt.setString(2, boarddata.getBOARD_PASS());
			pstmt.setString(3, boarddata.getBOARD_SUBJECT());
			pstmt.setString(4, boarddata.getBOARD_CONTENT());
			pstmt.setString(5, boarddata.getBOARD_FILE());

			// ������ ��� BOARD_RE_LEV, BOARD_RE_SEQ �ʵ� ���� 0�̴�.
			pstmt.setInt(6, 0); // BOARD_RE_LEV �ʵ�
			pstmt.setInt(7, 0); // BOARD_RE_SEQ �ʵ�
			pstmt.setInt(8, 0); // BOARD_READCOUNT �ʵ�

			int result2 = pstmt.executeUpdate();

			// result2�� 1�̸� �����̶� true��ȯ,0�̸� ���ж� false
			if (result2 == 1) {
				System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
				result = true;
			}

		} catch (Exception e) {
			System.out.println("boardInsert()����:" + e);
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

	public int getListCount() {
		int x = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from mboard");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getListCount()����:" + e);
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

		return x;
	}

	// �� ��� ����
	public List<BoardVO> getBoardList(int page, int limit) {
		// page:������
		// limit: ������ �� ����� ��
		// BOARD_RE_REF desc, BOARD_RE_SEQ asc�� ���� ������ ����
		// �������� �´� rnum�� ������ŭ �������� �������̴�.

		String board_list_sql = "select * " + "from (select rownum rnum, b.* " + " from (select * from mboard "
				+ " order by BOARD_RE_REF desc," + " BOARD_RE_SEQ asc) b) " + "where rnum>=? and rnum<=?";

		List<BoardVO> list = new ArrayList<BoardVO>();
		// �� �������� 10���� ����� ��� 1������,2������, 3������ ...
		int startrow = (page - 1) * limit + 1;
		// �б� ������ row ��ȣ(1, 11, 21 ...)
		int endrow = startrow + limit - 1;
		// ���� ������ row ��ȣ(10, 20, 30...)

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			// DB���� ������ VO��ü�� ��´�.
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_PASS(rs.getString("BOARD_PASS"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				// board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				// board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				// board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board); // ���� ���� ��ü�� ����Ʈ�� �����Ѵ�.
			}
			return list; // ���� ���� ��ü�� ������ ����Ʈ�� ȣ���� ������ ��������.

		} catch (Exception e) {
			System.out.println("getBoardList()����:" + e);
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

	// ��ȸ�� ������Ʈ : �۹�ȣ�� �ش��ϴ� ��ȸ���� 1 ������Ų��.
	public void setReadCountUpdate(int num) {
		String sql = "update MBOARD set " + "BOARD_READCOUNT = (BOARD_READCOUNT)+1 " + "where BOARD_NUM = ?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("setReadCountUpdate()����:" + e);
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

	}

	public BoardVO getDetail(int num) {
		BoardVO board = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"select * " + "from mboard " + "where BOARD_NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new BoardVO();
				// �ʿ��� �͵鸸 �����ͼ� setter�� ����
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_PASS(rs.getString("BOARD_PASS"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF")); //�亯 �� �� �ʿ�
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV")); //�亯 �� �� �ʿ�
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ")); //�亯 �� �� �ʿ�
				//board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				//board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			return board;
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
		} // finally
		return null;
	}// getDetail
	
	//�� �亯
	   public int boardReply(BoardVO board) {
		      //board ���̺��� board_num �ʵ��� �ִ밪�� ���ؿͼ� ���� ����� ��
		      //�� ��ȣ�� ���������� �����ϱ� �����Դϴ�.
		      //���� DB�� ������ �� �ٽ� �����ֱ� ���� board_num �ʵ��� ���� �����մϴ�.
		      String board_max_sql="select max(board_num) from mboard";
		    
		      int num=0;
		      /*
		       * �亯�� �� ���� �� �׷� ��ȣ�Դϴ�.
		       * �亯�� �ް� �Ǹ� �亯 ���� �� ��ȣ�� ���� ���� �� ��ȣ�� ���� ó���Ǹ鼭 ���� �׷쿡 ���ϰ� �˴ϴ�.
		       * �� ��Ͽ��� ������ �� �ϳ��� �׷����� ������ ��µ˴ϴ�.
		       */
		      int re_ref=board.getBOARD_RE_REF();
		      System.out.println("re_Ref="+re_ref);
		      /*
		       * ����� ���̸� �ǹ��մϴ�.
		       * ������ ���� ����� ��µ� �� �ѹ� �鿩���� ó���� �ǰ� ��ۿ� ���� ����� �鿩���Ⱑ �ι� ó���ǰ� �մϴ�.
		       * ������ ��� �� ���� 0�̰� ������ ����� 1, ����� ����� 2�� �˴ϴ�.
		       */
		      int re_lev=board.getBOARD_RE_LEV();
		      
		      //���� ���� �� �߿��� �ش� ���� ��µǴ� �����Դϴ�.
		      int re_seq=board.getBOARD_RE_SEQ();
		      
		      try {
		         con=ds.getConnection();
		         //Ʈ������� �̿��ϱ� ���ؼ� setAutoCommit�� false�� �����մϴ�.
		         con.setAutoCommit(false);
		         
		         pstmt=con.prepareStatement(board_max_sql);
		         rs=pstmt.executeQuery();
		         if(rs.next())
		            num=rs.getInt(1)+1;   //�亯 ������ �۹�ȣ
		         
		         //BOARD_RE_REF, BOARD_RE_SEQ ���� Ȯ���Ͽ� ���� �ۿ� �ٸ� ����� ������
		         //�ٸ� ��۵��� BOARD_RE_SEQ���� 1�� ������ŵ�ϴ�.
		         //���� ���� �ٸ� ��ۺ��� �տ� ��µǰ� �ϱ� ���ؼ��Դϴ�.
		         
		         String update_sql="update mboard" 
		               + "         set BOARD_RE_SEQ = BOARD_RE_SEQ + 1" 
		               + "         where BOARD_RE_REF = ?" 
		               + "         and BOARD_RE_SEQ>?";
		         
		         pstmt=con.prepareStatement(update_sql);
		         pstmt.setInt(1, re_ref);
		         pstmt.setInt(2, re_seq);
		         int result1=pstmt.executeUpdate();
		         //����� �亯 ���� BOARD_RE_LEV, BOARD_RE_SEQ ���� ���� �ۺ��� 1�� ������ŵ�ϴ�.
		         re_seq=re_seq+1;
		         re_lev=re_lev+1;
		         
		         String insert_sql=" insert into mboard" 
		               + "    (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT," 
		               + "     BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," 
		               + "     BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT," 
		               + "     BOARD_DATE) " 
		               + "        values(?,?,?,?,?," 
		               + "               ?,?,?," 
		               + "             ?,?,sysdate)";
		         
		         pstmt = con.prepareStatement(insert_sql);
		         pstmt.setInt(1, num);
		         pstmt.setString(2, board.getBOARD_NAME());
		         pstmt.setString(3, board.getBOARD_PASS());
		         pstmt.setString(4, board.getBOARD_SUBJECT());
		         pstmt.setString(5, board.getBOARD_CONTENT());
		         pstmt.setString(6, "");
		         pstmt.setInt(7, re_ref);
		         pstmt.setInt(8, re_lev);
		         pstmt.setInt(9, re_seq);   
		         pstmt.setInt(10, 0);      //BOARD_READCOUNT(��ȸ��)�� 0
		         int result2=pstmt.executeUpdate();
		         if(result1>=0&& result2==1) {
		            con.commit();
		            con.setAutoCommit(true);   //�ٽ� true�� ����
		         }else {
		            con.rollback();
		            System.out.println("rollback()");
		         }
		         
		      }
		      catch(SQLException ex) {
		         System.out.println("boardReply()����:"+ex);
		         if(con!=null)
		            try {
		               con.rollback();
		            }catch(SQLException e) {
		               e.printStackTrace();
		            }
		            finally {
		               if (rs != null) {
		                  try {
		                     rs.close();
		                  } catch (SQLException e) {
		                     e.printStackTrace();
		                  }
		               }
		               if (pstmt != null) {
		                  try {
		                     pstmt.close();
		                  } catch (SQLException e) {
		                     e.printStackTrace();
		                  }
		               }
		               if (con != null) {
		                  try {
		                     con.close();
		                  } catch (SQLException e) {
		                     e.printStackTrace();
		                  }
		               }
		            }   
		      }
		      return num;
		   }//boardReply end
	   
	   //�� ���� - ���Ͼ��ε嵵 ���� ����
	   public boolean boardModify(BoardVO modifyboard) {
		   String sql = "update mboard "
				       +"set BOARD_SUBJECT= ?, BOARD_CONTENT=?, "
				       +"BOARD_FILE=? where BOARD_NUM=? ";

			try {
				con = ds.getConnection();
    	        pstmt = con.prepareStatement(sql);
			    pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			    pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			    pstmt.setString(3, modifyboard.getBOARD_FILE());
			    pstmt.setInt(4, modifyboard.getBOARD_NUM());
			    
			   
				int result = pstmt.executeUpdate();
				if (result == 1) {
					System.out.println("���� ����");
					return true;
				}
			} catch (Exception e) {
				System.out.println("boardModify()����:" + e);
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
			return false;
	   }//boardModify end

	//�۾������� Ȯ�� - ��й�ȣ�� Ȯ���Ѵ�.
	public boolean isBoardWriter(int num, String pass) {

		String board_sql = "select * from mboard "
				          +"where BOARD_NUM = ?";
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(pass.equals(rs.getString("BOARD_PASS"))) {
				return true;				
			   }
			}
		}catch (SQLException se) {
			System.out.println("isBoardWriter()����"+se);
			se.printStackTrace();
		}finally {
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
		return false;
	}//isBoardWriter end
	
	//�� ���� (�Ʒ� �޸� �亯�۵� ���� ����)
	public boolean boardDelete(int num) {
		String select_sql 
		      = "select BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ "
		      + "from mboard "
		      + "where BOARD_NUM = ?";
		String board_delete_sql
		      = "delete from mboard "
		      + "where BOARD_RE_REF = ? "
		      + "and   BOARD_RE_LEV >= ? "
		      + "and   BOARD_RE_SEQ >= ? "
		      + "and   BOARD_RE_SEQ <("
		      + "                     nvl((select min(board_re_seq )"
		      + "                          from   MBOARD "
		      + "                          where  BOARD_RE_REF = ? "
		      + "                          and    BOARD_RE_LEV = ? "
		      + "                          and    BOARD_RE_SEQ > ?), "
		      + "                          (SELECT max(board_re_seq)+1 "
		      + "                            FROM  MBOARD "
		      + "                            WHERE BOARD_RE_REF = ?)))";
		//String board_delete_sql = "delete from board where board_num =?" �ϸ� ������ �۸� ����
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			int BOARD_RE_REF=0, BOARD_RE_LEV=0, BOARD_RE_SEQ=0;
			if(rs.next()) {
				BOARD_RE_REF = rs.getInt("BOARD_RE_REF");
				BOARD_RE_LEV = rs.getInt("BOARD_RE_LEV");
				BOARD_RE_SEQ = rs.getInt("BOARD_RE_SEQ");
			
				pstmt = con.prepareStatement(board_delete_sql);
				pstmt.setInt(1, BOARD_RE_REF);
				pstmt.setInt(2, BOARD_RE_LEV);
				pstmt.setInt(3, BOARD_RE_SEQ);
				
				pstmt.setInt(4, BOARD_RE_REF);
				pstmt.setInt(5, BOARD_RE_LEV);
				pstmt.setInt(6, BOARD_RE_SEQ);
				
				pstmt.setInt(7, BOARD_RE_REF);
				//���� ���� �� ������ �ο�(���ڵ�) ������ ��ȯ�ȴ�.
				int result = pstmt.executeUpdate();
				System.out.println(result+"�� �����Ǿ����ϴ�.");
				if(result >= 1)
				   return true;
			}
		}catch(Exception e) {
			System.out.println("boardDelete()����:"+e);
			e.printStackTrace();
		}finally {
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
		return false;
	}//boardDelete end
	
}//class