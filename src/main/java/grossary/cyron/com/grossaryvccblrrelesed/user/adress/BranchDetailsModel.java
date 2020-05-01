package grossary.cyron.com.grossaryvccblrrelesed.user.adress;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BranchDetailsModel {
    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("objBranchList")
    private List<ObjbranchlistEntity> objbranchlist;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public List<ObjbranchlistEntity> getObjbranchlist() {
        return objbranchlist;
    }

    public void setObjbranchlist(List<ObjbranchlistEntity> objbranchlist) {
        this.objbranchlist = objbranchlist;
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

    public static class ObjbranchlistEntity {
        @SerializedName("Email")
        private String email;
        @SerializedName("Phone")
        private String phone;
        @SerializedName("ZipCode")
        private String zipcode;
        @SerializedName("State")
        private String state;
        @SerializedName("City")
        private String city;
        @SerializedName("Address")
        private String address;
        @SerializedName("FullName")
        private String fullname;
        @SerializedName("BranchId")
        private int branchid;
        @SerializedName("UserId")
        private int userid;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public int getBranchid() {
            return branchid;
        }

        public void setBranchid(int branchid) {
            this.branchid = branchid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
    }

}



