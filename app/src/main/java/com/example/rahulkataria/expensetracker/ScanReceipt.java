package com.example.rahulkataria.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;

public class ScanReceipt extends AppCompatActivity {

    SurfaceView mCameraView;
    TextView mTextView;
    Button mButton;
    CameraSource mCameraSource;

    private static final String TAG = "ScanReceipt";
    private static final int requestPermissionID = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_receipt);

        mCameraView = findViewById(R.id.surfaceView);
        mTextView = findViewById(R.id.receipt_text);

        startCameraSource();
    }


}
