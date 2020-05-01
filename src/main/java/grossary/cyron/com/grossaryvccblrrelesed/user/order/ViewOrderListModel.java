package grossary.cyron.com.grossaryvccblrrelesed.user.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewOrderListModel {

    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("orderList")
    private List<OrderlistEntity> orderlist;

    public ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity response) {
        this.response = response;
    }

    public List<OrderlistEntity> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<OrderlistEntity> orderlist) {
        this.orderlist = orderlist;
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


    public  class OrderlistEntity {
        @SerializedName("OrderItemCount")
        private String orderitemcount;
        @SerializedName("ProductName")
        private String productname;
        @SerializedName("InvoiceStatus")
        private String invoicestatus;
        @SerializedName("Status")
        private String status;
        @SerializedName("TotalAmount")
        private String totalamount;
        @SerializedName("DeliveryCharges")
        private String deliverycharges;
        @SerializedName("Paymode")
        private String paymode;
        @SerializedName("ShippingAddress")
        private String shippingaddress;
        @SerializedName("CustomerName")
        private String customername;
        @SerializedName("TranDate")
        private String trandate;
        @SerializedName("TranNo")
        private String tranno;
        @SerializedName("ProductPrice")
        private String productprice;
        @SerializedName("OrderItemQty")
        private String orderitemqty;

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        @SerializedName("StoreName")
        private String storename;

        public String getOrderitemcount() {
            return orderitemcount;
        }

        public void setOrderitemcount(String orderitemcount) {
            this.orderitemcount = orderitemcount;
        }

        public String getInvoicestatus() {
            return invoicestatus;
        }

        public void setInvoicestatus(String invoicestatus) {
            this.invoicestatus = invoicestatus;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTotalamount() {
            return totalamount;
        }

        public void setTotalamount(String totalamount) {
            this.totalamount = totalamount;
        }

        public String getDeliverycharges() {
            return deliverycharges;
        }

        public void setDeliverycharges(String deliverycharges) {
            this.deliverycharges = deliverycharges;
        }

        public String getPaymode() {
            return paymode;
        }

        public void setPaymode(String paymode) {
            this.paymode = paymode;
        }

        public String getShippingaddress() {
            return shippingaddress;
        }

        public void setShippingaddress(String shippingaddress) {
            this.shippingaddress = shippingaddress;
        }

        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
        }

        public String getTrandate() {
            return trandate;
        }

        public void setTrandate(String trandate) {
            this.trandate = trandate;
        }

        public String getTranno() {
            return tranno;
        }

        public void setTranno(String tranno) {
            this.tranno = tranno;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public String getProductprice() {
            return productprice;
        }
        public void setProductprice(String productprice) {
            this.productprice = productprice;
        }

        public String getOrderitemqty() {
            return orderitemqty;
        }

        public void setOrderitemqty(String orderitemqty) {
            this.orderitemqty = orderitemqty;
        }
    }
}
