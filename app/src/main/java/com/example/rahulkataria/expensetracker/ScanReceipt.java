package com.example.rahulkataria.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

public class ScanReceipt extends AppCompatActivity {

    SurfaceView mCameraView;
    TextView mTextView;
    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_receipt);
    }


}
