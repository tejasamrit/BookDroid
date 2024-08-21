package com.scrounger.countrycurrencypicker.library;

import android.content.Context;
import java.text.Normalizer;

public class Helper {
    public static Integer getFlagDrawableId(String str, Context context) {
        return Integer.valueOf(context.getResources().getIdentifier("flag_" + str.toLowerCase(), "drawable", context.getPackageName()));
    }

    public static String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
