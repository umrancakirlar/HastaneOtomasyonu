package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class CalismaSaati extends Islem{
	
	private String durum;

	public CalismaSaati(int id, int doktor_id, String doktor_name, String tarih, String durum) {
		super();
		setId(id);
		setDoktor_id(doktor_id);
		setDoktor_name(doktor_name);
		setTarih(tarih);
		this.durum = durum;
	}
	public CalismaSaati() {
		
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
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
}
