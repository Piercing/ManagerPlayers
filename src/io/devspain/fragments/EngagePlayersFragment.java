package io.devspain.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import io.devspain.R;
import io.devspain.database.PlayersDAO;
import io.devspain.models.Player;
import io.devspain.models.Players;

public class EngagePlayersFragment extends ListFragment {
	String[] players = { "Thibout Courtouis", "John Terry", "Oscar", "Eden Hazard", "Diego Costa", "Petr Cech", "Didier Drogba",
			"Branislav Ivanovic" };

	public static String[]	namePlayers;
	PlayersDAO				playersDao;
	Players					mPlayers;

	// String[] dataNamesPlayers = LoadPlayersActivity.namePlayers;
	Bundle bundle = getArguments();
	// String getDataRowPlayer = bundle.getString("str");

	private List<String> mList = new ArrayList<String>();

	// Instance Interface
	private OnPlayerSelectedListener listener;

	// Crear nuevo objeto PlayersDataSource para que se cree la BBDD
	// PlayersDataSource datasource = new PlayersDataSource(getActivity().getApplicationContext());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set List Adapter ==> mList, first time list empty
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mList));
		// If not data, update data Players
		updatePlayersData();
	}

	public void updatePlayersData() {

		try {
			// Comprobamos si tenemos datos, si no tenemos datos los obtemos
			if (mPlayers.getPlayers() == null) {
				getPlayers();

			} else if (mPlayers.getPlayers() != null) {
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
						// Si pulsa en reintentar la descarga o la conexión volvemos a llamar al método:
						updatePlayersData();
					}
				});
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void insertDataPlayers() {
		try {
			if (PreferencesFragment.transferPlayers != null) {
				for (String playerTP : PreferencesFragment.transferPlayers) {
					// Create new player witn only parameter => name
					Player playerTransfer = new Player(playerTP);
					playersDao.insert(playerTransfer);
				}
			}

			if (PreferencesFragment.profitablePlayers != null) {
				for (String playerPP : PreferencesFragment.profitablePlayers) {
					Player playerProfitable = new Player(playerPP);
					playersDao.insert(playerProfitable);
				}
			}

			if (PreferencesFragment.disastrousPlayers != null) {
				for (String playerDP : PreferencesFragment.disastrousPlayers) {
					Player playerDisastrous = new Player(playerDP);
					playersDao.insert(playerDisastrous);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public String[] getPlayers() {

		// Get names players DDBB
		Players list = playersDao.query();

		// Convert list to array of players
		namePlayers = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			namePlayers[i] = list.get(i).getName();
		}
		return namePlayers;
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
