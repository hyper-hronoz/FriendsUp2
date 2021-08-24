package com.example.friendsup.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.friendsup.R;
import com.example.friendsup.model.UserModel;
import com.example.friendsup.repository.LoginRepository;

import java.util.HashMap;

public class LoginViewModel extends AndroidViewModel {
    public MutableLiveData<UserModel> userMutableLiveData;
    public LoginRepository<HashMap<String, String>> loginRepository;

    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    public MutableLiveData<String> errors = new MutableLiveData<>();

    public MutableLiveData<Integer> errorsVisibility = new MutableLiveData<Integer>();

    public MutableLiveData<Boolean> isCorrect = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<UserModel> getUserMutableLiveData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<UserModel>();
        }
        return userMutableLiveData;
    }

    public MutableLiveData<String> getErrors() {
        if (errors == null) {
            errors = new MutableLiveData<String>();
        }
        return errors;
    }

    public void setUserLocalAuthData(HashMap<String, String> map) {
        if (loginRepository == null) {
            loginRepository = new LoginRepository<HashMap<String, String>>();
        }
        if (isCorrect.getValue()) {
            System.out.println("Setting data...");
            loginRepository.setLocals(getApplication().getApplicationContext(), map);
            loginRepository.setRemotes(getApplication().getApplicationContext(), map);
        }
    }

    public void getUserLocalAuthData() {
        if (loginRepository == null) {
            loginRepository = new LoginRepository<HashMap<String, String>>();
        }
        HashMap<String, String> map = loginRepository.getLocals(getApplication().getApplicationContext());
        System.out.println("this is getting data: " + map.get(String.valueOf(R.string.user_email)));
        EmailAddress.setValue(map.get(String.valueOf(R.string.user_email)));
        Password.setValue(map.get(String.valueOf(R.string.user_password)));
    }

    public void onClick(View view) {
        System.out.println("Click");
        userMutableLiveData.setValue(new UserModel(EmailAddress.getValue(), Password.getValue()));
    }

    public void onDonNotHaveAccountClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registrationFragment);
    }
}