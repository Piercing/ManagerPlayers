package io.devspain.database;

/**
 * With this class I avoid the strong coupling between classes
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class DBConstants {

	public static final String TABLE_PLAYERS = "PLAYERS";

	// Table fields constants
	public static final String	KEY_PLAYER_CODE		= "_code";
	public static final String	KEY_PLAYER_NAME		= "name";
	public static final String	KEY_PLAYER_AGE		= "age";
	public static final String	KEY_PLAYER_STATE	= "state";

	public static final String	SQL_CREATE_PLAYERS_TABLE	= "CREATE TABLE " + TABLE_PLAYERS + "( " + KEY_PLAYER_CODE
			+ " integer primary key autoincrement, " + KEY_PLAYER_NAME + " text, " + KEY_PLAYER_AGE + " text, " + KEY_PLAYER_STATE + " text"
			+ ")";
	public static final String	DBNAME						= "Players";

	public static final String[]	allColumns		= { KEY_PLAYER_CODE, KEY_PLAYER_NAME, KEY_PLAYER_AGE, KEY_PLAYER_STATE };
	public static final int			VERSION_CODE	= 1;
}
