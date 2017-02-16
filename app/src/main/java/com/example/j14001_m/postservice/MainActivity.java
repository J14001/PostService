package com.example.j14001_m.postservice;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity implements LocationListener {

    private LocationManager locationManager;
    final double[] lat = {0.0};
    final double[] lng = {0.0};


    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationStart();


        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncHttp post = new AsyncHttp(String.valueOf(lat[0]), String.valueOf(lng[0]));
                post.execute();
            }
        });
    }

    //GPSスタート
    private void locationStart() {
        Log.d("debug", "locationStart()");
        //LocationManagerインスタンス生成
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            //GPS設定するように促す
            Intent settingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingIntent);
            Log.d("debug", "gpsEnable, startActivity");
        } else {
            Log.d("debug", "gpsEnabled");
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, this);
    }


    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onLocationChanged(Location location) {
        //緯度の表示
        lat[0] = location.getLatitude();
        lng[0] = location.getLongitude();
        Log.d("Debug", String.valueOf(lat[0]));
        Log.d("Debug", String.valueOf(lng[0]));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
