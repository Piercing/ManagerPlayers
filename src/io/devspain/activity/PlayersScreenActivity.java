package io.devspain.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import io.devspain.R;
import io.devspain.fragments.EngagePlayersFragment;
import io.devspain.fragments.EngagePlayersFragment.OnPlayerSelectedListener;
import io.devspain.fragments.RetirePlayersFragment;

public class PlayersScreenActivity extends FragmentActivity implements ActionBar.TabListener, OnPlayerSelectedListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get and setup actionBar on navigation mode tabs
		final ActionBar ab = getActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Add the tabs
		ab.addTab(ab.newTab().setText("Para Jubilar").setTabListener(this));
		ab.addTab(ab.newTab().setText("para Fichar").setTabListener(this));

		// Charge the layout of fragmente
		setContentView(R.layout.activity_edition_player);

		FragmentManager FM = getFragmentManager();
		FragmentTransaction FT = FM.beginTransaction();

		Fragment fragmentEngage = new Fragment();
		FT.replace(R.id.container, fragmentEngage);
		FT.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		// First tab show engage Player
		if (tab.getPosition() == 0) {
			EngagePlayersFragment engage = new EngagePlayersFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, engage).commit();
		} else if (tab.getPosition() == 1) {
			RetirePlayersFragment retire = new RetirePlayersFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, retire).commit();
		}
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerSelected(String str) {
		Fragment fragmentEngageSelected = new Fragment();

		Bundle args = new Bundle();
		args.putString("str", str);
		fragmentEngageSelected.setArguments(args);

		FragmentManager FM = getFragmentManager();
		FragmentTransaction FT = FM.beginTransaction();

		FT.replace(R.id.container, fragmentEngageSelected);
		FT.addToBackStack(null);

		FT.commit();
	}

}