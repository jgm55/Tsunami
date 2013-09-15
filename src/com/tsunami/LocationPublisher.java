package com.tsunami;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LocationPublisher extends BroadcastReceiver{
	private final String TAG = getClass().toString();

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "Retrieved broadcast.");
		publishToNetwork(intent);
	}
	
	private void publishToNetwork(Intent intent){
		String username = getUsername();
		
	}
	
	private String getUsername(){
		return "";
	}
}