package grossary.cyron.com.grossaryvccblrrelesed.admin.stock;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditStockProductDetailsModel {

    @SerializedName("ProductRangeList")
    private List<ProductrangelistEntity> productrangelist;
    @SerializedName("ProductList")
    private String productlist;
    @SerializedName("StoreId")
    private int storeid;
    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;
    @SerializedName("StockQty")
    private int stockqty;
    @SerializedName("AvailableStockQty")
    private int availablestockqty;
    @SerializedName("SubProductDesc")
    private String subproductdesc;
    @SerializedName("SubProductId")
    private int subproductid;
    @SerializedName("ProductId")
    private int productid;
    @SerializedName("SubCategoryId")
    private int subcategoryid;
    @SerializedName("CategoryId")
    private int categoryid;
    @SerializedName("StockId")
    private int stockid;

    public List<ProductrangelistEntity> getProductrangelist() {
        return productrangelist;
    }

    public void setProductrangelist(List<ProductrangelistEntity> productrangelist) {
        this.productrangelist = productrangelist;
    }

    public String getProductlist() {
        return productlist;
    }

    public void setProductlist(String productlist) {
        this.productlist = productlist;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
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

    public int getStockqty() {
        return stockqty;
    }

    public void setStockqty(int stockqty) {
        this.stockqty = stockqty;
    }

    public int getAvailablestockqty() {
        return availablestockqty;
    }

    public void setAvailablestockqty(int availablestockqty) {
        this.availablestockqty = availablestockqty;
    }

    public String getSubproductdesc() {
        return subproductdesc;
    }

    public void setSubproductdesc(String subproductdesc) {
        this.subproductdesc = subproductdesc;
    }

    public int getSubproductid() {
        return subproductid;
    }

    public void setSubproductid(int subproductid) {
        this.subproductid = subproductid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getSubcategoryid() {
        return subcategoryid;
    }

    public void setSubcategoryid(int subcategoryid) {
        this.subcategoryid = subcategoryid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getStockid() {
        return stockid;
    }

    public void setStockid(int stockid) {
        this.stockid = stockid;
    }

    public static class ProductrangelistEntity {
        @SerializedName("TypeName")
        private String typename;
        @SerializedName("ShippingPrice")
        private int shippingprice;
        @SerializedName("SellingPrice")
        private int sellingprice;
        @SerializedName("RateName")
        private String ratename;
        @SerializedName("RateId")
        private int rateid;
        @SerializedName("ProductId")
        private int productid;
        @SerializedName("ProductRangeId")
        private int productrangeid;

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getShippingprice() {
            return shippingprice;
        }

        public void setShippingprice(int shippingprice) {
            this.shippingprice = shippingprice;
        }

        public int getSellingprice() {
            return sellingprice;
        }

        public void setSellingprice(int sellingprice) {
            this.sellingprice = sellingprice;
        }

        public String getRatename() {
            return ratename;
        }

        public void setRatename(String ratename) {
            this.ratename = ratename;
        }

        public int getRateid() {
            return rateid;
        }

        public void setRateid(int rateid) {
            this.rateid = rateid;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public int getProductrangeid() {
            return productrangeid;
        }

        public void setProductrangeid(int productrangeid) {
            this.productrangeid = productrangeid;
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
