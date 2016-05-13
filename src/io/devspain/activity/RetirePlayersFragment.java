package io.devspain.activity;

import com.tutorials.tabswithlistview.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RetirePlayersFragment extends ListFragment {
	String[] players = { "Aaron Ramsey", "Jack Wilshere", "Mesut Ozil", "Alexis Sanchez", "Per Metesacker",
			"Keiron Gibbs", "Laurent Koscielny", "Olivier Giroud" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.retire_tab, container, false);

		// CREATE ADAPTER AND SET IT TO LV
		ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
		setListAdapter(adapter);

		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
	}

}
