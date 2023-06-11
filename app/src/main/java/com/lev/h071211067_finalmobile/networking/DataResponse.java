package com.lev.h071211067_finalmobile.networking;

import com.google.gson.annotations.SerializedName;

public class DataResponse<T> {
    @SerializedName("results")
    private T data;
    public T getData() {
        return data;
    }


}
