package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Helper.*;

public class Hasta extends User implements Islemler {
	public Hasta() {
		super();
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}

	public boolean kayit(String tcno, String password, String name) throws SQLException {
		String query = "INSERT INTO user" + "(tcno, password,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno= '" + tcno + "'");
			while (rs.next()) {
				count++;
				Helper.showMessage("Bu TC Numarasýna ait bir kayýt bulunmaktadýr");
				break;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
			}
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	public boolean randevuAl(int doktor_id, int hasta_id, String doktor_name, String hasta_name, String tarih) throws SQLException {
		String query = "INSERT INTO randevu" + "(doktor_id,doktor_name,hasta_id,hasta_name,tarih) VALUES" + "(?,?,?,?,?)";
		boolean key = false;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doktor_id);
			preparedStatement.setString(2, doktor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_name);
			preparedStatement.setString(5, tarih);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	public boolean updateCalismaDurum(int doktor_id, String tarih) throws SQLException {
		String query = "UPDATE saat SET durum = ? WHERE doktor_id =? AND tarih=?" ;
		boolean key = false;
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doktor_id);
			preparedStatement.setString(3, tarih);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	public boolean deleteRandevu(int id) throws SQLException {
		String query = "DELETE FROM randevu WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}public boolean updateRandevu(String tarih) throws SQLException {
		String query = "UPDATE saat SET durum=? WHERE tarih=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "a");
			preparedStatement.setString(2, tarih);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
}
