package profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.github.lgooddatepicker.tableeditors.DateTableEditor;

@SuppressWarnings("serial")
public class ServiceRecordFrame extends JFrame {

	ServiceRecordData record;
	JFrame frame = new JFrame();

	JFrame formFrame;
	JPanel btnPanel;
	JButton addBtn, saveBtn;
	DefaultTableModel tableModel;
	JTable serviceTable;
	JScrollPane scrollPane;

	UtilDateModel yearField = new UtilDateModel();
	Properties p = new Properties();
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;

	int rowEditedNo = -1;
	int columnEditedNo = -1;

	ServiceRecordFrame(ServiceRecordData record, JFrame formFrame) {
		this.record = record;
		this.formFrame = formFrame;

		frameBase();

		addButton();
		addTable();
		monitorClose();

	}

	public void frameBase() {
		frame.setTitle("Add Service Record Data");
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		formFrame.setEnabled(false);
		frame.setLayout(new BorderLayout());
	}

	public void addButton() {
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setPreferredSize(new Dimension(600, 50));

		saveBtn = new JButton("Save");
		btnPanel.add(saveBtn);

		addBtn = new JButton("Add Row");
		btnPanel.add(addBtn);
		frame.add(btnPanel, BorderLayout.PAGE_START);

		addBtn.addActionListener(e -> {
			addNewRowAfterValidation();
		});

		saveBtn.addActionListener(e -> {
			saveRecords();
		});

	}

	private void addNewRowAfterValidation() {

		boolean areRowsvalid = validatePreviousRows();

		if (areRowsvalid) {
			addNewRow();
		}
	}

	private boolean validateLastRowSaving() {

		String vl1 = String.valueOf(serviceTable.getValueAt(serviceTable.getRowCount() - 1, 0));
		String vl2 = String.valueOf(serviceTable.getValueAt(serviceTable.getRowCount() - 1, 1));
		String vl3 = String.valueOf(serviceTable.getValueAt(serviceTable.getRowCount() - 1, 2));

		boolean isDateSelected = false;
		boolean isMileageSelected = false;
		boolean isEngineSelected = false;
		boolean isIntegerValue = validateIsInteger(serviceTable.getRowCount() - 1);

		if (!vl1.isEmpty() && !vl1.equals("null")) {
			isDateSelected = true;
		}
		if (!vl2.isEmpty() && !vl2.equals("null")) {
			isMileageSelected = true;
		}
		if (!vl3.isEmpty() && !vl3.equals("null")) {
			isEngineSelected = true;
		}

		if ((isDateSelected && isMileageSelected && isEngineSelected && isIntegerValue)

				||

				(!isDateSelected && !isMileageSelected && !isEngineSelected)

		) {
			return true;

		}
		return false;

	}

	private void saveRecords() {

		String v1 = String.valueOf(serviceTable.getValueAt(0, 0));
		String v2 = String.valueOf(serviceTable.getValueAt(0, 1));
		String v3 = String.valueOf(serviceTable.getValueAt(0, 2));

		if (serviceTable.getRowCount() == 1 && (v1.equals("null") || v1.isEmpty())
				&& (v2.equals("null") || v2.isEmpty()) && (v3.equals("null") || v3.isEmpty())) {
			JOptionPane.showMessageDialog(frame, "No Data to Save!", "WARNING", JOptionPane.INFORMATION_MESSAGE);
		} else {

			boolean allValid = validateAllRows();

			if (allValid) {

				boolean lastRowValid = validateLastRowSaving();

				if (lastRowValid) {

					record.clearArrayList();

					for (int i = 0; i < serviceTable.getRowCount(); i++) {

						if (serviceTable.getValueAt(i, 0).toString().isEmpty()
								|| serviceTable.getValueAt(i, 1).toString().isEmpty()
								|| serviceTable.getValueAt(i, 2).toString().isEmpty()

						) {

							continue;
						}

						DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate datetime = null;
						try {
							datetime = LocalDate.parse(serviceTable.getValueAt(i, 0).toString(), pattern);
						} catch (DateTimeParseException e) {
							System.out.println(e);
						}

						Date selectedDate = java.util.Date
								.from(datetime.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
						SimpleDateFormat myFormat = new SimpleDateFormat("dd MMMM yyyy");

						record.setRecord(myFormat.format(selectedDate), serviceTable.getValueAt(i, 1).toString(),
								serviceTable.getValueAt(i, 2).toString());
					}

//					record.setIndex(serviceTable.getRowCount());

					JOptionPane.showMessageDialog(frame, "Data was saved successfully", "INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);

					formFrame.setEnabled(true);
					frame.setVisible(true);
					frame.dispose();

				} else {

					JOptionPane.showMessageDialog(frame,
							"Please fill all the empty fields in last row carefully! Total Miles can have integer values only!",
							"ERROR", JOptionPane.ERROR_MESSAGE);

				}

			} else {
				JOptionPane.showMessageDialog(frame,
						"Please fill all the empty fields carefully! Total Miles can have integer values only!",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	private boolean validateAllRows() {

		int rows = serviceTable.getRowCount() - 1;
		boolean isValid = true;

		for (int i = 0; i < rows; i++) {

			String v1 = String.valueOf(serviceTable.getValueAt(i, 0));
			String v2 = String.valueOf(serviceTable.getValueAt(i, 1));
			String v3 = String.valueOf(serviceTable.getValueAt(i, 2));

			if (v1.equals("null") || v1.isEmpty() || v2.equals("null") || v2.isEmpty() || v3.equals("null")
					|| v3.isEmpty()) {
				isValid = false;
			}

			if (!v3.equals("null") && !v3.isEmpty() && !validateIsInteger(i)) {
				serviceTable.setValueAt("", i, 1);
				isValid = false;
			}
		}

		return isValid;

	}

	private boolean validateIsInteger(int numberOfRow) {
		char[] numbers = String.valueOf(serviceTable.getValueAt(numberOfRow, 1)).toCharArray();

		if (String.valueOf(serviceTable.getValueAt(numberOfRow, 1)).isEmpty()) {
			return false;
		}

		for (int i = 0; i < numbers.length; i++) {

			char c = numbers[i];
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	private boolean validatePreviousRows() {

		int rowNumber = serviceTable.getRowCount() - 1;

		boolean previousValid = validateAllRows();

		boolean isDateSelected = false;
		boolean isMileageSelected = false;
		boolean isEngineSelected = false;
		String message = "Please enter";

		String v1 = String.valueOf(serviceTable.getValueAt(rowNumber, 0));
		String v2 = String.valueOf(serviceTable.getValueAt(rowNumber, 1));
		String v3 = String.valueOf(serviceTable.getValueAt(rowNumber, 2));

		if (!v1.isEmpty() && !v1.equals("null")) {
			isDateSelected = true;
		}
		if (!v2.isEmpty() && !v2.equals("null")) {
			isMileageSelected = true;
		}
		if (!v3.isEmpty() && !v2.equals("null")) {
			isEngineSelected = true;
		}

		if (!isDateSelected || !isEngineSelected || !isEngineSelected) {

			if (!isDateSelected) {
				message += " 'Date'";
			}
			if (!isMileageSelected) {
				message += "  'Total Miles'";
			}
			if (!isEngineSelected) {
				message += "  'Engine Check'";
			}
			message += " in last row! Total Miles can have integers only!";

			if (!previousValid) {
				JOptionPane.showMessageDialog(frame, message + " Also Please fill all empty fields in table!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, message, "ERROR", JOptionPane.ERROR_MESSAGE);
			}

			return false;

		}

		if (!validateIsInteger(rowNumber)) {

			if (!previousValid) {
				JOptionPane.showMessageDialog(frame,
						"'Total Miles' Column can have only integer values! Also Please fill all empty fields in table!",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(frame, "In last row 'Total Miles' Column can have only integer values!",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}

		return true;

	}

	public void addTable() {
		tableModel = new DefaultTableModel();
		serviceTable = new JTable(tableModel);
		tableModel.addColumn("Date");
		tableModel.addColumn("Total Miles");
		tableModel.addColumn("Engine Check");
		serviceTable.setBackground(Color.WHITE);
		serviceTable.setAutoCreateRowSorter(true);
		serviceTable.setCellSelectionEnabled(true);

		ArrayList<String[]> data = record.getRecord();
		if (data != null && data.size() > 0) {
			populateTableData(data);
		}
		addNewRow();
		serviceTable.setRowHeight(serviceTable.getRowHeight() + 10);
		serviceTable.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(serviceTable);
		frame.add(scrollPane, BorderLayout.CENTER);
	}

	private void populateTableData(ArrayList<String[]> data) {

		for (int i = 0; i < data.size(); i++) {

			String[] arr = data.get(i);

			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addItem("Yes");
			comboBox.addItem("No");
			

			DateTimeFormatter pattern;
			LocalDate datetime;
			try {
				pattern = DateTimeFormatter.ofPattern("dd MMMM yyyy");
				datetime = LocalDate.parse(arr[0], pattern);
				Date selectedDate = java.util.Date.from(datetime.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

				arr[0] = myFormat.format(selectedDate);
			} catch (DateTimeParseException e) {
				System.out.println(e);
			}


			TableColumn column1 = serviceTable.getColumnModel().getColumn(0);
			column1.setCellEditor(new DateTableEditor());

			TableColumn column3 = serviceTable.getColumnModel().getColumn(2);
			column3.setCellEditor(new DefaultCellEditor(comboBox));

			tableModel.insertRow(tableModel.getRowCount(), arr);
		}

	}

	public void addNewRow() {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Yes");
		comboBox.addItem("No");

		tableModel.insertRow(tableModel.getRowCount(), new Object[] { "", "", "" });
		TableColumn column1 = serviceTable.getColumnModel().getColumn(0);
		column1.setCellEditor(new DateTableEditor());

		TableColumn column3 = serviceTable.getColumnModel().getColumn(2);
		column3.setCellEditor(new DefaultCellEditor(comboBox));
	}

	public JFrame getFrame() {
		return frame;
	}

	public void monitorClose() {
		try {
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {

//					record.setRecordUsingArrayList(record.getRecord());

					formFrame.setEnabled(true);
					formFrame.setVisible(true);
					frame.dispose();
				}
			});

		} catch (DateTimeParseException e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

}
