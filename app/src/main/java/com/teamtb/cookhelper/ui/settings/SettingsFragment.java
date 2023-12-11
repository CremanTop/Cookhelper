package com.teamtb.cookhelper.ui.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.teamtb.cookhelper.R;
import com.teamtb.cookhelper.databinding.FragmentSettingsBinding;
import com.teamtb.cookhelper.locale.LocaleHelper;

public class SettingsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentSettingsBinding binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchTheme = binding.switchTheme;
        Spinner spinnerLanguage = binding.spinnerLanguage;
        Button buttonAccept = binding.buttonLanguageAccept;

        final String[] values = {"Русский", "English (US)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.requireContext(), R.layout.language, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLanguage.setAdapter(adapter);

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedItem = (String) spinnerLanguage.getSelectedItem();
                switch (selectedItem) {
                    case "Русский":
                        LocaleHelper.setLocale(SettingsFragment.this.getContext(), "ru");
                        SettingsFragment.this.getActivity().recreate();
                        break;
                    case "English (US)":
                        LocaleHelper.setLocale(SettingsFragment.this.getContext(), "en");
                        SettingsFragment.this.getActivity().recreate();
                        break;
                }
            }
        });
        SharedPreferences sharedPreference = requireActivity().getSharedPreferences("night", 0);
        Boolean isNightMode = sharedPreference.getBoolean("night_mode", true);
        switchTheme.setChecked(isNightMode);
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = sharedPreference.edit();
                    editor.putBoolean("night_mode", true);
                    editor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = sharedPreference.edit();
                    editor.putBoolean("night_mode", false);
                    editor.apply();
                }
            }
        });

        return root;
    }
}