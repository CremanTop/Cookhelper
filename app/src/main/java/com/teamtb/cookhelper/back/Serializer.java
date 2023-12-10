package com.teamtb.cookhelper.back;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class Serializer {
    public static <T> void serializeData(Context context, String path, ArrayList<T> list) {
        String data = new Gson().toJson(list);

        try {
            FileOutputStream file = context.openFileOutput(path, Context.MODE_PRIVATE);
            file.write(data.getBytes());
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> ArrayList<T> deserializeData(Context context, String path) {
        try {
            FileInputStream fileInput = context.openFileInput(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInput));

            StringBuilder stringBuilder = new StringBuilder();
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuilder.append(lines).append("\n");
            }

            return new Gson().fromJson(stringBuilder.toString(), ArrayList.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            serializeData(context, path, new ArrayList<>());
            return deserializeData(context, path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
