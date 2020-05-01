package grossary.cyron.com.grossaryvccblrrelesed.user.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchStockDetailsModel {
    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;

    @SerializedName("StockDetailsModel")
    private List<SearchStockDetailsModel.ObjstocksearchdetailsEntity> objstocksearchdetails;

    public SearchStockDetailsModel.ResponseEntity getResponse() {
        return response;
    }

    public void setResponse(SearchStockDetailsModel.ResponseEntity response) {
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

    public List<SearchStockDetailsModel.ObjstocksearchdetailsEntity> getObjstocksearchdetails() {
        return objstocksearchdetails;
    }

    public void setObjstocksearchdetails(List<SearchStockDetailsModel.ObjstocksearchdetailsEntity> objstocksearchdetails) {
        this.objstocksearchdetails = objstocksearchdetails;
    }


    public static class ObjstocksearchdetailsEntity {
        @SerializedName("ProductImage")
        private String productimage;
        @SerializedName("StoreName")
        private String storeName;
        @SerializedName("ProductName")
        private String productname;
        @SerializedName("ProductDescId")
        private int productdescid;

        public String getAvailableStockQty() {
            return availableStockQty;
        }

        public void setAvailableStockQty(String availableStockQty) {
            this.availableStockQty = availableStockQty;
        }

        @SerializedName("AvailableStockQty")
        private String availableStockQty;

        public String getProductimage() {
            return productimage;
        }

        public void setProductimage(String productimage) {
            this.productimage = productimage;
        }

        public String getCategoryname() {
            return storeName;
        }

        public void setCategoryname(String storeName) {
            this.storeName = storeName;
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
    }
}

