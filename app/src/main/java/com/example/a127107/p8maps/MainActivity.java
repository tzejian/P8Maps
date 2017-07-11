package com.example.a127107.p8maps;

import android.location.Location;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    private GoogleMap map;
    LatLng poi_central = new LatLng(1.297383, 103.849489);
    LatLng poi_north = new LatLng(1.371813, 103.846531);
    LatLng poi_tampines = new LatLng(1.353382, 103.945232);
    Spinner spn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        spn = (Spinner)findViewById(R.id.spinner);


        //meant to to get the reference to the Google Map object
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                //set the longitude and latitude
                LatLng poi_CausewayPoint = new LatLng(1.357852, 103.899966);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint,
                        11));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }



                Marker np = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("HQ-North")
                        .snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\nTel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));



                Marker tp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_tampines)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788\nOperating hours: 10am-5pm\nTel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));



                final Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542\nOperating hours: 10am-5pm\nTel:65433456")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
                        @Override
                    public boolean onMarkerClick(Marker marker) {
                            if(marker.getTitle().equals("HQ-North")){
                                Toast.makeText(MainActivity.this, "Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\nTel:65433456", Toast.LENGTH_LONG).show();
                            }
                            else if(marker.getTitle().equals("Central")){
                                Toast.makeText(MainActivity.this, "Block 3A, Orchard Ave 3, 134542\nOperating hours: 10am-5pm\nTel:65433456", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Block 555, Tampines Ave 3, 287788\nOperating hours: 10am-5pm\nTel:65433456", Toast.LENGTH_LONG).show();
                            }

                            return true;
                        }

                    });







                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);











            }
        });
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int spnIndex = spn.getSelectedItemPosition();
                if (spnIndex == 0){
                    if (map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                                16));
                    }

                }else if(spnIndex == 1){
                    if (map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                                16));
                    }
                }else{
                    if (map != null){
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_tampines,
                                16));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn1 = (Button) findViewById(R.id.btnCentral);
        btn2 = (Button) findViewById(R.id.btnEast);
        btn3 = (Button) findViewById(R.id.btnNorth);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                            16));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                            16));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_tampines,
                            16));
                }
            }
        });

    }
}