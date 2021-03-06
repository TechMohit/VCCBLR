package grossary.cyron.com.grossaryvccblrrelesed.user.account;

import com.google.gson.annotations.SerializedName;

/**
 * Created by subham_naik on 07-Oct-18.
 */

public class VerifyRegisterOTPModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("MobileNumber")
    private String mobilenumber;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
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
