package ca.demo.databasedemo;

import android.os.Bundle;






import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import android.app.ListActivity;



public class Manual extends CreateProfile{
	
	private Spinner spinner1, spinner2, spinner3, Bmonth, Bday, Byear, Emonth, Eday, Eyear;
	private EditText distance;
	private Button btnSubmit;
	private ListAdapter adapter;
	private ListView hostList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manual);
		hostList = (ListView) findViewById(R.id.listView1);
		hostList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				Intent intent = new Intent(v.getContext(), HostDetails.class);
		        Cursor cursor = (Cursor) adapter.getItem(position);
		       // Toast.makeText(Manual.this,
            	//		"OnClickListener : " + 
            	  //              "\nSpinner 1 : "+ cursor.getInt(cursor.getColumnIndex("_id")), 
            	               // "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())
            		//		Toast.LENGTH_SHORT).show();
		        intent.putExtra("ID", cursor.getInt(cursor.getColumnIndex("_id")));
		        startActivity(intent);                 
			}
		});
					
		//ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FRUITS);
        //ListView employeeList = (ListView) findViewById(R.id.listView1);
        //employeeList.setAdapter(adapter);
        // Button click Listener 
       // addListenerOnButton();
         
 
    }
	
	public void onClick_Submit(View v) {
		//displayText("Clicked display record!");
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		Bmonth= (Spinner) findViewById(R.id.BeginMonth);
		Bday= (Spinner) findViewById(R.id.BeginDay);
		Byear= (Spinner) findViewById(R.id.BeginYear);
		Emonth= (Spinner) findViewById(R.id.EndMonth);
		Eday= (Spinner) findViewById(R.id.EndDay);
		Eyear= (Spinner) findViewById(R.id.EndYear);
		distance = (EditText) findViewById(R.id.distance);
		if(distance.getText().toString().equals(""))
		{
			distance.setText("100");
		}

		//Cursor cursor = myDb.getRow(String.valueOf(spinner1.getSelectedItem()));
		Cursor cursor = myDb.getAllRows();
		displayRecordSet(cursor);
	}
	
	// Display an entire recordset to the screen.
	@SuppressWarnings("deprecation")
	public void displayRecordSet(Cursor cursor) {
		//Store Spinner Dates
		String myFormatString = "yyyy-M-dd"; // for example
        SimpleDateFormat df = new SimpleDateFormat(myFormatString);
        String Mstart = String.valueOf(Byear.getSelectedItem())+ "-" + String.valueOf(Bmonth.getSelectedItem()) +"-"+ String.valueOf(Bday.getSelectedItem());
        int Distance = Integer.parseInt(distance.getText().toString());
        String Mend = String.valueOf(Eyear.getSelectedItem())+ "-" + String.valueOf(Emonth.getSelectedItem()) +"-"+ String.valueOf(Eday.getSelectedItem());
        //Date start = df.parse(Mstart);
		//Date end = df.parse(Mend);
        // populate the message from the cursor
		MatrixCursor matrixCursor = new MatrixCursor(new String[] { "_id", "Name", "Pets", "Clean", "Smoking", "StartDate", "EndDate" });
		// Reset cursor to start, checking to see if there's data:
		if (cursor.moveToFirst()) {
			do {
				// Process the data:
				int id = cursor.getInt(DBAdapter.COL_ROWID);
				String name = cursor.getString(DBAdapter.COL_NAME);
				String Pets = cursor.getString(DBAdapter.COL_Pets);
				String Clean = cursor.getString(DBAdapter.COL_Clean);
				String Smoking = cursor.getString(DBAdapter.COL_Smoking);
				String Type = cursor.getString(DBAdapter.COL_Type);
				int Bemonth= cursor.getInt(DBAdapter.COL_BeginMonth);
				int Beday= cursor.getInt(DBAdapter.COL_BeginDay);
				int Beyear= cursor.getInt(DBAdapter.COL_BeginYear);
				int Enmonth= cursor.getInt(DBAdapter.COL_EndMonth);
				int Enday= cursor.getInt(DBAdapter.COL_EndDay);
				int Enyear= cursor.getInt(DBAdapter.COL_EndYear);
				int dist = cursor.getInt(DBAdapter.COL_Dist);
				//String myFormatString = "yyyy-M-dd"; // for example
	            //SimpleDateFormat df = new SimpleDateFormat(myFormatString);
				String Cstart = Beyear + "-" + Bemonth + "-" + Beday;
				String Cend = Enyear + "-" + Enmonth + "-" + Enday;
				Date start;
				Date end;
				try {
					start = df.parse(Cstart);
				 		
					end = df.parse(Cend);
				}
				catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(Distance>= dist){
				if(Type.equals("Host")){
				if(Pets.equals(String.valueOf(spinner1.getSelectedItem()))|| String.valueOf(spinner1.getSelectedItem()).equals("Dont Care"))
				// Append data to the message:
					{
					if(Clean.equals(String.valueOf(spinner2.getSelectedItem()))||String.valueOf(spinner2.getSelectedItem()).equals("Dont Care"))
						{
						if(Smoking.equals(String.valueOf(spinner3.getSelectedItem())))
							{
							try{
							if(Bmonth.getSelectedItemPosition()==0 && Emonth.getSelectedItemPosition()==0){
							matrixCursor.addRow(new Object[] { id, name, Pets, Clean, Smoking, Cstart, Cend });
							}
							else if (Bmonth.getSelectedItemPosition()==0 && (df.parse(Cend).after(df.parse(Mend)) ||df.parse(Cend).equals(df.parse(Mend))))
									{
									matrixCursor.addRow(new Object[] { id, name, Pets, Clean, Smoking, Cstart, Cend });
									}
							else if (Emonth.getSelectedItemPosition()==0 && (df.parse(Cstart).before(df.parse(Mstart))||df.parse(Cstart).equals(df.parse(Mstart)) ))
									{
									matrixCursor.addRow(new Object[] { id, name, Pets, Clean, Smoking, Cstart, Cend });
									}
							else if ((df.parse(Cend).after(df.parse(Mend)) ||df.parse(Cend).equals(df.parse(Mend))) && (df.parse(Cstart).before(df.parse(Mstart))||df.parse(Cstart).equals(df.parse(Mstart)) ))
							{
							matrixCursor.addRow(new Object[] { id, name, Pets, Clean, Smoking, Cstart, Cend });
							}
								}
							catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
							}
						}
					}
				}
			} while(cursor.moveToNext());
		}
		
		// Close the cursor to avoid a resource leak.
		cursor.close();
		adapter = new SimpleCursorAdapter(
                this, 
                R.layout.host_list_item, 
                matrixCursor, 
                new String[] { "Name" , "Pets", "Clean", "Smoking", "StartDate", "EndDate"}, 
                new int[] {R.id.firstName, R.id.Pet, R.id.Clean, R.id.Smoking, R.id.StartDate, R.id.EndDate });
					
		hostList.setAdapter(adapter);
				
		
		//displayText(message);
	}
 
   
	 
     
    //get the selected dropdown list value
     
    public void addListenerOnButton() {
 
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        
        
 
        btnSubmit.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View v) {
 
            	Toast.makeText(Manual.this,
            			"OnClickListener : " + 
            	                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()), 
            	               // "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())
            				Toast.LENGTH_SHORT).show();
            }
 
        });
 
    }
 


}



