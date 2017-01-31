package jp.supership.nativesample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.ads.NativeAd;
import com.socdm.d.adgeneration.ADG;
import com.socdm.d.adgeneration.ADGConsts;
import com.socdm.d.adgeneration.ADGListener;
import com.socdm.d.adgeneration.nativead.ADGNativeAd;

import jp.supership.nativesample.helper.ADGNativeAdHelper;
import jp.supership.nativesample.helper.FANNativeAdHelper;

public class RectangleNativeAdViewActivity extends Activity {
    private ADG adg;
    private LinearLayout ad_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rectangle_nativead);
        ad_container = (LinearLayout) findViewById(R.id.ad_container);
        adg = new ADG(this); // インスタンス生成
        adg.setLocationId("32791"); // 必ず本番IDに変えてください
        adg.setAdFrameSize(ADG.AdFrameSize.FREE.setSize(300, 250)); // サイズ指定
        adg.setAdListener(new AdListener()); // Listener定義
        adg.setUsePartsResponse(true);

        // 推奨の設定
        adg.setReloadWithVisibilityChanged(false);
        adg.setFillerRetry(false);

        // 表示
        ad_container.addView(adg);

        //Reload
        findViewById(R.id.reload_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adg.stop();
                adg.start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 広告表示/ローテーション再開
        if (adg != null) {
            adg.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ローテーション停止
        if (adg != null) {
            adg.pause();
        }
    }

    // Listener定義
    // エラー時のリトライは特段の理由がない限り必ず記述するようにしてください。
    class AdListener extends ADGListener {
        private static final String _TAG = "ADGListener";

        @Override
        public void onReceiveAd() {
            Log.d(_TAG, "onReceiveAdOfBanner");
        }

        @Override
        public void onReceiveAd(Object _nativeAd) {
            Log.d(_TAG, "onReceiveAdOfNativeAd");

            if (adg != null && _nativeAd instanceof ADGNativeAd) {
                ADGNativeAd nativeAd = (ADGNativeAd) _nativeAd;

                // インフォメーションアイコンのデフォルト表示OFF
                adg.setInformationIconViewDefault(false);

                FrameLayout layout = ADGNativeAdHelper.createAdView(nativeAd, getApplicationContext());

                // クリックやローテーションの制御に必要なため必ず記述してください
                adg.delegateViewManagement(layout, nativeAd);

                ad_container.addView(layout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)); // 広告を表示するべきViewにaddView

            } else if (adg != null && _nativeAd instanceof NativeAd) {
                NativeAd nativeAd = (NativeAd) _nativeAd;

                FrameLayout layout = FANNativeAdHelper.createAdView(nativeAd, getApplicationContext());

                //広告をclickした時のイベントを追加
                nativeAd.registerViewForInteraction(layout);

                adg.delegateViewManagement(layout);
                ad_container.addView(layout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)); // 広告を表示するべきViewにaddView
            }
        }


        @Override
        public void onFailedToReceiveAd(ADGConsts.ADGErrorCode code) {
            Log.d(_TAG, "onFailedToReceiveAd");
            // 不通とエラー過多のとき以外はリトライ
            // エラー時のリトライは特段の理由がない限り必ず記述するようにしてください。
            switch (code) {
                case EXCEED_LIMIT:
                case NEED_CONNECTION:
                case NO_AD:
                    break;
                default:
                    if (adg != null) {
                        adg.start();
                    }
                    break;
            }
        }

        @Override
        public void onOpenUrl() {
            Log.d(_TAG, "onOpenUrl");
        }

    }
}


