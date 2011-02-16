package profile;

import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Homepage extends JFrame {
	
	Homepage(Profile profile){
		
		JFrame frame = new JFrame();
		
		// Basic Setup
		frame.setTitle("Car Profile");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	 
		// adding addProfileButton panel	
		frame.add(new AddProfile().addProfile(frame), BorderLayout.PAGE_START);
		// adding ProfileData panel
		new ProfileDisplay(profile, frame);
	       
	       frame.invalidate();
	       frame.validate();
	       frame.repaint();
		
	}
	
	

}
