package com.tsunami;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LocationServiceScheduler {
	private final static String TAG = "LocationServiceScheduler";
	private static final long ALARM_INTERVAL = 1000 * 60 * 15;
	
	public static void scheduleService(Context context){
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, LocationService.class);
		PendingIntent pendingIntent = PendingIntent
				.getService(context, 0, intent, 0);
		alarmManager.setRepeating(AlarmManager.RTC,
				System.currentTimeMillis(), ALARM_INTERVAL, pendingIntent);
		Log.d(TAG, "Set AlarmManager");
	}
}
