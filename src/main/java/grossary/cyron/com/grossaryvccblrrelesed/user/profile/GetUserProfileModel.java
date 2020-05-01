package grossary.cyron.com.grossaryvccblrrelesed.user.profile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by subham_naik on 08-Oct-18.
 */

public class GetUserProfileModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("RoleCode")
    private String rolecode;
    @SerializedName("GSTNumber")
    private String gstnumber;
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
    @SerializedName("MobileNo")
    private String mobileno;
    @SerializedName("MobileNo2")
    private String mobileno2;

    @SerializedName("MobileNo3")
    private String mobileno3;

    @SerializedName("MobileNo4")
    private String mobileno4;

    @SerializedName("MobileNo5")
    private String mobileno5;



    @SerializedName("FullName")
    private String fullname;
    @SerializedName("LoginId")
    private String loginid;

    public String getMobileno2() {
        return mobileno2;
    }

    public void setMobileno2(String mobileno2) {
        this.mobileno2 = mobileno2;
    }

    public String getMobileno3() {
        return mobileno3;
    }

    public void setMobileno3(String mobileno3) {
        this.mobileno3 = mobileno3;
    }

    public String getMobileno4() {
        return mobileno4;
    }

    public void setMobileno4(String mobileno4) {
        this.mobileno4 = mobileno4;
    }

    public String getMobileno5() {
        return mobileno5;
    }

    public void setMobileno5(String mobileno5) {
        this.mobileno5 = mobileno5;
    }

    @SerializedName("userId")
    private int userid;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
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
