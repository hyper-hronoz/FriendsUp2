package com.example.friendsup.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.friendsup.databinding.EmailFragmentBinding;
import com.example.friendsup.databinding.PasswordFragmentBinding;
import com.example.friendsup.model.RegisteredUserModel;
import com.example.friendsup.validator.StringValidator;
import com.example.friendsup.viewmodel.EmailViewModel;
import com.example.friendsup.viewmodel.PasswordViewModel;
import com.example.friendsup.R;
import com.google.gson.Gson;

public class PasswordFragment extends Fragment {

    private PasswordViewModel mViewModel;

    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        PasswordFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.password_fragment, container, false);
        View view = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(PasswordViewModel.class);
        binding.setPasswordViewModel(mViewModel);

        Bundle bundle = this.getArguments();

        mViewModel.getPasswords().observe(getActivity(), passwords -> {
            String messages = "";
            String password1 = passwords.split(" ")[0];
            String password2 = passwords.split(" ")[1];

            System.out.println("Password1: " + password1);
            System.out.println("Password2: " + password2);

            binding.errors.setText("");
            mViewModel.isCorrect.setValue(false);
            binding.errors.setVisibility(View.GONE);
            if (password1.length() < 8) {
                messages += "Password minimal length 8" + "\n";
            }
            if (!password1.equals(password2)) {
                messages += "Password do not match" + "\n";
            }
            if (messages != "") {
                binding.errors.setVisibility(View.VISIBLE);
                binding.errors.setText(messages);
                return;
            }
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.user_registration), Context.MODE_PRIVATE);
            String string = sharedPreferences.getString("key", "");
            RegisteredUserModel registeredUserModel = new RegisteredUserModel();
            if (string != "") {
                registeredUserModel = new Gson().fromJson(string, RegisteredUserModel.class);
                System.out.println("this is the user");
                System.out.println(registeredUserModel.toString());
            } else {
                System.out.println("String is empty");
            }
            registeredUserModel.setPassword(password1);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("key", new Gson().toJson(registeredUserModel));
            editor.commit();

            mViewModel.isCorrect.setValue(true);
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}