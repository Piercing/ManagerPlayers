package io.devspain.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import io.devspain.R;

/**
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class ShowScreenPlayersActivity extends Activity implements OnClickListener {

	private static final int menu_configuracion = 1;
	private static final int menu_linterna = 2;
	private static final int menu_visitar_url = 3;
	private static final int menu_linterna_encender = 4;
	private static final int menu_linterna_apagar = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Set view content
		setContentView(R.layout.show_screen_players_activity);
		// Get reference to button, sincronayze view-control
		Button showScreenPlayersBtn = (Button) findViewById(R.id.btn_show_players);
		// Set event by clicking on the button
		showScreenPlayersBtn.setOnClickListener(this);
	}

	@Override
	// Add listener on click button
	public void onClick(View v) {
		Log.v("Listener", "Calling show screen players on press button");
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
			// Launch activity PreferencesActivity, with creating a explicit intent 
			Intent intent = new Intent(this, PreferencesActivity.class);
			// We ask Android you launch the explicit intent to display the screen
			startActivity(intent);
			
			break;

		case R.id.management_players:
			if (id == R.id.load_players) {
				// Launch activity LoadPlayersActivity

			} else if (id == R.id.delete_players) {
				// Launch activity DeletePlayersActivity

			}

			break;

		default:
			return true;

		}

		return returnSuper;
	}

	// Éste dice qué pasa al pulsar una opción de menú

}
