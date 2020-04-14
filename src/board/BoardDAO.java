package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	//데이터베이스에 접근하겠다는 하나의 객체
	private Connection conn;
	
	//어떠한 정보를 담을수 있는 하나의 객체
	private ResultSet rs;
	
	public BoardDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BOARDTEST";
			String dbID = "root";
			String dbPassword = "root";
			
			//Driver는 mysql에 접속할 수 있게 해주는 하나의 라이브러리
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public String getDate() {	//현재 시간을 가져오는 함수
		String SQL = "SELECT NOW()";	//mysql 문장
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
		}
		return ""; // 데이터베이스 오류
	}
	
	public int getNext() {
		String SQL = "SELECT boardID FROM BOARD ORDER BY boardID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
				}
			return 1; // 첫 번째 게시물인 경우
			}catch(Exception e) {
				e.printStackTrace();
			}
		return -1; // 데이터베이스 오류
	}
	
	public int write(String boardTitle, String userID, String boardContent) {	 // 실제로 글작성 함수
		String SQL = "INSERT INTO BOARD VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, boardTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, boardContent);
			pstmt.setInt(6, 1);
			return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	public ArrayList<Board> getList(int pageNumber){ // 특정한 리스트를 담아서 반환, 특정한 페이지에 정해진 글의 수를 가져옴
		String SQL = "SELECT * FROM BOARD WHERE boardID < ? AND boardAvailable = 1 ORDER BY boardID DESC LIMIT 20";
		// Board 클래스에서 나온 인스턴스를 보관할 수 있는 리스트를 만들어 new에 Board를 담음
		ArrayList<Board> list = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// sql문의 물음표에 들어갈 내용
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 20);	
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setBoardContent(rs.getString(5));
				board.setBoardAvailable(rs.getInt(6));
				list.add(board);
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		return list;
	}
	public boolean nextPage(int pageNumber) { // 한 페이지당 게시글의 개수가 20개 미만일때 다음과 이전이라는 페이지가 없다는 것을 알려줌(페이징처리)
		String SQL = "SELECT * FROM BOARD WHERE boardID < ? AND boardAvailable = 1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// sql문의 물음표에 들어갈 내용
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 20);	
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		return false;
	}
	// 하나의 글 내용을 불러오는 함수
	public Board getBoard(int boardID) {
		String SQL = "SELECT * FROM BOARD WHERE boardID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Board board = new Board();
				board.setBoardID(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserID(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setBoardContent(rs.getString(5));
				board.setBoardAvailable(rs.getInt(6));
				return board;
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public int update(int boardID, String boardTitle, String boardContent) {
		String SQL = "UPDATE BOARD SET boardTitle = ?, boardContent = ? WHERE boardID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardID);
			return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
		
	}
	
	public int delete(int boardID) { // 글 삭제 함수
		// Available = 0 은 글을 삭제하더라고 글에 대한 정보는 남아있게 함
		String SQL = "UPDATE BOARD SET boardAvailable = 0 WHERE boardID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardID);
			return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
