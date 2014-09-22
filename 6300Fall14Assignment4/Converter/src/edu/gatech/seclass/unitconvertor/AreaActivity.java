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

public class AreaActivity extends ActionBarActivity {

	int current;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//needed to keep track of which radio button is currently selected
		current=0;
		setContentView(R.layout.activity_area);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.area, menu);
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

	public void handleAreaClick(View view) {

		boolean checked = ((RadioButton) view).isChecked();

		EditText txt = (EditText) findViewById(R.id.text1);
		String ed_text = txt.getText().toString().trim();
		double area;
		//handles and checks if the user has inputted an empty string
		if (ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
			area = 0.00;
		} else {
			area = Double.parseDouble(txt.getText().toString());

		}
		switch (view.getId()) {
		case R.id.ftRadio:
			if (checked&& current!=0) {
				txt.setText(ftToMeters(area));
				current=0;
				
			}
			break;
		case R.id.mRadio:
			if (checked&&current!=1) {
				txt.setText(metersToFt(area));
				current=1;
				
			}
			break;
		}
		
	}
	public String ftToMeters(double area){
		if(area==0)
			return "0.0";
		double convertedArea = area * 10.764;
		//formats the output to the user
		DecimalFormat format = new DecimalFormat("#.####");
		return String.valueOf(format.format(convertedArea));
	}
	public String metersToFt(double area){
		if(area==0)
			return "0.0";
		double convertedArea = area / 10.764;
		//formats the output to the user
		DecimalFormat format = new DecimalFormat("#.####");
		return String.valueOf(format.format(convertedArea));
	}
}
