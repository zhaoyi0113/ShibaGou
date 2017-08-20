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
    private Button toImage;

    private static final LatLng LOCATION_GRAMPIANS
            = new LatLng(-37.6145,142.3244);


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

                Intent subscription = new Intent(MapActivity.this,ImageActivity.class);
                startActivity(subscription);

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Function is called once the map has fully loaded.
        // Set our map object to reference the loaded map
        m_cGoogleMap = googleMap;
        // Move the focus of the map to be on the Grampians park. 15 is for zoom
        m_cGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCATION_GRAMPIANS,15));

        m_cGoogleMap.addMarker(new MarkerOptions().position(LOCATION_GRAMPIANS).title("You Are Here"));
        //set map to satellite map
        m_cGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    public void toImagePage(){
        Intent toImage = new Intent(getApplicationContext(),ImageActivity.class);
        startActivity(toImage);

    }


}


