package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text;
    TextView ans;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    Button add, sub, mult, div;
    Button clear, equal;
    private String output = "";
    private String operation = "";
    private ArrayList <String> operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operations = new ArrayList<String>();
        text = findViewById(R.id.id_text);
        ans = findViewById(R.id.id_answer);

        b0 = findViewById(R.id.id_button0);
        b0.setOnClickListener(this);

        b1 = findViewById(R.id.id_button1);
        b1.setOnClickListener(this);

        b2 = findViewById(R.id.id_button2);
        b2.setOnClickListener(this);

        b3 = findViewById(R.id.id_button3);
        b3.setOnClickListener(this);

        b4 = findViewById(R.id.id_button4);
        b4.setOnClickListener(this);

        b5 = findViewById(R.id.id_button5);
        b5.setOnClickListener(this);

        b6 = findViewById(R.id.id_button6);
        b6.setOnClickListener(this);

        b7 = findViewById(R.id.id_button7);
        b7.setOnClickListener(this);

        b8 = findViewById(R.id.id_button8);
        b8.setOnClickListener(this);

        b9 = findViewById(R.id.id_button9);
        b9.setOnClickListener(this);

        add = findViewById(R.id.id_buttonAdd);
        add.setOnClickListener(this);

        sub = findViewById(R.id.id_buttonSub);
        sub.setOnClickListener(this);

        mult = findViewById(R.id.id_buttonMult);
        mult.setOnClickListener(this);

        div = findViewById(R.id.id_buttonDiv);
        div.setOnClickListener(this);

        clear = findViewById(R.id.id_buttonCE);
        clear.setOnClickListener(this);

        equal = findViewById(R.id.id_buttonEqual);
        equal.setOnClickListener(this);
    }

    double compute (String op, double left , double right)
    {
        if (op == "+")
            return left + right ;
        else if (op == "-")
            return left - right ;
        else if (op == "/")
            return left / right ;
        else if (op == "*")
            return left * right ;
        else
            return Double.parseDouble("ERROR");
    }

    double getResult (String output)
    {
        double result = 0.0 ;
        double leftval = 0.0;
        double rightval = 0.0;
        if (operations.size() > 0 )
        {
            String left = "";
            StringTokenizer right = new StringTokenizer(output, operations.get(0));
            left = right.nextToken();
            result = Double.parseDouble(left);
            int oplen = 0;
            int opsize = operations.size();

            for(int i = 1; i < opsize; i++)
            {
                oplen = oplen + left.length() + 1;

                String newString = output.substring(oplen);

                right = new StringTokenizer(newString.toString(),operations.get(i));
                left = right.nextToken();
                leftval = Double.parseDouble(left);
                result = compute(operations.get(i-1), result, leftval);
            }
            String finalright = output.substring(oplen+2);
            double finalval = Double.parseDouble(finalright);
            String opfinal = operations.get(opsize - 1);
            result = compute (opfinal, result , finalval );
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_button0)
            output = text.getText()+(String)b0.getText();

        else if (v.getId() == R.id.id_button1)
            output = text.getText()+(String)b1.getText();

        else if (v.getId() == R.id.id_button2)
            output = text.getText()+(String)b2.getText();

        else if (v.getId() == R.id.id_button3)
            output = text.getText()+(String)b3.getText();

        else if (v.getId() == R.id.id_button4)
            output = text.getText()+(String)b4.getText();

        else if (v.getId() == R.id.id_button5)
            output = text.getText()+(String)b5.getText();

        else if (v.getId() == R.id.id_button6)
            output = text.getText()+(String)b6.getText();

        else if (v.getId() == R.id.id_button7)
            output = text.getText()+(String)b7.getText();

        else if (v.getId() == R.id.id_button8)
            output = text.getText()+(String)b8.getText();

        else if (v.getId() == R.id.id_button9)
            output = text.getText()+(String)b9.getText();

        else if (v.getId() == R.id.id_buttonAdd) {
            operation = "+";
            output = text.getText()+operation;
            operations.add("+");
        }

        else if (v.getId() == R.id.id_buttonSub) {
            operations.add("-");
            operation = "-";
            output = text.getText() + operation;
        }

        else if (v.getId() == R.id.id_buttonMult){
            operations.add("*");
            operation = "*";
            output = text.getText() + operation;
        }

        else if (v.getId() == R.id.id_buttonDiv){
            operations.add("/");
            operation = "/";
            output = text.getText() + operation;
        }

        else if (v.getId() == R.id.id_buttonCE)
        {
            if (operations.size() > 1)
                ans.setText(Double.toString(getResult(output)));
            else
                ans.setText(output);
            output = "";
            operations.clear();
        }

        text.setText(output);
        String newOutput = (String)text.getText();

        if (v.getId() == R.id.id_buttonEqual)
        {
            if (operations.size() > 1 )
            {
                double resultOutput = getResult(output);
                ans.setText(output);
                text.setText(Double.toString(resultOutput));
                operations.clear();
                Log.d("NG_PRINT", "THE result is " + resultOutput);
            }
            else if (operations.size()  == 1) {

                output = (String)text.getText();

                if (operation.equals("+"))
                {
                    if (output.substring(0,1).equals("+") || output.substring(output.length()-1).equals("+") || output.substring(1,3).equals("++"))
                    {
                        output = "ERROR";
                    }
                    else
                    {
                        StringTokenizer printAdd = new StringTokenizer(output,"+");
                        double sum = 0.0;
                        double num = 0.0;
                        while (printAdd.hasMoreTokens())
                            sum += num+Double.parseDouble(printAdd.nextToken());

                        ans.setText("Ans "+output+" =");
                        output = Double.toString(sum);
                    }
                    text.setText(output);
                }

                if (operation.equals("-"))
                {
                    if (output.substring(0,1).equals("-") || output.substring(output.length()-1).equals("-"))
                    {
                        output = "ERROR";
                    }
                    else
                    {
                        StringTokenizer printSub = new StringTokenizer(output,"-");
                        double diff = 0.0;
                        while (printSub.hasMoreTokens())
                        {
                            diff = Double.parseDouble(printSub.nextToken());
                            diff -= Double.parseDouble(printSub.nextToken());
                        }
                        ans.setText("Ans "+output+" =");
                        output = Double.toString(diff);
                    }
                    text.setText(output);
                }

                if (operation.equals("*"))
                {
                    if (output.substring(0,1).equals("*") || output.substring(output.length()-1).equals("*"))
                    {
                        output = "ERROR";
                    }

                    else
                    {
                        StringTokenizer printMult = new StringTokenizer(output,operation);
                        double prod = 0.0;
                        while (printMult.hasMoreTokens())
                        {
                            prod = Double.parseDouble(printMult.nextToken());
                            prod *= Double.parseDouble(printMult.nextToken());
                        }
                        ans.setText("Ans "+output+" =");
                        output = Double.toString(prod);
                    }

                    text.setText(output);
                }

                if (operation.equals("/"))
                {
                    if (output.substring(0,1).equals("/") || output.substring(output.length()-1).equals("/"))
                    {
                        output = "ERROR";
                    }
                    else
                    {
                        StringTokenizer printDiv = new StringTokenizer(output,operation);
                        double quo = 0.0;
                        while (printDiv.hasMoreTokens())
                        {
                            quo = Double.parseDouble(printDiv.nextToken());
                            quo /= Double.parseDouble(printDiv.nextToken());
                        }
                        ans.setText("Ans "+output+" =");
                        output = Double.toString(quo);
                    }
                    text.setText(output);
                }

            }

        }

    }

}
