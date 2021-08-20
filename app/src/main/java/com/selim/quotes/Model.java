package com.selim.quotes;

import java.io.Serializable;

public class Model implements Serializable {


    String key;
    String text;
    String code;

    public Model() {
    }

    public Model(String key, String text, String code) {
        this.key = key;
        this.text = text;
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
