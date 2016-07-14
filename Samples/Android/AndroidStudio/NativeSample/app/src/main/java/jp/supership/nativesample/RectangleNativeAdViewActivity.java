package jp.supership.nativesample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.ads.AdSettings;
import com.facebook.ads.NativeAd;
import com.socdm.d.adgeneration.ADG;
import com.socdm.d.adgeneration.ADGConsts;
import com.socdm.d.adgeneration.ADGListener;
import com.socdm.d.adgeneration.nativead.ADGNativeAd;

import jp.supership.nativesample.helper.ADGNativeAdHelper;
import jp.supership.nativesample.helper.FANHelper;
import jp.supership.nativesample.utilities.Utilities;

import static jp.supership.nativesample.definitons.AdTypesConsts.getRectangleNativeadHeightWithConvertDp;
import static jp.supership.nativesample.definitons.AdTypesConsts.getRectangleNativeadWidthWithConvertDp;

public class RectangleNativeAdViewActivity extends Activity {
    private ADG adg;
    private LinearLayout ad_container;
    private static final int LMP = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;


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

            if (adg != null && _nativeAd instanceof ADGNativeAd) { // クラス判定
                ADGNativeAd nativeAd = (ADGNativeAd) _nativeAd; // キャスト

                //レイアウト設定
                FrameLayout layout = new FrameLayout(getApplicationContext());
                layout.setLayoutParams(new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                layout.setBackgroundColor(Color.WHITE);

                //広告枠のレイアウト
                LinearLayout nativeAdContainer = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams nativeAdContainerParam = new LinearLayout.LayoutParams(getRectangleNativeadWidthWithConvertDp(getApplicationContext()), getRectangleNativeadHeightWithConvertDp(getApplicationContext()));
                nativeAdContainer.setLayoutParams(nativeAdContainerParam);
                nativeAdContainer.setOrientation(LinearLayout.VERTICAL);
                layout.addView(nativeAdContainer);

                //header系
                LinearLayout headerWrapper = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams headerWrapperParams = new LinearLayout.LayoutParams(LMP, Utilities.convertedPixelToDp(getApplicationContext(), 30));
                headerWrapper.setLayoutParams(headerWrapperParams);
                headerWrapper.setOrientation(LinearLayout.HORIZONTAL);
                headerWrapper.setGravity(Gravity.LEFT);
                nativeAdContainer.addView(headerWrapper);

                //アイコン
                ADGNativeAdHelper.setIcon(nativeAd, headerWrapper, getApplicationContext());

                //タイトルと広告マークを縦に並べる
                LinearLayout titlesWrapper = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams titlesWrapperParams = new LinearLayout.LayoutParams(LMP, LMP);
                titlesWrapper.setLayoutParams(titlesWrapperParams);
                titlesWrapper.setOrientation(LinearLayout.VERTICAL);
                titlesWrapper.setPadding(Utilities.convertedPixelToDp(getApplicationContext(), 6), 0, 0, 0);
                headerWrapper.addView(titlesWrapper);

                //タイトル
                LinearLayout nativeAdTitle = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams nativeAdTitleParam = new LinearLayout.LayoutParams(LWC, Utilities.convertedPixelToDp(getApplicationContext(), 16));
                nativeAdTitle.setLayoutParams(nativeAdTitleParam);
                ADGNativeAdHelper.setTitle(nativeAd, nativeAdTitle, getApplicationContext());
                titlesWrapper.addView(nativeAdTitle);

                //広告
                ADGNativeAdHelper.setAdMark(titlesWrapper, getApplicationContext());
                //本文
                ADGNativeAdHelper.setDescription(nativeAd, nativeAdContainer, getApplicationContext());

                //メインイメージ
                LinearLayout nativeAdImage = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams nativeAdImageParam = new LinearLayout.LayoutParams(Utilities.convertedPixelToDp(getApplicationContext(), 300), Utilities.convertedPixelToDp(getApplicationContext(), 156));
                nativeAdImage.setLayoutParams(nativeAdImageParam);
                nativeAdContainer.addView(nativeAdImage);
                ADGNativeAdHelper.setMainImage(nativeAd, nativeAdImage, getApplicationContext());

                LinearLayout nativeAdLine = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams nativeAdLineParam = new LinearLayout.LayoutParams(LMP, LMP);
                nativeAdLine.setLayoutParams(nativeAdLineParam);
                nativeAdLine.setOrientation(LinearLayout.HORIZONTAL);
                nativeAdLine.setGravity(Gravity.LEFT);
                nativeAdContainer.addView(nativeAdLine);

                //スポンサー
                ADGNativeAdHelper.setSponsored(nativeAd, nativeAdLine, getApplicationContext());

                //4段目 ボタン
                ADGNativeAdHelper.setCta(nativeAd, nativeAdLine, getApplicationContext());

                // クリックやローテーションの制御に必要なため必ず記述してください
                adg.delegateViewManagement(layout, nativeAd);

                ad_container.addView(layout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)); // 広告を表示するべきViewにaddView

            } else if (adg != null && _nativeAd instanceof NativeAd) { //ここからFAN
                NativeAd nativeAd = (NativeAd) _nativeAd;

                //広告表示先レイアウト設定
                FrameLayout layout = new FrameLayout(getApplicationContext());
                layout.setLayoutParams(new ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                layout.setBackgroundColor(Color.WHITE);

                //広告枠のレイアウト(adChoice含む)
                FrameLayout nativeAdContainer = new FrameLayout(getApplicationContext());
                FrameLayout.LayoutParams nativeAdContainerParam = new FrameLayout.LayoutParams(getRectangleNativeadWidthWithConvertDp(getApplicationContext()), getRectangleNativeadHeightWithConvertDp(getApplicationContext()));
                nativeAdContainer.setLayoutParams(nativeAdContainerParam);
                layout.addView(nativeAdContainer);

                //広告枠のレイアウト
                LinearLayout nativeAdFrame = new LinearLayout(getApplicationContext());
                nativeAdFrame.setLayoutParams(new LinearLayout.LayoutParams(LMP, LMP));
                nativeAdFrame.setOrientation(LinearLayout.VERTICAL);
                nativeAdContainer.addView(nativeAdFrame);

                //header系
                LinearLayout headerWrapper = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams headerWrapperParams = new LinearLayout.LayoutParams(LMP, Utilities.convertedPixelToDp(getApplicationContext(), 30));
                headerWrapper.setLayoutParams(headerWrapperParams);
                headerWrapper.setOrientation(LinearLayout.HORIZONTAL);
                headerWrapper.setGravity(Gravity.LEFT);
                nativeAdFrame.addView(headerWrapper);

                //アイコン
                FANHelper.setIcon(nativeAd, headerWrapper, getApplicationContext());

                //タイトルと広告マークを縦に並べる
                LinearLayout titlesWrapper = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams titlesWrapperParams = new LinearLayout.LayoutParams(LMP, LMP);
                titlesWrapper.setLayoutParams(titlesWrapperParams);
                titlesWrapper.setOrientation(LinearLayout.VERTICAL);
                titlesWrapper.setPadding(Utilities.convertedPixelToDp(getApplicationContext(), 5), 0, 0, 0);
                headerWrapper.addView(titlesWrapper);

                //タイトル
                LinearLayout nativeAdTitle = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams nativeAdTitleParam = new LinearLayout.LayoutParams(LWC, Utilities.convertedPixelToDp(getApplicationContext(), 17));
                nativeAdTitle.setLayoutParams(nativeAdTitleParam);
                FANHelper.setTitle(nativeAd, nativeAdTitle, getApplicationContext());
                titlesWrapper.addView(nativeAdTitle);

                //広告
                FANHelper.setAdMark(titlesWrapper, getApplicationContext());

                //本文
                FANHelper.setDescription(nativeAd, nativeAdFrame, getApplicationContext());

                //画像または動画のView
                FrameLayout nativeAdImage = new FrameLayout(getApplicationContext());
                FrameLayout.LayoutParams nativeAdImageParam = new FrameLayout.LayoutParams(Utilities.convertedPixelToDp(getApplicationContext(), 300), Utilities.convertedPixelToDp(getApplicationContext(), 156));
                nativeAdImage.setLayoutParams(nativeAdImageParam);
                nativeAdFrame.addView(nativeAdImage);
                FANHelper.setMediaView(nativeAd, nativeAdImage, getApplicationContext());

                LinearLayout nativeAdLine = new LinearLayout(getApplicationContext());
                LinearLayout.LayoutParams nativeAdLineParam = new LinearLayout.LayoutParams(LMP, LMP);
                nativeAdLine.setLayoutParams(nativeAdLineParam);
                nativeAdLine.setOrientation(LinearLayout.HORIZONTAL);
                nativeAdLine.setGravity(Gravity.LEFT);
                nativeAdFrame.addView(nativeAdLine);

                //nativeSocialContext
                FANHelper.setNativeSocialContext(nativeAd, nativeAdLine, getApplicationContext());

                //nativeActionButton
                FANHelper.setCta(nativeAd, nativeAdLine, getApplicationContext());

                //AdChoice
                LinearLayout adChoiceContainer = new LinearLayout(getApplicationContext());
                adChoiceContainer.setGravity(Gravity.RIGHT | Gravity.TOP);
                nativeAdImage.addView(adChoiceContainer, new ViewGroup.LayoutParams(LMP, LMP));
                FANHelper.setAdChoicesView(nativeAd, nativeAdImage, adChoiceContainer, getApplicationContext());

                //広告をclickした時のイベントを追加
                nativeAd.registerViewForInteraction(ad_container);

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


