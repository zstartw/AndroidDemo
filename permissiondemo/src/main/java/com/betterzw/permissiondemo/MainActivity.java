package com.betterzw.permissiondemo;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.betterzw.permissiondemo.PermissionUtil.hasLocationPermission;
import static com.betterzw.permissiondemo.PermissionUtil.hasWriteExternalStoragePermission;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final int GO_TO_LOCATION_PERMISSION_REQUEST = 50;

    public static final int ACTIVITY_REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION = 44;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!hasLocationPermission(MainActivity.this)) {
                    requestLocationRuntimePermissions(GO_TO_LOCATION_PERMISSION_REQUEST);
                } else {
                    goToUserLocation();
                }
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(hasWriteExternalStoragePermission(MainActivity.this))) {
                    requestWriteExternalStoragePermission();
                } else {
//                    saveImage();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case GO_TO_LOCATION_PERMISSION_REQUEST:
                if (PermissionUtil.isPermitted(grantResults)) {
                    goToUserLocation();
                } else {
                    Log.d(TAG, "Write permission was denied by user ");
//                    onLoaded();
//                    FeedbackUtil.showMessage(getActivity(), R.string.nearby_zoom_to_location);
                }
                break;
            case ACTIVITY_REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (PermissionUtil.isPermitted(grantResults)) {
//                    saveImage();
                } else {
                    Log.d(TAG, "Write permission was denied by user");
//                    FeedbackUtil.showMessage(getActivity(),
//                            R.string.gallery_save_image_write_permission_rationale);
                }
                break;
            default:
                throw new RuntimeException("unexpected permission request code " + requestCode);
        }
    }

    public void goToUserLocation(){

    }

    private void requestLocationRuntimePermissions(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);
        }
        // once permission is granted/denied it will continue with onRequestPermissionsResult
    }

    private void requestWriteExternalStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, ACTIVITY_REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION);
        }
    }


}
