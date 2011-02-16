package profile;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ProfileDisplay extends JFrame{
	
	JLabel heading, brand, model, color, year, engineNo, seatsNo, licencePlate, ownerName, telephoneNo, email,
	licenseNo, socialSecurityNumber, address, serviceRecords, warrantyYear, Photo, imageName;

	JTextField brandField, modelField, colorField, engineNoField, seatsNoField, licencePlateField, ownerNameField,
	telephoneNoField, emailField, licenseNoField, socialSecurityNumberField, serviceRecordsField,
	warrantyYearField, yearField;
	
	JTextArea addressField;
	
	JButton viewRecords;
	
	JPanel mainPanel = new JPanel();
	
	Profile profile;
	
	JFrame homeFrame;
	
	ProfileDisplay(Profile profile, JFrame homeFrame){
		this.profile = profile;
		this.homeFrame = homeFrame;
		
		if(profile.getYear() == null || profile.getYear().length() == 0) {
			 homeFrame.add(ProfileDisplayPanel(), BorderLayout.CENTER);
		}
		else {
			homeFrame.add(ProfileDisplayTable(profile, homeFrame), BorderLayout.CENTER);
			
		}
		
		
	}
	
	public JPanel ProfileDisplayTable(Profile profile, JFrame frameHome){
		
		
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(237,245,225));
		
		// adding all form input fields
		addFormElements();
		
			
//	    BufferedImage image = null;
//        try
//        {
//          image = ImageIO.read(new File(profile.getPhoto()));
//        }
//        catch (Exception e)
//        {
//          e.printStackTrace();
//          System.exit(1);
//        }
        ImageIcon imageIcon = new ImageIcon(fitimage(profile.getPhoto() , 300, 300));
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(frameHome.getWidth()-350, 10 ,300, 300);
        mainPanel.add(imageLabel);
	
	
		return mainPanel;
	}
	
	
	public JPanel ProfileDisplayPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(0,0, 800,560);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(Color.WHITE);
		JLabel formData = new JLabel();
		formData.setBounds(0,0, 800,560);
		formData.setText("No Data to display! Please click on 'Create Car Profile'!");
		panel.add(formData);
		
		return panel;
	}
	
	private Image fitimage(Image img , int w , int h)
	{
	    BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedimage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0,w,h,null);
	    g2.dispose();
	    return resizedimage;
	}

	public void addFormElements() {
		
//		String[][] serviceData = {
//            { "Brand", profile.getBrand()},
//            { "Model", profile.getModel()},
//            { "Color", profile.getColor()},
//		};
//		
//		String[] serviceColumns = {"Date", "Service Detail"};
		
		JLabel car = new JLabel();
		car.setBounds(10, 0, 150, 50);
		
		car.setText("<html> <h1> Car Details : </h1></html>");
		car.setForeground(new Color(20,30,97));
		mainPanel.add(car);

		getLabel(brand, "Brand", 30, 50, 50, 30);
		brandField = new JTextField();
		getField(brandField, 90, 50, 200, 30, profile.getBrand());
		
		getLabel(model, "Model", 310, 50, 50, 30);
		modelField = new JTextField();
		getField(modelField, 370, 50, 200, 30, profile.getModel());
		
		getLabel(color, "Color", 590, 50, 50, 30);
		colorField = new JTextField();
		getField(colorField, 650, 50, 200, 30, profile.getColor());
		
		getLabel(year, "Year", 30, 110, 50, 30);
		yearField = new JTextField();
		getField(yearField, 90, 110, 200, 30, profile.getYear());
		
		getLabel(engineNo, "Engine No", 310, 110, 90, 30);
		engineNoField = new JTextField();
		getField(engineNoField, 390, 110, 200, 30, profile.getEngineNo());
		
		getLabel(seatsNo, "Seats Number", 610, 110, 110, 30);
		seatsNoField = new JTextField();
		getField(seatsNoField, 710, 110, 200, 30, profile.getSeatsNo());
		
		getLabel(warrantyYear, "Warranty Year", 30, 170, 100, 30);
		warrantyYearField = new JTextField();
		getField(warrantyYearField, 140, 170, 200, 30, profile.getWarrantyYear());

		getLabel(licencePlate, "License Plate", 360, 170, 110, 30);
		licencePlateField = new JTextField();
		getField(licencePlateField, 480, 170, 200, 30, profile.getLicencePlate());
		
		getLabel(serviceRecords, "Service Records", 30, 230, 110, 30);
		viewRecords = new JButton("View Records");
		viewRecords.setBounds(150, 230, 140, 30);
		mainPanel.add(viewRecords);
		viewRecordsBtnListener();
		
	    
		JLabel owner = new JLabel();
		owner.setBounds(10, 340, 200, 50);
		
		owner.setText("<html> <h1> Owner Details : </h1></html>");
		owner.setForeground(new Color(20,30,97));
		mainPanel.add(owner);
		
		getLabel(ownerName, "Name ", 30, 400, 50, 30);
		ownerNameField = new JTextField();
		getField(ownerNameField, 90, 400, 200, 30, profile.getOwnerName());
		
		getLabel(telephoneNo, "Telephone Number", 310, 400, 130, 30);
		telephoneNoField = new JTextField();
		getField(telephoneNoField, 450, 400, 200, 30, profile.getTelephoneNo());
		
		getLabel(email, "Email Address ", 680, 400, 110, 30);
		emailField = new JTextField();
		getField(emailField, 790, 400, 200, 30, profile.getEmail());
		
		getLabel(licenseNo, "Driver License", 30, 460, 110, 30);
		licenseNoField = new JTextField();
		getField(licenseNoField, 150, 460, 200, 30, profile.getLicenseNo());
		
		getLabel(socialSecurityNumber, "Social Security Number", 380, 460, 160, 30);
		socialSecurityNumberField = new JTextField();
		getField(socialSecurityNumberField, 540, 460, 200, 30, profile.getSocialSecurityNumber());
		
		getLabel(address, "Address", 770, 460, 160, 30);
		addressField = new JTextArea();
		addressField.setText(profile.getAddress());
		addressField.setBorder(BorderFactory.createEmptyBorder());
		addressField.setBounds(840, 460, 250, 100);
		addressField.setEditable(false);
		addressField.setBackground(Color.WHITE);
		mainPanel.add(addressField);
		
	}
	
	private void viewRecordsBtnListener() {
		viewRecords.addActionListener(e ->{
			new ViewServiceRecordsFrame(homeFrame, profile.getServiceRecords());
		});
	}

	private void getLabel(JLabel labelObject, String labeltext, int x, int y, int width,
			int height) {
		labelObject = new JLabel(labeltext + " : ");
		labelObject.setForeground(new Color(247,110,17));
		labelObject.setBounds(x, y, width, height);
		mainPanel.add(labelObject);

	}

	private void getField(JTextField fieldObject, int x, int y, int width, int height, String placeholder) {
		fieldObject.setBorder(BorderFactory.createEmptyBorder());
		fieldObject.setBounds(x, y, width, height);
		fieldObject.setText(placeholder);
		fieldObject.setEditable(false);
		fieldObject.setBackground(Color.WHITE);
		mainPanel.add(fieldObject);

	}
}
