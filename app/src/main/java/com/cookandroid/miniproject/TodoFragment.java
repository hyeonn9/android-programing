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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TodoFragment extends Fragment {

    private ArrayList<ToDoItem> toDoList;
    private ToDoListAdapter adapter;
    private ListView listView;
    private EditText editText;

    TextView mDateView;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toDoList = new ArrayList<>();
        adapter = new ToDoListAdapter(toDoList);
        listView = view.findViewById(R.id.listView);
        editText = view.findViewById(R.id.edtTodo);
        listView.setAdapter(adapter);

        mDateView = view.findViewById(R.id.date);
        mDateView.setText(getTime());

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
            toDoList.add(new ToDoItem(itemText, false));
            adapter.notifyDataSetChanged();
            editText.setText("");
        }
    }

    private class ToDoListAdapter extends ArrayAdapter<ToDoItem> {

        public ToDoListAdapter(ArrayList<ToDoItem> toDoList) {
            super(getActivity(), 0, toDoList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todolist, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.checkBox = convertView.findViewById(R.id.todoCheck);
                viewHolder.todoText = convertView.findViewById(R.id.todoTxt);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            ToDoItem currentItem = getItem(position);

            if (currentItem != null) {
                viewHolder.todoText.setText(currentItem.getText());
                viewHolder.checkBox.setOnCheckedChangeListener(null);
                viewHolder.checkBox.setChecked(currentItem.isChecked());

                viewHolder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    currentItem.setChecked(isChecked);
                });
            }

            return convertView;
        }

        private class ViewHolder {
            CheckBox checkBox;
            TextView todoText;
        }
    }

    private static class ToDoItem {
        private String text;
        private boolean isChecked;

        public ToDoItem(String text, boolean isChecked) {
            this.text = text;
            this.isChecked = isChecked;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}
