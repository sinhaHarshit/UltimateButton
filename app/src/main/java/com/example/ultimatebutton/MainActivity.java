package com.example.ultimatebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText brightness;

    private CheckBox brightnessCB, bluetoothCB, locationCB;
    private BluetoothAdapter BA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if app has necessary permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.System.canWrite(this)){
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:"+this.getPackageName()));
                startActivity(intent);
            }
        }

        brightness = findViewById(R.id.percentText);
        brightnessCB = findViewById(R.id.checkBox1);
        bluetoothCB = findViewById(R.id.checkBox2);
        locationCB = findViewById(R.id.checkBox3);
        BA = BluetoothAdapter.getDefaultAdapter();
        if (BA == null) {
            System.out.println("device not supported");
        }
    }

    public void setButton(View view) {


        if(!brightnessCB.isChecked() && !bluetoothCB.isChecked() && !locationCB.isChecked()){
            Toast.makeText(MainActivity.this,
                    "Try selecting some options",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            if(brightnessCB.isChecked()) {
                setBrightness();
            }

            if(bluetoothCB.isChecked()) {
                setBluetooth();
            }

            if(locationCB.isChecked()) {
                setLocation();
            }
        }


        //Toast.makeText(getApplicationContext(), "Try selecting some options", Toast.LENGTH_LONG).show();

    }

    public void setBrightness() {

        if(brightness.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this,
                    "Enter a valid brightness percent",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            int brightnessLevel = (int) (250 * 0.01 * Integer.parseInt(brightness.getText().toString()));

            if (brightnessLevel > 250){
                brightnessLevel = 250;
            }

            ContentResolver contentResolver = this.getApplicationContext().getContentResolver();
            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightnessLevel);
        }
    }

    public void setBluetooth() {
        System.out.println("setting bluetooth");
        /* if(BA.isEnabled()) {
            System.out.println("bluetooth is on");
            BA.disable();
        }*/
        Toast.makeText(MainActivity.this,
                "Bluetooth off",
                Toast.LENGTH_SHORT).show();
    }

    public void setLocation() {

    }

}