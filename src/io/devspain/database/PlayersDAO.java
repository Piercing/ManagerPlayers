package io.devspain.database;

import java.lang.ref.WeakReference;

import android.content.ContentValues;
import android.content.Context;
import io.devspain.models.Players;

/**
 * DATA ACCESS OBJECT (DAO) ==> Design Pattern
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class PlayersDAO {

	public static final String TABLE_PLAYERS = "PLAYERS";

	// Table fields constants
	public static final String KEY_PLAYER_CODE = "_code";
	public static final String KEY_PLAYER_NAME = "name";
	public static final String KEY_PLAYER_AGE = "age";
	public static final String KEY_PLAYER_STATE = "state";

	public static final String SQL_CREATE_PLAYERS_TABLE = "create table " + TABLE_PLAYERS + "( " + KEY_PLAYER_CODE
			+ " integer primary key autoincrement, " + KEY_PLAYER_NAME + " text not null, " + KEY_PLAYER_AGE
			+ " text not null, " + KEY_PLAYER_STATE + " text not null" + ");";

	public static final String[] allColumns = { KEY_PLAYER_CODE, KEY_PLAYER_NAME, KEY_PLAYER_AGE, KEY_PLAYER_STATE };

	// No code can be zero
	private static final long INVALID_CODE_DELETE_ALL_RECORDS = 0;
	// Name io.devspain.database
	private final String databaseName;

	// Save context, reference Weak a context to avoid lifecycles, if context is
	// destroyed, this context = null
	private WeakReference<Context> context;

	// I give a name to the io.devspain.database,
	// call constructor ==> 'public PlayersDAO(String databaseName, Context
	// context)'
	// with 'this', and I pass a name and a contentx.
	public PlayersDAO(Context context) {
		this("Players.sqlite", context);
	}

	public PlayersDAO(String databaseName, Context context) {
		this.context = new WeakReference<Context>(context);
		this.databaseName = databaseName;

	}

	public long insert(Players player) {
		// Check it player exist
		if (player == null) {
			throw new IllegalArgumentException("Passing NULL player");
		}
		// check it context exist
		if (context.get() == null) {
			throw new IllegalArgumentException("Context NULL");
		}
		// Insert, USE SINGLETON FOR THE ONLY INSTANCE, 2º parameter ==>
		// WeakReference for context
		DBHelper db = DBHelper.getInstance(databaseName, context.get());
		// Write into Table players, 3º parameters map with pair Key/Value
		// (Column/Column value to insert)
		// 2º paraemeter = null for fields null
		long code = db.getWritableDatabase().insert(TABLE_PLAYERS, null, getContentValues(player));
		// For each insertion I assign the code to the player new
		player.setCode(code);
		db.close();
		// Object release
		db = null;

		// Return code autogenerate for each player.
		return code;
	}

	public int update(long code, Players player) {

		if (player == null) {
			throw new IllegalArgumentException("Passing NULL player");
		}
		if (code < 1) {
			throw new IllegalArgumentException("Passing code invalid");
		}
		if (context.get() == null) {
			throw new IllegalArgumentException("Context NULL");
		}

		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());
		// Update table with values that contains 'getContentValues(player)
		// Clausure where ==> First way: 'KEY_PLAYER_CODE + "=?", new String[] {
		// "" + code }'
		// To prevent SQL injection code in a query by a user ==> Safely with
		// positional marker ==> ?
		int NumberOfRowsUpdate = db.getWritableDatabase().update(TABLE_PLAYERS, getContentValues(player),
				KEY_PLAYER_CODE + "=?", new String[] { "" + code });
		// Second way: a simple way
		// int NumberOfRowsUpdate2 =
		// db.getWritableDatabase().update(TABLE_PLAYERS,
		// this.getContentValues(player), KEY_PLAYER_CODE + "=" + code, null);

		db.close();
		db = null;
		return NumberOfRowsUpdate;
	}

	// Delete table players or delete player with clause WHERE ==>
	// KEY_PLAYER_CODE + " = " + code
	public void delete(long code) {
		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		if (code == INVALID_CODE_DELETE_ALL_RECORDS) {
			db.getWritableDatabase().delete(TABLE_PLAYERS, null, null);
		} else {
			db.getWritableDatabase().delete(TABLE_PLAYERS, KEY_PLAYER_CODE + " = " + code, null);
		}
		db.close();
		db = null;
	}

	// Call to delete method, if code = 0, delete TABLE PLAYERS
	public void deleteAll() {
		delete(INVALID_CODE_DELETE_ALL_RECORDS);
	}

	/**
	 * Dictionary that can add key value pairs ==> ContentValues
	 * 
	 * @param player
	 * @return
	 */
	public static ContentValues getContentValues(Players player) {
		ContentValues content = new ContentValues();
		// Put pair key/value
		content.put(KEY_PLAYER_NAME, player.getName());
		content.put(KEY_PLAYER_AGE, player.getAge());
		content.put(KEY_PLAYER_STATE, player.getState());
		return content;

	}
}
