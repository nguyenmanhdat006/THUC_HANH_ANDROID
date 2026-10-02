package com.thuc_hanh.todolist;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.DatePickerDialog;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        EditText taskInput = findViewById(R.id.task_input);
        EditText timeInput = findViewById(R.id.time_input);
        EditText noteInput = findViewById(R.id.note_input);
        Button addButton = findViewById(R.id.save_task_button);
        String username = getIntent().getStringExtra(TaskListActivity.EXTRA_USERNAME);
        Calendar selectedDate = Calendar.getInstance();
        timeInput.setFocusable(false);
        timeInput.setOnClickListener(v -> new DatePickerDialog(this, (view, year, month, day) -> {
            selectedDate.set(year, month, day, 0, 0, 0);
            selectedDate.set(Calendar.MILLISECOND, 0);
            timeInput.setText(DateFormat.getDateInstance(DateFormat.MEDIUM)
                    .format(new Date(selectedDate.getTimeInMillis())));
        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)).show());

        addButton.setOnClickListener(v -> {
            String task = taskInput.getText().toString().trim();
            String time = timeInput.getText().toString().trim();
            String note = noteInput.getText().toString().trim();
            if (task.isEmpty()) {
                Toast.makeText(this, R.string.task_required, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, TaskListActivity.class)
                    .putExtra(TaskListActivity.EXTRA_USERNAME, username)
                    .putExtra(TaskListActivity.EXTRA_TASK, task)
                    .putExtra(TaskListActivity.EXTRA_TIME, time)
                    .putExtra(TaskListActivity.EXTRA_NOTE, note)
                    .putExtra(TaskListActivity.EXTRA_TASK_DATE, selectedDate.getTimeInMillis())
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
