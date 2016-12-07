package jp.supership.nativesample.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;

/**
 * Download image asynchronously from URL
 */
public class DownloadImageAsync extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;

    public DownloadImageAsync(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            String imageUrl = params[0];
            return BitmapFactory.decodeStream(new URL(imageUrl).openStream());
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.imageView.setImageBitmap(bitmap);
    }
}
