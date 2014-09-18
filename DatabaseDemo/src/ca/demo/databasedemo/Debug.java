package ca.demo.databasedemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;

public class Debug extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug);
	}
	
	DatePicker myDatePicker = (DatePicker) findViewById(R.id.datePicker);
	//String selectedDate = DateFormat.get.getDateInstance().format(myDatePicker.getCalendarView().getDate());
	int day = myDatePicker.getYear();
	int month = myDatePicker.getMonth();
	int year= myDatePicker.getDayOfMonth();
}
