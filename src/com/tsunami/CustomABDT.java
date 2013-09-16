package com.tsunami;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;

public class CustomABDT extends ActionBarDrawerToggle {
	private final String TAG = this.getClass().toString();
	AbsNavDrawerActivity activity;
	DrawerLayout drawerLayout;
	
	public CustomABDT(AbsNavDrawerActivity activity, DrawerLayout drawerLayout) {
		super(activity, drawerLayout, R.drawable.ic_drawer,
				R.string.drawer_open, R.string.drawer_close);
		this.activity = activity;
		this.drawerLayout = drawerLayout;
		// TODO Auto-generated constructor stub
	}
	
	 public void onDrawerClosed(View view) {
         activity.getActionBar().setTitle(activity.getTitle());
         activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
     }

     /** Called when a drawer has settled in a completely open state. */
     public void onDrawerOpened(View drawerView) {
    	 activity.getActionBar().setTitle(activity.getString(R.string.app_name));
         activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
     }

}
