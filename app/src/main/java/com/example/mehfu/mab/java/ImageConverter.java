package com.example.mehfu.mab.java;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageConverter {

    private static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static String uriImageToString(Context con, Uri uri) throws IOException {
        InputStream is = con.getContentResolver().openInputStream(uri);

        byte[] inputData = getBytes(is);

        is.close();
        String encoded = Base64.encodeToString(inputData, Base64.DEFAULT);
        return encoded;
    }

    public static Bitmap stringToBitmap(String bitmapString){
        byte[] decodedString = Base64.decode(bitmapString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
