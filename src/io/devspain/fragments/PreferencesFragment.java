package io.devspain.fragments;

import java.util.HashSet;
import java.util.Set;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import io.devspain.R;
import io.devspain.database.DBConstants;
import io.devspain.database.PlayersDAO;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PreferencesFragment extends PreferenceFragment {

	public static Set<String>	transferPlayers		= null;
	public static Set<String>	profitablePlayers	= null;
	public static Set<String>	disastrousPlayers	= null;
	public static String		nameUser			= null;
	PlayersDAO					playersDao;

	// Default constructor
	public PreferencesFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Add preferences from resource
		addPreferencesFromResource(R.xml.preferences);

		// Get data Preferences
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

		// Get parameter of players & user
		nameUser = preferences.getString("name", "");
		transferPlayers = preferences.getStringSet("transfer_players", new HashSet<String>());
		profitablePlayers = preferences.getStringSet("profitable_players", new HashSet<String>());
		disastrousPlayers = preferences.getStringSet("disastrous_players", new HashSet<String>());
	}

	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// return super.onCreateView(inflater, container, savedInstanceState);
	// }

	/**
	 * Check application startup
	 * 
	 * @return
	 */
	public int appGetFirstTimeRun() {
		// Check if App Start First Time
		SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		int appCurrentBuildVersion = DBConstants.VERSION_CODE;
		int appLastBuildVersion = appPreferences.getInt("app_first_time", 0);

		Log.d("appPreferences", "app_first_time = " + appLastBuildVersion);

		if (appLastBuildVersion == appCurrentBuildVersion) {
			return 1; // ya has iniciado la app alguna vez

		} else {
			appPreferences.edit().putInt("app_first_time", appCurrentBuildVersion).apply();
			if (appLastBuildVersion == 0) {
				return 0; // es la primera vez
			} else {
				return 2; // es una versión nueva
			}
		}
	}

}
