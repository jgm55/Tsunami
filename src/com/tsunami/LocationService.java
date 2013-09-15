package com.tsunami;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service implements LocationListener {
	private final String TAG = this.getClass().toString();
	private static final int TEN_MINUTES = 1000 * 60 * 10;
	private static final int MIN_ACCURACY = 150;
	// MIN_TIME and MIN_DISTANCE are 0 to get constant updates until accuracy
	// requirement is satisfied (MIN_ACCURACY).
	private final long MIN_TIME = 0;
	private final float MIN_DISTANCE = 0;
	long nowInMillis = 0;
	LocationManager locationManager = null;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		locationManager = (LocationManager) getApplicationContext()
				.getSystemService(Context.LOCATION_SERVICE);

		Time now = new Time();
		now.setToNow();
		nowInMillis = now.toMillis(false);

		broadcastBestLocation();

		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
		return START_STICKY;
	}

	private void broadcastBestLocation() {
		showToast("getting best location estimate");
		Location networkLastKnown = locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		Location gpsLastKnown = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		showToast(networkLastKnown.toString());
		if (isLocationReliable(networkLastKnown, nowInMillis)) {
			mySendBroadcast(networkLastKnown);
		} else if (isLocationReliable(gpsLastKnown, nowInMillis)) {
			mySendBroadcast(gpsLastKnown);
		} else {
			showToast("Last known location not accurate, listening for location updates");
			listenForLocationUpdates();
		}
	}

	private void listenForLocationUpdates() {
		if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE,
					this);
		} else if (locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DISTANCE, this);
		} else {
			showToast("Location services turned off.");
			stopSelf();
		}
	}

	private void showToast(String string) {
		Toast.makeText(getBaseContext(), string, Toast.LENGTH_SHORT).show();
	}

	private boolean isLocationReliable(Location location, long nowInMillis) {
		return location != null
				&& location.getTime() > (nowInMillis - TEN_MINUTES)
				&& location.getAccuracy() < MIN_ACCURACY;
	}

	// Sends off broadcast with location & stops service.
	private void mySendBroadcast(Location location) {
		Intent intent = new Intent();
		intent.setAction("com.tsunami.LocationBroadcast");
		intent.putExtra("Location", location.toString());
		intent.putExtra("Latitude", location.getLatitude());
		intent.putExtra("Longitude", location.getLongitude());
		intent.putExtra("Accuracy", location.getAccuracy());
		sendBroadcast(intent);
		stopListening();
	}

	private void stopListening() {
		locationManager.removeUpdates(this);
		showToast("Stopping service.");
		stopSelf();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		if (location.getAccuracy() < MIN_ACCURACY) {
			mySendBroadcast(location);
		}
		showToast("Location found but not accurate. Accuracy: "
				+ location.getAccuracy());
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
