package com.example.schedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout linearLayoutStudyGroup;
    private LinearLayout linearLayoutTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();

        linearLayoutStudyGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = GroupSearchActivity.newIntent(HomeActivity.this);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        linearLayoutStudyGroup = findViewById(R.id.linearLayoutStudyGroup);
        linearLayoutTeachers = findViewById(R.id.linearLayoutTeachers);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}