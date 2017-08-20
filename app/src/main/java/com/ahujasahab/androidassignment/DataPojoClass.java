package com.ahujasahab.androidassignment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hp on 18-08-2017.
 */

public class DataPojoClass {
    @SerializedName("worldpopulation")
    @Expose
    private List<Worldpopulation> worldpopulation = null;

    public List<Worldpopulation> getWorldpopulation() {
        return worldpopulation;
    }

    public void setWorldpopulation(List<Worldpopulation> worldpopulation) {
        this.worldpopulation = worldpopulation;
    }

}

