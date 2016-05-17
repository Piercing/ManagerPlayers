package io.devspain.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import io.devspain.R;
import io.devspain.activity.LoadPlayersActivity;

public class EngagePlayersFragment extends ListFragment {
	String[] players = { "Thibout Courtouis", "John Terry", "Oscar", "Eden Hazard", "Diego Costa", "Petr Cech", "Didier Drogba",
			"Branislav Ivanovic" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_engage_tab, container, false);

		// Create adapter and set it to listView
		ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, LoadPlayersActivity.namePlayers);
		setListAdapter(adapter);

		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

	}

}