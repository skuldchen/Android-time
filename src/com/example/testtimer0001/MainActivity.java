package com.example.testtimer0001;
/////
///// http://examples.javacodegeeks.com/android/core/os/handler/android-timer-example/ 
/////
/////
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


 
public class MainActivity extends ActionBarActivity {

    private Button startButton; 
    private Button pauseButton; 
    private Button btnclear;
    
    private TextView timerValue; 
    private long startTime = 0L; 
    private Handler customHandler = new Handler(); 
    long timeInMilliseconds = 0L; 
    long timeSwapBuff = 0L; 
    long updatedTime = 0L; 
	
    boolean startOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        timerValue = (TextView) findViewById(R.id.timerValue);
        timerValue.setText("00:00");
        startButton = (Button) findViewById(R.id.startButton); 
        
        startButton.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View view) {
            	startOn = true;
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0); 
            } 
        }); 

        pauseButton = (Button) findViewById(R.id.pauseButton); 
        pauseButton.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View view) { 
            	startOn=false;
                timeSwapBuff += timeInMilliseconds; 
                customHandler.removeCallbacks(updateTimerThread); 
            } 
        });
        
        btnclear = (Button) findViewById(R.id.btnClear);
        btnclear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				if(startOn)
//				{
//					
//				}
//				else
//				{
				startOn=false;	
				customHandler.removeCallbacks(updateTimerThread); 
				startTime = 0L;
				timeInMilliseconds = 0L; 
			    timeSwapBuff = 0L; 
			    updatedTime = 0L;
			    timerValue.setText(Integer.toString(0));
			    timerValue.setText("00:00");
				//customHandler.postDelayed(updateTimerThread, 0);
				customHandler.removeCallbacks(null);
//				}
				
				
			}
		});
        skuldCurrentTime();
    }

 


    private Runnable updateTimerThread = new Runnable() { 
        public void run() { 
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime; 
            updatedTime = timeSwapBuff + timeInMilliseconds; 
			int secs = (int) (updatedTime / 1000); 
			int mins = secs / 60;  
            secs = secs % 60; 
            int milliseconds = (int) (updatedTime % 1000); 
            /*
             * If want to more detail into milliseconds, and then used below code. 
             */
//            timerValue.setText("" + mins + ":" 
//                    + String.format("%02d", secs) + ":" 
//                    + String.format("%03d", milliseconds)); 
            timerValue.setText(String.format("%02d", mins) + ":" 
                    + String.format("%02d", secs) ); 
            customHandler.postDelayed(this, 0); 

        } 
    }; 


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */
    
    
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
    
    /*
     * Current time, To Get it. At once
     */
       
    /*
     *  ref 1. Japanese http://www.adakoda.com/android/000119.html
     *  ref 2. Chinese http://oldgrayduck.blogspot.tw/2013/01/android_29.html
     */
   
    private void skuldCurrentTime()
    {
    	    	
    	// ref http://developer.android.com/reference/java/text/SimpleDateFormat.html
    	// 時間直接為string格式
    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	// or 
    	// String timeStamp = new SimpleDateFormat("yyyy-MMdd_HHmmss").format(new Date()); // 與上面差一個 "-" 
    	TextView timeCurrent = (TextView)findViewById(R.id.timeCurrent1);
    	timeCurrent.setText(timeStamp);
    	

    	// ref http://developer.android.com/reference/java/util/Date.html
    	//Date d = new Date();
    	//CharSequence s = DateFormat.format("yyyy-MM-dd kk:mm:ss", d.getTime());
    	Date d = new Date();
    	CharSequence s = DateFormat.format("yyyy-MM-dd kk:mm:ss", d.getTime());
    	TextView timeCurrent2 = (TextView)findViewById(R.id.timeCurrent2);
    	timeCurrent2.setText(s);

    }
    
    
}
