package profile;

import java.util.ArrayList;

public class ServiceRecordData {
	
	private ArrayList<String[]> record = new ArrayList<String[]>();
	
	
	public ArrayList<String[]> getRecord() {
		return record;
	}

	public void setRecord(String date, String mileage, String engineCheck) {
		
		String[] data = {date, mileage, engineCheck};
		this.record.add(data);
	}
	
	public void setRecordUsingArrayList(ArrayList<String[]> record) {
		
		clearArrayList();
		this.record = record;
	}
	
	
	public void clearArrayList() {
		record.clear();
	}


}
