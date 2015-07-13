package com.socdm.d.adgeneration.sample;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socdm.d.adgeneration.ADG;
import com.socdm.d.adgeneration.ADGListener;
import com.socdm.d.adgeneration.ADG.AdFrameSize;
import com.socdm.d.adgeneration.ADGConsts.ADGErrorCode;
import com.socdm.d.adgeneration.utils.LogUtils;

public class RectangleActivity extends ActionBarActivity {

	ADG adg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adg);
		Resources res = getResources();
		TextView title = (TextView) findViewById(R.id.page_title_01);
		title.setText(res.getString(R.string.rectangle));

		adg = new ADG(this);
		adg.setLocationId("18031");
		adg.setReloadWithVisibilityChanged(false);
		adg.setFillerRetry(false);
		adg.setAdFrameSize(AdFrameSize.RECT);
		adg.setAdListener(new ADGListener() {
			@Override
			public void onReceiveAd() {
				LogUtils.i("onReceiveAd");
			}

			@Override
			public void onFailedToReceiveAd(ADGErrorCode code) {
				LogUtils.i("onFailedToReceiveAd. (code: " + code.name() + ")");
				switch (code) {
				case EXCEED_LIMIT:
				case NEED_CONNECTION:
					break;
				default:
					adg.start();
					break;
				}
			}
		});

		LinearLayout layout = (LinearLayout) findViewById(R.id.ad_container_01);
		layout.addView(adg);

		Button btn = (Button) findViewById(R.id.close_btn_01);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (adg != null) {
			adg.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (adg != null) {
			adg.stop();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (adg != null) {
			adg.destroyAdView();
			adg = null;
		}
	}
}
