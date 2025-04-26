package com.example.schedu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedu.adapters.GroupAdapter;
import com.example.schedu.pojo.Group;

import java.util.List;

public class GroupSearchActivity extends AppCompatActivity {

    private GroupSearchViewModel viewModel;
    private EditText editTextSearch;
    private RecyclerView recyclerViewGroups;
    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_search);
        initViews();

        viewModel = new ViewModelProvider(this).get(GroupSearchViewModel.class);
        Log.d("GroupSearchActivity", recyclerViewGroups.toString());
        groupAdapter = new GroupAdapter();
        recyclerViewGroups.setAdapter(groupAdapter);

        viewModel.loadGroups();
        viewModel.getGroups().observe(this, new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {
                groupAdapter.setGroups(groups);
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.filterGroups(s.toString());
            }
        });
    }

    private void initViews() {
        editTextSearch = findViewById(R.id.editTextSearch);
        recyclerViewGroups = findViewById(R.id.recyclerViewGroups);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, GroupSearchActivity.class);
    }
}