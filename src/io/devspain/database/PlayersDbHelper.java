package io.devspain.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlayersDbHelper extends SQLiteOpenHelper {

	// Atributes for data base name & data base version
	public static final String	DATABASE_NAME		= "Players.db";
	public static final int		DATABASE_VERSION	= 1;

	public PlayersDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Crear la tabla Quotes
		db.execSQL(PlayersDataSource.CREATE_PLAYERS_SCRIPT);
		// Insertar registros iniciales
		db.execSQL(PlayersDataSource.INSERT_PLAYERS_SCRIPT);
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

}
