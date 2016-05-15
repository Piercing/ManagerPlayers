package io.devspain.test;

import android.test.AndroidTestCase;
import io.devspain.database.PlayersDAO;
import io.devspain.models.Players;

public class PlayersDAOTest extends AndroidTestCase {

	public void testCanInsertPlayers() {

		Players player = new Players("Cristiano Ronaldo", 28, false, 1);

		PlayersDAO playerDAO = new PlayersDAO(getContext());
		long code = playerDAO.insert(player);

		assertTrue(code > 0);

	}
}
