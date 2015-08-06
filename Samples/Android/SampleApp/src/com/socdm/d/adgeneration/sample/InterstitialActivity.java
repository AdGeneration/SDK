package com.socdm.d.adgeneration.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.socdm.d.adgeneration.ADGConsts.ADGErrorCode;
import com.socdm.d.adgeneration.interstitial.ADGInterstitial;
import com.socdm.d.adgeneration.interstitial.ADGInterstitialListener;
import com.socdm.d.adgeneration.utils.LogUtils;

public class InterstitialActivity extends ActionBarActivity {

	ADGInterstitial interstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interstitial);
		Resources res = getResources();
		TextView title = (TextView) findViewById(R.id.page_title_02);
		title.setText(res.getString(R.string.interstitial));

		interstitial = new ADGInterstitial(this);
		interstitial.setLocationId("18031");
		interstitial.setAdListener(new ADGInterstitialListener() {
			@Override
			public void onReceiveAd() {
				LogUtils.i("onReceiveAd");
			}
			@Override
			public void onFailedToReceiveAd(ADGErrorCode code) {
				LogUtils.i("onFailedToReceiveAd (code:" + code.name() + ")");
			}
			@Override
			public void onCloseInterstitial() {
				LogUtils.i("onCloseInterstitial");
			}
		});

		Button preloadBtn = (Button) findViewById(R.id.preload_btn);
		preloadBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				interstitial.preload();
			}
		});

		Button showBtn = (Button) findViewById(R.id.show_btn);
		showBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				interstitial.show();
			}
		});

		Button closeBtn = (Button) findViewById(R.id.close_btn_02);
		closeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		interstitial.dismiss();
	}

}
