package com.suffixit.stickynote.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.suffixit.stickynote.model.DistrictModel;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static DistrictModel getAllDistrict(Context context) throws IOException {

        InputStream inputStream = context.getAssets().open("districts.json");
        byte[] stream = new byte[inputStream.available()];

        inputStream.read(stream);
        inputStream.close();

        return new Gson().fromJson(String.valueOf(stream), DistrictModel.class);
    }

}
