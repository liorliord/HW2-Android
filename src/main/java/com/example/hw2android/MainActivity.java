package com.example.hw2android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int answer_cnt = 0;
    private static int cur_q_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set_next_question();
    }

    @SuppressLint("SetTextI18n")
    public void answer_click(View view) {
        QuestionsDB qdb = new QuestionsDB();
        String correct_answer = qdb.choices[cur_q_index][0];
        Button b = (Button)view;
        String answer_chosen = b.getText().toString();

        if (answer_chosen.equals(correct_answer))
            answer_cnt++;
        cur_q_index++;
        TextView score = findViewById(R.id.score);
        score.setText("score: "+answer_cnt);
        set_next_question();
    }

    public void set_next_question(){
        if(cur_q_index == 12) {
            new AlertDialog.Builder(this)
                    .setTitle("DONE! :)")
                    .setMessage("You answered "+answer_cnt+" questions.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
                    cur_q_index = 0;
                    answer_cnt = 0;
        }
        else{
            QuestionsDB qdb = new QuestionsDB();
            List<String> tmp_answer_lst = new ArrayList<>(Arrays.asList(qdb.choices[cur_q_index]));
            Collections.shuffle(tmp_answer_lst);
            TextView q = findViewById(R.id.question);
            TextView a1 = findViewById(R.id.ans1);
            TextView a2 = findViewById(R.id.ans2);
            TextView a3 = findViewById(R.id.ans3);
            TextView a4 = findViewById(R.id.ans4);
            q.setText(qdb.Questions[cur_q_index]);
            a1.setText(tmp_answer_lst.get(0));
            a2.setText(tmp_answer_lst.get(1));
            a3.setText(tmp_answer_lst.get(2));
            a4.setText(tmp_answer_lst.get(3));
        }
    }

}


