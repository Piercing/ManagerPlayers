package io.devspain.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import io.devspain.fragments.PreferencesFragment;

public class PreferencesUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(android.R.id.content, new PreferencesFragment());
		ft.commit();// DON'T FORGET!!!
	}

}