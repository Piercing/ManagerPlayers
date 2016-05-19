package io.devspain.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import io.devspain.R;
import io.devspain.database.PlayersDAO;
import io.devspain.fragments.EngagePlayersFragment;

/**
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class ShowScreenPlayersMainActivity extends Activity implements OnClickListener {

	public static boolean	flagPreferencesManager	= false;
	EngagePlayersFragment	loadPlayers;
	PlayersDAO				playersDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view content
		setContentView(R.layout.activity_show_screen_players);
		// Get reference to button, sincronayze view-control
		Button showScreenPlayersBtn = (Button) findViewById(R.id.btn_show_players);
		// Set event by clicking on the button
		showScreenPlayersBtn.setOnClickListener(this);

		// This method saves all attribute values defaultValue preferences in
		// SharedPreferences.
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		// Set default values tue
		flagPreferencesManager = true;

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
			// We ask Android you launch the explicit intent to display the
			// screen
			startActivity(intentPreferences);
			break;

		case R.id.load_players:
			// Get playes of Preferences user
			loadPlayers.insertDataPlayers();
			break;

		case R.id.delete_players:
			// Delete players of DDBB
			playersDao.deleteAll();
			break;

		default:
			return true;
		}

		return returnSuper;
	}
}
