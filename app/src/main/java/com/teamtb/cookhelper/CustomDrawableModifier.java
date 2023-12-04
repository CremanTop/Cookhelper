package com.teamtb.cookhelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.PorterDuff;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class CustomDrawableModifier {
    public static Drawable changeColor(Context context, Drawable drawable, int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(context, color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable.mutate();
    }
}
