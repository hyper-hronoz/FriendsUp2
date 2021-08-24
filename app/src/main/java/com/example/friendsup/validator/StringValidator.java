package com.example.friendsup.validator;

import java.util.ArrayList;
import java.util.List;

public class StringValidator implements Validator<StringValidator> {
    private boolean isCorrect = true;
    List<String> errorMessages = new ArrayList<>();

    private String stringName = "";
    private String string;

    @Override
    public StringValidator validate(String string) {
        this.string = string;
        this.stringName = "";
        return this;
    }

    @Override
    public StringValidator validate(String string, String stringName) {
        System.out.println(string + " " + stringName);
        this.string = string;
        this.stringName = stringName;
        return this;
    }

    @Override
    public boolean validate() {
        System.out.println(isCorrect);
        return isCorrect;
    }

    @Override
    public List<String> getMessage() {
        return errorMessages;
    }

    public StringValidator isEmpty() {
        System.out.println("Is empty: " + string);
        if (string == null || string.trim() == "" || string.equals("null null")) {
            System.out.println(string);
            errorMessages.add("The field " + stringName + " couldn't be empty!");
            this.isCorrect = false;
        }
        return this;
    }

    public StringValidator minSize(int min) {
        if (string != null) {
            if (string.trim().length() < min) {
                errorMessages.add("Field " + stringName + " min size is " + min);
                this.isCorrect = false;
            }
        }
        return this;
    }


    public StringValidator maxSize(int max) {
        if (string != null) {
            if (string.trim().length() > max) {
                errorMessages.add("Field " + stringName + " max size is " + max);
                this.isCorrect = false;
            }
        }
        return this;
    }

    public StringValidator isEmail() {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        if (string != null) {
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(string.trim());
            if (!m.matches())  {
                errorMessages.add("Field " + stringName + " must be an email");
                this.isCorrect = false;
            }
        }
        return this;
    }

}
