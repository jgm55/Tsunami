package com.tsunami;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public abstract class AbsNavDrawerActivity extends Activity{
	private final String TAG = this.getClass().toString();
	DrawerLayout drawerLayout;
	ListView drawerListView;
	/* My custom ActionBarDrawerToggle class */
	CustomABDT customABDT;
	int listPosition;

	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerListView);
        // menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	

	/* Layout methods */
	protected void initializeDrawer(){
		drawerListView = (ListView) findViewById(R.id.left_drawer);
		String[] topViews = getResources().getStringArray(R.array.top_views);
		drawerListView.setAdapter(new NavDrawerAdapter(this, topViews));
        // Set the list's click listener
	}
	
	// Registers custom ActionBarDrawerToggle class.
	protected void addABDT(){
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		customABDT = new CustomABDT(this, drawerLayout);
		drawerLayout.setDrawerListener(new CustomABDT(this, drawerLayout));
	}
	
	protected void addUpNavigation(){
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
	}
	
	/* ActionBarDataToggle hooks */
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        customABDT.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        customABDT.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (customABDT.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
	
}
