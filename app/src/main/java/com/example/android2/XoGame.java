package com.example.android2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android2.databinding.ActivityCalculatorBinding;
import com.example.android2.databinding.ActivityXoGameBinding;

public class XoGame extends AppCompatActivity {
    ActivityXoGameBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityXoGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    static int counterPlay=1;
  static   boolean continueGame=true;
    public void onNewGameClick(View view)
    {
        continueGame=true;
        Intent mIntent=getIntent();
        finish();
        startActivity(mIntent);
        counterPlay=1;
    }
    public void onButtonClick(View view)
    {
        while (continueGame) {
            Button button = (Button) view;
            boolean winnerCheck;
            if (!button.getText().toString().isEmpty())
                return;
            if (counterPlay % 2 == 0) {
                button.setText("X");
                winnerCheck = checkWinner("X");
                if (winnerCheck == true) {
                    Toast.makeText(this, "PLayer1 win (X)", Toast.LENGTH_SHORT);
                    continueGame = false;
                    binding.WinnerBoard.append("X");
                    counterPlay=1;
                }
            } else {
                button.setText("O");
                winnerCheck = checkWinner("O");
                if (winnerCheck == true) {
                    Toast.makeText(this, "PLayer2 win (O)", Toast.LENGTH_SHORT);
                    continueGame = false;
                    binding.WinnerBoard.append("O");
                    counterPlay=1;
                }
            }
            counterPlay++;
            if(counterPlay==10&&continueGame==true)
            {
                binding.WinnerBoard.setText("Draw");
                continueGame=false;
                counterPlay=0;
            }
        }
    }
    public boolean checkWinner(String playerSympol)
    {
        Button button;
        for (int j=1;j<10;j+=3) {
            int count=0;
            for (int i = j; i <= j+2; i++) {
                button = binding.getRoot().findViewWithTag(i + "");
                if (!playerSympol.equals(button.getText())) {
                    break;
                }
                count++;
            }
            if (count==3)
            {
                return true;
            }
        }
        for (int j=1;j<=3;j++) {
            int count=0;
            for (int i = j; i <= j+6; i+=3) {
                button = binding.getRoot().findViewWithTag(i + "");
                if (!playerSympol.equals(button.getText())) {
                    break;
                }
                count++;
            }
            if (count==3)
            {
                return true;
            }
        }
        for (int j=1;j<2;j++) {
            int count=0;
            for (int i = j; i <= j+8; i+=4) {
                button = binding.getRoot().findViewWithTag(i + "");
                if (!playerSympol.equals(button.getText())) {
                    break;
                }
                count++;
            }
            if (count==3)
            {
                return true;
            }
        }
        for (int j=3;j<4;j++) {
            int count=0;
            for (int i = j; i <= j+4; i+=2) {
                button = binding.getRoot().findViewWithTag(i + "");
                if (!playerSympol.equals(button.getText())) {
                    break;
                }
                count++;
            }
            if (count==3)
            {
                return true;
            }
        }
        return false;
    }
}