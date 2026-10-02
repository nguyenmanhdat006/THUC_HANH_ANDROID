package com.thuc_hanh.todolist;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class TaskListActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_TASK = "extra_task";
    public static final String EXTRA_TIME = "extra_time";
    public static final String EXTRA_NOTE = "extra_note";
    public static final String EXTRA_TASK_DATE = "extra_task_date";
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        setupScreen();
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void setupScreen() {
        Button addTaskButton = findViewById(R.id.add_task_button);
        addTaskButton.setOnClickListener(v ->
                startActivity(new Intent(TaskListActivity.this, AddTaskActivity.class)
                        .putExtra(EXTRA_USERNAME, username)));
    }

    private void handleIntent(Intent intent) {
        username = intent.getStringExtra(EXTRA_USERNAME);

        String task = intent.getStringExtra(EXTRA_TASK);
        if (task != null) {
            String time = intent.getStringExtra(EXTRA_TIME);
            String note = intent.getStringExtra(EXTRA_NOTE);
            long taskDate = intent.getLongExtra(EXTRA_TASK_DATE, System.currentTimeMillis());
            boolean upcoming = isFuture(taskDate);
            TextView taskView = findViewById(upcoming
                    ? R.id.upcoming_tasks_text : R.id.today_tasks_text);
            taskView.setText(getString(upcoming
                    ? R.string.upcoming_tasks_with_value : R.string.today_tasks_with_value,
                    task,
                    DateFormat.getDateInstance(DateFormat.SHORT).format(new Date(taskDate)),
                    note == null ? "" : note));
        }
    }

    private boolean isFuture(long time) {
        java.util.Calendar selected = java.util.Calendar.getInstance();
        selected.setTimeInMillis(time);
        java.util.Calendar today = java.util.Calendar.getInstance();
        selected.set(java.util.Calendar.HOUR_OF_DAY, 0);
        selected.set(java.util.Calendar.MINUTE, 0);
        selected.set(java.util.Calendar.SECOND, 0);
        selected.set(java.util.Calendar.MILLISECOND, 0);
        today.set(java.util.Calendar.HOUR_OF_DAY, 0);
        today.set(java.util.Calendar.MINUTE, 0);
        today.set(java.util.Calendar.SECOND, 0);
        today.set(java.util.Calendar.MILLISECOND, 0);
        return selected.after(today);
    }
}
