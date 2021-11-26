package com.motor.bikestart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.motor.bikestart.viewmodel.ViewModelClass;
import com.natasa.progressviews.ArcProgressBar;
import com.natasa.progressviews.LineProgressBar;
import com.natasa.progressviews.utils.OnProgressViewListener;
import com.natasa.progressviews.utils.ProgressLineOrientation;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewModelClass viewModel;
    TextView batteryData, speedData, rangeData, rpmData, tiltAngleData;
    int b = 0, s =0, r = 0, rp = 0, t = 0, j = 0, bw, tw;
    int count = 0;
    long mili = 13000;

    CountDownTimer timer;
    boolean onceHit = false;
    LineProgressBar batteryProgress, rpmProgress;
    //ArcProgressBar titlAngle;
    ArcSeekBar titlAngle;

    ImageView batteryWarn, tempWarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryData = findViewById(R.id.battery_data);
        speedData = findViewById(R.id.speed_data);
        rangeData = findViewById(R.id.range_data);
        rpmData = findViewById(R.id.rpm_data);
        tiltAngleData = findViewById(R.id.tilt_angle_data);

        batteryWarn = findViewById(R.id.battery);
        tempWarn = findViewById(R.id.temperature);

        batteryProgress = findViewById(R.id.battery_progress);
        batteryProgress.setLineOrientation(ProgressLineOrientation.VERTICAL);

        rpmProgress = findViewById(R.id.rpm_progress);
        rpmProgress.setLineOrientation(ProgressLineOrientation.VERTICAL);

        titlAngle = findViewById(R.id.titl_angle);
        int[] intArray = getResources().getIntArray(R.array.progressGradientColors);
        titlAngle.setProgressGradient(intArray);

        viewModel = ViewModelProviders.of(this).get(ViewModelClass.class);

        hitAPI();

        timer = new CountDownTimer(mili, 1200) {
            public void onTick(long millisUntilFinished) {
                if(onceHit) {
                    hitAPI();
                }
              //  Toast.makeText(MainActivity.this, ""+millisUntilFinished/1000, Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                timer.start();
                onceHit = true;
            }
        }.start();

    }

    public void hitAPI(){
        try {

            viewModel.getBikeData().observe(this, dataModel -> {
                if (dataModel != null) {

                    List<Integer> battery = dataModel.getBattery();
                    List<Integer> speed = dataModel.getSpeed();
                    List<Integer> range = dataModel.getRange();
                    List<Integer> rpm = dataModel.getRpm();
                    List<Integer> titl = dataModel.getTiltAngle();
                    List<Integer> batteryWarning = dataModel.getWarnings().getBattery();
                    List<Integer> temperatureWarning = dataModel.getWarnings().getTempreture();

                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        public void run() {

                            if(count == 20){
                                count = 0;
                                onceHit = false;
                            }

                            b = battery.get(count);
                            s = speed.get(count);
                            r = range.get(count);
                            rp = rpm.get(count);
                            t = titl.get(count);
                            bw = batteryWarning.get(count);
                            tw = temperatureWarning.get(count);

                            if(tw==1){
                                tempWarn.setVisibility(View.VISIBLE);
                            }else{
                                tempWarn.setVisibility(View.INVISIBLE);
                            }

                            if(bw==1){
                                batteryWarn.setVisibility(View.VISIBLE);
                            }else{
                                batteryWarn.setVisibility(View.INVISIBLE);
                            }

                            batteryData.setText("Battery "+b + "%");
                            speedData.setText(""+s);
                            rangeData.setText("Range " + r + " km");
                            rpmData.setText("Rpm " + rp + "\n");
                            tiltAngleData.setText("Titl Angle " + t + "\n");


                            batteryProgress.setProgress(b);
                            rpmProgress.setProgress((rp/25));
                            titlAngle.setProgress(t);



                            if (count < battery.size() - 1) {
                                handler.postDelayed(this, 600);
                            }

                            count++;

                        }
                    };
                    handler.post(runnable);
                }
            });

        } catch (Exception e) {
            batteryData.setText(e.getMessage());
        } finally {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(timer!=null){
            timer.cancel();
        }
    }
}