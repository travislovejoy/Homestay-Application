package ca.demo.databasedemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Steps to using the DB:
 * 1. [DONE] Instantiate the DB Adapter
 * 2. [DONE] Open the DB
 * 3. [DONE] use get, insert, delete, .. to change data.
 * 4. [DONE]Close the DB
 */

/**
 * Demo application to show how to use the 
 * built-in SQL lite database.
 */
public class CreateProfile extends Activity {
	private Spinner spinner1, spinner2, spinner3, smoking, Bmonth, Bday, Byear, Emonth, Eday, Eyear;
	
	DBAdapter myDb;
	private EditText name, password, Rname, email, age, phone, address, dist;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		openDB();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();	
		closeDB();
	}


	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
	}

	
	
	//public void displayText(String message) {
      //  TextView textView = (TextView) findViewById(R.id.textDisplay);
        //textView.setText(message);
	//}
	
	

	public void onClick_AddRecord(View v) {
		//displayText("Clicked add record!");
		boolean registerOk = true;
		name = (EditText) findViewById(R.id.name);
		password= (EditText) findViewById(R.id.password);
		Rname= (EditText) findViewById(R.id.RealName);
		email= (EditText) findViewById(R.id.email);
		age= (EditText) findViewById(R.id.age);
		address = (EditText) findViewById(R.id.address);
		dist = (EditText) findViewById(R.id.distance);
		phone= (EditText) findViewById(R.id.phone);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3= (Spinner) findViewById(R.id.spinner3);
		smoking= (Spinner) findViewById(R.id.smoking);
		Bmonth= (Spinner) findViewById(R.id.BeginMonth);
		Bday= (Spinner) findViewById(R.id.BeginDay);
		Byear= (Spinner) findViewById(R.id.BeginYear);
		Emonth= (Spinner) findViewById(R.id.EndMonth);
		Eday= (Spinner) findViewById(R.id.EndDay);
		Eyear= (Spinner) findViewById(R.id.EndYear);
		if(name.getText().toString().equals(""))
		{
			Toast.makeText(CreateProfile.this, "Username is a required field", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(password.getText().toString().equals(""))
		{
			Toast.makeText(CreateProfile.this, "password is a required field", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(email.getText().toString().equals(""))
		{
			Toast.makeText(CreateProfile.this, "email is a required field ", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(age.getText().toString().equals(""))
		{
			Toast.makeText(CreateProfile.this, "Age is a required field", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(dist.getText().toString().equals(""))
		{
			dist.setText("0");
		}
		
		if(registerOk && checkUserExist(name.getText().toString()))
		{
			Toast.makeText(v.getContext(), "Username already exist", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(phone.getText().toString().length() != 10)
		{
			Toast.makeText(CreateProfile.this, "Invalid Phone Number" , Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(registerOk && (Integer.parseInt(dist.getText().toString()) < 0 || Integer.parseInt(dist.getText().toString()) > 20 ))
		{
			Toast.makeText(CreateProfile.this, "Distance must be between 0 to 20", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(registerOk && (Integer.parseInt(age.getText().toString()) < 16  || Integer.parseInt(age.getText().toString()) > 100))
		{
			Toast.makeText(CreateProfile.this, "Age must be between 16 - 99", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		if(registerOk && (!validateDates(Integer.parseInt(String.valueOf(Bday.getSelectedItem())),Integer.parseInt(String.valueOf(Bmonth.getSelectedItem())),Integer.parseInt(String.valueOf(Byear.getSelectedItem())),Integer.parseInt(String.valueOf(Eday.getSelectedItem())),Integer.parseInt(String.valueOf(Emonth.getSelectedItem())),Integer.parseInt(String.valueOf(Eyear.getSelectedItem())))))
		{
			Toast.makeText(CreateProfile.this, "Duration of Dates invalid", Toast.LENGTH_SHORT).show();
			registerOk = false;
		}
		
		if(registerOk)
		{
		/*long newId =*/ myDb.insertRow(name.getText().toString(), password.getText().toString(),String.valueOf(spinner1.getSelectedItem()), String.valueOf(spinner2.getSelectedItem()),
				Rname.getText().toString(), address.getText().toString(), email.getText().toString(), phone.getText().toString(), 
				Integer.parseInt(age.getText().toString()), String.valueOf(spinner3.getSelectedItem()), String.valueOf(smoking.getSelectedItem()), String.valueOf(Bmonth.getSelectedItem()),
				String.valueOf(Bday.getSelectedItem()), String.valueOf(Byear.getSelectedItem()),String.valueOf(Emonth.getSelectedItem()), String.valueOf(Eday.getSelectedItem()), String.valueOf(Eyear.getSelectedItem()),
				Integer.parseInt(dist.getText().toString()));
		}
		if(String.valueOf(spinner3.getSelectedItem()).equals("Host") && registerOk){
			Toast.makeText(CreateProfile.this, "Profile Created", Toast.LENGTH_SHORT).show();
			Intent intent= new Intent(v.getContext(), MainActivity.class);
			startActivityForResult(intent, 0);
		}
		else if(!registerOk)
		{
			Intent intent = new Intent(v.getContext(), CreateProfile.class);
			startActivityForResult(intent, 0);
		}
		else
		{
		Intent intent= new Intent(v.getContext(), ManualWizard.class);
		startActivityForResult(intent, 0);
		}
		
	}

	//public void onClick_ClearAll(View v) {
		//displayText("Clicked clear all!");
		//myDb.deleteAll();
	//}
	
	public boolean checkUserExist(String username)
	{
		Cursor cursor = myDb.getAllRows();
		//displayRecordSet(cursor);
		if (cursor.moveToFirst())
		{
			do {
				String name2 = cursor.getString(DBAdapter.COL_NAME);
					if(name2.equals(username))
						return true;
			}while(cursor.moveToNext());
				return false;
		}
		return false;
	}
	
	public boolean validateDates(int bday,int bmonth,int byear,int eday,int emonth,int eyear)
	{
		if(eyear > byear)
			return true;
		else if(eyear == byear)
		{
			if(emonth > bmonth)
				return true; 
			else if(emonth == bmonth)
			{
				if(eday > bday || eday == bday)
					return true;
				else 
					return false;
			}
			else 
				return false;
		}
		else 
			return false;
	}
}

	
	









