package com.example.test1;

import java.util.Iterator;
import java.util.Set;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Preferences extends Activity {

	ArrayAdapter<String> mAdapter;
	SharedPreferences sharedPref;
	SharedPreferences.Editor sharedPref_editor;
	ListView view_list;
	String defValue = "#75A49D";
	String background_color;
	RelativeLayout mScreen;

	// arriver à garder la liste même après la fermeture de l'activité

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preferences);
		// Show the Up button in the action bar.
		setupActionBar();

		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		view_list = (ListView) findViewById(R.id.preferences_list);

		sharedPref = getSharedPreferences("com.example.test1",
				Context.MODE_PRIVATE);
		sharedPref_editor = sharedPref.edit();

		background_color = new String("BC");

		mScreen = (RelativeLayout) findViewById(R.id.preferences_id);

		// listener pour le changement de couleur
		view_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				// on change la couleur de la case
				String color_hexa = sharedPref.getString(
						String.valueOf(((TextView) v).getText()), defValue);
				int int_color = Color.parseColor(color_hexa);
				v.setBackgroundColor(int_color);

				// on change la couleur de fond
				mScreen.setBackgroundColor(int_color);

				// on sauvegarde la nouvelle couleur de fond
				sharedPref_editor.putString(background_color, color_hexa);

				sharedPref_editor.commit();

			}
		});

	}

	protected void onResume() {
		super.onResume();

		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);

		Set<String> set_pref = sharedPref.getAll().keySet();
		Iterator<String> it_pref = set_pref.iterator();
		while (it_pref.hasNext()) {
			String temp = (String) it_pref.next();
			if (!(temp.compareTo("BC") == 0)) {
				mAdapter.add(temp);
			}
		}
		view_list.setAdapter(mAdapter);

		
				
		for (int i = 1; i <= view_list.getCount(); ++i) {
			View v = view_list.getChildAt(i);
			Log.w("Preferences / onResume() :",
					"first : " + view_list.getLastVisiblePosition());
//			int int_color = Color.parseColor(sharedPref.getString(
//					((TextView) v).getText().toString(), defValue));
//			v.setBackgroundColor(int_color);
		}

		String color_hexa = sharedPref.getString(background_color, defValue);
		int int_color = Color.parseColor(color_hexa);

		mScreen.setBackgroundColor(int_color);
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	//
	// // ça marche !
	// String nom = data.getStringExtra(Ajout_Pref.COLOR_PREF);
	//
	// Log.w("onRestart()", "passage " + nom);
	//
	// ListView view_list = (ListView) findViewById(R.id.preferences_list);
	// mAdapter.add(nom);
	// view_list.setAdapter(mAdapter);
	//
	// }

	public void return_main(View view) {
		finish();
	}

	public ListView list() {
		return (ListView) findViewById(R.id.preferences_list);
	}

	public void preferences_add(View view) {

		Intent intent = new Intent(this, Ajout_Pref.class);
		startActivity(intent);
		// startActivityForResult(intent, 555);
	}

	public void preferences_reset(View view) {
		mAdapter.clear();

		SharedPreferences.Editor editor = sharedPref.edit();
		editor.clear();
		editor.commit();

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
		getMenuInflater().inflate(R.menu.preferences, menu);
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
