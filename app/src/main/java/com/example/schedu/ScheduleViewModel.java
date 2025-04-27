package com.example.schedu;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedu.api.ApiFactory;
import com.example.schedu.pojo.Schedule;
import com.example.schedu.pojo.ScheduleFilterPayload;
import com.example.schedu.pojo.Selector;
import com.example.schedu.pojo.StudyClass;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ScheduleViewModel extends ViewModel {

    private static final int DAY_MONDAY = 0;
    private static final int DAY_TUESDAY = 1;
    private static final int DAY_WEDNESDAY = 2;
    private static final int DAY_THURSDAY = 3;
    private static final int DAY_FRIDAY = 4;
    private static final int DAY_SATURDAY = 5;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<List<List<StudyClass>>> classes = new MutableLiveData<>();

    public LiveData<List<List<StudyClass>>> getClasses() {
        return classes;
    }

    public void loadSchedule(String studyGroup, int subgroup) {
        ScheduleFilterPayload payload = new ScheduleFilterPayload(
                "group",
                new Selector(studyGroup, subgroup)
        );
        Log.d("ScheduleViewModel", payload.toString());
        Disposable disposable = ApiFactory.getInstance()
                .loadSchedule(payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Schedule, List<List<StudyClass>>>() {
                    @Override
                    public List<List<StudyClass>> apply(Schedule schedule) throws Throwable {
                        Log.d("ScheduleViewModel", schedule.toString());
                        List<StudyClass> classesFromApi = schedule.getClassesList();
                        return getWeekClasses(classesFromApi);
                    }
                })
                .subscribe(new Consumer<List<List<StudyClass>>>() {
                    @Override
                    public void accept(List<List<StudyClass>> weekClasses) throws Throwable {
                        classes.setValue(weekClasses);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private List<List<StudyClass>> getWeekClasses(List<StudyClass> classesFromApi) {
        List<List<StudyClass>> weekClasses = new ArrayList<>();
        List<StudyClass> mondayClasses = new ArrayList<>();
        List<StudyClass> tuesdayClasses = new ArrayList<>();
        List<StudyClass> wednesdayClasses = new ArrayList<>();
        List<StudyClass> thursdayClasses = new ArrayList<>();
        List<StudyClass> fridayClasses = new ArrayList<>();
        List<StudyClass> saturdayClasses = new ArrayList<>();
        for (StudyClass studyClass : classesFromApi) {
            switch (studyClass.getDay()) {
                case DAY_MONDAY:
                    mondayClasses.add(studyClass);
                    break;
                case DAY_TUESDAY:
                    tuesdayClasses.add(studyClass);
                    break;
                case DAY_WEDNESDAY:
                    wednesdayClasses.add(studyClass);
                    break;
                case DAY_THURSDAY:
                    thursdayClasses.add(studyClass);
                    break;
                case DAY_FRIDAY:
                    fridayClasses.add(studyClass);
                    break;
                case DAY_SATURDAY:
                    saturdayClasses.add(studyClass);
                    break;
            }
        }
        if (!mondayClasses.isEmpty()) {
            weekClasses.add(mondayClasses);
        }
        if (!tuesdayClasses.isEmpty()) {
            weekClasses.add(tuesdayClasses);
        }
        if (!wednesdayClasses.isEmpty()) {
            weekClasses.add(wednesdayClasses);
        }
        if (!thursdayClasses.isEmpty()) {
            weekClasses.add(thursdayClasses);
        }
        if (!fridayClasses.isEmpty()) {
            weekClasses.add(fridayClasses);
        }
        if (!saturdayClasses.isEmpty()) {
            weekClasses.add(saturdayClasses);
        }
        return weekClasses;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
