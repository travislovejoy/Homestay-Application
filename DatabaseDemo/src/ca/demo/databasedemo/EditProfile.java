package ca.demo.databasedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends Activity {
	private Spinner spinner1, spinner2, spinner3, smoking, Bmonth, Bday, Byear, Emonth, Eday, Eyear;
	
	DBAdapter myDb;
	private EditText name, password, Rname, email, age, phone, address, dist;
	private long row_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bundle extras = getIntent().getExtras();
        row_id = extras.getInt("ID");
        Toast.makeText(EditProfile.this,
    			"row id: "+ row_id,
    				Toast.LENGTH_SHORT).show();

		
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
		name = (EditText) findViewById(R.id.name);
		password= (EditText) findViewById(R.id.password);
		Rname= (EditText) findViewById(R.id.RealName);
		email= (EditText) findViewById(R.id.email);
		age= (EditText) findViewById(R.id.age);
		dist= (EditText) findViewById(R.id.distance);
		address = (EditText) findViewById(R.id.address);
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
		/*long newId =*/ myDb.updateRow(row_id, name.getText().toString(), password.getText().toString(),String.valueOf(spinner1.getSelectedItem()), String.valueOf(spinner2.getSelectedItem()),
				Rname.getText().toString(), address.getText().toString(), email.getText().toString(), phone.getText().toString(), 
				Integer.parseInt(age.getText().toString()), String.valueOf(spinner3.getSelectedItem()), String.valueOf(smoking.getSelectedItem()), String.valueOf(Bmonth.getSelectedItem()),
				String.valueOf(Bday.getSelectedItem()), String.valueOf(Byear.getSelectedItem()),String.valueOf(Emonth.getSelectedItem()), String.valueOf(Eday.getSelectedItem()), String.valueOf(Eyear.getSelectedItem()),
				Integer.parseInt(dist.getText().toString()));
		
		if(String.valueOf(spinner3.getSelectedItem()).equals("Host")){
			Intent intent= new Intent(v.getContext(), MainActivity.class);
			startActivityForResult(intent, 0);
		}
		else{
		Intent intent= new Intent(v.getContext(), ManualWizard.class);
		startActivityForResult(intent, 0);
		}
		
	}

	//public void onClick_ClearAll(View v) {
		//displayText("Clicked clear all!");
		//myDb.deleteAll();
	//}
	
}
