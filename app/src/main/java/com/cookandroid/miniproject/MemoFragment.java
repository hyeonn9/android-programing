package com.cookandroid.miniproject;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemoFragment extends Fragment {

    private ArrayList<DiaryItem> diaryList;
    private DiaryListAdapter adapter;
    private ListView listView;
    private EditText editText;
    private RadioGroup weatherGroup;
    private ImageView weatherImageView;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        diaryList = new ArrayList<>();
        adapter = new DiaryListAdapter(diaryList);
        listView = view.findViewById(R.id.listView);
        editText = view.findViewById(R.id.editDiary);
        weatherGroup = view.findViewById(R.id.weatherView);
        weatherImageView = view.findViewById(R.id.weatherImageView);
        listView.setAdapter(adapter);

        Button addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToList();
            }
        });
    }

    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    private void addItemToList() {
        String itemText = editText.getText().toString();
        if (!itemText.isEmpty()) {
            int selectedWeatherId = weatherGroup.getCheckedRadioButtonId();
            RadioButton selectedWeatherButton = weatherGroup.findViewById(selectedWeatherId);
            String weather = selectedWeatherButton != null ? selectedWeatherButton.getText().toString() : "날씨 없음";

            int weatherIcon = getWeatherIconResourceId(selectedWeatherId);
            diaryList.add(new DiaryItem(getTime(), itemText, weather, weatherIcon));
            adapter.notifyDataSetChanged();
            editText.setText("");
            weatherGroup.clearCheck();
            weatherImageView.setImageResource(0); // 이미지 뷰 초기화
        }
    }

    private int getWeatherIconResourceId(int checkedId) {
        if (checkedId == R.id.wClear) {
            return R.drawable.icon_wclear;
        } else if (checkedId == R.id.wCloudy) {
            return R.drawable.icon_wcloudy;
        } else if (checkedId == R.id.wIce) {
            return R.drawable.icon_wrainsnow;
        } else {
            return 0; // 이미지 뷰 초기화
        }
    }

    private class DiaryListAdapter extends ArrayAdapter<DiaryItem> {

        public DiaryListAdapter(ArrayList<DiaryItem> diaryList) {
            super(getActivity(), 0, diaryList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_diary, parent, false);
            }

            DiaryItem currentItem = getItem(position);

            TextView dateView = convertView.findViewById(R.id.dateView);
            ImageView weatherImageView = convertView.findViewById(R.id.weatherImageView);
            TextView diaryView = convertView.findViewById(R.id.diaryView);

            if (currentItem != null) {
                dateView.setText(currentItem.getDate());
                weatherImageView.setImageResource(currentItem.getWeatherIcon());
                diaryView.setText(currentItem.getText());
            }

            return convertView;
        }
    }

    private static class DiaryItem {
        private String date;
        private String text;
        private String weather;
        private int weatherIcon;

        public DiaryItem(String date, String text, String weather, int weatherIcon) {
            this.date = date;
            this.text = text;
            this.weather = weather;
            this.weatherIcon = weatherIcon;
        }

        public String getDate() {
            return date;
        }

        public String getText() {
            return text;
        }

        public String getWeather() {
            return weather;
        }

        public int getWeatherIcon() {
            return weatherIcon;
        }
    }
}
