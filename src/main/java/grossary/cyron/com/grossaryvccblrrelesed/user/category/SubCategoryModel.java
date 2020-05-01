package grossary.cyron.com.grossaryvccblrrelesed.user.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SubCategoryModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;
    @SerializedName("objCategoryList")
    private List<ObjcategorylistEntity> objcategorylist;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public List<ObjcategorylistEntity> getObjcategorylist() {
        return objcategorylist;
    }

    public void setObjcategorylist(List<ObjcategorylistEntity> objcategorylist) {
        this.objcategorylist = objcategorylist;
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

    public static class ObjcategorylistEntity {
        @SerializedName("SubCatergoryImage")
        private String subcatergoryimage;
        @SerializedName("SubCatergoryName")
        private String subcatergoryname;
        @SerializedName("SubCatergoryId")
        private int subcatergoryid;

        public String getSubcatergoryimage() {
            return subcatergoryimage;
        }

        public void setSubcatergoryimage(String subcatergoryimage) {
            this.subcatergoryimage = subcatergoryimage;
        }

        public String getSubcatergoryname() {
            return subcatergoryname;
        }

        public void setSubcatergoryname(String subcatergoryname) {
            this.subcatergoryname = subcatergoryname;
        }

        public int getSubcatergoryid() {
            return subcatergoryid;
        }

        public void setSubcatergoryid(int subcatergoryid) {
            this.subcatergoryid = subcatergoryid;
        }
    }
}
