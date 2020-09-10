package com.magda.gadleaders.apis;

import com.magda.gadleaders.models.Leader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIQApi {
    String baseUrl ="https://gadsapi.herokuapp.com";

    @GET(" /api/skilliq")
    Call<List<Leader>> getIQLeaders();
}
