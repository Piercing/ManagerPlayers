package io.devspain.fragments;

import java.util.LinkedList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import io.devspain.R;
import io.devspain.activity.EditionPlayerActivity;
import io.devspain.activity.ShowScreenPlayersMainActivity;
import io.devspain.models.Player;

public class RetirePlayersFragment extends ListFragment {
	String[] playersRetire = { "Diego Maradona", "David Arbeloa", "Raul Gonzalez", "Xavi Hernández", "David Villa", "Iker Casillas",
			"Fernado Torres", "Zinadine Zidane" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		updatePlayersData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_engage_tab, container, false);
	}

	// Interface one to communicate with the activity
	public interface OnPlayerSelectedListener {
		public void onPlayerSelected(String str);
	}

	public static String itemValue;

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {

		// Get string value where they click on the list
		itemValue = (String) listView.getItemAtPosition(position);
		// Show new activity through a intent
		Intent newActivity = new Intent(getActivity(), EditionPlayerActivity.class);
		// Put:pass selected data to the activity with identifier
		newActivity.putExtra("name", itemValue);

		// Here we spend the parameter of intent previously created
		startActivity(newActivity);

		// We get the selected row (player)
		// listener.onPlayerSelected(players[position]);

		Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
	}

	// TODO: REFACTOR
	public void updatePlayersData() {

		// Text for the list emtpy
		// mList.add("Cargar jugadores en el menú preferencias desde la pantalla de incio para verlos");
		// Set List Adapter ==> mList, first time empty list
		try {
			if (getPlayersListToArrayPlayers() == null) {
				setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, playersRetire));

			} else if (getPlayersListToArrayPlayers() != null) {
				// Aquí tenemos datos, en vez de una lista vacía le paso
				// lo que hay en la namePlayers y los mostramos en la lista
				setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, playersRetire));

			} else {
				Builder alertDialog = new AlertDialog.Builder(getActivity().getApplicationContext());
				alertDialog.setTitle("Error");
				alertDialog.setMessage("No se pudieron obtener los jugadores, configure sus preferencias en el menú");
				alertDialog.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Si pulsa en reintentar la descarga o la conexión volvemos a llamarnos
						updatePlayersData();
					}
				});
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// Change player list to array player
	public static String[] getPlayersListToArrayPlayers() {

		String[] namePlayers = null;
		// Check if players list is empty, if this list is null create a new list
		if (ShowScreenPlayersMainActivity.mList == null) {
			ShowScreenPlayersMainActivity.mList = new LinkedList<Player>();
		} else {
			// Convert list to array of players
			namePlayers = new String[ShowScreenPlayersMainActivity.mList.size()];
			for (int i = 0; i < ShowScreenPlayersMainActivity.mList.size(); i++) {
				namePlayers[i] = ShowScreenPlayersMainActivity.mList.get(i).getName();
			}
		}
		return namePlayers;
	}

	// public String[] getPlayersQuery() {
	// PlayersDAO playersDao;
	// // Get names players DDBB
	// Players list = playersDao.query();
	//
	// // Convert list to array of players
	// namePlayers = new String[list.size()];
	// for (int i = 0; i < list.size(); i++) {
	// namePlayers[i] = list.get(i).getName();
	// }
	// return namePlayers;
	// }
}
