package grossary.cyron.com.grossaryvccblrrelesed.user.account;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateListModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("StateList")
    private List<StatelistEntity> statelist;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public List<StatelistEntity> getStatelist() {
        return statelist;
    }

    public void setStatelist(List<StatelistEntity> statelist) {
        this.statelist = statelist;
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

    public static class StatelistEntity {
        @SerializedName("StateName")
        private String statename;
        @SerializedName("StateId")
        private int stateid;

        public String getStatename() {
            return statename;
        }

        public void setStatename(String statename) {
            this.statename = statename;
        }

        public int getStateid() {
            return stateid;
        }

        public void setStateid(int stateid) {
            this.stateid = stateid;
        }
    }
}
