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

import com.example.friendsup.R;
import com.example.friendsup.databinding.EmailFragmentBinding;
import com.example.friendsup.databinding.RegistrationFragmentBinding;
import com.example.friendsup.model.RegisteredUserModel;
import com.example.friendsup.validator.StringValidator;
import com.example.friendsup.viewmodel.EmailViewModel;
import com.example.friendsup.viewmodel.RegistrationViewModel;
import com.google.gson.Gson;

import javax.xml.validation.Validator;

public class EmailFragment extends Fragment {

    private EmailViewModel mViewModel;

    public static EmailFragment newInstance() {
        return new EmailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        EmailFragmentBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.email_fragment, container, false);
        View view = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(EmailViewModel.class);

        mViewModel.getEmail().observe(getActivity(), email -> {
            StringValidator validator = new StringValidator();
            String errors = "";
            mViewModel.isCorrect.setValue(false);
            binding.errors.setVisibility(View.GONE);
            if (!validator.validate(email, "Email").isEmpty().isEmail().validate()) {
                for (String message: validator.getMessage()) {
                    errors += message + "\n";
                }
            }
            if (errors != "") {
                binding.errors.setVisibility(View.VISIBLE);
                binding.errors.setText(errors);
                return;
            }
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.user_registration), Context.MODE_PRIVATE);
            String string = sharedPreferences.getString("key", "");
            RegisteredUserModel registeredUserModel = new RegisteredUserModel();
            if (string != "") {
                registeredUserModel = new Gson().fromJson(string, RegisteredUserModel.class);
                System.out.println("this is the user");
            } else {
                System.out.println("String is empty");
            }
            registeredUserModel.setEmail(email);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("key", new Gson().toJson(registeredUserModel));
            editor.commit();
            mViewModel.registeredUserModelMutableLiveData.setValue(registeredUserModel);
            System.out.println(registeredUserModel.toString());
            mViewModel.isCorrect.setValue(true);
        });

        binding.setEmailViewModel(mViewModel);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}