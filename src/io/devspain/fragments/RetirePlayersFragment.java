package io.devspain.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
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
import io.devspain.database.PlayersDAO;
import io.devspain.models.Players;

public class RetirePlayersFragment extends ListFragment {
	String[] players = { "Diego Maradona", "David Arbeloa", "Raul Gonzalez", "Xavi Hernández", "David Villa", "Iker Casillas",
			"Fernado Torres", "Zinadine Zidane" };

	public static String[]	namePlayers;
	PlayersDAO				playersDao;
	Players					mPlayers	= new Players();

	// String[] dataNamesPlayers = LoadPlayersActivity.namePlayers;
	Bundle bundle = getArguments();
	// String getDataRowPlayer = bundle.getString("str");

	private List<String> mList = new ArrayList<String>();

	// Instance Interface
	private OnPlayerSelectedListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		updatePlayersData();
	}

	// TODO: REFACTOR
	public void updatePlayersData() {

		// Text for the list emtpy
		mList.add("Cargar jugadores en el menú preferencias desde la pantalla de incio para verlos");
		// Set List Adapter ==> mList, first time empty list
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mList));
		try {

			if (EngagePlayersFragment.getPlayersListToArrayPlayers() != null) {
				// Aquí tenemos datos, en vez de una lista vacía le paso
				// lo que hay en la namePlayers y los mostramos en la lista
				setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players));

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

	// public String[] getPlayers() {
	//
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_engage_tab, container, false);
	}

	// Interface one to communicate with the activity
	public interface OnPlayerSelectedListener {
		public void onPlayerSelected(String str);
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {

		// Get string value where they click on the list
		String itemValue = (String) listView.getItemAtPosition(position);
		// Show new activity through a intent
		Intent newActivity = new Intent(getActivity(), EditionPlayerActivity.class);
		// Put:pass selected data to the activity with identifier
		newActivity.putExtra("player", itemValue);
		// Here we spend the parameter of intent previously created
		startActivity(newActivity);

		Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

	}

	@Override
	// Verify that the host Activity implements the interface.
	// "OnAttach ()" method will be called each time the activity create an instance of the fragment.
	public void onAttach(Context context) {
		super.onAttach(context);
		try {
			listener = (OnPlayerSelectedListener) context;
		} catch (ClassCastException e) {
			e.getStackTrace();
		}
	}

	// Este es el caso contrario al 'onAttach', cuando el fragment deja de
	// estar dentro de mi actividad es decirle que 'CityListListener' es null
	// Es decir, cuando lo eliminemos y deje de estar disponible, ahorrando memoria.
	@Override
	public void onDetach() {
		super.onDetach();
		listener = null;
	}

}
