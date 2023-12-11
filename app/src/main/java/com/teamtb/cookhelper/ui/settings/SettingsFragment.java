package com.teamtb.cookhelper.ui.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
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

        switchTheme.setChecked(true);

        return root;
    }
}