package user;

//외부 라이브러리 추가
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	//데이터베이스에 접근하겠다는 하나의 객체
	private Connection conn;
	
	//SQL 구문을 실행하는 하나의 객체
	private PreparedStatement pstmt;
	
	//어떠한 정보를 담을수 있는 하나의 객체
	private ResultSet rs;
	
	//생성자 생성, 자동으로 db connection이 이루어 질 수 있도록 함
	public UserDAO() {
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
	
	public int login(String userID, String userPassword) {
		
		//SQL문을 실행해서 나온결과가 접속을 시도했던 위와같은 비밀번호가 일치한지
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			
			//어떠한 정해진sql문을 삽입하는 이러한 형식으로 인스턴스를 가져옴
			pstmt = conn.prepareStatement(SQL);
			
			//?에 해당하는부분에 userID를 넣어서 로그인한 userID와 기존에 생성된 userID가 같은지
			pstmt.setString(1, userID);
			
			//rs에 쿼리실행 결과를 넣어줌
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공
				}
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(User user) { // User클래스의 인스턴스
		String SQL = "INSERT INTO USER VALUES(?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
			
	}catch(Exception e) {
		e.printStackTrace();
	}
		return -1; // 데이터베이스 오류
	}
}
