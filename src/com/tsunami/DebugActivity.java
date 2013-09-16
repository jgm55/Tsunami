package com.tsunami;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DebugActivity extends AbsNavDrawerActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug);

		listPosition = 0;
		// register buttons, initialize drawer, etc.
		jumpstartViews();
		
		// Set alarm for periodic location updates.
		LocationServiceScheduler.scheduleService(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.debug, menu);
		return true;
	}
	
	
	/*
	 * Methods that deal with layout views go here.
	 */
	private void jumpstartViews(){
		initializeDrawer();
		addABDT();
		addUpNavigation();
		registerDebugOnClick();
		registerStartTsunamiOnClick();
	}
	
	private void registerDebugOnClick(){
		Button debug_button = (Button) findViewById(R.id.debug_button);
		debug_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startService(new Intent(DebugActivity.this, LocationService.class));
            }
        });
	}
	
	private void registerStartTsunamiOnClick(){
		Button start_tsunami_button = (Button) findViewById(R.id.start_tsunami_button);
		start_tsunami_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startService(new Intent(DebugActivity.this, LocationService.class));
            }
        });
	}

	
}
