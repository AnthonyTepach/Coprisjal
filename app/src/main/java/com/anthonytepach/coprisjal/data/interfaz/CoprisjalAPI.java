package com.anthonytepach.coprisjal.data.interfaz;



import com.anthonytepach.coprisjal.data.model.DataCoprisjal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoprisjalAPI {
    @GET("coprisjal/{folio}/{alfanum}")
    Call<DataCoprisjal> getDatos(@Path("folio") String folio, @Path("alfanum") String alfanum);
}
