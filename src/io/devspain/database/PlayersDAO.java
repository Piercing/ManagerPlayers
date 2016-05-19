package io.devspain.database;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import io.devspain.models.Player;
import io.devspain.models.Players;

/**
 * DATA ACCESS OBJECT (DAO) ==> Design Pattern
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class PlayersDAO {

	// No code player can be zero
	private static final long	INVALID_CODE_DELETE_ALL_RECORDS	= 0;
	private DBHelper			db;

	// Constructor for PlayersDAO
	public PlayersDAO() {
		// Extract one instance to save me a local copy
		db = DBHelper.getInstance();
	}

	public long insert(Player player) {
		// Check it player exist
		if (player == null) {
			throw new IllegalArgumentException("Passing NULL player");
		}
		// INSERT
		// Write into Table players, 3º parameters map with pair Key/Value (Column/Column value to insert)
		// 2º paraemeter = null for fields null
		long code = db.getWritableDatabase().insert(DBConstants.TABLE_PLAYERS, null, getContentValues(player));
		// For each insertion I assign the code to the player new
		player.setCode(code);
		db.close();

		// Return code autogenerate for each player.
		return code;
	}

	public int update(long code, Player player) {

		if (player == null) {
			throw new IllegalArgumentException("Passing NULL player");
		}
		if (code < 1) {
			throw new IllegalArgumentException("Passing code invalid");
		}
		// Update table with values that contains 'getContentValues(player)
		// Clausure where ==> First way: 'KEY_PLAYER_CODE + "=?", new String[] {
		// "" + code }'
		// To prevent SQL injection code in a query by a user ==> Safely with
		// positional marker ==> ?
		int NumberOfRowsUpdate = db.getWritableDatabase().update(DBConstants.TABLE_PLAYERS, getContentValues(player),
				DBConstants.KEY_PLAYER_CODE + "=?", new String[] { "" + code });
		// Second way: a simple way
		// int NumberOfRowsUpdate2 =
		// db.getWritableDatabase().update(TABLE_PLAYERS,
		// this.getContentValues(player), KEY_PLAYER_CODE + "=" + code, null);

		db.close();
		return NumberOfRowsUpdate;
	}

	// Delete table players or delete player with clause WHERE ==> KEY_PLAYER_CODE + " = " + code
	public void delete(long code) {
		if (code == INVALID_CODE_DELETE_ALL_RECORDS) {
			db.getWritableDatabase().delete(DBConstants.TABLE_PLAYERS, null, null);
		} else {
			db.getWritableDatabase().delete(DBConstants.TABLE_PLAYERS, DBConstants.KEY_PLAYER_CODE + " = " + code, null);
		}
		db.close();
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
	public static ContentValues getContentValues(Player player) {
		ContentValues content = new ContentValues();
		// Put pair key/value
		content.put(DBConstants.KEY_PLAYER_NAME, player.getName());
		content.put(DBConstants.KEY_PLAYER_AGE, player.getAge());
		content.put(DBConstants.KEY_PLAYER_STATE, player.getState());
		return content;
	}

	/**
	 * Returns a Player object from its code
	 * 
	 * @param code,
	 *            the player code in db
	 * @return Player object if found, null otherwise
	 */
	public @NonNull Player query(long code) {
		Player player = null;

		// Clausule where
		String where = DBConstants.KEY_PLAYER_CODE + "=" + code;
		// Get data from table players, where the code is that I receive as parameter
		Cursor cursor = db.getReadableDatabase().query(DBConstants.TABLE_PLAYERS, DBConstants.allColumns, where, null, null, null, null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				// Get player with the received code
				player = elementFromCursor(cursor);
			}
			// Close cursor
			cursor.close();
		}
		// Close DDBB
		db.close();
		// Return player with the id searched
		return player;
	}

	/**
	 * returns all players in DB inside a Cursor
	 * 
	 * @return cursor with all records
	 */
	public Cursor queryCursor() {
		// SELECT
		// Get cursor of read for all columns orderBy Code, equals 'SELECT *' public Cursor query(String table, String[] columns, String
		// selection,String[] selectionArgs, String groupBy, String having, String orderBy)
		Cursor cursor = db.getReadableDatabase().query(DBConstants.TABLE_PLAYERS, DBConstants.allColumns, null, null, null, null,
				DBConstants.KEY_PLAYER_NAME);
		return cursor;
	}

	public Players query() {
		// List of player that contains players
		List<Player> playerList = new LinkedList<Player>();

		// Call Query cursor, get cursor open with data players, name, code, etc
		Cursor cursor = queryCursor();
		// If the cursor has data
		if (cursor != null && cursor.getCount() > 0) {
			// Move to first register
			cursor.moveToFirst();
			do {
				// Create player with the data obtained
				Player player = elementFromCursor(cursor);
				// Add players to playerList
				playerList.add(player);
				// Tour the cursor
			} while (cursor.moveToNext());
		}
		Players players = Players.createPlayers(playerList);
		// Return players
		return players;
	}

	// Convenience method, add nulable notations for developers, cursor not possible null
	public static @NonNull Player elementFromCursor(@NonNull Cursor cursor) {
		// Solo están en los builder debug cuando están activos
		// Nunca vendrá a null el cursor, porque en mi proyecto construyo el cursor antes de llegar aquí, por tanto
		// sé que no viene nunca a null mi cursor, pero por si acaso meto una guarda por si me viniera a null.
		// Activo la aserción
		assert cursor != null;

		// Get the index of read column, final ==> inmutable data
		final String name = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_PLAYER_NAME));
		final int age = cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_PLAYER_AGE));
		final long code = cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_PLAYER_CODE));
		final String state = cursor.getString(cursor.getColumnIndex(DBConstants.KEY_PLAYER_STATE));

		// Convert state to boolean
		int stateConvertInt = DBHelper.convertStringToInt(state);
		Boolean stateConvertBool = DBHelper.convertIntToBoolean(stateConvertInt);

		// Build the obtained player
		Player player = new Player(name, age, stateConvertBool, code);
		// Return player, inmutable reference
		return player;
	}

}
