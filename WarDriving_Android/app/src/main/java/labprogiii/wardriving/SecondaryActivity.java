package labprogiii.wardriving;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SecondaryActivity extends AppCompatActivity {

    Button stop;

    private TextView timerValue;
    private TextView numRedes;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    private int count;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    private final int TIME_INTERVAL = 415;
    private final int REQUEST_LOCATION = 134;

    private GPSTracker gpsTracker;
    private WifiManager wifiManager;

    private double latitude;
    private double longitude;
    private List<WifiInfo> wifiInfoList;

    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        stop = (Button) findViewById(R.id.buttonStop);
        timerValue = (TextView) findViewById(R.id.timerValue);
        numRedes = (TextView) findViewById(R.id.numRedes);
        Date dataTemp = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        data = dateFormat.format(dataTemp);
        Toast test = Toast.makeText(this,data,Toast.LENGTH_LONG);
        test.show();

        count = -1;

        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
        gpsTracker = new GPSTracker(SecondaryActivity.this);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("O que deseja fazer?")
            .setPositiveButton("Enviar ao servidor", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RequestParams params = new RequestParams();
                //int i = 0;

                for(WifiInfo wifi : wifiInfoList){
                    params.add("SSID",wifi.getSSID());
                    params.add("Intensidade",wifi.getIntensidade() + "");
                    params.add("Latitude",wifi.getLatitude() + "");
                    params.add("Longitude",wifi.getLongitude() + "");
                    params.add("Data",wifi.getData());
                    HttpCommands.post(params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast toast = Toast.makeText(SecondaryActivity.this, "Erro na passagem de dados", Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        }
                    });
                }
                finish();
            }
        })
            .setNegativeButton("Retornar ao menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    //startActivity(intent);
                    finish();
                }
            });

        stop.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });


        wifiInfoList = new ArrayList<>();
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
    }

    @Override
    protected void onStop() {
        customHandler.removeCallbacks(updateTimerThread);
        gpsTracker.stopUsingGPS();
        super.onStop();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            int i;
            count++;

            if(numRedes.getText().toString() == "" || numRedes.getText().toString() == null){
                i = 0;
                numRedes.setText("" + i);
            }
            else {
                i = Integer.parseInt(numRedes.getText().toString());
            }

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            if(count % TIME_INTERVAL == 0) {
//                Toast toast = Toast.makeText(getApplicationContext(), "Próxima consulta", Toast.LENGTH_SHORT);
//                toast.show();

                wifiManager.startScan();

                List<ScanResult> scanResultList = wifiManager.getScanResults();

                gpsTracker = new GPSTracker(SecondaryActivity.this);
                if (Build.VERSION.SDK_INT == 23) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                    } else {
                        if (gpsTracker.canGetLocation()) {
                            latitude = gpsTracker.getLatitude();
                            longitude = gpsTracker.getLongitude();
                        } else {
                            gpsTracker.showSettingsAlert();
                        }
                    }
                } else {
                    if (gpsTracker.canGetLocation()) {
                        latitude = gpsTracker.getLatitude();
                        longitude = gpsTracker.getLongitude();
                    } else {
                        gpsTracker.showSettingsAlert();
                    }
                }

                for (ScanResult result : scanResultList) {
                    wifiInfoList.add(new WifiInfo(result.SSID, result.level, latitude, longitude,data));
                    i++;
                }
                count = 0;
                numRedes.setText("" + i);
            }
            customHandler.postDelayed(this, 0);
        }
    };

}
