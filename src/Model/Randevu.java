package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Randevu extends Islem {
	private int hasta_id;
	private String hasta_name;
	public Randevu(int id, int doktor_id, int hasta_id, String doktor_name, String hasta_name, String tarih) {
		super();
		setId(id);
		setDoktor_id(doktor_id);
		this.hasta_id = hasta_id;
		setDoktor_name(doktor_name);
		this.hasta_name = hasta_name;
		setTarih(tarih);
	}
	public Randevu() {
		
	}
	public int getHasta_id() {
		return hasta_id;
	}
	public void setHasta_id(int hasta_id) {
		this.hasta_id = hasta_id;
	}
	public String getHasta_name() {
		return hasta_name;
	}
	public void setHasta_name(String hasta_name) {
		this.hasta_name = hasta_name;
	}
	public ArrayList<Randevu> getHastaList(int hasta_id) throws SQLException {
		ArrayList<Randevu> list=new ArrayList<>();
		Randevu obj;
		Connection con=conn.connDb();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM randevu WHERE hasta_id="+hasta_id);
			while(rs.next()) {
			obj=new Randevu();
			obj.setId(rs.getInt("id"));
			obj.setDoktor_id(rs.getInt("doktor_id"));
			obj.setDoktor_name(rs.getString("doktor_name"));
			obj.setHasta_id(rs.getInt("hasta_id"));
			obj.setHasta_name(rs.getString("hasta_name"));
			obj.setTarih(rs.getString("tarih"));
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
	public ArrayList<Randevu> getDoktorList(int doktor_id) throws SQLException {
		ArrayList<Randevu> list=new ArrayList<>();
		Randevu obj;
		Connection con=conn.connDb();
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM randevu WHERE doktor_id="+doktor_id);
			while(rs.next()) {
			obj=new Randevu();
			obj.setId(rs.getInt("id"));
			obj.setDoktor_id(rs.getInt("doktor_id"));
			obj.setDoktor_name(rs.getString("doktor_name"));
			obj.setHasta_id(rs.getInt("hasta_id"));
			obj.setHasta_name(rs.getString("hasta_name"));
			obj.setTarih(rs.getString("tarih"));
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

}
