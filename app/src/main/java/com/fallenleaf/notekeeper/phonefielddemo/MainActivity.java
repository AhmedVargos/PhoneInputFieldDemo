package com.fallenleaf.notekeeper.phonefielddemo;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        String[] codesArray = getResources().getStringArray(R.array.codesArray); //Codes
        TypedArray flagImgs = getResources().obtainTypedArray(R.array.flag_images); //Flags

        phoneField.setFlagSpinner(flagImgs,codesArray); //Add the flagImages and codes

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The Phone Number Is: " + phoneField.getPhoneNumber(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
