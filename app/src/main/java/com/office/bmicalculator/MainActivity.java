package com.office.bmicalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String PREF_IS_METRIC = "system_of_unit";

    TextView txt_result_bmi;
    TextView txt_result_cat;
    AutoCompleteTextView txt_height;
    AutoCompleteTextView txt_weight;
    Button btn_more_info;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText e1 = (EditText) findViewById(R.id.txt_height);
        final EditText e2 = (EditText) findViewById(R.id.txt_weight);
        final TextView tv4 = (TextView) findViewById(R.id.txt_result_bmi);

//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

//        txt_height = findViewById(R.id.txt_height);
//        txt_weight = findViewById(R.id.txt_weight);
//        initTextField(txt_height);
//        initTextField(txt_weight);

//        txt_result_bmi = findViewById(R.id.txt_result_bmi);
//        txt_result_cat = findViewById(R.id.txt_result_cat);
        btn_more_info = findViewById(R.id.btn_more_info);

        btn_more_info.setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @Override
            public void onClick(View v) {

                String str1 = e1.getText().toString();
                String str2 = e2.getText().toString();

                if (TextUtils.isEmpty(str1)) {
                    e1.setError("Please enter your weight");
                    e1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(str2)) {
                    e2.setError("Please enter your height");
                    e2.requestFocus();
                    return;
                }

//Get the user values from the widget reference
                float weight = Float.parseFloat(str1);
                float height = Float.parseFloat(str2) / 100;

//Calculate BMI value
                float bmiValue = calculateBMI(weight, height);

//Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);

                tv4.setText(String.valueOf(bmiValue + "-" + bmiInterpretation));

            }
        });


    }

    //Calculate BMI
    private float calculateBMI(float weight, float height) {
        return (float) (weight / (height * height));
    }

    // Interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue < 18.5) {

            return "Underweight";
        } else if (bmiValue < 25) {

            return "Normal";
        } else if (bmiValue < 30) {

            return "Overweight";
        } else {
            return "Obese";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

//    private void initTextField(EditText editText) {
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//    @SuppressLint("StringFormatMatches")
//    private void calculateBmiIfPossible() {
//
//        if (isValidInput(txt_height) && isValidInput(txt_weight)) {
//            double bmi = calculateBmiAndCastIfNeeded(getTextAsDouble(txt_height), getTextAsDouble(txt_weight));
//            txt_result_bmi.setText(getString(R.string.bmi_result, bmi));
//            txt_result_cat.setText(getCategory(bmi));
//        } else {
//            txt_result_bmi.setText("");
//            txt_result_cat.setText("");
//        }
//
//    }
//
//    private boolean isValidInput(EditText editText) {
//        return getTextAsDouble(editText) > 0;
//    }
//
//    private double getTextAsDouble(EditText editText) {
//        String input = editText.getText().toString().replace(',', '.');
//        try {
//            return Double.valueOf(input);
//        } catch (NumberFormatException e) {
//            return 0;
//        }
//
//    }
//
//    private double calculateBmiAndCastIfNeeded(double height, double weight) {
//        height = isMetric() ? height : height / 39.37008;
//        weight = isMetric() ? weight : weight / 2.204623;
//        return calculateBmi(height, weight);
//    }
//
//    public static double calculateBmi(double height, double weight) {
//        return Math.round(weight / Math.pow(height, 2) * 10d) / 10d;
//    }
//
//    private String getCategory(double bmi) {
//        if (bmi < 15) {
//            return getString(R.string.bmi_cat_1);
//        }
//        if (bmi < 16) {
//            return getString(R.string.bmi_cat_2);
//        }
//        if (bmi < 18.5) {
//            return getString(R.string.bmi_cat_3);
//        }
//        if (bmi < 25) {
//            return getString(R.string.bmi_cat_4);
//        }
//        if (bmi < 30) {
//            return getString(R.string.bmi_cat_5);
//        }
//        if (bmi < 35) {
//            return getString(R.string.bmi_cat_6);
//        }
//        if (bmi < 40) {
//            return getString(R.string.bmi_cat_7);
//        }
//        if (bmi < 45) {
//            return getString(R.string.bmi_cat_8);
//        }
//        if (bmi < 50) {
//            return getString(R.string.bmi_cat_9);
//        }
//        if (bmi < 60) {
//            return getString(R.string.bmi_cat_10);
//        }
//        return getString(R.string.bmi_cat_11);
//    }
//
//    private void setSystemOfUnits() {
//        RadioButton btn_metric = findViewById(R.id.btn_metric);
//        RadioButton btn_imperial = findViewById(R.id.btn_imperial);
//        btn_metric.setChecked(isMetric());
//        btn_imperial.setChecked(!isMetric());
//
//        TextInputLayout txt_weight_outer = findViewById(R.id.txt_weight_outer);
//        TextInputLayout txt_height_outer = findViewById(R.id.txt_height_outer);
//        txt_weight_outer.setHint(isMetric() ? getString(R.string.weight_metric) : getString(R.string.weight_imperial));
//        txt_height_outer.setHint(isMetric() ? getString(R.string.height_metric) : getString(R.string.height_imperial));
//    }
//
//    private boolean isMetric() {
//        boolean defaultToMetric = getString(R.string.default_unit).equals(getString(R.string.metric));
//        return sharedPreferences.getBoolean(PREF_IS_METRIC, defaultToMetric);
//    }
//
//    public void setSystemOfUnits(View v) {
//        sharedPreferences.edit().putBoolean(PREF_IS_METRIC, v.getId() == R.id.btn_metric).apply();
//        setSystemOfUnits();
//        calculateBmiIfPossible();
//    }

}
