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

public class MainActivity extends Activity{
	
	DBAdapter myDb;
	private EditText username, password;
	TextView status; 
	boolean loggedin= false;
	int rowid;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
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
	
	public void onClick_login(View v) {
		//displayText("Clicked display record!");
		username = (EditText) findViewById(R.id.username);
		password= (EditText) findViewById(R.id.password);
		status =new TextView(this);
		//Cursor cursor = myDb.getRow(String.valueOf(spinner1.getSelectedItem()));
		Cursor cursor = myDb.getAllRows();
		//displayRecordSet(cursor);
		if (cursor.moveToFirst())
		{
			do {
				String name = cursor.getString(DBAdapter.COL_NAME);
				String pass = cursor.getString(DBAdapter.COL_PASSWORD);
					if(name.equals(username.getText().toString())){
						if(pass.equals(password.getText().toString())){
							status=(TextView)findViewById(R.id.login_status);
							rowid = cursor.getInt(DBAdapter.COL_ROWID);
							status.setText("logged in as: "+ username.getText().toString()+ Integer.toString(rowid));
							loggedin =true;
							//rowid = cursor.getInt(DBAdapter.COL_ROWID);
						}
					}
			}while(cursor.moveToNext()&& loggedin == false);
				if(loggedin == false)
				{
				
				Toast.makeText(MainActivity.this,
            			"incorrect username or password",
            				Toast.LENGTH_SHORT).show();
				}else
				{
					Toast.makeText(MainActivity.this,
	            			"Succesful Log-in",
	            				Toast.LENGTH_SHORT).show();
				}
			}
		}
	
	
	public void onClick_CreateProfile(View v) {
		Intent intent= new Intent(v.getContext(), CreateProfile.class);
		startActivityForResult(intent, 0);
	}

	public void onClick_SearchHosts(View v) {
		Intent intent= new Intent(v.getContext(), ManualWizard.class);
		startActivityForResult(intent, 0);
	}
	public void onClick_EditProfile(View v) {
		Intent intent= new Intent(v.getContext(), EditProfile.class);
		 intent.putExtra("ID", rowid);
	        startActivity(intent);  
		
	}
}
