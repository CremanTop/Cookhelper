package com.teamtb.cookhelper.ui.recipe_list;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.teamtb.cookhelper.R;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeAdapter extends ArrayAdapter<String> {
    public SearchRecipeAdapter(Context context) {
        this(context, new ArrayList<>(), new ArrayList<>());
    }
    public SearchRecipeAdapter(@NonNull Context context, List<String> objects, List<String> ingredients) {
        super(context, R.layout.recipe, R.id.recipeName, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.recipe, null);
        }

        final ImageView back = view.findViewById(R.id.recipeBack);
        final TextView textView = view.findViewById(R.id.recipeName);

        textView.setText("Аниме");

        // устанавливаем цвет фону
        final Drawable drawable = ContextCompat.getDrawable(this.getContext(), R.drawable.field_back);
        drawable.setColorFilter(ContextCompat.getColor(this.getContext(), R.color.gray_light), PorterDuff.Mode.SRC_ATOP);
        back.setImageDrawable(drawable);


        return view;
    }


}
