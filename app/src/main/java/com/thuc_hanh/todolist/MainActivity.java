package com.thuc_hanh.todolist;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText usernameInput = findViewById(R.id.username_input);
        EditText passwordInput = findViewById(R.id.password_input);
        Button loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString();

            if ("admin".equals(username) && "12345".equals(password)) {
                Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
                intent.putExtra(TaskListActivity.EXTRA_USERNAME, username);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }
}