package com.example.android2;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android2.databinding.ActivityCalculatorBinding;

public class Calculator extends AppCompatActivity {
ActivityCalculatorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCalculatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    public void onDigitClick(View view)
    {
        Button button=(Button)view ;
        binding.txtView.append(button.getText().toString());
    }
    static String  savedOperator="";
   static String savedNum="";
    static String savedDots="";
static  String res="";

    public void onOperatorClick(View view)  {
        Button clikedOperator = (Button) view;
        if (savedOperator.isEmpty()) {
            savedNum = binding.txtView.getText().toString().trim();
        }
        else {
            String rhs = binding.txtView.getText().toString().trim();
            savedNum=calculate(savedNum,savedOperator,rhs);
        }
        savedOperator = clikedOperator.getText().toString().trim();
        binding.txtView.setText("");
    }
    public void onEqualCLick(View view)
    {
        Button equalOerator=(Button)view;
        binding.txtView.setText(res);
    }
    public String calculate(String lhs,String operator , String rhs)
        {
            double n1 = Double.parseDouble(lhs);
            double n2 = Double.parseDouble(rhs);
            if (("+").equals(operator))
            {
                res=(n1+n2)+"";

            }
            else if(("-").equals(operator))
            {
                res=(n1-n2)+"";

            } else if (("*").equals(operator)) {
                res=(n1*n2)+"";
            } else if (("/").equals(operator)) {
                if (n2==0.0)
                {
                  res="Math error";

                }
                else
                    res=(n1/n2)+"";
           }
            else if (("=").equals((operator))) {
                savedNum="";
                savedOperator="";
                return res;
            }
            savedNum=res;
            savedOperator="";
            return res;
        }
        public void onClearClick(View view) {
            binding.txtView.setText("");
            savedOperator="";
            savedNum="";
        }
        public void onBackSpaceClick(View view) {

            String text = binding.txtView.getText().toString().trim();
            if (!text.isEmpty()) {
                StringBuffer newText = new StringBuffer(text);
                newText.deleteCharAt(newText.length() - 1);
                binding.txtView.setText(newText);
            }
        }
        public void onPowerPress(View view)
        {
            savedNum=binding.txtView.getText().toString().trim();
            double base=Double.parseDouble(savedNum);
            binding.txtView.setText(Math.pow(base,2.0)+"");
            savedNum="";
        }
        public void onSquareRootPress(View view)
        {
            savedNum=binding.txtView.getText().toString().trim();
            double base=Double.parseDouble(savedNum);
            binding.txtView.setText(Math.pow(base,0.5)+"");
            savedNum="";
        }
        public void onDotCLick(View view)
        {
            if (savedDots.isEmpty())
            {
                binding.txtView.append(".");
                savedDots=".";
            }
        }
}