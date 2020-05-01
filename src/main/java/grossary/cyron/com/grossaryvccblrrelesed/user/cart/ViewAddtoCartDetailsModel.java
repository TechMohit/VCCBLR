package grossary.cyron.com.grossaryvccblrrelesed.user.cart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewAddtoCartDetailsModel {


    @SerializedName("Response")
    private ResponseEntity response;
    @SerializedName("Status")
    private String status;
    @SerializedName("StatusMessage")
    private String statusmessage;
    @SerializedName("TotalItemCount")
    private int totalitemcount;
    @SerializedName("GrandToal")
    private int grandtoal;
    @SerializedName("TotalAmount")
    private int totalamount;
    @SerializedName("TotalShippingCharges")
    private int totalshippingcharges;
    @SerializedName("objViewAddCartList")
    private ObjviewaddcartlistEntity objviewaddcartlist;

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

    public int getTotalitemcount() {
        return totalitemcount;
    }

    public void setTotalitemcount(int totalitemcount) {
        this.totalitemcount = totalitemcount;
    }

    public int getGrandtoal() {
        return grandtoal;
    }

    public void setGrandtoal(int grandtoal) {
        this.grandtoal = grandtoal;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }

    public int getTotalshippingcharges() {
        return totalshippingcharges;
    }

    public void setTotalshippingcharges(int totalshippingcharges) {
        this.totalshippingcharges = totalshippingcharges;
    }

    public ObjviewaddcartlistEntity getObjviewaddcartlist() {
        return objviewaddcartlist;
    }

    public void setObjviewaddcartlist(ObjviewaddcartlistEntity objviewaddcartlist) {
        this.objviewaddcartlist = objviewaddcartlist;
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

    public static class ObjviewaddcartlistEntity {
        @SerializedName("SelectedPaymode")
        private String selectedpaymode;
        @SerializedName("Paymode")
        private List<PaymodeEntity> paymode;
        @SerializedName("Remarks")
        private String remarks;
        @SerializedName("ListAddToCartViewModel")
        private List<ListaddtocartviewmodelEntity> listaddtocartviewmodel;
        @SerializedName("DiscountType")
        private List<DiscounttypeEntity> discounttype;
        @SerializedName("StoreId")
        private int storeid;
        @SerializedName("DiscounTypeId")
        private int discountypeid;
        @SerializedName("StockQty")
        private int stockqty;
        @SerializedName("ProductRateType")
        private String productratetype;
        @SerializedName("ShippingCharges")
        private int shippingcharges;
        @SerializedName("TotalSellingPrice")
        private int totalsellingprice;
        @SerializedName("UnitQty")
        private int unitqty;
        @SerializedName("MRPPrice")
        private int mrpprice;
        @SerializedName("SellingPrice")
        private int sellingprice;
        @SerializedName("Qty")
        private String qty;
        @SerializedName("ProductImage")
        private String productimage;
        @SerializedName("ProductName")
        private String productname;

        public String getSelectedpaymode() {
            return selectedpaymode;
        }

        public void setSelectedpaymode(String selectedpaymode) {
            this.selectedpaymode = selectedpaymode;
        }

        public List<PaymodeEntity> getPaymode() {
            return paymode;
        }

        public void setPaymode(List<PaymodeEntity> paymode) {
            this.paymode = paymode;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public List<ListaddtocartviewmodelEntity> getListaddtocartviewmodel() {
            return listaddtocartviewmodel;
        }

        public void setListaddtocartviewmodel(List<ListaddtocartviewmodelEntity> listaddtocartviewmodel) {
            this.listaddtocartviewmodel = listaddtocartviewmodel;
        }

        public List<DiscounttypeEntity> getDiscounttype() {
            return discounttype;
        }

        public void setDiscounttype(List<DiscounttypeEntity> discounttype) {
            this.discounttype = discounttype;
        }

        public int getStoreid() {
            return storeid;
        }

        public void setStoreid(int storeid) {
            this.storeid = storeid;
        }

        public int getDiscountypeid() {
            return discountypeid;
        }

        public void setDiscountypeid(int discountypeid) {
            this.discountypeid = discountypeid;
        }

        public int getStockqty() {
            return stockqty;
        }

        public void setStockqty(int stockqty) {
            this.stockqty = stockqty;
        }

        public String getProductratetype() {
            return productratetype;
        }

        public void setProductratetype(String productratetype) {
            this.productratetype = productratetype;
        }

        public int getShippingcharges() {
            return shippingcharges;
        }

        public void setShippingcharges(int shippingcharges) {
            this.shippingcharges = shippingcharges;
        }

        public int getTotalsellingprice() {
            return totalsellingprice;
        }

        public void setTotalsellingprice(int totalsellingprice) {
            this.totalsellingprice = totalsellingprice;
        }

        public int getUnitqty() {
            return unitqty;
        }

        public void setUnitqty(int unitqty) {
            this.unitqty = unitqty;
        }

        public int getMrpprice() {
            return mrpprice;
        }

        public void setMrpprice(int mrpprice) {
            this.mrpprice = mrpprice;
        }

        public int getSellingprice() {
            return sellingprice;
        }

        public void setSellingprice(int sellingprice) {
            this.sellingprice = sellingprice;
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

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }
    }

    public static class PaymodeEntity {
        @SerializedName("PaymodeDesc")
        private String paymodedesc;
        @SerializedName("Paymode")
        private String paymode;
        @SerializedName("PaymodeId")
        private int paymodeid;

        public String getPaymodedesc() {
            return paymodedesc;
        }

        public void setPaymodedesc(String paymodedesc) {
            this.paymodedesc = paymodedesc;
        }

        public String getPaymode() {
            return paymode;
        }

        public void setPaymode(String paymode) {
            this.paymode = paymode;
        }

        public int getPaymodeid() {
            return paymodeid;
        }

        public void setPaymodeid(int paymodeid) {
            this.paymodeid = paymodeid;
        }
    }

    public static class ListaddtocartviewmodelEntity {
        @SerializedName("SelectedPaymode")
        private String selectedpaymode;
        @SerializedName("Remarks")
        private String remarks;
        @SerializedName("ListAddToCartViewModel")
        private String listaddtocartviewmodel;
        @SerializedName("StoreId")
        private int storeid;
        @SerializedName("DiscounTypeId")
        private int discountypeid;
        @SerializedName("StockQty")
        private int stockqty;
        @SerializedName("StockMsg")
        private String stockmsg;
        @SerializedName("ProductRateType")
        private String productratetype;
        @SerializedName("ShippingCharges")
        private int shippingcharges;
        @SerializedName("TotalSellingPrice")
        private int totalsellingprice;
        @SerializedName("OrderId")
        private String orderid;
        @SerializedName("UnitQty")
        private int unitqty;
        @SerializedName("MRPPrice")
        private int mrpprice;
        @SerializedName("SellingPrice")
        private int sellingprice;
        @SerializedName("Qty")
        private String qty;
        @SerializedName("ProductImage")
        private String productimage;
        @SerializedName("ProductName")
        private String productname;

        public String getSelectedpaymode() {
            return selectedpaymode;
        }

        public void setSelectedpaymode(String selectedpaymode) {
            this.selectedpaymode = selectedpaymode;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getListaddtocartviewmodel() {
            return listaddtocartviewmodel;
        }

        public void setListaddtocartviewmodel(String listaddtocartviewmodel) {
            this.listaddtocartviewmodel = listaddtocartviewmodel;
        }

        public int getStoreid() {
            return storeid;
        }

        public void setStoreid(int storeid) {
            this.storeid = storeid;
        }

        public int getDiscountypeid() {
            return discountypeid;
        }

        public void setDiscountypeid(int discountypeid) {
            this.discountypeid = discountypeid;
        }

        public int getStockqty() {
            return stockqty;
        }

        public void setStockqty(int stockqty) {
            this.stockqty = stockqty;
        }

        public String getStockmsg() {
            return stockmsg;
        }

        public void setStockmsg(String stockmsg) {
            this.stockmsg = stockmsg;
        }

        public String getProductratetype() {
            return productratetype;
        }

        public void setProductratetype(String productratetype) {
            this.productratetype = productratetype;
        }

        public int getShippingcharges() {
            return shippingcharges;
        }

        public void setShippingcharges(int shippingcharges) {
            this.shippingcharges = shippingcharges;
        }

        public int getTotalsellingprice() {
            return totalsellingprice;
        }

        public void setTotalsellingprice(int totalsellingprice) {
            this.totalsellingprice = totalsellingprice;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public int getUnitqty() {
            return unitqty;
        }

        public void setUnitqty(int unitqty) {
            this.unitqty = unitqty;
        }

        public int getMrpprice() {
            return mrpprice;
        }

        public void setMrpprice(int mrpprice) {
            this.mrpprice = mrpprice;
        }

        public int getSellingprice() {
            return sellingprice;
        }

        public void setSellingprice(int sellingprice) {
            this.sellingprice = sellingprice;
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

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }
    }

    public static class DiscounttypeEntity {
        @SerializedName("DStoreId")
        private int dstoreid;
        @SerializedName("SelectedDiscount")
        private boolean selecteddiscount;
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
}
