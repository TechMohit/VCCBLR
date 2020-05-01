package grossary.cyron.com.grossaryvccblrrelesed.admin.orderDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminOrderdetailModel {


    @SerializedName("OrderReconModel")
    private List<OrderreconmodelEntity> orderreconmodel;
    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;

    public List<OrderreconmodelEntity> getOrderreconmodel() {
        return orderreconmodel;
    }

    public void setOrderreconmodel(List<OrderreconmodelEntity> orderreconmodel) {
        this.orderreconmodel = orderreconmodel;
    }

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

    public static class OrderreconmodelEntity {
        @SerializedName("Type")
        private String type;
        @SerializedName("Discount")
        private String discount;
        @SerializedName("LoadingDate")
        private String loadingdate;
        @SerializedName("OrderType")
        private String ordertype;
        @SerializedName("OrderAmount")
        private String orderamount;
        @SerializedName("Remarks")
        private String remarks;
        @SerializedName("DriverMobileNo")
        private String drivermobileno;
        @SerializedName("LorryNumber")
        private String lorrynumber;
        @SerializedName("StoreId")
        private int storeid;
        @SerializedName("InvoiceId")
        private int invoiceid;
        @SerializedName("BalInvoiceQty")
        private int balinvoiceqty;
        @SerializedName("BranchId")
        private int branchid;
        @SerializedName("InvoiceAmount")
        private int invoiceamount;
        @SerializedName("InvoiceQty")
        private int invoiceqty;
        @SerializedName("NoInvoice")
        private int noinvoice;
        @SerializedName("StoreName")
        private String storename;
        @SerializedName("Qty")
        private String qty;
        @SerializedName("DeliveredDate")
        private String delivereddate;
        @SerializedName("ProductName")
        private String productname;
        @SerializedName("BuyerName")
        private String buyername;
        @SerializedName("Delivered")
        private boolean delivered;
        @SerializedName("InvoiceDate")
        private String invoicedate;
        @SerializedName("InvoiceNo")
        private String invoiceno;
        @SerializedName("TranDate")
        private String trandate;
        @SerializedName("TranNo")
        private String tranno;
        @SerializedName("OrderNo")
        private String orderno;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getLoadingdate() {
            return loadingdate;
        }

        public void setLoadingdate(String loadingdate) {
            this.loadingdate = loadingdate;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getOrderamount() {
            return orderamount;
        }

        public void setOrderamount(String orderamount) {
            this.orderamount = orderamount;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getDrivermobileno() {
            return drivermobileno;
        }

        public void setDrivermobileno(String drivermobileno) {
            this.drivermobileno = drivermobileno;
        }

        public String getLorrynumber() {
            return lorrynumber;
        }

        public void setLorrynumber(String lorrynumber) {
            this.lorrynumber = lorrynumber;
        }

        public int getStoreid() {
            return storeid;
        }

        public void setStoreid(int storeid) {
            this.storeid = storeid;
        }

        public int getInvoiceid() {
            return invoiceid;
        }

        public void setInvoiceid(int invoiceid) {
            this.invoiceid = invoiceid;
        }

        public int getBalinvoiceqty() {
            return balinvoiceqty;
        }

        public void setBalinvoiceqty(int balinvoiceqty) {
            this.balinvoiceqty = balinvoiceqty;
        }

        public int getBranchid() {
            return branchid;
        }

        public void setBranchid(int branchid) {
            this.branchid = branchid;
        }

        public int getInvoiceamount() {
            return invoiceamount;
        }

        public void setInvoiceamount(int invoiceamount) {
            this.invoiceamount = invoiceamount;
        }

        public int getInvoiceqty() {
            return invoiceqty;
        }

        public void setInvoiceqty(int invoiceqty) {
            this.invoiceqty = invoiceqty;
        }

        public int getNoinvoice() {
            return noinvoice;
        }

        public void setNoinvoice(int noinvoice) {
            this.noinvoice = noinvoice;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getDelivereddate() {
            return delivereddate;
        }

        public void setDelivereddate(String delivereddate) {
            this.delivereddate = delivereddate;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public String getBuyername() {
            return buyername;
        }

        public void setBuyername(String buyername) {
            this.buyername = buyername;
        }

        public boolean getDelivered() {
            return delivered;
        }

        public void setDelivered(boolean delivered) {
            this.delivered = delivered;
        }

        public String getInvoicedate() {
            return invoicedate;
        }

        public void setInvoicedate(String invoicedate) {
            this.invoicedate = invoicedate;
        }

        public String getInvoiceno() {
            return invoiceno;
        }

        public void setInvoiceno(String invoiceno) {
            this.invoiceno = invoiceno;
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

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }
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
