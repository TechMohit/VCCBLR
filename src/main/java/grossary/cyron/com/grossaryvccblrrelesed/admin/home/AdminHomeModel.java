package grossary.cyron.com.grossaryvccblrrelesed.admin.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminHomeModel {


    @SerializedName("DiscountType")
    private List<DiscounttypeEntity> discounttype;
    @SerializedName("AuctionList")
    private List<AuctionlistEntity> auctionlist;
    @SerializedName("OrderPerviousModel")
    private List<OrderperviousmodelEntity> orderperviousmodel;
    @SerializedName("ReconDetailsList")
    private List<RecondetailslistEntity> recondetailslist;
    @SerializedName("ProductList")
    private List<ProductlistEntity> productlist;
    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;

    public List<DiscounttypeEntity> getDiscounttype() {
        return discounttype;
    }

    public void setDiscounttype(List<DiscounttypeEntity> discounttype) {
        this.discounttype = discounttype;
    }

    public List<AuctionlistEntity> getAuctionlist() {
        return auctionlist;
    }

    public void setAuctionlist(List<AuctionlistEntity> auctionlist) {
        this.auctionlist = auctionlist;
    }

    public List<OrderperviousmodelEntity> getOrderperviousmodel() {
        return orderperviousmodel;
    }

    public void setOrderperviousmodel(List<OrderperviousmodelEntity> orderperviousmodel) {
        this.orderperviousmodel = orderperviousmodel;
    }

    public List<RecondetailslistEntity> getRecondetailslist() {
        return recondetailslist;
    }

    public void setRecondetailslist(List<RecondetailslistEntity> recondetailslist) {
        this.recondetailslist = recondetailslist;
    }

    public List<ProductlistEntity> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<ProductlistEntity> productlist) {
        this.productlist = productlist;
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

    public static class DiscounttypeEntity {
        @SerializedName("DStoreId")
        private int dstoreid;
        @SerializedName("SelectedDiscount")
        private boolean selecteddiscount;
        @SerializedName("NoDays")
        private String nodays;
        @SerializedName("Discount")
        private String discount;
        @SerializedName("DiscountId")
        private int discountid;
        @SerializedName("DiscountTypeId")
        private int discounttypeid;

        public int getDstoreid() {
            return dstoreid;
        }

        public void setDstoreid(int dstoreid) {
            this.dstoreid = dstoreid;
        }

        public boolean getSelecteddiscount() {
            return selecteddiscount;
        }

        public void setSelecteddiscount(boolean selecteddiscount) {
            this.selecteddiscount = selecteddiscount;
        }

        public String getNodays() {
            return nodays;
        }

        public void setNodays(String nodays) {
            this.nodays = nodays;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public int getDiscountid() {
            return discountid;
        }

        public void setDiscountid(int discountid) {
            this.discountid = discountid;
        }

        public int getDiscounttypeid() {
            return discounttypeid;
        }

        public void setDiscounttypeid(int discounttypeid) {
            this.discounttypeid = discounttypeid;
        }
    }

    public static class AuctionlistEntity {
        @SerializedName("DiscountTypeId")
        private int discounttypeid;
        @SerializedName("CustomerName")
        private String customername;
        @SerializedName("ProductName")
        private String productname;
        @SerializedName("StoreName")
        private String storename;
        @SerializedName("ApproverDate")
        private String approverdate;
        @SerializedName("RequestDate")
        private String requestdate;
        @SerializedName("CustomerPrice")
        private int customerprice;
        @SerializedName("SellingPrice")
        private int sellingprice;
        @SerializedName("Qty")
        private int qty;
        @SerializedName("UserId")
        private int userid;
        @SerializedName("ProductDescId")
        private int productdescid;
        @SerializedName("StoreId")
        private int storeid;
        @SerializedName("AuctionId")
        private int auctionid;

        public int getDiscounttypeid() {
            return discounttypeid;
        }

        public void setDiscounttypeid(int discounttypeid) {
            this.discounttypeid = discounttypeid;
        }

        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
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

        public String getApproverdate() {
            return approverdate;
        }

        public void setApproverdate(String approverdate) {
            this.approverdate = approverdate;
        }

        public String getRequestdate() {
            return requestdate;
        }

        public void setRequestdate(String requestdate) {
            this.requestdate = requestdate;
        }

        public int getCustomerprice() {
            return customerprice;
        }

        public void setCustomerprice(int customerprice) {
            this.customerprice = customerprice;
        }

        public int getSellingprice() {
            return sellingprice;
        }

        public void setSellingprice(int sellingprice) {
            this.sellingprice = sellingprice;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
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

        public int getStoreid() {
            return storeid;
        }

        public void setStoreid(int storeid) {
            this.storeid = storeid;
        }

        public int getAuctionid() {
            return auctionid;
        }

        public void setAuctionid(int auctionid) {
            this.auctionid = auctionid;
        }
    }

    public static class OrderperviousmodelEntity {
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

    public static class RecondetailslistEntity {
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

    public static class ProductlistEntity {
        @SerializedName("VAT")
        private String vat;
        @SerializedName("Type")
        private String type;
        @SerializedName("EncryptedProductDetailsId")
        private String encryptedproductdetailsid;
        @SerializedName("ProductRateType")
        private String productratetype;
        @SerializedName("AvailableStockQty")
        private String availablestockqty;
        @SerializedName("StoreName")
        private String storename;
        @SerializedName("SubCategoryId")
        private int subcategoryid;
        @SerializedName("CategoryId")
        private int categoryid;
        @SerializedName("MainProductsDesc")
        private String mainproductsdesc;
        @SerializedName("Qty")
        private String qty;
        @SerializedName("ProductImage")
        private String productimage;
        @SerializedName("SellingPrice")
        private String sellingprice;
        @SerializedName("MRPPrice")
        private String mrpprice;
        @SerializedName("ProductDescName")
        private String productdescname;
        @SerializedName("ProductDescId")
        private int productdescid;
        @SerializedName("ProductDesc")
        private String productdesc;
        @SerializedName("ProductName")
        private String productname;
        @SerializedName("ProductId")
        private int productid;

        public String getVat() {
            return vat;
        }

        public void setVat(String vat) {
            this.vat = vat;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getEncryptedproductdetailsid() {
            return encryptedproductdetailsid;
        }

        public void setEncryptedproductdetailsid(String encryptedproductdetailsid) {
            this.encryptedproductdetailsid = encryptedproductdetailsid;
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

        public String getStorename() {
            return storename;
        }

        public void setStorename(String storename) {
            this.storename = storename;
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

        public String getMainproductsdesc() {
            return mainproductsdesc;
        }

        public void setMainproductsdesc(String mainproductsdesc) {
            this.mainproductsdesc = mainproductsdesc;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getProductimage() {
            return productimage;
        }

        public void setProductimage(String productimage) {
            this.productimage = productimage;
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

        public String getProductdescname() {
            return productdescname;
        }

        public void setProductdescname(String productdescname) {
            this.productdescname = productdescname;
        }

        public int getProductdescid() {
            return productdescid;
        }

        public void setProductdescid(int productdescid) {
            this.productdescid = productdescid;
        }

        public String getProductdesc() {
            return productdesc;
        }

        public void setProductdesc(String productdesc) {
            this.productdesc = productdesc;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
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
