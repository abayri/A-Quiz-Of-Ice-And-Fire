package com.example.android.asongoficeandfirequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // constant variables to represent Toast messages depending on score received by user
    final String TAG_1 = "Sorry, better luck next time!";
    final String TAG_2 = "Not the best, but not the worst either :-)";
    final String TAG_3 = "Nice work!";
    final String TAG_4 = "Woohoo! You're a genius!";

    // constant variable to track total number of questions
    final int NUMBER_OF_QUESTIONS = 8;

    // Declarations for the correct radio button answers
    // Reference for binding views: https://www.sitepoint.com/tidying-code-with-android-butterknife/
    @BindView(R.id.correct_choice_1)
    RadioButton correct1;
    @BindView(R.id.correct_choice_2)
    RadioButton correct2;
    @BindView(R.id.correct_choice_3)
    RadioButton correct3;
    @BindView(R.id.correct_choice_4)
    RadioButton correct4;
    @BindView(R.id.correct_choice_5)
    RadioButton correct5;
    @BindView(R.id.correct_choice_6)
    RadioButton correct6;

    // Declarations for RadioGroups
    @BindView(R.id.group_1)
    RadioGroup group1;
    @BindView(R.id.group_2)
    RadioGroup group2;
    @BindView(R.id.group_3)
    RadioGroup group3;
    @BindView(R.id.group_4)
    RadioGroup group4;
    @BindView(R.id.group_5)
    RadioGroup group5;
    @BindView(R.id.group_6)
    RadioGroup group6;

    int lengthOfRadio = 6;

    // Declarations for correct text answer
    @BindView(R.id.house_words)
    EditText textAnswer;

    // Declarations for correct checkbox answers
    @BindView(R.id.cb_1)
    CheckBox cb1;
    @BindView(R.id.cb_2)
    CheckBox cb2;
    @BindView(R.id.cb_3)
    CheckBox cb3;
    @BindView(R.id.cb_4)
    CheckBox cb4;

    // Declarations for filler checkbox answers
    @BindView(R.id.filler_1)
    CheckBox wrong1;
    @BindView(R.id.filler_2)
    CheckBox wrong2;

    int lengthOfCheckBoxes = 6;

    // Counter to check the number of correct answers chosen by user
    int counter = 0;

    // Correct text answer
    String answer = "hear me roar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Injects the views from the XML file into the Main Activity with Butter Knife library
        ButterKnife.bind(this);
    }

    public void submitQuiz(View view) {
        RadioButton[] correctRadioChoices = new RadioButton[lengthOfRadio];
        correctRadioChoices[0] = correct1;
        correctRadioChoices[1] = correct2;
        correctRadioChoices[2] = correct3;
        correctRadioChoices[3] = correct4;
        correctRadioChoices[4] = correct5;
        correctRadioChoices[5] = correct6;

        RadioGroup[] radioGroups = new RadioGroup[lengthOfRadio];
        radioGroups[0] = group1;
        radioGroups[1] = group2;
        radioGroups[2] = group3;
        radioGroups[3] = group4;
        radioGroups[4] = group5;
        radioGroups[5] = group6;

        CheckBox[] correctCheckedBoxes = new CheckBox[lengthOfCheckBoxes];
        correctCheckedBoxes[0] = cb1;
        correctCheckedBoxes[1] = cb2;
        correctCheckedBoxes[2] = cb3;
        correctCheckedBoxes[3] = cb4;
        correctCheckedBoxes[4] = wrong1;
        correctCheckedBoxes[5] = wrong2;

        for (int i = 0; i < correctRadioChoices.length; i++) {
            boolean isCorrect = correctRadioChoices[i].isChecked();

            if (isCorrect) {
                counter++;
            }
        }

        // Check to see all the proper check boxes are selected (no partial marks)
        if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked()
                && !wrong1.isChecked() && !wrong2.isChecked()) {
            counter++;
        }

        String text = textAnswer.getText().toString().toLowerCase();

        if (text.compareTo(answer) == 0) {
            counter++;
        }

        if (counter <= 3) {
            Toast.makeText(MainActivity.this, "You got " + counter + "/" +
                    NUMBER_OF_QUESTIONS + " correct." + "\n" +
                    TAG_1, Toast.LENGTH_LONG).show();
        } else if (counter <= 5) {
            Toast.makeText(MainActivity.this, "You got " + counter + "/" +
                    NUMBER_OF_QUESTIONS + " correct." + "\n" +
                    TAG_2, Toast.LENGTH_LONG).show();
        } else if (counter <= 7) {
            Toast.makeText(MainActivity.this, "You got " + counter + "/" +
                    NUMBER_OF_QUESTIONS + " correct." + "\n" +
                    TAG_3, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "You got " + counter + "/" +
                    NUMBER_OF_QUESTIONS + " correct." + "\n" +
                    TAG_4, Toast.LENGTH_LONG).show();
        }

        for (int i = 0; i < radioGroups.length; i++) {
            radioGroups[i].clearCheck();
        }

        for (int i = 0; i < correctCheckedBoxes.length; i++) {
            correctCheckedBoxes[i].setChecked(false);
        }

        textAnswer.setText("");

        // Reset counter to 0 to ensure values don't keep on adding up every time user clicks submit
        counter = 0;
    }
}








