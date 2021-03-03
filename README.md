![2](https://user-images.githubusercontent.com/63631952/94245674-8fe9f900-ff55-11ea-94b8-0fd04324eec0.png)
![3](https://user-images.githubusercontent.com/63631952/94245718-a42df600-ff55-11ea-852e-53c486e57204.png)

# 부트스트랩을 이용한 게시판
----------------------------

## 프로젝트 계획이유
> 게시판이라는 것 자체가 여러 사람들이 정보를 공유하고 여러 가지에 대해서 의견을 내는 아주 자유로운 곳이기 때문에 계획을 하게 되었습니다.
> 또한 개발자가 되기 위해서라면 제일 대중적인 프로젝트이고 조금이나마 사용자들에게 편리함을 주기 위해서 선택했습니다.

## 프로젝트 정보(개발환경)
> 1. 개발을 하기 위해 제일 대중적인 Eclipse2020-09를 사용했습니다.
> 2. 웹과 JSP가 연동되기 위한 tomcat9.0 서버를 사용했습니다.
> 3. JSP는 서버 프로그래밍 언어이고 java를 사용하기 때문에 자바 개발 환경인 jdk14를 사용했습니다.
> 4. 프론트엔드 프레임워크인 bootstrap은 3.3.7을 사용했습니다.
> 5. 전반적으로 회원과 게시글을 관리할 DB는 MySQL8.0을 사용했습니다.
> 6. MySQL을 사용해서 만든 데이터베이스를 JDBC5.1.48을 이용하여 JSP와 연동하였습니다.

## 프로젝트 주요 기능
![화면 캡처 2020-09-25 163154](https://user-images.githubusercontent.com/63631952/94245068-b3f90a80-ff54-11ea-8bf6-318f3ebd1593.png)

- 데이터베이스를 이용해 회원정보와 게시글리스트 관리
  - 소스폴더에 패키지를 생성해서 자바빈을 이용하여 관리했습니다.
 ``` JAVA
 package user;

public class User {
	
	private String userID;
	private String userPassword;
	private String userName;
	private String userGender;
	private String userEmail;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
```
![1](https://user-images.githubusercontent.com/63631952/94245412-2b2e9e80-ff55-11ea-8339-d01ca0fd906c.png)

``` JAVA
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
			String dbID = "";
			String dbPassword = "";
			
			//Driver는 mysql에 접속할 수 있게 해주는 하나의 라이브러리
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
```
- 자바빈을 이용해서 로그인, 회원가입을 구현했습니다.
- 게시판
  - 게시글 작성 후 게시판리스트에 출력했습니다.
  - 로그인한 아이디가 일치할 경우 게시글을 수정하고 삭제가 가능합니다.

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?
url=https%3A%2F%2Fgithub.com%2Fhak9ya&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

