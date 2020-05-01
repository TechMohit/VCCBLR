package grossary.cyron.com.grossaryvccblrrelesed.user.category;

import com.google.gson.annotations.SerializedName;

public class ProductdDescDetailsModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;
    @SerializedName("SubProductDesc")
    private String subproductdesc;
    @SerializedName("SubProductQTY")
    private String subproductqty;
    @SerializedName("SellingPrice")
    private String sellingprice;
    @SerializedName("ProductImage")
    private String productimage;
    @SerializedName("ProductName")
    private String productname;
    @SerializedName("StoreName")
    private String storename;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @SerializedName("CategoryName")
    private String categoryName;
    @SerializedName("ShippingCharges")
    private int shippingcharges;
    @SerializedName("StoreId")
    private int storeid;
    @SerializedName("ProductId")
    private int productid;
    @SerializedName("UserId")
    private int userid;
    @SerializedName("ProductDescId")
    private int productdescid;

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

    public String getSubproductdesc() {
        return subproductdesc;
    }

    public void setSubproductdesc(String subproductdesc) {
        this.subproductdesc = subproductdesc;
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

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public int getShippingcharges() {
        return shippingcharges;
    }

    public void setShippingcharges(int shippingcharges) {
        this.shippingcharges = shippingcharges;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProductdescid() {
        return productdescid;
    }

    public void setProductdescid(int productdescid) {
        this.productdescid = productdescid;
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
