package com.campus.universe.app.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import timber.log.Timber;

/**
 * Image processing utility functions
 */
public class ImageUtils {

    /**
     * Create circular bitmap
     */
    public static Bitmap getCircledBitmap(Bitmap bitmap) {
        try {
            int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
            Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(output);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, size, size);
            RectF rectF = new RectF(rect);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(0xff424242);
            canvas.drawOval(rectF, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

            return output;
        } catch (Exception e) {
            Timber.e(e, "Error creating circular bitmap");
            return bitmap;
        }
    }

    /**
     * Scale bitmap to specific size
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
        try {
            return Bitmap.createScaledBitmap(bitmap, width, height, true);
        } catch (Exception e) {
            Timber.e(e, "Error scaling bitmap");
            return bitmap;
        }
    }

    /**
     * Compress bitmap to specific quality
     */
    public static Bitmap compressBitmap(Bitmap bitmap, int quality) {
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            byte[] data = baos.toByteArray();
            return android.graphics.BitmapFactory.decodeByteArray(data, 0, data.length);
        } catch (Exception e) {
            Timber.e(e, "Error compressing bitmap");
            return bitmap;
        }
    }

    /**
     * Get bitmap from URI
     */
    public static Bitmap getBitmapFromUri(android.net.Uri uri, android.content.Context context) {
        try {
            return android.graphics.BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(uri)
            );
        } catch (Exception e) {
            Timber.e(e, "Error getting bitmap from URI");
            return null;
        }
    }

    /**
     * Check if image file size is valid
     */
    public static boolean isValidImageSize(long sizeInBytes) {
        return sizeInBytes <= Constants.MAX_IMAGE_SIZE;
    }

    /**
     * Check if video file size is valid
     */
    public static boolean isValidVideoSize(long sizeInBytes) {
        return sizeInBytes <= Constants.MAX_VIDEO_SIZE;
    }

    /**
     * Get file size in MB
     */
    public static double getFileSizeInMB(long sizeInBytes) {
        return (double) sizeInBytes / (1024 * 1024);
    }
}
