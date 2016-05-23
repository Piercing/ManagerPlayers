package io.devspain.database;

/**
 * With this class I avoid the strong coupling between classes
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class DBConstants {

	public static final String TABLE_PLAYERS = "Players";
	// Table fields constants, // Metainformación de la base de datos
	public static final String	KEY_PLAYER_ID		= "_id";
	public static final String	KEY_PLAYER_CODE		= "_code";
	public static final String	KEY_PLAYER_NAME		= "name";
	public static final String	KEY_PLAYER_AGE		= "age";
	public static final String	KEY_PLAYER_STATE	= "state";
	public static final String	STRING_TYPE			= "text";
	public static final String	INT_TYPE			= "integer";

	// Table fields constants
	public static class ColumsPlayers {
		public static final String	KEY_PLAYER_CODE		= "BaseColumns._code";
		public static final String	KEY_PLAYER_NAME		= "name";
		public static final String	KEY_PLAYER_AGE		= "age";
		public static final String	KEY_PLAYER_STATE	= "state";
	}

	// Script de Creación de la tabla Players, distintas versiones
	public static final String CREATE_PLAYERS_SCRIPT = "create table " + TABLE_PLAYERS + "(" + ColumsPlayers.KEY_PLAYER_CODE + " "
			+ INT_TYPE + " primary key autoincrement," + ColumsPlayers.KEY_PLAYER_NAME + " " + STRING_TYPE + ColumsPlayers.KEY_PLAYER_AGE
			+ " " + STRING_TYPE + ColumsPlayers.KEY_PLAYER_AGE + " " + STRING_TYPE + " )";

	// Scripts de inserción por defecto
	public static final String INSERT_PLAYERS_SCRIPT = "insert into " + TABLE_PLAYERS + " values(" + "null," + "\"FIGO\"," + "\"42\","
			+ "\"true\" )," + "(null," + "\"RAUL\"," + "\"43\"," + "\"true\" )," + "(null," + "\"ARBELOA\"," + "\"34\"," + "\"true\" ),"
			+ "(null," + "\"MARADONA\"," + "\"54\"," + "\"true\" )," + "(null," + "\"ZIDANE\"," + "\"45\"," + "\"false\" )" + ")";

	public static final String SQL_CREATE_PLAYERS_TABLE = "create table " + TABLE_PLAYERS + " ( " + KEY_PLAYER_ID
			+ " integer primary key autoincrement, " + KEY_PLAYER_CODE + " integer , " + KEY_PLAYER_NAME + " text, " + KEY_PLAYER_AGE
			+ " text, " + KEY_PLAYER_STATE + " text" + ")";

	public static final String	SQL_CREATE_PLAYERS_TABLE2	= "create table Players(_code integer primary key autoincrement, name text, age text, state text)";
	public static final String	DBNAME						= "Players";

	public static final String[]	allColumns		= { KEY_PLAYER_ID, KEY_PLAYER_CODE, KEY_PLAYER_NAME, KEY_PLAYER_AGE, KEY_PLAYER_STATE };
	public static final int			VERSION_CODE	= 1;
}
