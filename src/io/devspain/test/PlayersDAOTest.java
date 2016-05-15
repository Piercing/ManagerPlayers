package io.devspain.test;

import android.test.AndroidTestCase;
import io.devspain.database.DBHelper;
import io.devspain.database.PlayersDAO;
import io.devspain.models.Player;

public class PlayersDAOTest extends AndroidTestCase {

	public void testCanInsertPlayers() {

		// Create DDBB tester
		DBHelper.configure("TestDB.sqlite", getContext());

		Player player = new Player("Cristiano Ronaldo", 28, false, 1);

		PlayersDAO playerDAO = new PlayersDAO();
		long code = playerDAO.insert(player);

		assertTrue(code > 0);

	}
}
