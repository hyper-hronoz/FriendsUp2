package com.example.friendsup.view;

import androidx.constraintlayout.widget.Guideline;
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
import com.example.friendsup.databinding.LoginFragmentBinding;
import com.example.friendsup.databinding.RegistrationFragmentBinding;
import com.example.friendsup.validator.StringValidator;
import com.example.friendsup.viewmodel.RegistrationViewModel;
import com.google.gson.Gson;

public class RegistrationFragment extends Fragment {

    private RegistrationViewModel mViewModel;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RegistrationFragmentBinding binding = DataBindingUtil.inflate( inflater, R.layout.registration_fragment, container, false);
        View view = binding.getRoot();

        mViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        binding.errors.setVisibility(View.GONE);
        mViewModel.getRegisteredUserModelMutableLiveData().observe(getActivity(), registeredUserModel -> {

            System.out.println("Hello this viewmodel works");
            System.out.println(registeredUserModel.toString());

            binding.errors.setText("");
            binding.errors.setVisibility(View.GONE);
            mViewModel.isCorrect.setValue(false);

            String messages = "";
            StringValidator stringValidator = new StringValidator();
            if (!(stringValidator.validate(registeredUserModel.getUsername(), "fields").isEmpty().validate() )) {
                System.out.println("Validation is incorrect");
                for (String message : stringValidator.getMessage()) {
                    System.out.println(message);
                    messages += message + "\n";
                }
                binding.errors.setVisibility(View.VISIBLE);
            }
            if (!(registeredUserModel.getGender() == "M" || registeredUserModel.getGender() == "W" || registeredUserModel.getGender() == "O")) {
                messages += "You must select gender to continue" + "\n";
            }
            if (messages != "") {
                binding.errors.setText(messages);
                return;
            }
            Gson gson = new Gson();
            String json = gson.toJson(registeredUserModel);


            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.user_registration), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("key", json);
            editor.commit();
            mViewModel.isCorrect.setValue(true);

        });

        binding.setRegistrationViewModel(mViewModel);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}