package io.devspain.models;

import java.util.LinkedList;
import java.util.List;

public class Players {

	// Propiedad players que contiene una lista genérica de Player
	static List<Player>		players;
	public static String[]	namePlayers;

	public Players() {
	}

	// Factory static method
	public static Players createPlayers(List<Player> players) {
		Players myPlayers = new Players();

		// Recorro la lista de players que recibo como parámetro
		for (Player n : players) {
			// Llamo al método 'add' con la instancia que me he creado, pasándole cada player de la lista
			// El método 'add' llama a su vez al método 'getPlayers', al cual le añadimos el player que
			// le pasamos a la lista que creamos dentro de él 'players', el cual nos devuelve la lista de
			// los players que contiene, más el que le hemos pasado como parámetro.
			myPlayers.add(n);
		}

		// Return list players
		return myPlayers;
	}

	public int size() {
		return getPlayers().size();
	}

	public Player get(int index) {
		return getPlayers().get(index);
	}

	public void delete(Player player) {
		getPlayers().remove(player);
	}

	public void remove(Player player) {

	}

	// Método para añadir un player a la lista.
	// Llamo a al método getPlayers y le añado
	// el player que recibo como parámetro ya que es de tipo List.
	private void add(Player n) {
		getPlayers().add(n);
	}

	public static String[] getPlayersListToArrayPlayers() {

		// Check if players list is empty, if this list is null create a new list
		if (players == null) {
			players = new LinkedList<Player>();
		} else {
			// Convert list to array of players
			namePlayers = new String[players.size()];
			for (int i = 0; i < players.size(); i++) {
				namePlayers[i] = players.get(i).getName();
			}
		}
		return namePlayers;
	}

	// Getter
	public List<Player> getPlayers() {
		// Compruebo si no contiene nada la lista players
		if (players == null) {
			// Si es null, creo una nueva lista
			players = new LinkedList<Player>();
		}
		// Devuelvo la lista con los players
		return players;
	}
}
