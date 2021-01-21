package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void butonTextDegistir() {
		UIManager.put("OptionPane.cancelButtonText", "iptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	public static void showMessage(String str) {
		String msg;
		butonTextDegistir();
		switch(str) {
		case "fill":
			msg="L�tfen t�m alanlar� doldurunuz!";
			break;
		case "success":
			msg="��lem Ba�ar�l�";
			break;
		case "ERROR":
			msg="Bir hata ger�ekle�ri";
			break;
			default:
				msg=str;
		}
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	public static boolean confirm(String str) {
		String msg;
		butonTextDegistir();
		switch(str) {
		case "sure":
			msg="Bu i�lemi ger�ekle�tirmek istiyor musun?";
			break;
			default:
				msg=str;
				break;
		}
		int res=JOptionPane.showConfirmDialog(null, msg,"Dikkat!",JOptionPane.YES_NO_OPTION);
		if(res==0)
			return true;
		else
			return false;
		
	}

}
