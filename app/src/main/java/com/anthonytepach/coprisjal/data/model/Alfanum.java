package com.anthonytepach.coprisjal.data.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alfanum {
    @SerializedName("alfanum")
    @Expose
    private String alfanum;

    public String getAlfanum() {
        return alfanum;
    }

    public void setAlfanum(String alfanum) {
        this.alfanum = alfanum;
    }
}
