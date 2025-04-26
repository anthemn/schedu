package com.example.schedu.api;

import com.example.schedu.pojo.Filters;
import com.example.schedu.pojo.Schedule;
import com.example.schedu.pojo.ScheduleFilterPayload;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("filters")
    Single<Filters> loadFilters();

    @POST("schedule")
    Single<Schedule> loadSchedule(@Body ScheduleFilterPayload payload);
}
