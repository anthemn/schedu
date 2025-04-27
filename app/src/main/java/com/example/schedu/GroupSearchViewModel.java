package com.example.schedu;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schedu.api.ApiFactory;
import com.example.schedu.pojo.Filters;
import com.example.schedu.pojo.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GroupSearchViewModel extends ViewModel {

    private static final String LOG_TAG = "GroupSearchViewModel";
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<List<Group>> groups = new MutableLiveData<>();
    private List<Group> groupsFromApi;

    public LiveData<List<Group>> getGroups() {
        return groups;
    }

    public void loadGroups() {
        Disposable disposable = ApiFactory.getInstance().loadFilters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Filters>() {
                            @Override
                            public void accept(Filters filters) throws Throwable {
                                groups.setValue(filters.getGroups());
                                groupsFromApi = filters.getGroups();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(LOG_TAG, throwable.toString());
                            }
                        });
        compositeDisposable.add(disposable);
    }

    public void filterGroups(String searchText) {
        List<Group> sortedGroups = new ArrayList<>();
        if (!Objects.equals(searchText, "") && groupsFromApi != null) {
            for (Group group : groupsFromApi) {
                if (group.getGroup().toLowerCase().contains(searchText.toLowerCase().trim())) {
                    sortedGroups.add(group);
                }
            }
            groups.setValue(sortedGroups);

        } else {
            groups.setValue(groupsFromApi);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
