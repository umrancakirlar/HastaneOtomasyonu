package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void butonTextDegistir() {
		UIManager.put("OptionPane.cancelButtonText", "iptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	public static void showMessage(String str) {
		String msg;
		butonTextDegistir();
		switch(str) {
		case "fill":
			msg="Lütfen tüm alanlarý doldurunuz!";
			break;
		case "success":
			msg="Ýþlem Baþarýlý";
			break;
		case "ERROR":
			msg="Bir hata gerçekleþri";
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
			msg="Bu iþlemi gerçekleþtirmek istiyor musun?";
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
