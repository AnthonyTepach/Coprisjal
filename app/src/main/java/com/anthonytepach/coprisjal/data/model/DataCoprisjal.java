package com.anthonytepach.coprisjal.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCoprisjal {
    @SerializedName("folio")
    @Expose
    private String folio;
    @SerializedName("alfanum")
    @Expose
    private String alfanum;

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getAlfanum() {
        return alfanum;
    }

    public void setAlfanum(String alfanum) {
        this.alfanum = alfanum;
    }
}
