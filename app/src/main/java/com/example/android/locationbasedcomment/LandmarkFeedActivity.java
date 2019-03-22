package com.example.android.locationbasedcomment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class LandmarkFeedActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    public String latitude, longitude;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Landmark> mLandmarkList = new ArrayList<>();
    RelativeLayout layout;
    public static String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            getLocation();

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        Toast.makeText(this, "Username is: " + username, Toast.LENGTH_SHORT).show();



        layout = findViewById(R.id.location_layout);
        mRecyclerView = findViewById(R.id.location_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        landmarkList();
        setAdapterAndUpdateData();
    }

    private void landmarkList() {
        mLandmarkList = new ArrayList<>();
        Location currentLocation = new Location("currentLocation");
        currentLocation.setLatitude(Double.parseDouble(this.latitude));
        currentLocation.setLongitude(Double.parseDouble(this.longitude));

        Location mlkB = new Location("mlkB");
        mlkB.setLatitude(37.869288);
        mlkB.setLongitude(-122.260125);
        float distance1 = currentLocation.distanceTo(mlkB);

        Location outsideS = new Location("outsideS");
        outsideS.setLatitude(37.871305);
        outsideS.setLongitude(-122.252516);
        float distance2 = currentLocation.distanceTo(outsideS);

        Location macchiB = new Location("macchiB");
        macchiB.setLatitude(37.874118);
        macchiB.setLongitude(-122.258778);
        float distance3 = currentLocation.distanceTo(macchiB);

        Location lesB = new Location("lesB");
        lesB.setLatitude(37.871707);
        lesB.setLongitude(-122.253602);
        float distance4 = currentLocation.distanceTo(lesB);

        Location strawberryC = new Location("strawberryC");
        strawberryC.setLatitude(37.869861);
        strawberryC.setLongitude(-122.261148);
        float distance5 = currentLocation.distanceTo(strawberryC);

        Location southH = new Location("southH");
        southH.setLatitude(37.871382);
        southH.setLongitude(-122.258355);
        float distance6 = currentLocation.distanceTo(southH);

        Location bellB = new Location("bellB");
        bellB.setLatitude(37.872061599999995);
        bellB.setLongitude(-122.2578123);
        float distance7 = currentLocation.distanceTo(bellB);

        Location benchB = new Location("benchB");
        benchB.setLatitude(37.87233810000001);
        benchB.setLongitude(-122.25792999999999);
        float distance8 = currentLocation.distanceTo(benchB);

        String a1, a2, a3, a4, a5, a6, a7, a8;
        if (Math.round(distance1) < 10) {
            a1 = "less than 10 meters away";
        } else {
            a1 = String.valueOf(Math.round(distance1)) + " meters away";
        }
        if (Math.round(distance2) < 10) {
            a2 = "less than 10 meters away";
        } else {
            a2 = String.valueOf(Math.round(distance2)) + " meters away";
        }
        if (Math.round(distance3) < 10) {
            a3 = "less than 10 meters away";
        } else {
            a3 = String.valueOf(Math.round(distance3)) + " meters away";
        }
        if (Math.round(distance4) < 10) {
            a4 = "less than 10 meters away";
        } else {
            a4 = String.valueOf(Math.round(distance4)) + " meters away";
        }
        if (Math.round(distance5) < 10) {
            a5 = "less than 10 meters away";
        } else {
            a5 = String.valueOf(Math.round(distance5)) + " meters away";
        }
        if (Math.round(distance6) < 10) {
            a6 = "less than 10 meters away";
        } else {
            a6 = String.valueOf(Math.round(distance6)) + " meters away";
        }
        if (Math.round(distance7) < 10) {
            a7 = "less than 10 meters away";
        } else {
            a7 = String.valueOf(Math.round(distance7)) + " meters away";
        }
        if (Math.round(distance8) < 10) {
            a8 = "less than 10 meters away";
        } else {
            a8 = String.valueOf(Math.round(distance8)) + " meters away";
        }

        Landmark mlkBear = new Landmark(R.drawable.mlk_bear, "Class of 1927 Bear", a1);
        Landmark outsideStadium = new Landmark(R.drawable.outside_stadium, "Stadium Entrance Bear", a2);
        Landmark macchiBears = new Landmark(R.drawable.macchi_bears, "Macchi Bears", a3);
        Landmark lesBears = new Landmark(R.drawable.les_bears, "Les Bears", a4);
        Landmark strawberryCreek = new Landmark(R.drawable.strawberry_creek, "Strawberry Creek Topiary Bear", a5);
        Landmark southHall = new Landmark(R.drawable.south_hall, "South Hall Little Bear", a6);
        Landmark bellBears = new Landmark(R.drawable.bell_bears, "Great Bear Bell Bears", a7);
        Landmark benchBears = new Landmark(R.drawable.bench_bears, "Campanile Esplanade Bears", a8);

        mLandmarkList.add(mlkBear);
        mLandmarkList.add(outsideStadium);
        mLandmarkList.add(macchiBears);
        mLandmarkList.add(lesBears);
        mLandmarkList.add(strawberryCreek);
        mLandmarkList.add(southHall);
        mLandmarkList.add(bellBears);
        mLandmarkList.add(benchBears);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_location_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


            getLocation();
            landmarkList();
            setAdapterAndUpdateData();
            Toast.makeText(this, "Your current location is:" + "\n" + "Latitude = " + latitude + "\n" + "Longitude = " + longitude, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    public void getLocation() {
        Location location = null;
        try {
            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGPSEnabled && !isNetworkEnabled) {

            } else {
                boolean canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (long) 1, (float) 1,  this);
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            double latit = location.getLatitude();
                            double longi = location.getLongitude();
                            this.latitude = String.valueOf(latit);
                            this.longitude = String.valueOf(longi);
                            Log.e("lat1 is: " + this.latitude, "getLocation: ");
                            Log.e("lon1 is: " + this.longitude, "getLocation: ");
                        }
                    }
                }

                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long) 1, (float) 1, this);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                double latit = location.getLatitude();
                                double longi = location.getLongitude();
                                this.latitude = String.valueOf(latit);
                                this.longitude = String.valueOf(longi);
                                Log.e("lat2 is: " + this.latitude, "getLocation: ");
                                Log.e("lon2 is: " + this.longitude, "getLocation: ");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setAdapterAndUpdateData() {
        mAdapter = new LandmarkAdapter(this, mLandmarkList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

