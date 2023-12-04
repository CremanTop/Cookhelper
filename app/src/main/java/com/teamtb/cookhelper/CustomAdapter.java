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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CustomAdapter extends ArrayAdapter<String> {
    private final ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>();

    public CustomAdapter(@NonNull Context context) {
        this(context, new ArrayList<String>());
    }

    public CustomAdapter(@NonNull Context context, List<String> objects) {
        super(context, R.layout.ingredient, R.id.ingredientName, objects);

        for (String str : objects) {
            addElement(str);
        }
    }

    public void addElement(String key) {
        int[] colors = new int[] {R.color.orange_button, R.color.green_button, R.color.cyan_button, R.color.pink_button, R.color.yellow_button, R.color.violet_button};
        int color = colors[new Random().nextInt(colors.length)];
        AbstractMap.SimpleEntry<String, Integer> entry = new AbstractMap.SimpleEntry<>(key, color);
        list.add(entry);
        this.add(key);
        this.notifyDataSetChanged();
    }

    public void removeElement(int position, String key) {
        if (list.size() <= position) return;
        list.remove(position);
        CustomAdapter.this.remove(key);
        CustomAdapter.this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ingredient, null);
        }

        RelativeLayout relativeLayout = view.findViewById(R.id.ingredientLayout);
        ImageView back = view.findViewById(R.id.ingredientBack);
        TextView textView = view.findViewById(R.id.ingredientName);
        ImageButton buttonRemove = view.findViewById(R.id.buttonIngredientRemove);

        if (list.size() <= position) return view;
        Map.Entry<String, Integer> entry = list.get(position);

        if (entry == null) return view;
        textView.setText(entry.getKey());

        Drawable drawable = ContextCompat.getDrawable(this.getContext(), R.drawable.field_back);
        drawable.setColorFilter(ContextCompat.getColor(this.getContext(), entry.getValue()), PorterDuff.Mode.SRC_ATOP);
        back.setImageDrawable(drawable);

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                removeElement(position, entry.getKey());
                //Toast.makeText(view.getContext(), entry.getKey() + position, Toast.LENGTH_SHORT).show();
            }
        });

        String item = getItem(position);
        //Toast.makeText(this.getContext(), item, Toast.LENGTH_SHORT).show();

//        // Примените логику, чтобы установить нужный цвет для RelativeLayout
//        if (/* условие для изменения цвета */) {
//            relativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.your_color));
//        } else {
//            relativeLayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.default_color));
//        }

        return view;
    }
}