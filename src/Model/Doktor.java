package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doktor extends User implements Islemler {
	public Doktor() {
		super();
	}

	public Doktor(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}

	public boolean addCalismaSaati(int doktor_id, String doktor_name, String tarih) throws SQLException {
		String query = "INSERT INTO saat" + "(doktor_id, doktor_name,tarih) VALUES" + "(?,?,?)";
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM saat WHERE durum='a' AND doktor_id=" + doktor_id + " AND tarih='" + tarih + "'");
			while (rs.next()) {
				count++;
				break;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doktor_id);
				preparedStatement.setString(2, doktor_name);
				preparedStatement.setString(3, tarih);
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

	public ArrayList<CalismaSaati> getCalismaList(int doktor_id) throws SQLException {
		ArrayList<CalismaSaati> list = new ArrayList<>();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;
		CalismaSaati obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM saat WHERE durum='a' AND doktor_id=" + doktor_id);
			while (rs.next()) {
				obj = new CalismaSaati();
				obj.setId(rs.getInt("id"));
				obj.setDoktor_id(rs.getInt("doktor_id"));
				obj.setDoktor_name(rs.getString("doktor_name"));
				obj.setDurum(rs.getString("durum"));
				obj.setTarih(rs.getString("tarih"));
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
	public boolean deleteCalismaSaati(int id) throws SQLException {
		String query = "DELETE FROM saat WHERE id=?";
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
	}
	public boolean updateRandevu(String tarih) throws SQLException {
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
