package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Klinik {
	DBConnection conn=new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement =null;
	private int id;
	private String name;
	public Klinik() {
		
	}
	public Klinik(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Klinik> getList() throws SQLException {
		ArrayList<Klinik> list=new ArrayList<>();
		Klinik obj;
		Connection con=conn.connDb();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM klinik");
			while(rs.next()) {
			obj=new Klinik(rs.getInt("id"),rs.getString("name"));
			list.add(obj);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
		
	}
	public Klinik getFetch(int id) {
		Connection con=conn.connDb();
		Klinik k=new Klinik();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM klinik WHERE id="+id);
			while(rs.next()) {
				k.setId(rs.getInt("id"));
				k.setName(rs.getString("name"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k;
		
	}
	public boolean addKlinik(String name) throws SQLException {
		String query="INSERT INTO klinik"+"(name) VALUES"+"(?)";
		boolean key=false;
		Connection con=conn.connDb();
		try {
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			key=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(key)
			return true;
		else
			return false;
	}
	public boolean deleteKlinik(int id) throws SQLException {
		String query="DELETE FROM klinik WHERE id=?";
		boolean key=false;
		Connection con=conn.connDb();
		try {
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(key)
			return true;
		else
			return false;
	}
	public boolean updateKlinik(int id,String name) throws SQLException {
		String query="UPDATE klinik SET name=? WHERE id=?";
		boolean key=false;
		Connection con=conn.connDb();
		try {
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(key)
			return true;
		else
			return false;
	}
	public ArrayList<User> getKlinikDoktorList(int klinik_id) throws SQLException { //bir klinikte �al��an doktorlar�n listesi
		ArrayList<User> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id,u.tcno,u.name,u.type,u.password FROM employee e LEFT JOIN user u ON e.user_id=u.id WHERE klinik_id="+klinik_id);
			while (rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.name"), rs.getString("u.password"),
						rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;

	}
}
