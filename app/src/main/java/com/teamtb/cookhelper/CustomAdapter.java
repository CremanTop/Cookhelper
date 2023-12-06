package com.teamtb.cookhelper;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CustomAdapter extends ArrayAdapter<String> {
    private final ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>();

    public CustomAdapter(@NonNull Context context) {
        this(context, new ArrayList<>());
    }

    public CustomAdapter(@NonNull Context context, List<String> objects) {
        super(context, R.layout.ingredient, R.id.ingredientName, objects);

        for (String str : objects) {
            addElement(str);
        }
        for (String str : deserializeData()) {
            addElement(str);
        }
    }

    public void addElement(String key) {
        int[] colors = new int[] {R.color.orange_button, R.color.green_button, R.color.cyan_button, R.color.pink_button, R.color.yellow_button, R.color.violet_button};
        int color = colors[new Random().nextInt(colors.length)]; // берём случайный цвет
        String text = key.toLowerCase();
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<>(text, color); // пара значений: текст-цвет
        list.add(entry);
        this.add(text);
        this.notifyDataSetChanged();

        serializeData();
    }

    public void removeElement(int position, String key) {
        if (list.size() <= position) return;
        list.remove(position);
        CustomAdapter.this.remove(key);
        CustomAdapter.this.notifyDataSetChanged();

        serializeData();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ingredient, null);
        }

        final RelativeLayout relativeLayout = view.findViewById(R.id.ingredientLayout);
        final ImageView back = view.findViewById(R.id.ingredientBack);
        final TextView textView = view.findViewById(R.id.ingredientName);
        final ImageButton buttonRemove = view.findViewById(R.id.buttonIngredientRemove);

        if (list.size() <= position) return view;
        Map.Entry<String, Integer> entry = list.get(position); // пара текст-цвет для текущего элемента

        if (entry == null) return view;

        String text = entry.getKey();
        text = text.substring(0, 1).toUpperCase() + text.substring(1); // делаем первую букву заглавной

        // если весь текст не помещается в поле, то обрезаем его
        if (text.length() > 9) {
            text = text.substring(0, 9) + "...";
        }

        textView.setText(text);

        // устанавливаем цвет фону
        final Drawable drawable = ContextCompat.getDrawable(this.getContext(), R.drawable.field_back);
        drawable.setColorFilter(ContextCompat.getColor(this.getContext(), entry.getValue()), PorterDuff.Mode.SRC_ATOP);
        back.setImageDrawable(drawable);

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removeElement(position, entry.getKey());
            }
        });

        //String item = getItem(position);
        return view;
    }

    public void serializeData() {
        ArrayList<String> list_ingredients = new ArrayList<>();
        for (Map.Entry<String, Integer> element : list) {
            list_ingredients.add(element.getKey());
        }
        String data = new Gson().toJson(list_ingredients);

        try {
            FileOutputStream file = this.getContext().openFileOutput("ingredient_data.json", Context.MODE_PRIVATE);
            file.write(data.getBytes());
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this.getContext(), "File not found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> deserializeData() {
        try {
            FileInputStream fileInput = this.getContext().openFileInput("ingredient_data.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInput));

            StringBuilder stringBuilder = new StringBuilder();
            String lines = "";
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuilder.append(lines).append("\n");
            }

            return new Gson().fromJson(stringBuilder.toString(), ArrayList.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this.getContext(), "File not found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}