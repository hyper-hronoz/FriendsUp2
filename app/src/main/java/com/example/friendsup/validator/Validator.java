package com.example.friendsup.validator;

import java.util.List;

public interface Validator<T> {

    boolean validate();
    T validate(String string);
    T validate(String string, String stringName);
    List<String> getMessage();
}
