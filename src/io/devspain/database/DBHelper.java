package io.devspain.database;

import java.lang.ref.WeakReference;
import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	// Versioned of the DDBB
	public static final int DATABASE_VERSION = 1;

	// Scripts for create DDBB tables
	public static final String[] CREATE_DATABASE_SCRITPS = { DBConstants.SQL_CREATE_PLAYERS_TABLE };

	// Update DDBB
	public static final String DROP_DATABASE = "";

	// Instance of the DDBB
	private static DBHelper sInstance;
	// Reference to name DDBB
	private static String dbName;
	// Siempre que nos quedemos con un contexto guardamos una referencia 'weak', good practice
	private static WeakReference<Context> weakContext;

	// Constructor DBHelper. The method constructor is private, only a instance
	// ==> SINLGETON PATTERN
	private DBHelper(String nameDB, Context context) {
		// If version number is distinct of 'DATABASE_VERSION' current,
		// automatically applies the method 'onUpgrade'
		super(context, nameDB, null, DATABASE_VERSION);
	}

	/**
	 * Method for save reference weak to Context & save dataBase name
	 * 
	 * It initializes for the first time, and only once, the database and context
	 * 
	 * Configuro primero el DBHelper y luego lo llamo en getInstance
	 * 
	 * @param databaseName,
	 * @param context
	 */
	public static void configure(final String databaseName, final Context context) {
		// Save name DDBB in dbName, first time, and only once
		dbName = databaseName;
		// Reference weak to context, first time and only once
		weakContext = new WeakReference<Context>(context);
	}

	// If not created the instance creates an instance, and if the instance is created returns that there was.

	public static DBHelper getInstance() {
		// If I have no the name && context, call method 'configure'
		if (dbName == null && weakContext == null) {
			throw new IllegalStateException("No database name privide, no context");
		}
		// Use the application context, which will ensure that you don't accidentally leak an Activity's context.
		if (sInstance == null) {
			// First time it's call create DBHelper
			sInstance = new DBHelper(dbName, weakContext.get().getApplicationContext());
		}
		return sInstance;
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);// call super (anti-patten)

		// Called everytime a DB connection is opened. We activate foreing keys
		// to have ON_CASCADE deletion
		// If API LEVEL < 16, use this
		// db.execSQL("PRAGMA foreign_keys = ON");

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

	public static int convertStringToInt(String s) {
		int newInt = Integer.parseInt(s);
		return newInt;
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
