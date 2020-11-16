package android.example.conquerer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mQuizImage;
    private String mAnswer;
    private int mScore = 0;

    private int mQuizNum = 1;
    private int questionNum = 0;

    private TextView mQuestionViews;
    private TextView mQuizNumView;


    private Questions mQuestions = new Questions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionViews = findViewById(R.id.question_TextViews);
        mQuizNumView = findViewById(R.id.quiz_numTextViews);

        updateQuestions();

        Button Submit = findViewById(R.id.submit_button);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quiz logic
                if (mQuestions.getType(questionNum) == "radiobutton") {
                    if (mQuestions.getCorrectAnswer(questionNum).equals(mAnswer)) {
                        mScore++;
                        displayToastCorrectAnswer();
                    }else {
                        displayToastInCorrectAnswer();
                    }
                }

                SystemClock.sleep(1000);

                if (questionNum == mQuestions.getLength() - 1) {
                    // displaying results

                    Intent intent_result = new Intent(MainActivity.this, ResultActivity.class);
                    intent_result.putExtra("totalQuestion", mQuestions.getLength());
                    intent_result.putExtra("finalScore", mScore);
                    startActivity(intent_result);

                    questionNum = 0;
                    mQuizNum = 0;
                    mScore = 0;
                }else {
                    questionNum++;
                    mQuizNum++;
                }

                updateQuestions();
            }
        });
    }

    private void displayToastCorrectAnswer() {
        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
    }
    private void displayToastInCorrectAnswer() {
        Toast.makeText(this, "inCorrect", Toast.LENGTH_SHORT).show();
    }

    private void updateQuestions() {
        LinearLayout answer_Layout = findViewById(R.id.answer_layout);
        answer_Layout.removeAllViews();
        mAnswer = "";

        mQuizNumView.setText(mQuizNum + "/" + String.valueOf(mQuestions.getLength()));
        mQuestionViews.setText(mQuestions.getQuestions(questionNum));

        if (mQuestions.getType(questionNum) == "radiobutton") {
            showAnswerButton(questionNum);
        }
        showImage();

        ScrollView sv = findViewById(R.id.Scrollview_id);

        sv.smoothScrollTo(0, 0);
    }

    private void showImage () {
        mQuizImage = findViewById(R.id.quiz_ImageView);

        String img = mQuestions.getImages(questionNum);

        mQuizImage.setImageResource(getResources().getIdentifier(img, "drawable", getPackageName()));
    }

    private void showAnswerButton(int qnum) {

        final LinearLayout answerLayout = findViewById(R.id.answer_layout);

        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
          LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        rg.setLayoutParams(lp);
        rg.setPadding(200,0,0,0);

        final RadioButton[] rb1 = new RadioButton[3];

        for (int i = 0; i <= 2; i++) {
            rb1[i] = new RadioButton(this);
            rb1[i].setText(mQuestions.getChoices(qnum) [i]);
            rb1[i].setTextColor(Color.BLACK);
            rb1[i].setPadding(8,16,8,16);
            rb1[i].setTextSize(25);
            rb1[i].setId(i);
            rb1[i].setWidth(500);

            rg.addView(rb1[i]);
        }

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mAnswer = mQuestions.getChoices(questionNum)[checkedId];
            }
        });

        answerLayout.addView(rg);
    }
}