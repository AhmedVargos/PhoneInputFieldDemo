package com.fallenleaf.notekeeper.phonefielddemo.CustomeField;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.telephony.PhoneNumberFormattingTextWatcher;
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

/**
 * Created by Ahmed Ehab on 6/27/2018.
 */
public class PhoneField extends LinearLayout {
    Spinner flagSpinner;
    TextInputEditText phoneTextField;
    private View view;
    private int codeLength = -1;
    private TextWatcher phoneTextWatcher = new TextWatcher() {
        private String realOldText;

        //private int tempSelection;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //tempSelection = phoneTextField.getSelectionStart() - 1;
            realOldText = s.toString();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
             //String tempString = s.toString();
            //s = tempString.replaceAll(" ","");
            /*if( s.length() % 3 == 0 ){
                phoneTextField.setText(s + " ");
                phoneTextField.setSelection(phoneTextField.
                        getText().toString().length());

            }*/

            //makes the limit of numbers are 10
            if( (s.length() - codeLength) > 10 ){
                phoneTextField.setText(s.subSequence(0,s.length()-1));
                phoneTextField.setSelection(phoneTextField.
                        getText().toString().length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.e("TEST", "onTextChanged: ");

            //prevent the user from removing the code
            if (!s.toString().startsWith(codeString)) {
                String oldText = realOldText.replace(" ", ""); //phoneTextField.getText().toString().replace(" ", "");
                if(codeLength != -1){
                    oldText = oldText.substring(codeLength-1,oldText.length());
                }

                phoneTextField.setText(codeString + oldText);
                phoneTextField.setSelection(phoneTextField.
                        getText().toString().length());

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
        //addView(view,0);
        flagSpinner = findViewById(R.id.phone_field_flag_spinner);
        phoneTextField = findViewById(R.id.phone_field_textInput);

        if (flagSpinner == null || phoneTextField == null) {
            throw new IllegalStateException("Please provide a valid xml layout");
        }
    }



    public void setFlagSpinner(TypedArray flagImagesArray, String[] flagCodesArray){
        final CustomFlagAdapter adapter = new CustomFlagAdapter(getContext(),flagImagesArray,flagCodesArray);
        flagSpinner.setAdapter(adapter);
        flagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //change the code of the phone text field
                changeTheInputFieldCode((String) adapterView.getItemAtPosition(i) + " ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        phoneTextField.addTextChangedListener(phoneTextWatcher);

    }

    private void changeTheInputFieldCode(String codeSelected) {
        String oldText = phoneTextField.getText().toString();
        if(codeLength != -1){
            oldText = oldText.substring(codeLength,phoneTextField.length());
        }
        codeLength = codeSelected.length();
        codeString = codeSelected;
        phoneTextField.setText(codeString + oldText);
        phoneTextField.setSelection(phoneTextField.getText().toString().length());
    }

    public String getPhoneNumber(){
        return phoneTextField.getText().toString();
    }



    public Spinner getFlagSpinner() {
        return flagSpinner;
    }


    public TextInputEditText getPhoneTextField() {
        return phoneTextField;
    }

}

