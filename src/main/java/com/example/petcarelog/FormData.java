package com.example.petcarelog;

import java.sql.Time;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FormData extends RealmObject {

    //realm primary Key로 사용할 필드
    @PrimaryKey
    private String insertTime;
    private String dignosis;
    private String symptom;
    private String note;

        private String aklp;
        private String alp;
        private String bun;
        private String creatine;

    private int type;

    public FormData() {

    }

    public FormData(String insertTime, String dignosis, String symptom, String note, int type) {
        this.insertTime = insertTime;
        this.dignosis = dignosis;
        this.symptom = symptom;
        this.note = note;
        this.type = type;
    }

    public FormData(String insertTime, String aklp, String alp, String bun, String creatine, int type){
        this.insertTime = insertTime;
        this.aklp = aklp;
        this.alp = alp;
        this.bun = bun;
        this.creatine = creatine;
        this.type = Constants.TYPE_BLOOD;
    }

    public String getInsertTime() { return insertTime; }

    public int getType() { return type; }

    public String getDignosis() {
        return dignosis;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getNote() { return note; }

    public String getAklp() {return aklp;}

    public String getAlp() {return alp;}

    public String getBun() {return bun;}

    public String getCreatine() {return creatine;}

    public void setCreatine(String creatine) {this.creatine = creatine;}

    public void setBun(String bun) { this.aklp = bun; }
    public void setAklp(String aklp) { this.aklp = aklp; }

    public void setAlp(String alp) { this.aklp = alp; }

    public void setInsertTime(String insertTime) { this.insertTime = insertTime; }

    public void setDignosis(String dignosis) {
        this.dignosis = dignosis;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setType(int type) { this.type = type; }



    @Override
    public String toString() {
        return "FormData{" +
                "insertTime='" + insertTime + '\'' +
                ", dignosis='" + dignosis + '\'' +
                ", symptom='" + symptom + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}


