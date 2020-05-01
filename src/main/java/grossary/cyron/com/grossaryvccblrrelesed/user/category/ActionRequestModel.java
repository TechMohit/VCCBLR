package grossary.cyron.com.grossaryvccblrrelesed.user.category;

import com.google.gson.annotations.SerializedName;

public class ActionRequestModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("SampleRequestStatus")
    private String samplerequeststatus;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public String getSamplerequeststatus() {
        return samplerequeststatus;
    }

    public void setSamplerequeststatus(String samplerequeststatus) {
        this.samplerequeststatus = samplerequeststatus;
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
