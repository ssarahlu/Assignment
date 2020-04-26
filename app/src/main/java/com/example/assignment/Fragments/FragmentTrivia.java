package com.example.assignment.Fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.assignment.Data.MusicTrivia;
import com.example.assignment.Global;
import com.example.assignment.R;
import com.example.assignment.TriviaQuestionActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Used the following repository for reference: https://github.com/EklavyaM/Trivia

public class FragmentTrivia extends Fragment implements View.OnClickListener{
        private View view;
        private TextView question, optionA, optionB, optionC, optionD, pass;
        private Global g;
        private MusicTrivia trivia;
        private int position;

        public static FragmentTrivia newInstance(int position){
            FragmentTrivia frag = new FragmentTrivia();
            Bundle bundle = new Bundle();

            bundle.putInt("POSITION", position);
            frag.setArguments(bundle);
            return frag;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            view = inflater.inflate(R.layout.fragment_trivia, container, false);

            g = Global.getInstance(getContext());

            position = getArguments().getInt("POSITION");
            trivia = g.currentList.get(position);

            init();
            setup();
            return view;
        }

        private void init(){
            g = Global.getInstance(getContext());
            question = view.findViewById(R.id.question);
            optionA = view.findViewById(R.id.option_A);
            optionB = view.findViewById(R.id.option_B);
            optionC = view.findViewById(R.id.option_C);
            optionD = view.findViewById(R.id.option_D);
            pass = view.findViewById(R.id.skip);

            optionA.setOnClickListener(this);
            optionB.setOnClickListener(this);
            optionC.setOnClickListener(this);
            optionD.setOnClickListener(this);

            pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((TriviaQuestionActivity) getContext()).nextQuestion();
                }
            });
        }

        private void setup(){
            List<String> list = new ArrayList<>();
            list.add(trivia.getCorrectAnswer());
            list.addAll(trivia.getIncorrectAnswers());
            Collections.shuffle(list);

            question.setText(Html.fromHtml(trivia.getQuestion()));
            optionA.setText(Html.fromHtml(list.get(0)));
            optionB.setText(Html.fromHtml(list.get(1)));
            optionC.setText(Html.fromHtml(list.get(2)));
            optionD.setText(Html.fromHtml(list.get(3)));
        }

        @Override
        public void onClick(View v) {
            if(!g.getIsGameOver()) {
                TextView text = (TextView) v;
                String option = text.getText().toString();
                TriviaQuestionActivity instance = ((TriviaQuestionActivity) getContext());
                if (option.contentEquals(Html.fromHtml(trivia.getCorrectAnswer()))) {
                    instance.onAnswerClicked(true, trivia.getDifficulty());
                } else {
                    instance.onAnswerClicked(false, trivia.getDifficulty());
                }
            }
        }
    }