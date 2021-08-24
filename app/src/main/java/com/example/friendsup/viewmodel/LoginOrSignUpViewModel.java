package com.example.friendsup.viewmodel;

import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.friendsup.R;

public class LoginOrSignUpViewModel extends ViewModel {
    public void onLoginClick(View view) {
        System.out.println("Click");
        Navigation.findNavController(view).navigate(R.id.action_loginOrSignUpFragment_to_loginFragment);
    }

    public void onRegistrationClick(View view) {
        System.out.println("Click");
        Navigation.findNavController(view).navigate(R.id.action_loginOrSignUpFragment_to_registrationFragment);
    }
}