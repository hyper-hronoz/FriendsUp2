package com.example.friendsup.viewmodel;

import android.provider.Telephony;
import android.view.View;
import android.widget.RadioGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.friendsup.R;
import com.example.friendsup.model.RegisteredUserModel;

public class RegistrationViewModel extends ViewModel {
    public MutableLiveData<String> firstName = new MutableLiveData<>();
    public MutableLiveData<String> lastName = new MutableLiveData<>();
    public MutableLiveData<String> genderSelected = new MutableLiveData<>();
    public MutableLiveData<Integer> genderIdSelected = new MutableLiveData<Integer>();
    public MutableLiveData<RegisteredUserModel> registeredUserModelMutableLiveData;
    public MutableLiveData<String> errors = new MutableLiveData<>();
    public MutableLiveData<Boolean> isCorrect = new MutableLiveData<>();

    RegisteredUserModel registeredUserModel = new RegisteredUserModel();

    public void onRegistrationButtonClick(View view) {
        registeredUserModel.setUsername(firstName.getValue() + " " + lastName.getValue());
        System.out.println(registeredUserModel.toString());
        registeredUserModelMutableLiveData.setValue(registeredUserModel);
        if (isCorrect.getValue()) {
            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_passwordFragment);
        }
    }

    public MutableLiveData<RegisteredUserModel> getRegisteredUserModelMutableLiveData() {
        if (registeredUserModelMutableLiveData == null) {
            registeredUserModelMutableLiveData = new MutableLiveData<>();
        }
        return registeredUserModelMutableLiveData;
    }

    public void onAlreadyHaveAccount(View view) {
        System.out.println("Click");
        Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
    }

    public void onSplitTypeChanged(RadioGroup radioGroup, int id) {
        System.out.println("Checked id is: " + id);
        switch (id) {
            case -1:
                break;
            case R.id.radio_men:
                registeredUserModel.setGender("M");
                break;
            case R.id.radio_women:
                registeredUserModel.setGender("W");
                break;
            case R.id.radio_other:
                registeredUserModel.setGender("O");
                break;
        }
    }
}