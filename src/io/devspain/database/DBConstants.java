package io.devspain.database;

/**
 * With this class I avoid the strong coupling between classes
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class DBConstants {

	public static final String TABLE_PLAYERS = "Players";

	// Table fields constants
	public static final String	KEY_PLAYER_ID		= "_id";
	public static final String	KEY_PLAYER_CODE		= "_code";
	public static final String	KEY_PLAYER_NAME		= "name";
	public static final String	KEY_PLAYER_AGE		= "age";
	public static final String	KEY_PLAYER_STATE	= "state";

	public static final String SQL_CREATE_PLAYERS_TABLE = "create table " + TABLE_PLAYERS + " ( " + KEY_PLAYER_ID
			+ " integer primary key autoincrement, " + KEY_PLAYER_CODE + " integer , " + KEY_PLAYER_NAME + " text, " + KEY_PLAYER_AGE
			+ " text, " + KEY_PLAYER_STATE + " text" + ")";

	public static final String	SQL_CREATE_PLAYERS_TABLE2	= "create table Players(_code integer primary key autoincrement, name text, age text, state text)";
	public static final String	DBNAME						= "Players";

	public static final String[]	allColumns		= { KEY_PLAYER_ID, KEY_PLAYER_CODE, KEY_PLAYER_NAME, KEY_PLAYER_AGE, KEY_PLAYER_STATE };
	public static final int			VERSION_CODE	= 1;
}
