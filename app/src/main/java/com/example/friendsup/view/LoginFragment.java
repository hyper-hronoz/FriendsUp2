package com.example.friendsup.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.friendsup.R;
import com.example.friendsup.databinding.LoginFragmentBinding;
import com.example.friendsup.validator.StringValidator;
import com.example.friendsup.viewmodel.LoginViewModel;

import java.util.HashMap;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    // view elements

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        LoginFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.login_fragment, container, false);
        View view = binding.getRoot();

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUserMutableLiveData().observe(getActivity(), user -> {
            System.out.println("Change");
            StringValidator stringValidator = new StringValidator();
            String messages = "";
            binding.errors.setText("");
            binding.errors.setVisibility(View.GONE);
            loginViewModel.isCorrect.setValue(false);
            if (!stringValidator.validate(user.getEmail(), "Email").isEmpty().isEmail().validate()) {
                for (String message : stringValidator.getMessage()) {
                    System.out.println(message);
                    messages += message + "\n";
                }
                binding.errors.setText(messages);
                binding.errors.setVisibility(View.VISIBLE);
                stringValidator = new StringValidator();
            } else if (!stringValidator.validate(user.getPassword(), "Password").isEmpty().minSize(8).validate()) {
                for (String message : stringValidator.getMessage()) {
                    System.out.println(message);
                    messages += message + "\n";
                }
                binding.errors.setVisibility(View.VISIBLE);
                binding.errors.setText(messages);
            } else {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(String.valueOf(R.string.user_email), user.getEmail());
                map.put(String.valueOf(R.string.user_password), user.getPassword());
                binding.errors.setVisibility(View.VISIBLE);
                loginViewModel.isCorrect.setValue(true);
                loginViewModel.setUserLocalAuthData(map);
            }
        });

        loginViewModel.getUserLocalAuthData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // TODO: Use the ViewModel
    }

}