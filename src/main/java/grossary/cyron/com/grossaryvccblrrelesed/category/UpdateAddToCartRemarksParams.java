package grossary.cyron.com.grossaryvccblrrelesed.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateAddToCartRemarksParams {


    @SerializedName("remarkList")
    private List<RemarklistEntity> remarklist;

    public List<RemarklistEntity> getRemarklist() {
        return remarklist;
    }

    public void setRemarklist(List<RemarklistEntity> remarklist) {
        this.remarklist = remarklist;
    }

    public static class RemarklistEntity {
        @SerializedName("remark")
        private String remark;
        @SerializedName("OrderId")
        private String orderid;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }
    }
}
