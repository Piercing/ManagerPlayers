package io.devspain.activity;

import com.tutorials.tabswithlistview.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author merlosalbarracin@gmail.com
 *
 */
public class ShowScreenPlayersActivity extends Activity implements OnClickListener {

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
		// Define activity
		Intent intent = new Intent(this, PlayersScreenActivity.class);

		// Init activity
		startActivity(intent);
	}
}
