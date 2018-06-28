package com.fallenleaf.notekeeper.phonefielddemo;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.fallenleaf.notekeeper.phonefielddemo.CustomeField.PhoneField;

/**
 * Created by Ahmed Ehab on 6/27/2018.
 */
public class MainActivity extends AppCompatActivity{

    PhoneField phoneField;
    Button submitButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneField = findViewById(R.id.activity_phone_filed);
        submitButton = findViewById(R.id.submit_button);

        String[] codesArray = getResources().getStringArray(R.array.codesArray);
        TypedArray flagImgs = getResources().obtainTypedArray(R.array.flag_images);

        phoneField.setFlagSpinner(flagImgs,codesArray);
    }
}
