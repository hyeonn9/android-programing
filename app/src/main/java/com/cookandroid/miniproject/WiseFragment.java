package com.cookandroid.miniproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class WiseFragment extends Fragment {

    private TextView wordView;
    private TextView authorView;
    private Button wiseBtn;

    private ArrayList<String> word;
    private ArrayList<String> author;
    private int currentIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wise, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wordView = view.findViewById(R.id.wiseWordView);
        authorView = view.findViewById(R.id.authorView);
        wiseBtn = view.findViewById(R.id.wisewordBtn);

        word = new ArrayList<>();
        author = new ArrayList<>();

        word.add("멈추지 말고 한 가지 목표에 매진하라. 그것이 성공의 비결이다.");
        author.add("- 안나 파블로바");
        word.add("당신의 행복은 무엇이 당신의 영혼을 노래하게 하는가에 따라 결정된다.");
        author.add("- 낸시 설리번");
        word.add("우리가 노력 없이 얻는 거의 유일한 것은 노년이다.");
        author.add("- 글로리아 피처");
        word.add("인생은 자전거를 타는 것과 같다. 균형을 잡으려면 움직여야 한다.");
        author.add("- 알버트 아인슈타인");
        word.add("인생에 있어서 최고의 행복은 우리가 사랑받고 있음을 확신하는 것이다.");
        author.add("- 빅터 위고");
        word.add("과거에서 교훈을 얻을 수는 있어도 과거 속에 살 수는 없다.");
        author.add("- 린든 B.존슨");
        word.add("오늘 할 수 있는 일을 내일로 미루지 마라.");
        author.add("- 벤자민 프랭클린");
        word.add("약간의 광기도 없는 위대한 천재란 있을 수 없다.");
        author.add("- 아리스토텔레스");

        displayWord();

        wiseBtn.setOnClickListener(view1 -> {
            currentIndex = (currentIndex + 1) % word.size();
            displayWord();
        });
    };

    private void displayWord(){
        wordView.setText(word.get(currentIndex));
        authorView.setText(author.get(currentIndex));
    }
}