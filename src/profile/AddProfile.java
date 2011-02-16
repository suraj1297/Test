package profile;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class AddProfile {
	
	public JPanel addProfile(JFrame frame) {
		
		// Adding addNewProfileButton and Profile Panel in homepage.
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 30));
		JButton addProfile = new JButton("Create Car Profile");
		addProfile.setPreferredSize(new Dimension(130, 40));
		addProfile.setBorder(BorderFactory.createEmptyBorder());
		addProfile.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		buttonPanel.add(addProfile);
		
		
		addProfile.addActionListener(e -> {
			
			new ProfileForm(frame);
//			frame.dispose();
		});
		
		
		return buttonPanel;
		
	}

}
