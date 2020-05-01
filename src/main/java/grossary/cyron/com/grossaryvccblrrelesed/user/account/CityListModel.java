package grossary.cyron.com.grossaryvccblrrelesed.user.account;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityListModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("CityList")
    private List<CitylistEntity> citylist;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public List<CitylistEntity> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CitylistEntity> citylist) {
        this.citylist = citylist;
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

    public static class CitylistEntity {
        @SerializedName("CityName")
        private String cityname;
        @SerializedName("CityId")
        private int cityid;

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }
    }
}
