package io.devspain.models;

import java.util.ArrayList;
import java.util.List;

public class PlayersData {

	public static List<Players> PLAYERS = new ArrayList<Players>();

	static {

		PLAYERS.add(new Players("Cristiano Ronaldo", 33, false, 1));
		PLAYERS.add(new Players("Messi-La pulga", 31, false, 2));
		PLAYERS.add(new Players("Sergio Ramos", 33, false, 3));
		PLAYERS.add(new Players("Gharet Bale", 28, false, 4));
		PLAYERS.add(new Players("David Arbeloa", 35, true, 5));
	}

	public static List<String> totalNamesPlayers = new ArrayList<String>();

	static {
		for (Players player : PLAYERS) {
			totalNamesPlayers.add(player.getName());
		}
	}
}
