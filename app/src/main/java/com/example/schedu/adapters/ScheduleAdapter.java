package com.example.schedu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedu.R;
import com.example.schedu.pojo.StudyClass;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private List<List<StudyClass>> classes = new ArrayList<>();
    private int currentWeek = 0;

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public void setClasses(List<List<StudyClass>> classes) {
        this.classes = classes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.schedule_item,
                        parent,
                        false
                );
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        List<StudyClass> day = classes.get(position);

        if (day.isEmpty()) {
            return;
        }

        String title = selectTitle(day.get(0).getDay());
        holder.textViewWeekDay.setText(title);
        StringBuilder daySchedule = new StringBuilder();
        for (StudyClass studyClass : day) {
            if (studyClass.getWeek() == currentWeek) {
                String info = String.format("%s пара (%s - %s)\n%s\n%s | %s | %s\n\n",
                        studyClass.getOrder(),
                        studyClass.getBegins(),
                        studyClass.getEnds(),
                        studyClass.getTitle(),
                        String.join(", ", studyClass.getLocation()),
                        String.join(", ", studyClass.getTeacher()),
                        String.join(", ", studyClass.getGroup())
                );
                daySchedule.append(info);
            }
        }
        holder.textViewSchedule.setText(daySchedule.toString());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    private String selectTitle(int day) {
        switch (day) {
            case 0:
                return "Понедельник";
            case 1:
                return "Вторник";
            case 2:
                return "Среда";
            case 3:
                return "Четверг";
            case 4:
                return "Пятница";
            case 5:
                return "Суббота";
        }
        return "";
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewWeekDay;
        private TextView textViewSchedule;

        public ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWeekDay = itemView.findViewById(R.id.textViewWeekDay);
            textViewSchedule = itemView.findViewById(R.id.textViewSchedule);
        }
    }
}
