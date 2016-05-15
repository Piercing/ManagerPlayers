package io.devspain.database;

import java.sql.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	// Versioned of the DDBB
	public static final int DATABASE_VERSION = 1;

	// Scripts for create DDBB tables
	public static final String[] CREATE_DATABASE_SCRITPS = { PlayersDAO.SQL_CREATE_PLAYERS_TABLE };

	// Update DDBB
	public static final String DROP_DATABASE = "";

	// Instance of the DDBB
	private static DBHelper sInstance;

	// Constructor DBHelper. The method constructor is private, only a instance
	// ==> SINLGETON PATTERN
	private DBHelper(String nameDB, Context context) {
		// If version number is distinct of 'DATABASE_VERSION' current,
		// automatically applies the method 'onUpgrade'
		super(context, nameDB, null, DATABASE_VERSION);
	}

	// If not created the instance creates an instance, and if the instance is
	// created returns that there was.
	public static DBHelper getInstance(String databaseName, Context context) {
		// Use the application context, which will ensure that you
		// don't accidentally leak an Activity's context.
		if (sInstance == null) {
			sInstance = new DBHelper(databaseName, context.getApplicationContext());
		}
		return sInstance;
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);// call super (anti-patten)

		// Called everytime a DB connection is opened. We activate foreing keys
		// to have ON_CASCADE deletion
		// If API LEVEL < 16, use this
		db.execSQL("PRAGMA foreign_keys = ON");

		// If API LEVEL > 16, use this
		db.setForeignKeyConstraintsEnabled(true);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createDB(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// In case of upgrade distinct versions, e.g. from 1 to 4
		switch (oldVersion) {
		case 1:
			// Upgrades for version 1 => 2
			Log.i("DBHelper", "Migratin from V1 to V2");

			break;
		case 2:
			// upgrades for version 2 ==> 3

		case 3:
			// upgrades for version 3 ==> 4

		default:
			break;
		}
	}

	/**
	 * Runs through all text strings in the array where the scripts are
	 * 
	 * @param db
	 */
	private void createDB(SQLiteDatabase db) {
		for (String sql : CREATE_DATABASE_SCRITPS) {
			db.execSQL(sql);
		}
	}

	// ********* Convenience methods to convert types ********* //
	// In SQLite, 1 = true and 0 = false
	public static int convertBooleanToInt(boolean b) {
		return b ? 1 : 0;
	}

	public static boolean convertIntToBoolean(int b) {
		return b == 0 ? false : true;
	}

	public static Long convertDateToLong(Date date) {
		if (date != null) {
			return date.getTime();
		}
		return null;
	}

	public static Date convertLongToDate(Long dateAsLong) {
		if (dateAsLong == null) {
			return null;
		}
		return new Date(dateAsLong);

	}

}
