package jp.supership.nativesample.utilities;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

public class Utilities {
    /**
     * DPをピクセルに変換
     *
     * @param context Context
     * @param dp      dp
     * @return pixel
     */
    public static int convertDpToPixel(Context context, int dp) {
        // density (比率)を取得する
        float density = context.getResources().getDisplayMetrics().density;

        // 50 dp を pixel に変換する ( dp × density + 0.5f（四捨五入) )
        int px = (int) (dp * density + 0.5f);
        return px;
    }

    /**
     * TextViewの末尾に...を設置
     *
     * @param tv TextView
     */
    public static void setOneLineAndEllipsisForTextView(TextView tv) {
        tv.setHorizontallyScrolling(true);
        tv.setLines(1);
        tv.setEllipsize(TextUtils.TruncateAt.END);
    }
}
