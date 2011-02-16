package profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ViewServiceRecordsFrame extends JFrame{
	
	JFrame parentFrame;
	
	JFrame mainFrame;
	DefaultTableModel tableModel;
	JTable serviceTable;
	JScrollPane servicePane;
	
	ArrayList<String[]> data;
	
	ViewServiceRecordsFrame(JFrame parentFrame, ArrayList<String[]> data){
		
		this.parentFrame = parentFrame;
		this.data = data;
		
		
		mainFrame = new JFrame();
		basicFrameSetup();
		tableView();

		

	}
	
	
	private void tableView() {
		
		tableModel = new DefaultTableModel();
		serviceTable = new JTable(tableModel);
		serviceTable.setBackground(Color.WHITE);
		serviceTable.setRowHeight(serviceTable.getRowHeight() + 5);
		serviceTable.setFillsViewportHeight(true);
		tableModel.addColumn("Date");
		tableModel.addColumn("Total Miles");
		tableModel.addColumn("Engine Check");
		
		if(data != null && data.size() > 0) {
			populateTableData();
		}
		
		
	    
//	    servicePane.setBounds(150, 230, 370, 101);
//	    serviceTable.getColumnModel().getColumn(0).setPreferredWidth(70);
//	    serviceTable.getColumnModel().getColumn(1).setPreferredWidth(300);
	    
		servicePane = new JScrollPane(serviceTable);
	    serviceTable.setEnabled(false);
	    mainFrame.add(servicePane, BorderLayout.CENTER);
	    monitorClose();
		
	}
	
	private void populateTableData() {
		
		for(int i = 0; i < data.size(); i++) {
			tableModel.insertRow(tableModel.getRowCount(), data.get(i));
		}
		
	}
	
	
	private void basicFrameSetup() {
		// Basic Setup
		mainFrame.setTitle("Car Service Records");
		mainFrame.setVisible(true);
		parentFrame.setEnabled(false);
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setSize(600, 600);
		mainFrame.setLayout(new BorderLayout());
	
	}
	
	public void monitorClose() {
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				parentFrame.setEnabled(true);
				parentFrame.setVisible(true);
				mainFrame.dispose();
			}
		});
	}
	
	
}
