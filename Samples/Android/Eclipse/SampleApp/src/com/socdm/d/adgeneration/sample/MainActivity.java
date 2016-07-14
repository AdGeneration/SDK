package com.socdm.d.adgeneration.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.socdm.d.adgeneration.sample.models.ListItem;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends ActionBarActivity {

	private static final String MAIN_KEY = "main";
	private static final String SUB_KEY = "sub";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Resources res = getResources();

		// initialize items
		final List<ListItem> items = new ArrayList<ListItem>();
		items.add(new ListItem(R.string.banner, R.string.banner_size, BannerActivity.class));
		items.add(new ListItem(R.string.rectangle, R.string.rectangle_size, RectangleActivity.class));
		items.add(new ListItem(R.string.interstitial, R.string.rectangle_size, InterstitialActivity.class));

		// convert items.
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (ListItem item : items) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(MAIN_KEY, res.getString(item.getMainId()));
			map.put(SUB_KEY, "AdSize: " + res.getString(item.getSubId()));
			list.add(map);
		}

		// setup adapter.
		SimpleAdapter adapter = new SimpleAdapter(this, list, 
				android.R.layout.simple_list_item_2, 
				new String[] { MAIN_KEY, SUB_KEY }, 
				new int[] { android.R.id.text1, android.R.id.text2 });

		// setup listView.
		ListView listView = (ListView) findViewById(R.id.sample_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (items.size() > position) {
					Class<?> c = items.get(position).getKlass();
					Intent intent = new Intent(MainActivity.this, c);
					MainActivity.this.startActivity(intent);
				}
			}
		});
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
}
