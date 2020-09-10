package com.magda.gadleaders.apis;

import com.magda.gadleaders.models.Leader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearnersApi {
    String baseUrl ="https://gadsapi.herokuapp.com";

    @GET("/api/hours")
    Call<List<Leader>> getLeaders();
}
