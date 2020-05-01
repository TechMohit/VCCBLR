package grossary.cyron.com.grossaryvccblrrelesed.user.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CategoryModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;
    @SerializedName("objProductDetailsList")
    private List<ObjproductdetailslistEntity> objproductdetailslist;

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

    public List<ObjproductdetailslistEntity> getObjproductdetailslist() {
        return objproductdetailslist;
    }

    public void setObjproductdetailslist(List<ObjproductdetailslistEntity> objproductdetailslist) {
        this.objproductdetailslist = objproductdetailslist;
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

    public static class ObjproductdetailslistEntity {
        @SerializedName("Type")
        private String type;
        @SerializedName("ProductRateType")
        private String productratetype;
        @SerializedName("AvailableStockQty")
        private String availablestockqty;
        @SerializedName("ShippingCharge")
        private String shippingcharge;
        @SerializedName("SubProductQTY")
        private String subproductqty;
        @SerializedName("SellingPrice")
        private String sellingprice;
        @SerializedName("MRPPrice")
        private String mrpprice;
        @SerializedName("StoreName")
        private String storename;
        @SerializedName("ProductImage")
        private String productimage;
        @SerializedName("ProductName")
        private String productname;
        @SerializedName("ProductDescId")
        private int productdescid;
        @SerializedName("StoreId")
        private int storeid;
        @SerializedName("ProductId")
        private int productid;

        public String getGst() {
            return gst;
        }

        public void setGst(String gst) {
            this.gst = gst;
        }

        public String getSubProductDesc() {
            return subProductDesc;
        }

        public void setSubProductDesc(String subProductDesc) {
            this.subProductDesc = subProductDesc;
        }

        @SerializedName("GST")
        private String gst;
        @SerializedName("SubProductDesc")
        private String subProductDesc;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProductratetype() {
            return productratetype;
        }

        public void setProductratetype(String productratetype) {
            this.productratetype = productratetype;
        }

        public String getAvailablestockqty() {
            return availablestockqty;
        }

        public void setAvailablestockqty(String availablestockqty) {
            this.availablestockqty = availablestockqty;
        }

        public String getShippingcharge() {
            return shippingcharge;
        }

        public void setShippingcharge(String shippingcharge) {
            this.shippingcharge = shippingcharge;
        }

        public String getSubproductqty() {
            return subproductqty;
        }

        public void setSubproductqty(String subproductqty) {
            this.subproductqty = subproductqty;
        }

        public String getSellingprice() {
            return sellingprice;
        }

        public void setSellingprice(String sellingprice) {
            this.sellingprice = sellingprice;
        }

        public String getMrpprice() {
            return mrpprice;
        }

        public void setMrpprice(String mrpprice) {
            this.mrpprice = mrpprice;
        }

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
        }

        public String getProductimage() {
            return productimage;
        }

        public void setProductimage(String productimage) {
            this.productimage = productimage;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public int getProductdescid() {
            return productdescid;
        }

        public void setProductdescid(int productdescid) {
            this.productdescid = productdescid;
        }

        public int getStoreid() {
            return storeid;
        }

        public void setStoreid(int storeid) {
            this.storeid = storeid;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }
    }
}
