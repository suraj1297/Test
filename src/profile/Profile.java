package profile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Profile {
	
	private String heading, message, brand, model, color, year, engineNo, seatsNo, licencePlate, ownerName, telephoneNo, email,
	licenseNo, socialSecurityNumber, address , warrantyYear;
	
	private ArrayList<String[]> serviceRecords;
	
	private BufferedImage Photo;

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public String getSeatsNo() {
		return seatsNo;
	}

	public void setSeatsNo(String seatsNo) {
		this.seatsNo = seatsNo;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<String[]> getServiceRecords() {
		return serviceRecords;
	}

	public void setServiceRecords(ArrayList<String[]> serviceRecords) {
		this.serviceRecords = serviceRecords;
	}

	public String getWarrantyYear() {
		return warrantyYear;
	}

	public void setWarrantyYear(String warrantyYear) {
		this.warrantyYear = warrantyYear;
	}

	public BufferedImage getPhoto() {
		return Photo;
	}

	public void setPhoto(BufferedImage photo) {
		Photo = photo;
	}
	
	

}
