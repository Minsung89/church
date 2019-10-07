package com.ydn.church.Entity;

public class MyAlarm {

    private String sequence;

    private String subTitle;

    private String page;

    private String alarmTime;

    private String alarmYn;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmYn() {
        return alarmYn;
    }

    public void setAlarmYn(String alarmYn) {
        this.alarmYn = alarmYn;
    }

    @Override
    public String toString() {
        return "MyAlarm{" +
                "sequence='" + sequence + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", page='" + page + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                ", alarmYn='" + alarmYn + '\'' +
                '}';
    }

    public String titelString(){
        return subTitle + "  " + page;
    }
}
