package com.w.school_herper_front.bean;
/*
* CONTENT:RewardBean
* DEVELOPER:Zhangxixian
* STATE:
* DATE:18/12/12
* */
import java.sql.Date;

public class RewardBean {

    private Date publishTime;
    private Date deadline;

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
