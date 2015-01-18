package ie.gmit.computing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * The class is to jump to other activities
 * @author Alex.Zeng
 *
 */
public class MainActivity extends Activity {

	Button add;
	Button search;
	Button delete;
	Button setting;
	Button can;
	ImageView img;
	Bitmap b;
 
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=(Button)findViewById(R.id.add);
        search=(Button)findViewById(R.id.search);
        delete=(Button)findViewById(R.id.delete);
        setting=(Button)findViewById(R.id.setings);
        can=(Button)findViewById(R.id.camera);
        img=(ImageView) findViewById(R.id.imageView);
        
 setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,Settings.class);
				startActivity(intent);
			}
			
		});
 
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,Add.class);
				startActivity(intent);
			}
			
		});
        
 delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,Delete.class);
				startActivity(intent);
			}
			
		});
 
        search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,Search.class);
				intent.putExtra("gps", gps());
				intent.putExtra("bitmap", getB());
				startActivity(intent);	
			}
		});
    

    can.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent,1000);
			
		}
	});

    }
	
	
	   public Bitmap getB() {
			return b;
		}
		public void setB(Bitmap b) {
			this.b = b;
		}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode==1000&&resultCode==RESULT_OK) {
		Bundle bundle=data.getExtras();
		Bitmap bitmap=(Bitmap) bundle.get("data");
		if(bitmap==null)
			Toast.makeText(getApplicationContext(), "BITMAP: NULL",100).show();
		
		img.setImageBitmap(bitmap);
		setB(bitmap);
	}
}

public String gps() {
    LocationManager locationManager;
    String serviceName = Context.LOCATION_SERVICE;
    locationManager = (LocationManager) getSystemService(serviceName);

    Criteria criteria = new Criteria();
    criteria.setAccuracy(Criteria.ACCURACY_FINE);
    criteria.setAltitudeRequired(false);
    criteria.setBearingRequired(false);
    criteria.setCostAllowed(true);
    criteria.setPowerRequirement(Criteria.POWER_LOW);
    String provider = locationManager.getBestProvider(criteria, true);

    Location location = locationManager.getLastKnownLocation(provider);
    String gps = updateWithNewLocation(location);
    locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
    return gps;
}

public final LocationListener locationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
        updateWithNewLocation(location);
    }

    public void onProviderDisabled(String provider) {
        updateWithNewLocation(null);
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
};


public String updateWithNewLocation(Location location) {
    if (location != null) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        return "Latitude:" + lat + " Longitude:" + lng;
    } else
        return "cannot get GPS";
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
