package com.w.school_herper_front.bean;

/**
* CONTENT:RewardBean
* DEVELOPER:Zhangxixian
* STATE:
* DATE:18/12/12
* */

public class RewardBean {
    private int posterId;
    private String posterName;
    private String rewardTitle;
    private String rewardContent;
    private String imgUrl;
    private String publishTime;
    private String deadline;
    private String rewardState;
    private Double rewardMoney;

    public RewardBean(int posterId, String rewardContent, String rewardTitle, String deadline, Double rewardMoney) {
        this.posterId = posterId;
        this.rewardContent = rewardContent;
        this.rewardTitle = rewardTitle;
        this.deadline = deadline;
        this.rewardMoney = rewardMoney;
    }

    public RewardBean(String posterName, String rewardTitle, String rewardContent, String deadline, String rewardState, Double rewardMoney) {
        this.posterName = posterName;
        this.rewardTitle = rewardTitle;
        this.rewardContent = rewardContent;
        this.deadline = deadline;
        this.rewardState = rewardState;
        this.rewardMoney = rewardMoney;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public int getPosterId() {
        return posterId;
    }

    public void setPosterId(int posterId) {
        this.posterId = posterId;
    }

    public String getRewardContent() {
        return rewardContent;
    }

    public void setRewardContent(String rewardContent) {
        this.rewardContent = rewardContent;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getRewardState() {
        return rewardState;
    }

    public void setRewardState(String rewardState) {
        this.rewardState = rewardState;
    }

    public Double getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(Double rewardMoney) {
        this.rewardMoney = rewardMoney;
    }
}
