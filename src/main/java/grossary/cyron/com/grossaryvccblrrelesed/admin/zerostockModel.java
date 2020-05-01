package grossary.cyron.com.grossaryvccblrrelesed.admin;

public class zerostockModel {
    String ProductId;
    String ProductName;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public String getProductDescId() {
        return ProductDescId;
    }

    public void setProductDescId(String productDescId) {
        ProductDescId = productDescId;
    }

    public String getProductDescName() {
        return ProductDescName;
    }

    public void setProductDescName(String productDescName) {
        ProductDescName = productDescName;
    }

    public String getMRPPrice() {
        return MRPPrice;
    }

    public void setMRPPrice(String MRPPrice) {
        this.MRPPrice = MRPPrice;
    }

    public String getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getMainProductsDesc() {
        return MainProductsDesc;
    }

    public void setMainProductsDesc(String mainProductsDesc) {
        MainProductsDesc = mainProductsDesc;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getAvailableStockQty() {
        return AvailableStockQty;
    }

    public void setAvailableStockQty(String availableStockQty) {
        AvailableStockQty = availableStockQty;
    }

    public String getProductRateType() {
        return ProductRateType;
    }

    public void setProductRateType(String productRateType) {
        ProductRateType = productRateType;
    }

    public String getEncryptedProductDetailsId() {
        return EncryptedProductDetailsId;
    }

    public void setEncryptedProductDetailsId(String encryptedProductDetailsId) {
        EncryptedProductDetailsId = encryptedProductDetailsId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    String ProductDesc;
    String ProductDescId;
    String ProductDescName;
    String MRPPrice;
    String SellingPrice;
    String ProductImage;
    String Qty;
    String MainProductsDesc;
    String CategoryId;
    String SubCategoryId;
    String StoreName;
    String AvailableStockQty;
    String ProductRateType;
    String EncryptedProductDetailsId;
    String Type;
    String VAT;

    public zerostockModel(String productId, String productName, String productDesc, String productDescId, String productDescName, String MRPPrice, String sellingPrice, String productImage, String qty, String mainProductsDesc, String categoryId, String subCategoryId, String storeName, String availableStockQty, String productRateType, String encryptedProductDetailsId, String type, String VAT) {
        ProductId = productId;
        ProductName = productName;
        ProductDesc = productDesc;
        ProductDescId = productDescId;
        ProductDescName = productDescName;
        this.MRPPrice = MRPPrice;
        SellingPrice = sellingPrice;
        ProductImage = productImage;
        Qty = qty;
        MainProductsDesc = mainProductsDesc;
        CategoryId = categoryId;
        SubCategoryId = subCategoryId;
        StoreName = storeName;
        AvailableStockQty = availableStockQty;
        ProductRateType = productRateType;
        EncryptedProductDetailsId = encryptedProductDetailsId;
        Type = type;
        this.VAT = VAT;
    }






}
