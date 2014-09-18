package ca.demo.databasedemo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


	
	
public class Wizard extends CreateProfile{
	
	private Spinner spinner1, spinner2, spinner3, Bmonth, Bday, Byear, Emonth, Eday, Eyear, smokep, petp, cleanp, distp;
	private Button btnSubmit;
	private ListAdapter adapter;
	private ListView hostList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wizard);
		hostList = (ListView) findViewById(R.id.listView1);
		hostList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				Intent intent = new Intent(v.getContext(), HostDetails.class);
		        Cursor cursor = (Cursor) adapter.getItem(position);
		        Toast.makeText(Wizard.this,
            			"OnClickListener : " + 
            	                "\nSpinner 1 : "+ cursor.getInt(cursor.getColumnIndex("_id")), 
            	               // "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())
            				Toast.LENGTH_SHORT).show();
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
		smokep = (Spinner) findViewById(R.id.smokingPreference);
		cleanp = (Spinner) findViewById(R.id.CleanPreference);
		petp = (Spinner) findViewById(R.id.petPreference);
		distp = (Spinner) findViewById(R.id.distancePreference);
		
		//Cursor cursor = myDb.getRow(String.valueOf(spinner1.getSelectedItem()));
		Cursor cursor = myDb.getAllRows();
		displayRecordSet(cursor);
	}
	
	// Display an entire recordset to the screen.
	@SuppressWarnings("deprecation")
	public void displayRecordSet(Cursor cursor) {
		int hostsSaved = 0;
		Object chosenHosts[][]= new Object[100][];
		int scores[] = new int[100];
		int score = 0;
		int lowestScoreIndex = 0;
		int smokepref = Integer.parseInt(String.valueOf(smokep.getSelectedItem()));
		int cleanpref = Integer.parseInt(String.valueOf(cleanp.getSelectedItem()));
		int petpref = Integer.parseInt(String.valueOf(petp.getSelectedItem()));
		int distpref = Integer.parseInt(String.valueOf(distp.getSelectedItem()));
		
		final EditText d = (EditText) findViewById (R.id.distance);
		String dString = d.getText().toString();
		final int selectedDistance = Integer.parseInt(dString);
		
		//Store Spinner Dates
		String myFormatString = "yyyy-M-dd"; // for example
        SimpleDateFormat df = new SimpleDateFormat(myFormatString);
        String studentStart = String.valueOf(Byear.getSelectedItem())+ "-" + String.valueOf(Bmonth.getSelectedItem()) +"-"+ String.valueOf(Bday.getSelectedItem());
        String studentEnd = String.valueOf(Eyear.getSelectedItem())+ "-" + String.valueOf(Emonth.getSelectedItem()) +"-"+ String.valueOf(Eday.getSelectedItem());
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
				int hostDistance= cursor.getInt(DBAdapter.COL_Dist);
				//String myFormatString = "yyyy-M-dd"; // for example
	            //SimpleDateFormat df = new SimpleDateFormat(myFormatString);
				String hostStart = Beyear + "-" + Bemonth + "-" + Beday;
				String hostEnd = Enyear + "-" + Enmonth + "-" + Enday;
				Date start;
				Date end;
				
				score = 0;
				try {
					start = df.parse(hostStart);
				 		
					end = df.parse(hostEnd);
				}
				catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(Type.equals("Host")){
					//pets
					if(Pets.equals(String.valueOf(spinner1.getSelectedItem()))){
						score = score+petpref;
					}
					else if(!Pets.equals(String.valueOf(spinner1.getSelectedItem()))){
						if(petpref==0) score = -9999;
						else{ score = score + 2*(11-petpref); }
					}
					else{ score = score+5;}
					
					//clean
					if(Clean.equals(String.valueOf(spinner2.getSelectedItem()))){
						score = score+cleanpref;
					}
					else if(!Clean.equals(String.valueOf(spinner2.getSelectedItem()))){
						if(cleanpref==0) score = -9999;
						else{ score = score + 2*(11-cleanpref); }
					}
					else{ score = score+5;}
					
					//smoking
					if(Smoking.equals(String.valueOf(spinner3.getSelectedItem()))){
						score = score+smokepref;
					}
					else if(!Smoking.equals(String.valueOf(spinner3.getSelectedItem()))){
						if(smokepref==0) score = -9999;
						else{ score = score + 2*(11-smokepref); }
					}
					else{ score = score+5;}
					
					if(hostDistance <= selectedDistance)
						score = score+distpref;
					else if(hostDistance > selectedDistance && distpref == 0)
						score = -9999;
					else{ score = score+2*(11-distpref); }
							
					try{
					//hostStart, hostEnd, studentStart, studentEnd
						if ((df.parse(hostStart).after(df.parse(studentStart)) && df.parse(studentEnd).after(df.parse(hostEnd)) ))
						{ //bad condition.
							score = score*4;
							if(score>0){
								chosenHosts[hostsSaved] = new Object[] { id, name, Pets, Clean, Smoking, hostStart, hostEnd };
								scores[hostsSaved] = score;
								hostsSaved++;
							}
						}
						else if ((df.parse(studentStart).after(df.parse(hostStart))||(df.parse(studentStart).equals(df.parse(hostStart)))) 
								&& (df.parse(hostEnd).equals(df.parse(studentEnd))||(df.parse(hostEnd).equals(df.parse(studentEnd)))))						
						{ //this is the good condition!
							score = score/4;
							if(score>0){
								chosenHosts[hostsSaved] = new Object[] { id, name, Pets, Clean, Smoking, hostStart, hostEnd };
								scores[hostsSaved] = score;
								hostsSaved++;
							}
						}
						else{ //whatever
							score = score*2; 
							if(score>0){
								chosenHosts[hostsSaved] = new Object[] { id, name, Pets, Clean, Smoking, hostStart, hostEnd };
								scores[hostsSaved] = score;
								hostsSaved++;
							}
						}
					}
					catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}						
			} while(cursor.moveToNext());
			
			score = 9999;
			for(int i = 0; i<hostsSaved; i++){
				for(int j = i; j<hostsSaved; j++){
					if(scores[j]<score){
						lowestScoreIndex = j;
						score = scores[j];
					}
				}
				matrixCursor.addRow(chosenHosts[lowestScoreIndex]);
				scores[lowestScoreIndex] = 10000;
				score = 9999;
			}
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
 
            	Toast.makeText(Wizard.this,
            			"OnClickListener : " + 
            	                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()), 
            	               // "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())
            				Toast.LENGTH_SHORT).show();
            }
 
        });
 
    }
 


}