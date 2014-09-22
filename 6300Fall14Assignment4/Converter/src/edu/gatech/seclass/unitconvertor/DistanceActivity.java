package edu.gatech.seclass.unitconvertor;

import java.text.DecimalFormat;

import edu.gatech.converter.R;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class DistanceActivity extends ActionBarActivity {

	int current;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//needed to keep track of which radio button is currently selected
		current = 0;
		setContentView(R.layout.activity_distance);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void handleDistanceClick(View view) {

		boolean checked = ((RadioButton) view).isChecked();

		EditText txt = (EditText) findViewById(R.id.text1);
		String ed_text = txt.getText().toString().trim();
		double distance;
		//handles and checks if the user has inputted an empty string
		if (ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
			distance = 0.00;
		} else {
			distance = Double.parseDouble(txt.getText().toString());

		}
		switch (view.getId()) {
		case R.id.radio0:
			if (checked && current != 0) {
				txt.setText(kmToMiles(distance));
				current = 0;
			}
			break;
		case R.id.radio1:
			if (checked && current != 1) {
				txt.setText(milesToKm(distance));
				current = 1;
			}
			break;
		}
	}

	public String milesToKm(double miles) {
		if (miles == 0)
			return "0.0";
		double km = miles * 1.60934;
		//formats the output to the user
		DecimalFormat format = new DecimalFormat("#.####");
		return String.valueOf(format.format(km));
	}

	public String kmToMiles(double km) {
		if (km == 0)
			return "0.0";
		double miles = km / 1.60934;
		//formats the output to the user
		DecimalFormat format = new DecimalFormat("#.####");
		return String.valueOf(format.format(miles));
	}
}
