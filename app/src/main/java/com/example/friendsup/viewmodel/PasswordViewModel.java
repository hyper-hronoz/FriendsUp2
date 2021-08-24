package com.example.friendsup.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.friendsup.R;

public class PasswordViewModel extends ViewModel {

    public MutableLiveData<String> password1 = new MutableLiveData<>();
    public MutableLiveData<String> password2 = new MutableLiveData<>();
    public MutableLiveData<String> passwords = new MutableLiveData<>();

    public MutableLiveData<Boolean> isCorrect = new MutableLiveData<>();

    public MutableLiveData<String> getPasswords() {
        if (this.passwords == null) {
            passwords = new MutableLiveData<>();
        }
        return passwords;
    }

    public void onClick(View view) {
        passwords.setValue(password1.getValue() + " " + password2.getValue());
        if (isCorrect.getValue()) {
            Navigation.findNavController(view).navigate(R.id.action_passwordFragment_to_emailFragment);
        }
    }
}
