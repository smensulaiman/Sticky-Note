package com.acjl.foodmenu.utils;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.acjl.foodmenu.model.DistrictModel;
import com.acjl.foodmenu.model.Note;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    private static final String TAG = "Utils";

    public static DistrictModel getAllDistrict(Context context) throws IOException {
        InputStream inputStream = context.getAssets().open("districts.json");
        byte[] stream = new byte[inputStream.available()];

        inputStream.read(stream);
        inputStream.close();

        return new Gson().fromJson(new String(stream, "UTF-8"), DistrictModel.class);
    }

    public static void shareText(Context context, Note note){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                note.getTitle() +"\n\n"+note.getDescription());
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }

}
