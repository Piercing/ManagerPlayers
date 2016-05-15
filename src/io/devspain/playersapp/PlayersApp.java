package io.devspain.playersapp;

import android.app.Application;
import android.util.Log;
import io.devspain.database.DBConstants;
import io.devspain.database.DBHelper;

public class PlayersApp extends Application {

	public static final String TAG = PlayersApp.class.getName();

	@Override
	public void onCreate() {
		super.onCreate();

		Log.d(TAG, "Hello Players");

		// El primero que llame a getInstance usará estos valores para crear la aplicación
		DBHelper.configure(DBConstants.DBNAME, getApplicationContext());
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
}
