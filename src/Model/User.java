package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import Helper.DBConnection;
public class User {
		DBConnection conn=new DBConnection();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		private int id;
		private String tcno,name,password,type;
		public User(int id, String tcno, String name, String password, String type) {
			super();
			this.id = id;
			this.tcno = tcno;
			this.name = name;
			this.password = password;
			this.type = type;
		}
		public User() {
			
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTcno() {
			return tcno;
		}
		public void setTcno(String tcno) {
			this.tcno = tcno;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		

	

}
