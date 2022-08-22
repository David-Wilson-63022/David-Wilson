package com.revature.POJOS;

import java.util.Objects;

public class RequestPojo {
    private Integer requestId;
    private Integer reimbursementAmount;
    private String transDescription;
    private String requestStatus;
    private Integer userId;

    public RequestPojo() {
    }

    public RequestPojo(Integer requestId, Integer reimbursementAmount, String transDescription, String requestStatus, Integer userId) {
        this.requestId = requestId;
        this.reimbursementAmount = reimbursementAmount;
        this.transDescription = transDescription;
        this.requestStatus = requestStatus;
        this.userId = userId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getReimbursementAmount() {
        return reimbursementAmount;
    }

    public void setReimbursementAmount(Integer reimbursementAmount) {
        this.reimbursementAmount = reimbursementAmount;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public  void setTransDescription(String transDescription) {
        this.transDescription = transDescription;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", reimbursementAmount='" + reimbursementAmount + '\'' +
                ", message='" + transDescription + '\'' +
                ", requestStatus=" + requestStatus +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestPojo requestPojo = (RequestPojo) o;
        return Objects.equals(requestId, requestPojo.requestId) && Objects.equals(reimbursementAmount, requestPojo.reimbursementAmount) && Objects.equals(transDescription, requestPojo.transDescription) && Objects.equals(requestStatus, requestPojo.requestStatus) && Objects.equals(userId, requestPojo.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, reimbursementAmount, transDescription, requestStatus, userId);
    }
}