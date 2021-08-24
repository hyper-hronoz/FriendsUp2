package com.example.friendsup.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.friendsup.databinding.LoginOrSignUpFragmentBinding;
import com.example.friendsup.viewmodel.LoginOrSignUpViewModel;
import com.example.friendsup.R;
import com.example.friendsup.databinding.LoginFragmentBinding;

public class LoginOrSignUpFragment extends Fragment {

    private LoginOrSignUpViewModel mViewModel;

    public static LoginOrSignUpFragment newInstance() {
        return new LoginOrSignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LoginOrSignUpFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.login_or_sign_up_fragment, container, false);
        View view = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(LoginOrSignUpViewModel.class);

        binding.setLoginSignUpViewModel(mViewModel);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}