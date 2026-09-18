package com.thuc_hanh.quan_ly_sinh_vien;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtAge, edtGpa;
    private Button btnAdd, btnClear;
    private TextView tvResult;
    private ScrollView scrollView;

    private ArrayList<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtGpa = findViewById(R.id.edtGpa);

        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);

        tvResult = findViewById(R.id.tvResult);

        btnAdd.setOnClickListener(v -> addStudent());
        btnClear.setOnClickListener(v -> clearData());
    }

    private void addStudent() {
        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String ageStr = edtAge.getText().toString().trim();
        String gpaStr = edtGpa.getText().toString().trim();

        if (id.isEmpty() || name.isEmpty() || ageStr.isEmpty() || gpaStr.isEmpty()) {
            Toast.makeText(
                    this,
                    "Vui lòng nhập đầy đủ thông tin!",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        int age = Integer.parseInt(ageStr);
        double gpa = Double.parseDouble(gpaStr);

        Student student = new Student(id, name, age, gpa);

        studentList.add(student);

        showStudentList();

        edtId.setText("");
        edtName.setText("");
        edtAge.setText("");
        edtGpa.setText("");

        edtId.requestFocus();
    }

    private void showStudentList() {

        StringBuilder result = new StringBuilder();

        for (Student s : studentList) {
            result.append("Mã SV: ")
                    .append(s.getId())
                    .append("\n");

            result.append("Họ tên: ")
                    .append(s.getName())
                    .append("\n");

            result.append("Tuổi: ")
                    .append(s.getAge())
                    .append("\n");

            result.append("GPA: ")
                    .append(s.getGpa())
                    .append("\n");

        }

        tvResult.setText(result.toString());
    }

    private void clearData() {
        studentList.clear();

        tvResult.setText("");

        edtId.setText("");
        edtName.setText("");
        edtAge.setText("");
        edtGpa.setText("");
    }
}