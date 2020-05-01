package grossary.cyron.com.grossaryvccblrrelesed.admin;

public class PaymentCollectionModel {
    public PaymentCollectionModel(String tranNo, String invoiceNo, String invoiceDate, String buyerName, String productName, String qty, String storeName, String invoiceAmount, String productPrice) {
        TranNo = tranNo;
        InvoiceNo = invoiceNo;
        InvoiceDate = invoiceDate;
        BuyerName = buyerName;
        ProductName = productName;
        Qty = qty;
        StoreName = storeName;
        InvoiceAmount = invoiceAmount;
        ProductPrice = productPrice;
    }

    public String getTranNo() {
        return TranNo;
    }

    public void setTranNo(String tranNo) {
        TranNo = tranNo;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }

    public String getBuyerName() {
        return BuyerName;
    }

    public void setBuyerName(String buyerName) {
        BuyerName = buyerName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getInvoiceAmount() {
        return InvoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        InvoiceAmount = invoiceAmount;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    private String TranNo,InvoiceNo,InvoiceDate,BuyerName,ProductName,Qty,StoreName,InvoiceAmount,ProductPrice;


}
