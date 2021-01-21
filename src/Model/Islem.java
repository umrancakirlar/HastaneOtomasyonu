package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Helper.DBConnection;

public abstract class Islem {
	
	private int id, doktor_id;
	private String doktor_name, tarih;

	DBConnection conn=new DBConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement =null;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoktor_id() {
		return doktor_id;
	}

	public void setDoktor_id(int doktor_id) {
		this.doktor_id = doktor_id;
	}
	public String getDoktor_name() {
		return doktor_name;
	}

	public void setDoktor_name(String doktor_name) {
		this.doktor_name = doktor_name;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
}
