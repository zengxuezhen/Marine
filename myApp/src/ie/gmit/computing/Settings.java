package ie.gmit.computing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Set the preference
 * @author Alex.Zeng
 *
 */
public class Settings extends Activity {

    Button save;
    EditText ship;
    EditText person;
    EditText email;
    
    String shipName;
    String personName;
    String emaillString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		ship=(EditText)findViewById(R.id.shipName);
		person=(EditText)findViewById(R.id.personName);

		email=(EditText)findViewById(R.id.email);
		
		save=(Button)findViewById(R.id.saveInfo);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sharedPreferences=getSharedPreferences("info.txt", 0);
				SharedPreferences.Editor editor=sharedPreferences.edit();
				
				shipName=ship.getText().toString();
				personName=person.getText().toString();
				emaillString=email.getText().toString();
				
				if(!shipName.equals("")&& !personName.equals("") && !emaillString.equals("")){
					editor.putString("ship", shipName);
					editor.putString("person", personName);
					editor.putString("email", emaillString);
					Toast.makeText(Settings.this, "Saved", Toast.LENGTH_SHORT).show();
				}else if(shipName.isEmpty()|| personName.isEmpty() || emaillString.isEmpty()){
					Toast.makeText(Settings.this, "Cannot be null", Toast.LENGTH_SHORT).show();
				}
				
				editor.commit();
			}
		});

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences sharedPreferences=getSharedPreferences("info.txt", 0);
		
		ship.setText(sharedPreferences.getString("ship", ""));
		person.setText(sharedPreferences.getString("person", ""));
		email.setText(sharedPreferences.getString("email", ""));
	}
}
