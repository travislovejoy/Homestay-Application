package ca.demo.databasedemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class HostDetails extends Manual{

	Cursor cursor;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.hostdetails);
	        Bundle extras = getIntent().getExtras();
	        int hostId = extras.getInt("ID");
	        String Id = Integer.toString(hostId);
	        cursor = myDb.getRow(Id);
	        String name = cursor.getString(DBAdapter.COL_NAME);
	        String age = cursor.getString(DBAdapter.COL_Age);
	        String clean = cursor.getString(DBAdapter.COL_Clean);
	        String smoking = cursor.getString(DBAdapter.COL_Smoking);
	        String Bmonth = cursor.getString(DBAdapter.COL_BeginMonth);
	        String Bday = cursor.getString(DBAdapter.COL_BeginDay);
	        String Byear = cursor.getString(DBAdapter.COL_BeginYear);
	        String Emonth = cursor.getString(DBAdapter.COL_EndMonth);
	        String Eday = cursor.getString(DBAdapter.COL_EndDay);
	        String Eyear = cursor.getString(DBAdapter.COL_EndYear);
	        String pets = cursor.getString(DBAdapter.COL_Pets);
	        String dist = cursor.getString(DBAdapter.COL_Dist);
	        
	      TextView Name=new TextView(this); 
	      TextView Age=new TextView(this); 
	      TextView Pets=new TextView(this); 
	      TextView Cleanliness=new TextView(this); 
	      TextView Smoking=new TextView(this);
	      TextView BeginDate=new TextView(this);
	      TextView EndDate=new TextView(this);
	      TextView Dist=new TextView(this);
	      
	        Name=(TextView)findViewById(R.id.Name); 
	        Age= (TextView)findViewById(R.id.Age);
	        Cleanliness= (TextView)findViewById(R.id.Clean);
	        Smoking = (TextView)findViewById(R.id.smoking);
	        BeginDate= (TextView)findViewById(R.id.beginDate);
	        EndDate= (TextView)findViewById(R.id.endDate);
	        Pets= (TextView)findViewById(R.id.Pets);
	        Dist= (TextView)findViewById(R.id.Distance);
	        Name.setText("Name: "+ name);
	        Age.setText("Age: "+ age);
	        Pets.setText("Pets: "+ pets);
	        Cleanliness.setText("Cleanliness: "+ clean);
	        Smoking.setText("Smoking: " + smoking);    
	        BeginDate.setText("Begin Date: " + Bmonth +"/" + Bday + "/"+ Byear);
	        EndDate.setText("End Date: " + Emonth +"/" + Eday + "/"+ Eyear);
	       Dist.setText("Distance:  " + dist);
	 }
	 
	
	 public void onClick_email(View v) {
		 //Cursor cursor = myDb.getRow(Id)
	 Intent i = new Intent(Intent.ACTION_SEND);
	 i.setType("message/rfc822");
	 i.putExtra(Intent.EXTRA_EMAIL  , new String[]{cursor.getString(DBAdapter.COL_email)});
	 i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
	 i.putExtra(Intent.EXTRA_TEXT   , "body of email");
	 try {
	     startActivity(Intent.createChooser(i, "Send mail..."));
	 } catch (android.content.ActivityNotFoundException ex) {
	     Toast.makeText(HostDetails.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
	 }
	 }
}
