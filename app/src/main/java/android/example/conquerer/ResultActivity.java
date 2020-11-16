package android.example.conquerer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView answerCorrect, yourScore;
    CheckBox moreThan50, lessthan50;
    boolean isFiftyplus, isNotFiftyplus;
    String name;

    private EditText nameEditText;
    private int totalQuestion, finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public void show_score(View view) {
        yourScore = findViewById(R.id.your_score);
        answerCorrect = findViewById(R.id.answer_correctly);
        nameEditText = findViewById(R.id.user_name);
        moreThan50 = findViewById(R.id.more_than);
        isFiftyplus = moreThan50.isChecked();
        lessthan50 = findViewById(R.id.less_than);
        isNotFiftyplus = lessthan50.isChecked();
        name = nameEditText.getText().toString();

        if (isFiftyplus && finalScore >= 7) {
            displayToastifRight();

            Intent intent = getIntent();
            totalQuestion = intent.getIntExtra("totalQuestion", 0);
            finalScore = intent.getIntExtra("finalScore", 0);
            int totalScore = finalScore * 100 / 7 + 1;

            yourScore.setText(String.format("%s%%", Integer.toString(Integer.valueOf(totalScore))));
            answerCorrect.setText(name + totalScore + "% out of 100");
        }else {
            displayToastifWrong();

            Intent intent = getIntent();
            totalQuestion = intent.getIntExtra("totalQuestion", 0);
            finalScore = intent.getIntExtra("finalScore", 0);
            int totalScore = finalScore * 100 / 7;

            yourScore.setText(String.format("%s%%", Integer.toString(Integer.valueOf(totalScore))));
            answerCorrect.setText(name + totalScore + "% out of 100");
        }
    }

    private void displayToastifRight() {
        Toast.makeText(this, "Confident Boost", Toast.LENGTH_SHORT).show();
    }
    private void displayToastifWrong() {
        Toast.makeText(this, "This was a confidence test \n it's a free point for having confidence in your ability", Toast.LENGTH_LONG).show();
    }
}