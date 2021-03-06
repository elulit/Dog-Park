package com.example.eluli.dp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }



       /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        final AlertDialog.Builder firstMenu = new AlertDialog.Builder(this);
        final AlertDialog.Builder secondMenu = new AlertDialog.Builder(this);

        // Add a marker in Sydney and move the camera
        LatLng dogPark1 = new LatLng(31.26676,34.79993);
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) { /**long press**/

                final LatLng dialogPos = new LatLng(latLng.latitude, latLng.longitude);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            //Positive - a dog park
                            case DialogInterface.BUTTON_POSITIVE:
                                mMap.addMarker(new MarkerOptions().position(dialogPos).title("Dog-Park"));
                                Log.e("YES","");

                                break;

                            case DialogInterface.BUTTON_NEGATIVE://No button clicked
                                Log.e("NO","sd");
                                break;
                        }
                    }
                };

                firstMenu.setMessage("Is This a Dog-Park?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
        //LatLng loc = new LatLng(Location)
        mMap.addMarker(new MarkerOptions().position(dogPark1).title("marker"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(dogPark1));

    }
}
