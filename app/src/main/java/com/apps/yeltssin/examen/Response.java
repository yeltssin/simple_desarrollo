package com.apps.yeltssin.examen;

public class Response {
    Data data;
    private float code;
    private boolean success;


    // Getter Methods

    public Data getData() {
        return data;
    }

    public float getCode() {
        return code;
    }

    public boolean getSuccess() {
        return success;
    }

    // Setter Methods

    public void setData(Data dataObject) {
        this.data = dataObject;
    }

    public void setCode(float code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
