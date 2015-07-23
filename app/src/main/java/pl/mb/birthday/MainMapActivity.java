package pl.mb.birthday;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;


public class MainMapActivity extends Activity implements LocationListener, GoogleMap.OnMarkerClickListener{

    private GoogleMap googleMap;
    private LocationManager locationManager;
    private Location actualLocation;

    private Location [] locationArray = new Location[]{
            new Location(LocationManager.GPS_PROVIDER),
            new Location(LocationManager.GPS_PROVIDER)
    };

    {
        locationArray[0].setLatitude(50.08754037913904);
        locationArray[0].setLongitude(19.939354062080383);

        locationArray[1].setLatitude(50.06862685670055);
        locationArray[1].setLongitude(19.901154041290283);
    }

    private String [] descArray = new String[]{
            "dom",
            "basen"
    };


    private int actualIndexToDiscover = 0;
//    private Location basen = new Location(LocationManager.GPS_PROVIDER);
//    private Location dom = new Location(LocationManager.GPS_PROVIDER);
//    private boolean basenAdded = false;
//    private boolean domAdded = false;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        prefs = this.getSharedPreferences("my_prefs",Context.MODE_PRIVATE);
        actualIndexToDiscover = prefs.getInt("index",0);

        createMapView();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);

        Toast.makeText(getApplicationContext(),
                "running on create", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_map, menu);
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


    /**
     * Initialises the mapview
     */
    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                } else {
                    googleMap.setMyLocationEnabled(true);
                }

                for(int i = 0 ; i < actualIndexToDiscover ; i++){
                    addMarker(locationArray[i],descArray[i]);
                }

                googleMap.setOnMarkerClickListener(this);

            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    /**
     * Adds a marker to the map
     * @param lastKnownLocation
     */
    private void addMarker(Location lastKnownLocation, String marker){

        /** Make sure that the map has been initialised **/
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude()))
                            .title(marker)
                            .draggable(true)
            );
        }
    }

    @Override
    public void onLocationChanged(Location location) {

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(),location.getLongitude()))      // Sets the center of the map to Mountain View
                    .zoom(15)                   // Sets the zoom
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        actualLocation = location;

        if(actualLocation.distanceTo(locationArray[actualIndexToDiscover]) < 500) {
            notifyMainScreen("Jestes w okolicy: " + descArray[actualIndexToDiscover]);
            addMarker(locationArray[actualIndexToDiscover],descArray[actualIndexToDiscover]);
            actualIndexToDiscover++;
            prefs.edit().putInt("index",actualIndexToDiscover).apply();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void saveLocation(View view) {
        Toast.makeText(getApplicationContext(),
                "Saving location", Toast.LENGTH_SHORT).show();
        addMarker(actualLocation,"zapisane");
    }

    private void notifyMainScreen(String message){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.cake_small)
                        .setContentTitle("Urodzinowa")
                        .setContentText(message);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = this.getIntent();

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        Notification notification = mBuilder.build();
        notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, notification);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String title = marker.getTitle();
        Intent intent = new Intent(this, TipActivity.class);

        intent.putExtra("title", title);

        startActivity(intent);

        return true;
    }
}
