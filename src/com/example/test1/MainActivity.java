package com.example.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.test1.MESSAGE";
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
		setContentView(R.layout.activity_main);

		sharedPref = getSharedPreferences("com.example.test1",
				Context.MODE_PRIVATE);
		sharedPref_editor = sharedPref.edit();
		background_color = new String("BC");
		mScreen = (RelativeLayout) findViewById(R.id.main_id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	/**
	 * Réaction au clic sur le bouton
	 */
	public void sendMessage(View view) {
		// Fait qqchose
		Intent intent = new Intent(this, DisplayMessageActivity.class);

		EditText edit = (EditText) findViewById(R.id.edit_message);
		String message = edit.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);

		startActivity(intent);

	}

	public void goTo_IMC(View view) {

		Intent intent = new Intent(this, CalculIMC.class);

		startActivity(intent);

	}

	public void goTo_preferences(View view) {
		Intent intent = new Intent(this, Preferences.class);

		startActivity(intent);
	}

	public void onResume() {
		super.onResume();
		String color_hexa = sharedPref.getString(background_color, defValue);
		int int_color = Color.parseColor(color_hexa);

		mScreen.setBackgroundColor(int_color);
	}

}
