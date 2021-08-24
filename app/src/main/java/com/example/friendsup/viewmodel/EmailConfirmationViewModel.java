package com.example.friendsup.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class EmailConfirmationViewModel extends ViewModel {
    public MutableLiveData<String> message = new MutableLiveData<>();

    public MutableLiveData<String> getMessage() {
        if (message == null) {
            this.message = new MutableLiveData<>();
        }
        return message;
    }

    public void sendEmailClick(View view) {
        message.setValue("We send your an email!");
    }

    public void checkEmailConfirmedClick(View view) {

    }
}