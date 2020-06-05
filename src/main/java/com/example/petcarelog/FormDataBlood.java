package com.example.petcarelog;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FormDataBlood {

    //realm primary Key로 사용할 필드
    @PrimaryKey
    private String insertTime;

    private float aklp;
    private float alp;
    private float bun;
    private float creatine;


    public FormDataBlood() {

    }

    public FormDataBlood( String insertTime, float aklp, float alp, float bun, float creatine) {
        this.insertTime = insertTime;
        this.aklp = aklp;
        this.alp = alp;
        this.bun = bun;
        this.creatine = creatine;
    }

    public float getAklp() {
        return aklp;
    }

    public void setAklp(float aklp) {
        this.aklp = aklp;
    }

    public float getAlp() {
        return alp;
    }

    public void setAlp(float alp) {
        this.alp = alp;
    }

    public float getBun() {
        return bun;
    }

    public void setBun(float bun) {
        this.bun = bun;
    }

    public float getCreatine() {
        return creatine;
    }

    public void setCreatine(float creatine) {
        this.creatine = creatine;
    }


    @Override
    public String toString() {
        return "FormData{" +
                "insertTime='" + insertTime + '\'' +
                "aklp='" + aklp + '\'' +
                ", alp='" + alp + '\'' +
                ", bun='" + bun + '\'' +
                ", creatine='" + creatine + '\'' +
                '}';
    }
}


