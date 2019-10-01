import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		    // Set cross-platform Java L&F (also called "Metal")
		    UIManager.setLookAndFeel(
		        UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) { /* ignore, not important */ }
		
		RegiCode regi = new RegiCode();
		//Phototheque phototheque = new Phototheque();

	}

}