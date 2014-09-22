package edu.gatech.seclass.unitconvertor;

import edu.gatech.converter.R;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	
	public void handleClick(View view){
		
		String name = ((Button) view).getText().toString();
		
		switch(view.getId()){
		case R.id.distanceButton:
			Intent distanceIntent = new Intent(MainActivity.this, DistanceActivity.class);
			MainActivity.this.startActivity(distanceIntent);
			break;
		case R.id.areaButton:
			Intent areaIntent = new Intent(MainActivity.this, AreaActivity.class);
			MainActivity.this.startActivity(areaIntent);
			break;
		case R.id.currencyButton:
			Intent currencyIntent = new Intent(MainActivity.this, CurrencyActivity.class);
			MainActivity.this.startActivity(currencyIntent);
			break;
		
		}
		
	}
	
	
}
