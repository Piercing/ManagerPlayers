package io.devspain.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import io.devspain.R;
import io.devspain.database.DBConstants;
import io.devspain.database.DBHelper;
import io.devspain.database.PlayersDAO;
import io.devspain.fragments.EngagePlayersFragment;
import io.devspain.fragments.PreferencesFragment;
import io.devspain.models.Player;
import io.devspain.models.Players;

public class EditionPlayerActivity extends Activity implements android.view.View.OnClickListener {

	int				code	= 0;
	Bundle			parameters;
	Player			player;
	TextView		playerAge;
	TextView		playerCode;
	TextView		playerName;
	TextView		playerState;
	CheckBox		checkBoxRetire;
	ContentValues	values;
	Players			players;
	int				castIndexToInt;
	long			castCodeToInt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Charge the layout
		setContentView(R.layout.activity_edition_player);

		if (PlayersScreenActivity.tab.getPosition() == 0) {
			// Synchronize activity/view
			playerCode = (TextView) findViewById(R.id.player_code);
			playerState = (TextView) findViewById(R.id.player_state);
			playerName = (TextView) findViewById(R.id.player_name);
			checkBoxRetire = (CheckBox) findViewById(R.id.checkbox_retire);
			playerAge = (TextView) findViewById(R.id.player_state);

			// Obtain the intent of coming
			parameters = getIntent().getExtras();
			if (parameters != null) {
				playerName.setText(parameters.getString("name"));
			}
		}

		if (PlayersScreenActivity.tab.getPosition() == 1) {
			// Synchronize activity/view
			playerCode = (TextView) findViewById(R.id.player_code);
			playerState = (TextView) findViewById(R.id.player_state);
			playerName = (TextView) findViewById(R.id.player_name);
			checkBoxRetire = (CheckBox) findViewById(R.id.checkbox_retire);
			playerAge = (TextView) findViewById(R.id.player_state);

			// Obtain the intent of coming
			parameters = getIntent().getExtras();
			if (parameters != null) {
				playerName.setText(parameters.getString("name"));
			}
		}

		// Creat a button
		Button saveBtn = (Button) findViewById(R.id.btn_save_changes_players);

		// Set event by clicking on the button
		saveBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		values = new ContentValues();
		// Query at DDBB
		players = PlayersDAO.query();
		// Get the position of EngagePlayersFragment
		String index = EngagePlayersFragment.itemValue;
		// Cast positon String to int
		castIndexToInt = DBHelper.convertStringToInt(index);
		// Cast data textView code
		castCodeToInt = DBHelper.convertStringToInt((String) playerCode.getText());
		// Uodate player in DDBB
		updatePlayersDB();
	}

	private void updatePlayersDB() {

		// Iterate the query results
		for (int i = 0; i < players.size(); i++) {

			// Get position & code of player selected
			player = players.getPlayers().get(castIndexToInt);
			code = (int) players.getPlayers().get(castIndexToInt).getCode();

			// If the code player of the query of Players is equals to code of view
			if (code == castCodeToInt) {
				// Cast String age to int
				int ageToInt = DBHelper.convertStringToInt((String) playerAge.getText());
				// Check value age
				if (ageToInt >= 16 || ageToInt <= 50) {
					values.put(DBConstants.KEY_PLAYER_AGE, ageToInt);
				}
				// If checkBox is checked
				if (checkBoxRetire.isChecked()) {
					// State player is for "Para Jubilar"
					values.put(DBConstants.KEY_PLAYER_STATE, "Para Jubilar");
				} else {
					// Else, player is for "Para Retirar"
					values.put(DBConstants.KEY_PLAYER_STATE, "Para Retirar");
				}
			}
			if (player.equals(PreferencesFragment.disastrousPlayers)) {
				putColors();
			}
		}
		// Update player DDBB
		PlayersDAO.update(code, player);
	}

	// Set color red to text player name
	public void putColors() {
		playerName.setTextColor(playerName.getContext().getResources().getColor(R.color.ROJO));
	}
}
