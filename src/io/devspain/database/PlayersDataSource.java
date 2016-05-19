package io.devspain.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PlayersDataSource {

	// Metainformación de la base de datos
	public static final String	TABLE_PLAYERS	= "Players";
	public static final String	STRING_TYPE		= "text";
	public static final String	INT_TYPE		= "integer";

	// Table fields constants
	public static class ColumsPlayers {
		public static final String	KEY_PLAYER_CODE		= "BaseColumns._code";
		public static final String	KEY_PLAYER_NAME		= "name";
		public static final String	KEY_PLAYER_AGE		= "age";
		public static final String	KEY_PLAYER_STATE	= "state";
	}

	// Script de Creación de la tabla Players
	public static final String CREATE_PLAYERS_SCRIPT = "create table " + TABLE_PLAYERS + "(" + ColumsPlayers.KEY_PLAYER_CODE + " "
			+ INT_TYPE + " primary key autoincrement," + ColumsPlayers.KEY_PLAYER_NAME + " " + STRING_TYPE + ColumsPlayers.KEY_PLAYER_AGE
			+ " " + STRING_TYPE + ColumsPlayers.KEY_PLAYER_AGE + " " + STRING_TYPE + " )";

	// Scripts de inserción por defecto
	public static final String INSERT_PLAYERS_SCRIPT = "insert into " + TABLE_PLAYERS + " values(" + "null," + "\"FIGO\"," + "\"42\","
			+ "\"true\" )," + "(null," + "\"RAUL\"," + "\"43\"," + "\"true\" )," + "(null," + "\"ARBELOA\"," + "\"34\"," + "\"true\" ),"
			+ "(null," + "\"MARADONA\"," + "\"54\"," + "\"true\" )," + "(null," + "\"ZIDANE\"," + "\"45\"," + "\"false\" )" + ")";

	// Private variables
	// las usaremos en el constructor para acceder a la base de datos con el método
	// getWritableDatabase(), el cual retorna en una instancia de SQLiteDatabase que
	// nos permitirá leer y modificar la información de la base de datos directamente
	private PlayersDbHelper	openHelper;
	private SQLiteDatabase	database;

	public PlayersDataSource(Context context) {
		// Creando una instancia hacia la base de datos
		openHelper = new PlayersDbHelper(context);
		database = openHelper.getWritableDatabase();
	}
}
