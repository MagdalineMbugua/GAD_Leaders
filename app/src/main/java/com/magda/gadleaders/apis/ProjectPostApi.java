package com.magda.gadleaders.apis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProjectPostApi {
    String baseUrl ="https://docs.google.com/forms/d/e/";
    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ResponseBody>submitProject(
            @Field("entry.1824927963")String emailAddress,
            @Field("entry.1877115667")String firstName,
            @Field("entry.2006916086")String lastName,
            @Field("entry.284483984")String projectLink

    );
}
