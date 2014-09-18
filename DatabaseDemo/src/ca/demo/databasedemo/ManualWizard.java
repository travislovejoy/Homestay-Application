package ca.demo.databasedemo;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


	
	
public class ManualWizard extends CreateProfile{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manualwizard);

}
	public void onClick_Manual(View v) {
		Intent intent= new Intent(v.getContext(), Manual.class);
		startActivityForResult(intent, 0);
	}
	public void onClick_Wizard(View v) {
		Intent intent= new Intent(v.getContext(), Wizard.class);
		startActivityForResult(intent, 0);
	}
}
