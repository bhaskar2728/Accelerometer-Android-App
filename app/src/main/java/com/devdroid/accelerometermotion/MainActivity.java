package com.devdroid.accelerometermotion;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Accelerometer accelerometer;
    TextView txtDirection,txtDir1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDirection = findViewById(R.id.txtDir);
        txtDir1 = findViewById(R.id.txtDir1);

        accelerometer = new Accelerometer(this);

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float dx, float dy, float dz) {
//                DecimalFormat numberFormat = new DecimalFormat("#.000");
//                double res = Math.sqrt(dx*dx + dy*dy + dz*dz);

//                txtDirection.setText("dz = " + numberFormat.format(dz) );
                if(dz>0.1f){
                    txtDir1.setText("Moving forward");
                }
                else if(dz<-0.1f){
                    txtDir1.setText("Moving backward");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        accelerometer.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();
    }
}