package com.upadhyde.android.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class ReaderUtils {

    private final static int HEADER_TEXT_FILE = 223;
    private final static String TEXT_FILE_ROW_SEPERATER = "!";


    public static String[] readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append(TEXT_FILE_ROW_SEPERATER);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return text.toString().substring(HEADER_TEXT_FILE).split(TEXT_FILE_ROW_SEPERATER);

    }


}
