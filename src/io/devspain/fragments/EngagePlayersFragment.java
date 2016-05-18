package io.devspain.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import io.devspain.R;
import io.devspain.activity.LoadPlayersActivity;

public class EngagePlayersFragment extends ListFragment {
	String[] players = { "Thibout Courtouis", "John Terry", "Oscar", "Eden Hazard", "Diego Costa", "Petr Cech", "Didier Drogba",
			"Branislav Ivanovic" };

	String[]	dataNamesPlayers	= LoadPlayersActivity.namePlayers;
	Bundle		bundle				= getArguments();
	String		getDataRowPlayer	= bundle.getString("str");
	// Instance Interface
	private OnPlayerSelectedListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create adapter and set it to listView
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players));

		// if (getActivity().findViewById(R.id.load_players).isActivated()) {
		// // Create adapter and set it to listView
		// ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
		// setListAdapter(adapter);
		// } else if (!getActivity().findViewById(R.id.load_players).isActivated()) {
		// ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
		// setListAdapter(adapter);
		// }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_engage_tab, container, false);
	}

	// Interface one to communicate with the activity
	public interface OnPlayerSelectedListener {
		public void onPlayerSelected(String str);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// We get the selected row (player)
		listener.onPlayerSelected(players[position]);

		Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

	}

	@SuppressWarnings("deprecation")
	@Override
	// Verify that the host Activity implements the interface.
	// "OnAttach ()" method will be called each time the activity create an instance of the fragment.
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			listener = (OnPlayerSelectedListener) activity;
		} catch (ClassCastException e) {
			e.getStackTrace();
		}
	}

}
