package com.benameur.carretriever.controler;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.benameur.carretriever.model.Car;
import com.benameur.carretriever.model.User;
import com.benameur.carretriever.model.UserBank;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import com.benameur.carretriever.R;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap map;

    private TextView text;
    private Button buttonAdd;
    private Button buttonRemove;
    private TextView greetings;
    private User user;
    private UserBank userBank;
    private Location location;
    private Marker carMarker;
    private Marker userMarker;

    public static final String KEY_PREF_PSEUDO = "KEY_PREF_PSEUDO";
    public static final String USERBANK_DATA = "USERBANK_DATA";
    public static final int LOCATION_REQUEST = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        text = (TextView) findViewById(R.id.activity_map_text);
        greetings = (TextView) findViewById(R.id.activity_map_greetings);
        buttonAdd = (Button) findViewById(R.id.activity_map_button_add);
        buttonRemove = (Button) findViewById(R.id.activity_map_button_remove);

        buttonAdd.setTag(0);
        buttonRemove.setTag(1);
        buttonAdd.setOnClickListener(this);
        buttonRemove.setOnClickListener(this);

        Intent intent = getIntent();

        if (intent != null) {
            String pseudo = intent.getStringExtra(KEY_PREF_PSEUDO);
            userBank = (UserBank) intent.getSerializableExtra(USERBANK_DATA);
            user = userBank.findUser(pseudo);
        }

        greetings.setText("Bonjour " + user.getPseudo());

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        Location location = getLocation();
        MarkerOptions marker = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).draggable(true);
        userMarker = map.addMarker(marker);
        // TODO: On gère le cas ou l'utilisateur a une voiture ou non
        if(user.isHasCar()) {
            text.setText("Vous pouvez supprimer la voiture enregistré");
            buttonRemove.setVisibility(View.VISIBLE);
            buttonAdd.setVisibility(View.GONE);
            marker = new MarkerOptions().position(new LatLng(user.getCar().getLat(), user.getCar().getLng())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            carMarker = map.addMarker(marker);
        } else {
            text.setText("Aucune voiture n'est enregistrée pour le moment");
        }
    }

    private Location getLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
        }
        return locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == LOCATION_REQUEST) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                location = getLocation();
                MarkerOptions marker = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).draggable(true);
                userMarker = map.addMarker(marker);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        if(index == 0) {
            System.out.println("On appui sur add");
            buttonRemove.setVisibility(View.VISIBLE);
            buttonAdd.setVisibility(View.GONE);
            location = getLocation();
            Car car = new Car(location.getLatitude(), location.getLongitude());
            user.setCar(car);
            user.setHasCar(true);
            userBank.updateUser(user);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            carMarker = map.addMarker(marker);
            text.setText("Vous pouvez supprimer la voiture enregistré");
        } else if(index == 1) {
            System.out.println("On appui sur remove");
            buttonRemove.setVisibility(View.GONE);
            buttonAdd.setVisibility(View.VISIBLE);
            user.setCar(null);
            text.setText("Aucune voiture n'est enregistrée pour le moment");
            carMarker.remove();
        } else {
            System.out.println("Erreur");
        }
    }

}
