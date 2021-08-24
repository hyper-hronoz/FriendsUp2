package com.example.friendsup.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.friendsup.model.RegisteredUserModel;
import com.example.friendsup.viewmodel.EmailConfirmationViewModel;
import com.example.friendsup.R;
import com.example.friendsup.databinding.EmailConfirmationFragmentBinding;
import com.google.gson.Gson;

public class EmailConfirmationFragment extends Fragment {

    private EmailConfirmationViewModel mViewModel;

    public static EmailConfirmationFragment newInstance() {
        return new EmailConfirmationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        EmailConfirmationFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.email_confirmation_fragment, container, false);
        View view = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(EmailConfirmationViewModel.class);

        binding.setEmailConfirmationViewModel(mViewModel);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.user_registration), Context.MODE_PRIVATE);
        RegisteredUserModel registeredUserModel = new Gson().fromJson(sharedPreferences.getString("key", ""), RegisteredUserModel.class);

        binding.userMessage.setText("We've sent you a confirmation email to " + registeredUserModel.getEmail() + ", please confirm it to continue");

        mViewModel.getMessage().observe(getActivity(), message -> {
            if (message != null) {
                Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}