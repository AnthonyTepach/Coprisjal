package com.anthonytepach.coprisjal.data.interfaz;


import com.anthonytepach.coprisjal.data.model.Alfanum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComputerFormsAPI {
    @GET("api_get/{folio}")
    Call<Alfanum> getAlfanum(@Path("folio") String folio);
}
