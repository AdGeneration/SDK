package com.example.sampleapp;

//AdGeneration packages
import com.socdm.d.adgeneration.ADG;
import com.socdm.d.adgeneration.ADG.AdFrameSize;
import com.socdm.d.adgeneration.ADGListener;
import com.socdm.d.adgeneration.interstitial.ADGInterstitial;
import com.socdm.d.adgeneration.interstitial.ADGInterstitialListener;

import android.widget.LinearLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private ADG adg;
	private ADGInterstitial adgInter;
	static private LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layout = new LinearLayout(this);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		setContentView(layout ,  lp);
		layout.setOrientation(LinearLayout.VERTICAL);
	
        // Banner
		adg = new ADG(this);
		adg.setLocationId("10724");
		adg.setAdFrameSize(AdFrameSize.SP);
		adg.setAdListener(new AdListener());
		layout.addView(adg);

        // Interstitial
        adgInter = new ADGInterstitial(this);
        adgInter.setLocationId("18031");
        adgInter.setAdListener(new AdInterListener());
        adgInter.setBackgroundType(2);
        adgInter.setCloseButtonType(2);
        //adgInter.setSpan(50 , true);
        //adgInter.setPreventAccidentalClick(true);

        Button loadBtn = new Button(this);
        loadBtn.setText("Interstitial Load");
        layout.addView(loadBtn);
        loadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                adgInter.preload();
            }
        });

        Button showBtn = new Button(this);
        showBtn.setText("Interstitial Show");
        layout.addView(showBtn);
        showBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                adgInter.show();
            }
        });
	}

    @Override
    public void onStop(){
        adgInter.dismiss();// Cnacel
        super.onStop();
    }
	
    @Override
    protected void onResume() {
    	super.onResume();
    }

    @Override
    protected void onPause() {
    	super.onPause();
    }
    
    
    class AdListener extends ADGListener {
    	@Override
    	public void onReceiveAd() {
    		//広告受信
    	}
    	@Override
    	public void onFailedToReceiveAd() {
    		//広告受信失敗
    	}
    	@Override
    	public void onInternalBrowserOpen() {
    		//内部ブラウザオープン
    	}
    	@Override
    	public void onInternalBrowserClose() {
    		//内部ブラウザクローズ
    	}
    	@Override
    	public void onVideoPlayerStart() {
    		//動画プレイヤー開始
    	}
    	@Override
    	public void onVideoPlayerEnd() {
    		//動画プレイヤー終了
    	}
    }

    class AdInterListener extends ADGInterstitialListener{
        @Override
        public void onReceiveAd() {
          //広告受信
        }
        @Override
        public void onFailedToReceiveAd() {
          //広告受信失敗
        }
        @Override
        public void onCloseInterstitial() {
          //インタースティシャルクローズ
        }
    }
}
