package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;


public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button addButton;
    Button deleteButton;
    int selectedPosition = -1;   // -1 = nothing selected

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

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_city_button);
        deleteButton = findViewById(R.id.delete_city_button);


        String []cities = {"Edmonton","Middle of no where"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        addButton.setOnClickListener(v -> {
            EditText input = new EditText(this);

            new AlertDialog.Builder(this)
                    .setTitle("Enter city name")
                    .setView(input)
                    .setPositiveButton("CONFIRM", (dialog, which) -> {
                        String cityName = input.getText().toString();
                        dataList.add(cityName);
                        cityAdapter.notifyDataSetChanged();
                    })
                    .show();
        });

        deleteButton.setOnClickListener(v -> {
            if (selectedPosition >= 0 && selectedPosition < dataList.size()) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });

    }
}