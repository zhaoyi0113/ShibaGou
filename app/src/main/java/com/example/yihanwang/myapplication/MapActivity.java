package com.example.yihanwang.myapplication;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap m_cGoogleMap;
    private Location m_cCurrentLocation;
    private Button toImage;

    private static final LatLng LOCATION_CAULFIELD
            = new LatLng(-37.8770, 145.0443); private static final LatLng LOCATION_CLAYTON
            = new LatLng(-37.9150, 145.1300); private static final LatLng LOCATION_BERWICK
            = new LatLng(-38.0405, 145.3399); private static final LatLng LOCATION_PENINSULA
            = new LatLng(-38.1536, 145.1344); private static final LatLng LOCATION_PARKVILLE
            = new LatLng(-37.7838, 144.9587); private static final LatLng LOCATION_GIPPSLAND
            = new LatLng(-38.3112, 146.4294);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Get access to our MapFragment
        MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
    // Set up an asyncronous callback to let us know when the map has loaded
        mapFrag.getMapAsync(this);
        toImage = (Button)findViewById(R.id.findPlant);

        toImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent subscription = new Intent(getApplicationContext(),ImageActivity.class);
                startActivity(subscription);

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Function is called once the map has fully loaded.
    // Set our map object to reference the loaded map
        m_cGoogleMap = googleMap;
    // Move the focus of the map to be on Caulfield Campus. 15 is for zoom
        m_cGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_CAULFIELD,15));
    // Call our Add Default Markers function
    // NOTE: In a proper application it may be better to load these from a DB AddDefaultMarkers();
    }


    private void AddDefaultMarkers() {
    // Create a series of markers for each campus with the title being the campus name
        m_cGoogleMap.addMarker(new MarkerOptions()
                .position(LOCATION_CAULFIELD).title("Monash Caulfield")); m_cGoogleMap.addMarker(new MarkerOptions()
                .position(LOCATION_CLAYTON).title("Monash Clayton")); m_cGoogleMap.addMarker(new MarkerOptions()
                .position(LOCATION_BERWICK).title("Monash Berwick"));
        m_cGoogleMap.addMarker(new MarkerOptions() .position(LOCATION_PENINSULA).title("Monash Peninsula"));
        m_cGoogleMap.addMarker(new MarkerOptions() .position(LOCATION_GIPPSLAND).title("Monash Gippsland"));
        m_cGoogleMap.addMarker(new MarkerOptions().position(LOCATION_PARKVILLE).title("Monash Parkville"));
    }


}


