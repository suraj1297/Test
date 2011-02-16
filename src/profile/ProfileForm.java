package profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import CRUD.Create;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class ProfileForm extends JFrame {

	String specialCharacters = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
	String emailCharacters = "!#$%&*()'+,-/:;<=>?[]^_`{|}";

	JFrame homeFrame;

	Profile profile = new Profile();
	Create createRecord;

	ServiceRecordData record = new ServiceRecordData();

	ServiceRecordFrame recordFrame;

	JLabel message = new JLabel("All fields in form are mandatory. For Car image only .jpg and .png files allowed.");

	JLabel heading, brand, model, color, year, engineNo, seatsNo, licencePlate, ownerName, telephoneNo, email,
			licenseNo, socialSecurityNumber, address, serviceRecords, warrantyYear, Photo, imageName;

	JTextField brandField, modelField, colorField, engineNoField, seatsNoField, licencePlateField, ownerNameField,
			telephoneNoField, emailField, licenseNoField, socialSecurityNumberField, addressField, warrantyYearField, yearField;

	JButton addServiceBtn;
	
//	JSpinner ;

//	UtilDateModel yearField = new UtilDateModel();
//	Properties p = new Properties();
//	JDatePanelImpl datePanel;
//	JDatePickerImpl datePicker;

	JButton submitButton, PhotoField;
	JFileChooser image;
	String finalImage = "";
	BufferedImage uploadedImage = null;

	Panel headingPanel = new Panel();
	Panel formPanel = new Panel();
	Panel formPanel1 = new Panel();
	Panel formPanel2 = new Panel();

	JFrame formFrame = new JFrame();

	ProfileForm(JFrame homeFrame) {

		this.homeFrame = homeFrame;

		// Basic Setup
		formFrame.setTitle("Car Profile Form");
		formFrame.setVisible(true);
		homeFrame.setEnabled(false);
		formFrame.setAlwaysOnTop(true);
		formFrame.setSize(1000, 600);
		formFrame.setLayout(new BorderLayout());

		// Heading Panel
		headingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		formFrame.add(headingPanel, BorderLayout.PAGE_START);

		heading = new JLabel("Car Profile Form");
		headingPanel.add(heading);

		// Form Outer panel
		formPanel.setLayout(new GridLayout(1, 2));
		formFrame.add(formPanel, BorderLayout.CENTER);

		// Left form panel
		formPanel1.setLayout(null);
		formPanel.add(formPanel1);

		// Right form panel
		formPanel2.setLayout(null);
		formPanel.add(formPanel2);

		// adding all form input fields
		addFormElements();

		// Footer Panel
		Panel footerPanel = new Panel();
		footerPanel.setPreferredSize(new Dimension(1000, 120));
		footerPanel.setLayout(null);
		formFrame.add(footerPanel, BorderLayout.PAGE_END);

		submitButton = new JButton("Submit");
		submitButton.setBounds(40, 20, 100, 30);

		message.setBounds(160, 20, 500, 30);
		message.setForeground(new Color(255, 0, 0));
		footerPanel.add(submitButton);
		footerPanel.add(message);

		// Listen for changes in the text and validate
		validation();

		// afterFocusListener
		validateAfterFocusLost();

		// add Listener to service record button

		serviceRecordListener();

		// adding listener to submit button
//		submitButtonListener();
		submitButton.addActionListener(e -> {
			profile.setPhoto(uploadedImage);
			new Homepage(profile);
			try {
				createRecord = new Create(profile);
				
			} catch (SAXException | IOException | TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			formFrame.setEnabled(true);
			homeFrame.dispose();
			formFrame.dispose();
		});

		formFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				homeFrame.setEnabled(true);
				formFrame.dispose();

			}
		});

	}

	private void serviceRecordListener() {

		addServiceBtn.addActionListener(e -> {
			recordFrame = new ServiceRecordFrame(record, formFrame);
		});

	}

	private void validateAfterFocusLost() {

		brandField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = brandField.getText();
				if (value.length() > 0 && value.length() < 2 && !e.isTemporary()) {
					dialogMessage("Error: Brand can have alphabets only and 15 characters maximum and 2 minimum");
					brandField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = brandField.getText();
				if (value.length() > 0 && (Character.isDigit(value.charAt(value.length() - 1))
						|| specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
						|| value.length() > 15)) {
					brandField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		modelField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = modelField.getText();
				if (value.length() > 0 && value.length() < 2 && !e.isTemporary()) {
					dialogMessage("Error: Model can have alphabets only and 15 characters maximum and 2 minimum");
					modelField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = modelField.getText();
				if (value.length() > 0 && (Character.isDigit(value.charAt(value.length() - 1))
						|| specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
						|| value.length() > 15)) {
					modelField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		colorField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = colorField.getText();
				if (value.length() > 0 && value.length() < 2 && !e.isTemporary()) {
					dialogMessage("Error: Color can have alphabets only and 10 characters maximum and 2 minimum");
					colorField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = colorField.getText();
				if (value.length() > 0 && (Character.isDigit(value.charAt(value.length() - 1))
						|| specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
						|| value.length() > 10)) {
					colorField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		engineNoField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = engineNoField.getText();
				boolean isvalid = hasIntAndAlphabet(value);
				if (value.length() > 0 && !e.isTemporary() && (value.length() != 17 || !isvalid)) {
					dialogMessage("Error: Engine No must have 17 characters which includes alphabets and numbers both");
					engineNoField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = engineNoField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| value.length() > 17)) {
					engineNoField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		seatsNoField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = seatsNoField.getText();
				if (value.length() > 0 && (!Character.isDigit(value.charAt(value.length() - 1))
						|| specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
						|| value.length() > 1)) {
					seatsNoField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		licencePlateField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {

				String value = licencePlateField.getText();
				boolean isvalid = hasIntAndAlphabet(value);

				if (value.length() > 0 && !e.isTemporary() && (value.length() < 5 || !isvalid)) {

					dialogMessage(
							"Error: License plates must have atleast 5 and maximum 7 characters which includes alphabets and numbers both");
					licencePlateField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = licencePlateField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| value.length() > 7)) {
					licencePlateField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		ownerNameField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = ownerNameField.getText();
				if (value.length() > 0 && value.length() < 2 && !e.isTemporary()) {
					dialogMessage("Error: Brand can have alphabets only and 15 characters maximum and 2 minimum");
					ownerNameField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = ownerNameField.getText();
				if (value.length() > 0 && (Character.isDigit(value.charAt(value.length() - 1))
						|| specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
						|| value.length() > 15)) {
					ownerNameField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		telephoneNoField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = telephoneNoField.getText();
				if (value.length() > 0 && !e.isTemporary() && value.length() != 10) {
					dialogMessage("Error: Owner Telephone numbers can only contain 10 digit number");
					telephoneNoField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = telephoneNoField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| !Character.isDigit(value.charAt(value.length() - 1)) || value.length() > 10)) {
					telephoneNoField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		licenseNoField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = licenseNoField.getText();
				if (value.length() > 0 && value.length() < 6 && !e.isTemporary()) {
					dialogMessage(
							"Error: Owner Driver License can contain 6 to 12 characters which can include number and alphabets");
					licenseNoField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = licenseNoField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| value.length() > 12)) {
					licenseNoField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		socialSecurityNumberField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = socialSecurityNumberField.getText();
				if (value.length() > 0 && value.length() != 9 && !e.isTemporary()) {
					dialogMessage("Error: Owner Social Security Number must be 9 digit number");
					socialSecurityNumberField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = socialSecurityNumberField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| !Character.isDigit(value.charAt(value.length() - 1)) || value.length() > 9)) {
					socialSecurityNumberField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		addressField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = addressField.getText();
				if (value.length() > 0 && value.length() < 5 && !e.isTemporary()) {
					dialogMessage("Error: Owner Address must be 5 to 150 characters long");
					addressField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = addressField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| value.length() > 150)) {
					addressField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		warrantyYearField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = warrantyYearField.getText();

				LocalDate current_date = LocalDate.now();
				int current_Year = current_date.getYear();

				if (value.length() > 0 && !e.isTemporary() && value.length() != 4) {

					dialogMessage("Error: Warranty Year must be 4 digit");
					warrantyYearField.setText("");
				} else if (value.length() > 0 && !e.isTemporary()) {
					int userYear = Integer.parseInt(value);
					if (userYear < current_Year) {
						dialogMessage("Error: Warranty Year must greater than or equal to current year");
						warrantyYearField.setText("");
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = warrantyYearField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| !Character.isDigit(value.charAt(value.length() - 1)) || value.length() > 4)) {
					warrantyYearField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		emailField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = emailField.getText();

				boolean isvalid = validateEmailAfterFocus(value);

				if (value.length() > 0 && !e.isTemporary() && !isvalid) {
					dialogMessage("Error: Please enter correct email id.");
					emailField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = emailField.getText();
				if (value.length() > 0
						&& (emailCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| value.length() > 25)) {
					emailField.setText(value.substring(0, value.length() - 1));
				}
			}
		});
		
		yearField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String value = yearField.getText();
				if (value.length() > 0 && !e.isTemporary() && (Integer.parseInt(value) < 1900 || Integer.parseInt(value) > 3000)) {
					dialogMessage("Error: Year can be between 1900 and 3000.");
					yearField.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				String value = yearField.getText();
				if (value.length() > 0
						&& (specialCharacters.contains(Character.toString(value.charAt(value.length() - 1)))
								|| !Character.isDigit(value.charAt(value.length() - 1)) || value.length() > 4)) {
					yearField.setText(value.substring(0, value.length() - 1));
				}
			}
		});

		
	}

	private boolean validateEmailAfterFocus(String value) {

		int atCount = 0;
		int dotCount = 0;
		int atIndex = 0;
		int dotIndex = 0;

		char[] chars = value.toCharArray();

		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (Character.toString(c).equals("@")) {
				atCount += 1;
				atIndex = i;
			}
			if (Character.toString(c).equals(".")) {
				dotCount += 1;
				dotIndex = i;
			}

			if (emailCharacters.contains(Character.toString(c))) {
				return false;
			}

		}

		if (atCount != 1 || dotCount < 1) {
			return false;
		}

		String valueBetweenAtAndDot = value.substring(atIndex + 1, dotIndex);

		String charsAfterDot = value.substring(dotIndex + 1);

		if (valueBetweenAtAndDot.length() < 1 || charsAfterDot.length() < 1) {
			return false;
		}

		return true;

	}

	private boolean hasIntAndAlphabet(String value) {

		boolean hasInt = false;
		boolean hasAlphabet = false;

		char[] chars = value.toLowerCase().toCharArray();

		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (specialCharacters.contains(Character.toString(c))) {
				return false;
			}

			if (Character.isDigit(c)) {
				hasInt = true;
			} else if (!specialCharacters.contains(Character.toString(c))) {
				hasAlphabet = true;
			}

		}

		if (hasInt && hasAlphabet) {
			return true;
		}
		return false;

	}

	private void validateString(JTextField fieldObject, String messageText, String validateField) {

		fieldObject.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				warn();
			}

			public void removeUpdate(DocumentEvent e) {
				warn();

			}

			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			public void warn() {

				String value = fieldObject.getText();

				switch (validateField) {
				case "brand": {

					validateName(value, messageText, 15);
					break;
				}
				case "color": {

					validateName(value, messageText, 10);
					break;
				}
				case "engineNo": {

					validateEngineNo(value);
					break;
				}
				case "seatsNo": {

					validateSeatsNo(value, "Seats Number");
					break;
				}
				case "licensePlate": {

					validateLicensePlate(value);
					break;
				}
				case "telephone": {

					validateTelephone(value);
					break;
				}
				case "licenseNo": {

					validateLicenseNo(value);
					break;
				}
				case "ssn": {

					validateSSN(value);
					break;
				}
				case "address": {

					validateAddress(value);
					break;
				}
				case "serviceRecords": {

					validateSeatsNo(value, "Service Records");
					break;
				}

				case "warranty": {

					validateWarranty(value);
					break;
				}
				case "email": {

					validateEmail(value);
					break;
				}
				case "year": {

					validateYear(value);
					break;
				}

				default:
					throw new IllegalArgumentException("Unexpected value: " + validateField);
				}

			}
		});
	}

	private void validateYear(String value) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || !Character.isDigit(c) || value.length() > 4) {
				dialogMessage("Error: Year can be between 1900 and 3000.");
				break;
			}

		}
		
	}

	private void validateEmail(String value) {

		char[] chars = value.toCharArray();

		for (char c : chars) {

			if (emailCharacters.contains(Character.toString(c)) || value.length() > 25) {
				dialogMessage("Error: Email can have only 25 characters which can include alphabets, numbers, @ and .");
				break;
			}
		}

	}

	private void validateWarranty(String value) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || !Character.isDigit(c) || value.length() > 4) {
				dialogMessage("Error: Warranty Year must be 4 digit and greater than current year");
				break;
			}

		}

	}

	private void validateAddress(String value) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || value.length() > 150) {
				dialogMessage("Error: Owner Address must be 5 to 150 characters long");
				break;
			}

		}

	}

	private void validateSSN(String value) {

		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || !Character.isDigit(c) || value.length() > 9) {
				dialogMessage("Error: Owner Social Security Number must be 9 digit number");
				break;
			}

		}
	}

	private void validateLicenseNo(String value) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || value.length() > 12) {
				dialogMessage(
						"Error: Owner Driver License can contain 6 to 12 characters which can include number and alphabets");
				break;
			}

		}

	}

	private void validateTelephone(String value) {

		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];
			if (specialCharacters.contains(Character.toString(c)) || !Character.isDigit(c) || value.length() > 10) {
				dialogMessage("Error: Owner Telephone numbers can only contain 10 digit number");
				break;
			}

		}

	}

	private void validateLicensePlate(String value) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || value.length() > 7) {
				dialogMessage(
						"Error: License plates must have atleast 5 and maximum 7 characters which includes alphabets and numbers both");
				break;
			}
		}

	}

	private void validateSeatsNo(String value, String fieldName) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || !Character.isDigit(c) || value.length() > 1) {
				dialogMessage("Error: " + fieldName + " can be one digit number only");
				break;
			}
		}

	}

	private void validateEngineNo(String value) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];

			if (specialCharacters.contains(Character.toString(c)) || value.length() > 17) {
				dialogMessage("Error: Engine No must have 17 characters which includes alphabets and numbers only");
				break;
			}
		}
	}

	private void validateName(String value, String messageText, int max) {
		char[] chars = value.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			char c = chars[i];
			if (specialCharacters.contains(Character.toString(c)) || Character.isDigit(c) || value.length() > max) {
				dialogMessage("Error: " + messageText + " can have only alphabets and " + max
						+ " characters maximum and 2 minimum");
				break;
			}
		}
	}

	private void validation() {

		validateString(brandField, "Brand", "brand");
		validateString(modelField, "Model", "brand");
		validateString(colorField, "Color", "color");
		validateString(engineNoField, "Engine Number", "engineNo");
		validateString(seatsNoField, "Seats Number", "seatsNo");
		validateString(licencePlateField, "License Plates", "licensePlate");
		validateString(ownerNameField, "Owner Name", "brand");
		validateString(telephoneNoField, "Owner Telephone Number", "telephone");
		validateString(licenseNoField, "Owner Driver License", "licenseNo");
		validateString(socialSecurityNumberField, "Social Security Number", "ssn");
		validateString(addressField, "Owner Address", "address");
//		validateString(serviceRecordsField, "Service Records", "serviceRecords");
		validateString(warrantyYearField, "Warranty Year", "warranty");
		validateString(emailField, "Email", "email");
		validateString(yearField, "Year", "year");
	}

	public void addFormElements() {

		getLabel(brand, "Brand", formPanel1, 40, 20, 110, 20);
		brandField = new JTextField();
		getField(brandField, 170, 20, 200, 25, formPanel1);

		getLabel(model, "Model", formPanel1, 40, 60, 110, 20);
		modelField = new JTextField();
		getField(modelField, 170, 60, 200, 25, formPanel1);

		getLabel(color, "Color", formPanel1, 40, 100, 110, 20);
		colorField = new JTextField();
		getField(colorField, 170, 100, 200, 25, formPanel1);

		getLabel(year, "Year", formPanel1, 40, 140, 110, 20);
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		datePanel = new JDatePanelImpl(yearField, p);
//		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		datePicker.setBorder(BorderFactory.createEmptyBorder());
//		datePicker.setBounds(170, 140, 200, 25);
		
		yearField = new JTextField();
		getField(yearField, 170, 140, 200, 25, formPanel1);
//		yearField.setBounds(170, 140, 200, 25);
//		formPanel1.add(yearField);

		getLabel(engineNo, "Engine Number", formPanel1, 40, 180, 110, 20);
		engineNoField = new JTextField();
		getField(engineNoField, 170, 180, 200, 25, formPanel1);

		getLabel(seatsNo, "Seats Number", formPanel1, 40, 220, 110, 20);
		seatsNoField = new JTextField();
		getField(seatsNoField, 170, 220, 200, 25, formPanel1);

		getLabel(licencePlate, "License Plates", formPanel1, 40, 260, 110, 20);
		licencePlateField = new JTextField();
		getField(licencePlateField, 170, 260, 200, 25, formPanel1);

		getLabel(ownerName, "Owner Name", formPanel1, 40, 300, 110, 20);
		ownerNameField = new JTextField();
		getField(ownerNameField, 170, 300, 200, 25, formPanel1);

		getLabel(telephoneNo, "Owner Telephone Numbers", formPanel2, 40, 20, 180, 20);
		telephoneNoField = new JTextField();
		getField(telephoneNoField, 220, 20, 200, 25, formPanel2);

		getLabel(email, "Owner Email Addresses", formPanel2, 40, 60, 170, 20);
		emailField = new JTextField();
		getField(emailField, 210, 60, 200, 25, formPanel2);

		getLabel(licenseNo, "Owner Driver License", formPanel2, 40, 100, 170, 20);
		licenseNoField = new JTextField();
		getField(licenseNoField, 210, 100, 200, 25, formPanel2);

		getLabel(socialSecurityNumber, "Owner Social Security Number", formPanel2, 40, 140, 200, 20);
		socialSecurityNumberField = new JTextField();
		getField(socialSecurityNumberField, 240, 140, 200, 25, formPanel2);

		getLabel(address, "Owner Address", formPanel2, 40, 180, 110, 20);
		addressField = new JTextField();
		getField(addressField, 170, 180, 200, 25, formPanel2);

		getLabel(serviceRecords, "Service Records", formPanel2, 40, 220, 110, 20);
		addServiceBtn = new JButton("Add Service Records");
		addServiceBtn.setBorder(BorderFactory.createEmptyBorder());
		addServiceBtn.setBounds(170, 220, 150, 30);
		formPanel2.add(addServiceBtn);

		getLabel(warrantyYear, "Warranty Year", formPanel2, 40, 260, 110, 20);
		warrantyYearField = new JTextField();
		getField(warrantyYearField, 170, 260, 200, 25, formPanel2);

		getLabel(Photo, "Photo", formPanel2, 40, 300, 110, 20);
		PhotoField = new JButton("Upload Image");
		image = new JFileChooser();
		imageName = new JLabel("");
		imageUploadListener();
		PhotoField.setBorder(BorderFactory.createEmptyBorder());
		PhotoField.setBounds(170, 300, 90, 40);
		imageName.setBounds(270, 300, 300, 40);
		imageName.setForeground(new Color(247, 173, 25));
		formPanel2.add(PhotoField);
		formPanel2.add(imageName);
	}

	private void imageUploadListener() {
		PhotoField.addActionListener(e -> {
			int returnVal = image.showOpenDialog(formFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = image.getSelectedFile();

				String picName = file.getName();
				String extension = picName.substring(picName.length() - 3);
				if (extension.equals("jpg") || extension.equals("png")) {
					finalImage = file.getPath();
					try {
						uploadedImage = ImageIO.read(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					imageName.setText(picName);
				} else {
					dialogMessage("Error: For Car image only .jpg and .png files allowed");
				}
			}
		});
	}

	private void submitButtonListener() {

		submitButton.addActionListener(e -> {

			if ((record.getRecord() == null || record.getRecord().size() == 0) && finalImage.length() < 5) {
				dialogMessage("Error: please enter service record details and upload image");
			} else if (record.getRecord() == null || record.getRecord().size() == 0) {
				dialogMessage("Error: please enter service record details");
			} else if (finalImage.length() < 5) {
				dialogMessage("Error: please upload image");
			} else {

				profile.setBrand(brandField.getText());
				profile.setModel(modelField.getText());
				profile.setColor(colorField.getText());
				profile.setYear(yearField.getText());

				profile.setEngineNo(engineNoField.getText());
				profile.setSeatsNo(seatsNoField.getText());
				profile.setLicencePlate(licencePlateField.getText());
				profile.setOwnerName(ownerNameField.getText());
				profile.setTelephoneNo(telephoneNoField.getText());
				profile.setEmail(emailField.getText());
				profile.setLicenseNo(licenseNoField.getText());
				profile.setSocialSecurityNumber(socialSecurityNumberField.getText());
				profile.setAddress(addressField.getText());
				profile.setServiceRecords(record.getRecord());
				profile.setWarrantyYear(warrantyYearField.getText());
				profile.setPhoto(uploadedImage);

				boolean allFieldsValid = validateAllFields(profile);

				if (allFieldsValid) {
					new Homepage(profile);
					try {
						createRecord = new Create(profile);
						
					} catch (SAXException | IOException | TransformerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					formFrame.setEnabled(true);
					homeFrame.dispose();
					formFrame.dispose();
				} else {
					dialogMessage("Error: Please fill all the details correctly");
				}

			}

		});

	}

	private boolean validateAllFields(Profile profile2) {

		if (profile2.getBrand().length() > 0 && profile2.getModel().length() > 0 && profile2.getColor().length() > 0
				&& profile2.getYear().length() > 0 && profile2.getEngineNo().length() > 0
				&& profile2.getSeatsNo().length() > 0 && profile2.getLicencePlate().length() > 0
				&& profile2.getOwnerName().length() > 0 && profile2.getTelephoneNo().length() > 0
				&& profile2.getEmail().length() > 0 && profile2.getLicenseNo().length() > 0
				&& profile2.getSocialSecurityNumber().length() > 0 && profile2.getAddress().length() > 0
				&& profile2.getServiceRecords() != null && profile2.getServiceRecords().size() > 0
				&& profile2.getWarrantyYear().length() > 0 && profile2.getPhoto() != null) {
			return true;
		}

		return false;
	}

	private void getLabel(JLabel labelObject, String labeltext, Panel panelObject, int x, int y, int width,
			int height) {
		labelObject = new JLabel(labeltext + " : ");
		labelObject.setBounds(x, y, width, height);
		panelObject.add(labelObject);

	}

	private void getField(JTextField fieldObject, int x, int y, int width, int height, Panel panelObject) {
		fieldObject.setBorder(BorderFactory.createEmptyBorder());
		fieldObject.setBounds(x, y, width, height);
		panelObject.add(fieldObject);

	}

	private void dialogMessage(String message) {
		JOptionPane.showMessageDialog(formFrame, message, "Error Message", JOptionPane.ERROR_MESSAGE);
	}

}
