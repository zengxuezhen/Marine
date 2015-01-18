package ie.gmit.computing;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Save the image , information, ie: GPS Coordinates to a file
 * @author Alex.Zeng
 *
 */
public class save extends Activity {
	Button save;
	Button back;
	Button btm;
	ImageView image;
	static int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        save=(Button) findViewById(R.id.select);
        back=(Button) findViewById(R.id.back);
        image=(ImageView) findViewById(R.id.imageView2);
        btm=(Button)findViewById(R.id.btm);
        Bitmap bitmap=getIntent().getParcelableExtra("bitmap");
		if(bitmap==null)
		Toast.makeText(getApplicationContext(), "BITMAP: NULL",100).show();
		image.setImageBitmap(bitmap);
		
		btm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				   startActivity(intent);
			}
		});
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s=getIntent().getStringExtra("gps");
				Bitmap bitmap=getIntent().getParcelableExtra("bitmap");
//				if(bitmap==null)
//				Toast.makeText(getApplicationContext(), "BITMAP: NULL",100);
//				image.setImageBitmap(bitmap);
				
				
				FileOutputStream fos=null;
				BufferedWriter writer=null;
				FileOutputStream fos2=null;
				FileOutputStream fos3=null;
				StringBuffer stringBuffer=new StringBuffer();
				SharedPreferences sharedPreferences=getSharedPreferences("info.txt", 0);
				
				try {
					fos=getApplicationContext().openFileOutput("img_"+c+".png",Context.MODE_MULTI_PROCESS);
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
					
					fos2=getApplicationContext().openFileOutput("gps_"+c+".txt",Context.MODE_MULTI_PROCESS);
					writer=new BufferedWriter(new OutputStreamWriter(fos2));
					writer.write(s);
					
					fos3=getApplicationContext().openFileOutput("info_"+c+".txt", Context.MODE_MULTI_PROCESS);
					writer=new BufferedWriter(new OutputStreamWriter(fos3));
					stringBuffer.append(sharedPreferences.getString("ship", "")+"\n");
					stringBuffer.append(sharedPreferences.getString("person", "")+"\n");
					stringBuffer.append(sharedPreferences.getString("email", "")+"\n");
					
					writer.write(new String(stringBuffer));
					
					writer.flush();
					fos.flush();
					
					Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.e("File not saved", "File not saved");
					e.printStackTrace();
				}finally{
					try {
						fos2.close();
						fos3.close();
						writer.close();
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
		});
        
        

    }
 /**
  * go to Search class
  * @param view
  */
   public void back(View view){
	   Intent intent=new Intent(getApplicationContext(),Search.class);
	   startActivity(intent);
   }
   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
