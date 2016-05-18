package io.devspain.activity;

import android.app.Activity;
import android.os.Bundle;
import io.devspain.R;
import io.devspain.database.PlayersDAO;
import io.devspain.models.Players;

public class LoadPlayersActivity extends Activity {

	PlayersDAO				playersDao;
	public static String[]	namePlayers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_players);

		// Get names players DDBB
		Players list = playersDao.query();

		// Convert list to array of players
		namePlayers = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			namePlayers[i] = list.get(i).getName();
		}
	}
}
