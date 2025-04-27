package com.example.schedu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedu.adapters.ScheduleAdapter;
import com.example.schedu.pojo.StudyClass;

import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private static final String EXTRA_STUDY_GROUP = "study_group";
    private ScheduleViewModel viewModel;
    private RecyclerView recyclerViewSchedule;
    private ScheduleAdapter scheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        initViews();
        scheduleAdapter = new ScheduleAdapter();
        recyclerViewSchedule.setAdapter(scheduleAdapter);

        viewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        //TODO add subgroup logic
        String studyGroup = getIntent().getStringExtra(EXTRA_STUDY_GROUP).split(" ")[0];
        viewModel.loadSchedule(studyGroup, 2);
        viewModel.getClasses().observe(this, new Observer<List<List<StudyClass>>>() {
            @Override
            public void onChanged(List<List<StudyClass>> classesList) {
                scheduleAdapter.setClasses(classesList);
                Log.d("ScheduleActivity", classesList.toString());
            }
        });
    }

    private void initViews() {
        recyclerViewSchedule = findViewById(R.id.recyclerViewSchedule);
    }

    public static Intent newIntent(Context context, String studyGroup) {
        Intent intent = new Intent(context, ScheduleActivity.class);
        intent.putExtra(EXTRA_STUDY_GROUP, studyGroup);
        return intent;
    }
}