package com.teamtb.cookhelper.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.teamtb.cookhelper.R;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Введите ингридиенты");
    }

    public LiveData<String> getText() {
        return mText;
    }
}