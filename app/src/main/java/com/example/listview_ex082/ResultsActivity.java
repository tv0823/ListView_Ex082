package com.example.listview_ex082;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Intent gi;
    ListView firstTwentyLv;
    TextView firstNumTv, numDTv, placeTv, sumToNumTV;
    double firstNum, numD, seqSum;
    int math;
    String[] seqArr;
    double[] sumValuesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        firstTwentyLv = findViewById(R.id.firstTwentyLv);
        firstNumTv = findViewById(R.id.firstNumTv);
        numDTv = findViewById(R.id.numD);
        placeTv = findViewById(R.id.placeTv);
        sumToNumTV = findViewById(R.id.sumToNumTV);

        gi = getIntent();
        firstNum = gi.getDoubleExtra("firstNum",0);
        numD = gi.getDoubleExtra("numD",0);
        math = gi.getIntExtra("action", -1);

        seqSum = 0;
        seqArr = new String[20];
        sumValuesArr = new double[20];

        calcArrValues();

        firstTwentyLv.setOnItemClickListener(this);
        firstTwentyLv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, seqArr);
        firstTwentyLv.setAdapter(adp);

        firstNumTv.setText("X1 = " + String.format("%.2f", firstNum));
        numDTv.setText("d = " + String.format("%.2f", numD));
    }

    public String bigNumSimplifier(double value){
        String scientificNotation = String.format("%.4e", value);
        String[] parts = scientificNotation.split("e");
        double base = Double.parseDouble(parts[0]) / 10.0;
        int exponent = Integer.parseInt(parts[1]) + 1;

        return String.format("%.4f * 10^%d", base, exponent);
    }

    public void calcArrValues() {
        for (int i = 0; i < 20; i++) {
            double currentValue;

            if (math == 1) {
                currentValue = firstNum + i * numD;
            } else {
                currentValue = firstNum * Math.pow(numD, i);
            }

            if (currentValue > 1000000) {
                seqArr[i] = bigNumSimplifier(currentValue);
            } else {
                seqArr[i] = String.format("%.2f", currentValue);
            }

            seqSum += currentValue;
            sumValuesArr[i] = seqSum;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        firstNumTv.setVisibility(View.VISIBLE);
        numDTv.setVisibility(View.VISIBLE);
        placeTv.setVisibility(View.VISIBLE);
        sumToNumTV.setVisibility(View.VISIBLE);

        placeTv.setText("n = " + (pos+1));
        if (sumValuesArr[pos] > 1000000) {
            sumToNumTV.setText("Sn = " + bigNumSimplifier(sumValuesArr[pos]));
        } else {
            sumToNumTV.setText("Sn = " + String.format("%.2f", sumValuesArr[pos]));
        }
    }

    public void goBack(View view) {
        setResult(RESULT_OK, gi);
        finish();
    }
}