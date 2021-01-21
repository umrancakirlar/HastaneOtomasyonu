package Model;

import java.sql.SQLException;

public interface Islemler {
	boolean deleteRandevu(int id)throws SQLException;
	public boolean updateRandevu(String tarih)throws SQLException;
	
}
