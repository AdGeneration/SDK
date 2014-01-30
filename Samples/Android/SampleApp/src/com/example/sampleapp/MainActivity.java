package com.example.sampleapp;

//AdGeneration packages
import com.socdm.d.adgeneration.ADG;
import com.socdm.d.adgeneration.ADG.AdFrameSize;
import com.socdm.d.adgeneration.ADGListener;

import android.widget.RelativeLayout;
import android.view.Gravity;
import android.os.Bundle;
import android.app.Activity;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {
	
	private ADG adg;
	static private RelativeLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        layout = new RelativeLayout(this);
        setContentView(layout ,  new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.TOP|Gravity.CENTER);
        
    	adg = new ADG(this);
    	adg.setLocationId("10724");//広告枠番号
    	adg.setAdFrameSize(AdFrameSize.SP);//広告枠サイズ
    	adg.setAdListener(new AdListener());//イベント受け取り設定
		layout.addView(adg);
	}
	
    @Override
    protected void onResume() {
        adg.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        adg.stop();
        super.onPause();
    }
    
    //イベント取得用リスナークラス
    class AdListener extends ADGListener {
        @Override
        public void onReceiveAd() {
           //広告取得
        }
        @Override
        public void onFailedToReceiveAd() {
            //広告取得失敗
        }
        @Override
        public void onInternalBrowserOpen() {
            //アプリ内ブラウザオープン
        }
        @Override
        public void onInternalBrowserClose() {
            //アプリ内ブラウザクローズ
        }
        @Override
        public void onVideoPlayerStart() {
            //動画プレーヤー開始
        }
        @Override
        public void onVideoPlayerEnd() {
            //動画プレーヤー終了
        }
    }
}
