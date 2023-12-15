package com.teamtb.cookhelper.ui.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.teamtb.cookhelper.R;
import com.teamtb.cookhelper.databinding.FragmentSettingsBinding;
import com.teamtb.cookhelper.locale.LocaleHelper;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentSettingsBinding binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchTheme = binding.switchTheme;
        Spinner spinnerLanguage = binding.spinnerLanguage;

        final String[] values = {"Русский", "English (US)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.requireContext(), R.layout.language, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLanguage.setAdapter(adapter);

        String lan = LocaleHelper.getLanguage(this.requireContext());
        switch (lan) {
            case "en":
                spinnerLanguage.setSelection(1);
                break;
            case "ru":
                spinnerLanguage.setSelection(0);
                break;
        }

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) spinnerLanguage.getSelectedItem();
                switch (selectedItem) {
                    case "Русский":
                        if (!Objects.equals(LocaleHelper.getLanguage(SettingsFragment.this.getContext()), "ru")) {
                            LocaleHelper.setLocale(SettingsFragment.this.getContext(), "ru");
                            SettingsFragment.this.getActivity().recreate();
                        }
                        break;
                    case "English (US)":
                        if (!Objects.equals(LocaleHelper.getLanguage(SettingsFragment.this.getContext()), "en")) {
                            LocaleHelper.setLocale(SettingsFragment.this.getContext(), "en");
                            SettingsFragment.this.getActivity().recreate();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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