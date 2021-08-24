package com.example.friendsup.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.friendsup.R;
import com.example.friendsup.model.RegisteredUserModel;
import com.example.friendsup.repository.RegistrationRepository;
import com.google.gson.Gson;

import java.util.HashMap;

public class EmailViewModel extends AndroidViewModel {

    public MutableLiveData<String> email;
    public MutableLiveData<String> emailProxy = new MutableLiveData<>();

    public MutableLiveData<String> errors = new MutableLiveData<>();
    public MutableLiveData<Boolean> isCorrect = new MutableLiveData<>();

    public MutableLiveData<RegisteredUserModel> registeredUserModelMutableLiveData = new MutableLiveData<>();

    public EmailViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getEmail() {
        if (email == null) {
            email = new MutableLiveData<String>();
        }
        return email;
    }

    public void onClick(View view) {
        email.setValue(emailProxy.getValue());
        if (isCorrect.getValue()) {
            regUser();
            Navigation.findNavController(view).navigate(R.id.action_emailFragment_to_emailConfirmationFragment);
        }
    }

    private void regUser() {
        RegistrationRepository registrationRepository = new RegistrationRepository();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("key", new Gson().toJson(registeredUserModelMutableLiveData.getValue()));
        if (registeredUserModelMutableLiveData != null) {
            registrationRepository.setRemotes(getApplication().getApplicationContext(), map);
        } else {
            System.out.println("RegistrationViewModel equals null");
        }
    }

    // TODO: Implement the ViewModel
    public void onAlreadyHaveAccountClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_emailFragment_to_loginFragment);
    }
}