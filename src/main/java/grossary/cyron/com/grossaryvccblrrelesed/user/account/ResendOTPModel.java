package grossary.cyron.com.grossaryvccblrrelesed.user.account;

import com.google.gson.annotations.SerializedName;

/**
 * Created by subham_naik on 07-Oct-18.
 */

public class ResendOTPModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("StatusMessage")
    private String statusmessage;
    @SerializedName("Status")
    private String status;
    @SerializedName("OTP")
    private String otp;
    @SerializedName("RoleCode")
    private String rolecode;
    @SerializedName("GstNumber")
    private String gstnumber;
    @SerializedName("StoreId")
    private String storeid;
    @SerializedName("ZipCode")
    private String zipcode;
    @SerializedName("State")
    private String state;
    @SerializedName("City")
    private String city;
    @SerializedName("Address")
    private String address;
    @SerializedName("Email")
    private String email;
    @SerializedName("Mobile")
    private String mobile;
    @SerializedName("FullName")
    private String fullname;
    @SerializedName("LoginId")
    private String loginid;
    @SerializedName("UserId")
    private int userid;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getGstnumber() {
        return gstnumber;
    }

    public void setGstnumber(String gstnumber) {
        this.gstnumber = gstnumber;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public static class ResponseEntity {
        @SerializedName("Reason")
        private String reason;
        @SerializedName("ResponseVal")
        private boolean responseval;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public boolean getResponseval() {
            return responseval;
        }

        public void setResponseval(boolean responseval) {
            this.responseval = responseval;
        }
    }
}
