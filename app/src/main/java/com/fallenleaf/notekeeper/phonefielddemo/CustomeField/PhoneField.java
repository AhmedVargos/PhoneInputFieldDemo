package com.fallenleaf.notekeeper.phonefielddemo.CustomeField;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.fallenleaf.notekeeper.phonefielddemo.R;

import ru.kolotnev.formattedittext.MaskedEditText;

/**
 * Created by Ahmed Ehab on 6/27/2018.
 */
public class PhoneField extends LinearLayout {
    public static final String CODE = "CODE";
    Spinner flagSpinner;
    MaskedEditText phoneTextField;
    private View view;
    private int codeLength = -1;
    private TextWatcher phoneTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //Prevent the user from removing the code.
            String newText = s.toString();
            newText = newText.replaceAll("\\+","");
            newText = newText.replaceAll(" ","");
            if(!newText.startsWith(codeString)){
                String oldText = "";
                if(codeLength+1 <= newText.length()){
                    oldText = newText.substring(codeLength+1,newText.length());
                }
                phoneTextField.setText(codeString + oldText);
            }
        }
    };
    private String codeString;
    public PhoneField(Context context) {
        super(context);
        init(context);
    }


    public PhoneField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PhoneField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.phone_input_layout, this);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        flagSpinner = findViewById(R.id.phone_field_flag_spinner);
        phoneTextField = findViewById(R.id.phone_field_textInput);

        if (flagSpinner == null || phoneTextField == null) {
            throw new IllegalStateException("Please provide a valid xml layout");
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    public void setFlagSpinner(TypedArray flagImagesArray, String[] flagCodesArray){
        final CustomFlagAdapter adapter = new CustomFlagAdapter(getContext(),flagImagesArray,flagCodesArray);
        flagSpinner.setAdapter(adapter);
        flagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //change the code of the phone text field
                changeTheInputFieldCode((String) adapterView.getItemAtPosition(i) ); //+ " "
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        phoneTextField.addTextChangedListener(phoneTextWatcher);

    }

    private void changeTheInputFieldCode(String codeSelected) {
        String oldText = phoneTextField.getText(true).toString();
        if(codeLength != -1){
            oldText = oldText.substring(codeLength,oldText.length());
        }

        codeLength = codeSelected.length();
        codeString = codeSelected;
        String codeMask = "";
        for(int i = 0; i <codeLength; i++){
            codeMask += "9"; //add a number for code as a mask
        }

        String finalMask = "+" + codeMask + " 999 999 9999"; //Format the phone number
        phoneTextField.setMask(finalMask);
        phoneTextField.setText(codeString + oldText); // change the country code and keep old text
        phoneTextField.setSelection(phoneTextField.getText().toString().length());
    }

    public String getPhoneNumber(){
        return phoneTextField.getText(true).toString();
    }

    public Spinner getFlagSpinner() {
        return flagSpinner;
    }


    public MaskedEditText getPhoneTextField() {
        return phoneTextField;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Parcelable) {
            Bundle bundle = (Bundle) state;
            state = bundle.getParcelable("super");
            codeString = bundle.getString(CODE,"20");
        }
        super.onRestoreInstanceState(state);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putString(CODE, codeString); //Save Country code

        return bundle;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        phoneTextField.removeTextChangedListener(phoneTextWatcher);
    }
}

