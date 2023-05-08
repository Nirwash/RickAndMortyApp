package com.nirwashh.rickandmortyapp.core.utils;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class StringParser {
    public static String parser(List<String> strings) {
        ArrayList<String> returnStrings = new ArrayList<>();
        for (String string : strings) {
            Uri uri = Uri.parse(string);
            String[] parts = uri.toString().split("/");
            String id = parts[parts.length - 1];
            returnStrings.add(id);
        }
        return String.valueOf(returnStrings);
    }
}
