package io.devspain.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import io.devspain.R;
import io.devspain.database.PlayersDAO;
import io.devspain.database.PlayersSQLiteHelper;
import io.devspain.fragments.EngagePlayersFragment;
import io.devspain.fragments.PreferencesFragment;
import io.devspain.models.Player;

/**
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class ShowScreenPlayersMainActivity extends Activity implements OnClickListener {

	public static boolean		flagPreferencesManager	= false;
	EngagePlayersFragment		loadPlayers;
	PlayersDAO					playersDao;
	private SQLiteDatabase		db;
	public static List<Player>	mList;

	Player	playerTransfer;
	Player	playerProfitable;
	Player	playerDisastrous;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view content
		setContentView(R.layout.activity_show_screen_players);
		// Get reference to button, sincronayze view-control
		Button showScreenPlayersBtn = (Button) findViewById(R.id.btn_show_players);
		// Set event by clicking on the button
		showScreenPlayersBtn.setOnClickListener(this);

		// This method saves all attribute values defaultValue preferences in SharedPreferences.
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		// Set default values tue
		flagPreferencesManager = true;

		// Open DDBB writing mode
		PlayersSQLiteHelper playesDataBaseHelper = new PlayersSQLiteHelper(this, "DBPlayers", null, 1);

		db = playesDataBaseHelper.getWritableDatabase();

		// String position = PlayersData.totalNamesPlayers.get(0);
		// Log.v("positon", position);
	}

	@Override
	// Add listener when click button
	public void onClick(View v) {
		// Log.v("Listener", "Calling show screen players on press button");
		// Define activity by means of intents
		Intent intent = new Intent(this, PlayersScreenActivity.class);
		// Init activity
		startActivity(intent);
	}

	// Create Menu, he says how is the menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	// It tells what happens when you press a menu option
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Always call first super
		boolean returnSuper = super.onOptionsItemSelected(item);

		// Get item id
		int id = item.getItemId();
		switch (id) {
		case R.id.preferences:
			// Launch activity PreferencesUserActivity, with a explicit intent
			Intent intentPreferences = new Intent(this, PreferencesUserActivity.class);
			// We ask Android you launch the explicit intent to display the screen
			startActivity(intentPreferences);
			break;

		case R.id.load_players:

			// TODO: REFACTOR
			// Get values of Preferences
			if (PreferencesFragment.transferPlayers != null) {
				for (String playerTP : PreferencesFragment.transferPlayers) {
					// Create new player witn only parameter => name & insert to DDBB
					playerTransfer = new Player(playerTP);
					mList.add(playerTransfer);
				}
			}

			if (PreferencesFragment.profitablePlayers != null) {
				for (String playerPP : PreferencesFragment.profitablePlayers) {
					playerProfitable = new Player(playerPP);
					mList.add(playerProfitable);
				}
			}

			if (PreferencesFragment.disastrousPlayers != null) {
				for (String playerDP : PreferencesFragment.disastrousPlayers) {
					playerDisastrous = new Player(playerDP);
					mList.add(playerDisastrous);
				}
			}

			try {
				// Insert players name into DDBB
				PlayersDAO.insert(playerTransfer);
				PlayersDAO.insert(playerProfitable);
				PlayersDAO.insert(playerDisastrous);
			} catch (Exception e) {
				e.getStackTrace();
			}

			// Save playes of user Preferences
			// loadPlayers.insertDataPlayers();
			break;

		case R.id.delete_players:

			// TODO: REFACTOR
			try {
				// Get all code players
				long codeTransfer = playerTransfer.getCode();
				long codeProfitable = playerProfitable.getCode();
				long codeDisastrous = playerDisastrous.getCode();
				// Delete players DDBB
				PlayersDAO.delete(codeTransfer);
				PlayersDAO.delete(codeProfitable);
				PlayersDAO.delete(codeDisastrous);

			} catch (Exception e) {
				e.getStackTrace();
			}

			// Delete players of DDBB
			// playersDao.deleteAll();
			break;
		default:
			return returnSuper;
		}

		return returnSuper;
	}
}
