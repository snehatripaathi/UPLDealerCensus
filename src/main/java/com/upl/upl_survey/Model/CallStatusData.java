package com.upl.upl_survey.Model;

import java.util.StringJoiner;

public class CallStatusData {

    private String subDistrict;
    private String state;
    private String district;
    private String callStatus;
    private Long statusCount;


    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public Long getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Long statusCount) {
        this.statusCount = statusCount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CallStatusData.class.getSimpleName() + "[", "]")
                .add("subDistrict='" + subDistrict + "'")
                .add("state='" + state + "'")
                .add("district='" + district + "'")
                .add("callStatus='" + callStatus + "'")
                .add("statusCount=" + statusCount)
                .toString();
    }
}
