package io.devspain.activity;

import com.tutorials.tabswithlistview.R;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// GET AND SETUP ACTIONBAR
		final ActionBar ab = getActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// ADD THE TABS
		ab.addTab(ab.newTab().setText("Para Jubilar").setTabListener(this));
		ab.addTab(ab.newTab().setText("para Fichar").setTabListener(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

		// FIRST TAB SHOW MAN UTD
		if (tab.getPosition() == 0) {
			EngagePlayersFragment c = new EngagePlayersFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, c).commit();
		} else if (tab.getPosition() == 1) {
			RetirePlayersFragment a = new RetirePlayersFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.container, a).commit();
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

}