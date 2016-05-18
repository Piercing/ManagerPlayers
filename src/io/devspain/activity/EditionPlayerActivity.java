package io.devspain.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import io.devspain.R;
import io.devspain.fragments.EngagePlayersFragment.OnPlayerSelectedListener;

public class EditionPlayerActivity extends Activity implements OnPlayerSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Charge the layout of fragmente
		setContentView(R.layout.activity_edition_player);

		FragmentManager FM = getFragmentManager();
		FragmentTransaction FT = FM.beginTransaction();

		Fragment fragmentEngage = new Fragment();
		FT.replace(R.id.container, fragmentEngage);
		FT.commit();

	}

	// En el método que nos obliga a sobreescribir la interface guardamos los
	// datos que recibimos de cada evento del fragment en un Bundle y se lo
	// aplicamos a un nuevo fragment. A continuación se crea una transacción
	// a este nuevo fragment y se añade a la pila de retroceso.

	@Override
	public void onPlayerSelected(String str) {

		Fragment fragmentEngageSelected = new Fragment();

		Bundle args = new Bundle();
		args.putString("str", str);
		fragmentEngageSelected.setArguments(args);

		FragmentManager FM = getFragmentManager();
		FragmentTransaction FT = FM.beginTransaction();

		FT.replace(R.id.container, fragmentEngageSelected);
		FT.addToBackStack(null);

		FT.commit();
	}

}
