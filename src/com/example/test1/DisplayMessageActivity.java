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

public class DisplayMessageActivity extends Activity {
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
		setContentView(R.layout.activity_display_message);
		// Show the Up button in the action bar.
		setupActionBar();

		sharedPref = getSharedPreferences("com.example.test1",
				Context.MODE_PRIVATE);
		sharedPref_editor = sharedPref.edit();

		background_color = new String("BC");

		mScreen = (RelativeLayout) findViewById(R.id.display_id);

		// getIntent
		Intent parent_intent = getIntent();
		String message = parent_intent
				.getStringExtra(MainActivity.EXTRA_MESSAGE);

		// Create text view
		TextView textview = (TextView) findViewById(R.id.display_result);
		textview.setTextSize(50);
		textview.setText(message);

		// Set the textView as the activity layout
		// setContentView(textview);

	}

	public void return_main(View view) {
		finish();
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
		getMenuInflater().inflate(R.menu.display_message, menu);
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
