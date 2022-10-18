package com.example.pageslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView [] dots;
    FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        analytics = FirebaseAnalytics.getInstance(MainActivity.this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotsPanel = (LinearLayout) findViewById(R.id.sliderDot);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        dotsCount = viewPagerAdapter.getCount();
         dots = new ImageView[dotsCount];

         for (int i =0; i<dotsCount; i++){
             dots[i] = new ImageView(this);
             dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
             params.setMargins(8,0,8,0);
             sliderDotsPanel.addView(dots[i],params);
         }
         dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

         viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {

                 for (int i =0; i<dotsCount; i++){

                     dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.nonactive_dot));
                 }

                 dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));

             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });






         Timer timer = new Timer();
         timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);

    }


    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (viewPager.getCurrentItem() == 0){
                    viewPager.setCurrentItem(1);
                }else if (viewPager.getCurrentItem() ==1){
                    viewPager.setCurrentItem(2);
                }else{
                    viewPager.setCurrentItem(0);
                }
            }
        });

        }
    }


}