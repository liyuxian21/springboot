package com.user.entity;

public class User {
    private String userCode;
    private String userName;
    private String deptName;
    private String pingying;

    public String getPingying() {
        return pingying;
    }

    public void setPingying(String pingying) {
        this.pingying = pingying;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
