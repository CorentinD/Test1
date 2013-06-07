package com.example.test1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Ajout_Pref extends Activity {

	public final static String COLOR_PREF = "color_pref";
	ArrayAdapter<String> mAdapter;
	SharedPreferences sharedPref;
	SharedPreferences.Editor sharedPref_editor;
	ListView view_list;
	String defValue = "#75A49D";
	String background_color;
	RelativeLayout mScreen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_pref);
		// Show the Up button in the action bar.
		setupActionBar();
		sharedPref = getSharedPreferences("com.example.test1",
				Context.MODE_PRIVATE);
		sharedPref_editor = sharedPref.edit();

		background_color = new String("BC");

		mScreen = (RelativeLayout) findViewById(R.id.ajout_id);
	}

	public void return_main(View view) {
		finish();
	}

	public void preferences_add_ajout(View view) {

		TextView view_nom = (TextView) findViewById(R.id.preferences_add_nom);
		TextView view_value = (TextView) findViewById(R.id.preferences_add_value);

		if (view_nom.getText().length() > 0
				&& view_value.getText().length() > 0) {

			String nom = String.valueOf(view_nom.getText());
			String value = view_value.getText().toString();

			// On sauvegarde les clés-valeurs
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putString(nom, value);
			editor.commit();

			// Fait qqchose
			// Intent parent_intent = getIntent();
			// parent_intent.putExtra(COLOR_PREF, nom);
			//
			// setResult(555, parent_intent);
			finish();

		}
	} // end preferences_add_ajout

	public void test() {
		Intent intent = new Intent(this, Test.class);
		startActivity(intent);
	}

	public void onResume() {
		super.onResume();

		String color_hexa = sharedPref.getString(background_color, defValue);
		int int_color = Color.parseColor(color_hexa);

		mScreen.setBackgroundColor(int_color);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajout__pref, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
