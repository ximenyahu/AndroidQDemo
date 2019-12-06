package com.simon.androidqdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    //使用google gms包下面的API
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btRequest = findViewById(R.id.bt_request);
        Button btRequestBackGround = findViewById(R.id.bt_request_background);
        Button btLastLocation = findViewById(R.id.bt_last_location);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        btRequest.setOnClickListener(this);
        btRequestBackGround.setOnClickListener(this);
        btLastLocation.setOnClickListener(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {
            Log.i(TAG, "onRequestPermissionsResult: 0");
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "grantResults[i]: " + grantResults[i]);
                    Toast.makeText(MainActivity.this, "获取到权限", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i(TAG, "grantResults[i]: " + grantResults[i]);
                    Toast.makeText(MainActivity.this, "没有获取到权限", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (requestCode == 1) {
            Log.i(TAG, "onRequestPermissionsResult: 1");
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "grantResults[i]: " + grantResults[i]);
                    Toast.makeText(MainActivity.this, "获取到权限", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i(TAG, "grantResults[i]: " + grantResults[i]);
                    Toast.makeText(MainActivity.this, "没有获取到权限", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_request:
                if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                }
                break;
            case R.id.bt_request_background:
                if (checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                            1);
                }
                break;
            case R.id.bt_last_location:
                //获取最近一次的位置信息
                LocationRequest locationRequest = new LocationRequest();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                        .addLocationRequest(locationRequest);
                fusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // got last know location, In some rare situation this can be null.
                        if (location == null) {
                            // logic to handle location object

                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
