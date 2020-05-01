package grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit;

import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.admin.orderDetail.AdminOrderdetailModel;
import grossary.cyron.com.grossaryvccblrrelesed.admin.preOrderDetail.AdminPreOrderdetailModel;
import grossary.cyron.com.grossaryvccblrrelesed.admin.stock.EditStockProductDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.admin.stock.UpdateStockProductDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.category.UpdateAddToCartRemarksModel;
import grossary.cyron.com.grossaryvccblrrelesed.category.UpdateAddToCartRemarksParams;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.CityListModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.RegisterModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.ResendOTPModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.StateListModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.VerifyRegisterOTPModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.adress.BranchDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.brands.OfferProductDescDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.DeleteFromCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.UpdateFromCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.ViewAddtoCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.ActionRequestModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.AddToCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.CategoryModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.ProductdDescDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.SampleRequestModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.SubCategoryModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.UpdateAddToCartDiscountModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.ViewCartItemCountDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.home.HomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.order.OrderDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.order.ViewOrderListModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.payment.PaymentGatewayRequestModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.payment.SubmitTransactionModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.profile.GetUserProfileModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.profile.GetUserProfileUpdateModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.search.ProductSearchDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.search.SearchStockDetailsModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIInterface {

    @POST()
    @FormUrlEncoded
    Call<LoginModel> authenticate(@Url String url, @Field("MobileNumber") String MobileNumber);

    @POST()
    @FormUrlEncoded
    Call<LoginModel> authenticateLoadUser(@Url String url, @Field("LoginId") String loginId);

    @POST()
    @FormUrlEncoded
    Call<GetUserProfileUpdateModel> getUserProfileUpdate(@Url String url, @Field("userId") String UserId, @Field("LoginId") String LoginId
            , @Field("FullName") String FullName, @Field("MobileNo") String MobileNo, @Field("MobileNo2") String MobileNo2,@Field("MobileNo3") String MobileNo3,@Field("MobileNo4") String MobileNo4,@Field("MobileNo5") String MobileNo5, @Field("Email") String Email, @Field("Address") String Address
            , @Field("GSTNumber") String GSTNumber , @Field("ZipCode") String ZipCode , @Field("City") String City
            , @Field("State") String State);

    @POST()
    @FormUrlEncoded
    Call<GetUserProfileModel> getUserProfile(@Url String url, @Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<BranchDetailsModel> branchDetails(@Url String url, @Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<ViewOrderListModel> viewOrderList(@Url String url, @Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<UpdateAddToCartDiscountModel> updateAddToCartDiscount(@Url String url, @Field("OrderId") String OrderId,
                                                               @Field("DiscountType") String DiscountType
    , @Field("OrderType") String OrderType, @Field("Remarks") String Remarks);


    @POST()
    @FormUrlEncoded
    Call<DeleteFromCartDetailsModel> deleteFromCartDetails(@Url String url, @Field("OrderId") String OrderId);

    @POST()
    @FormUrlEncoded
    Call<UpdateFromCartDetailsModel> updateFromCartDetails(@Url String url, @Field("OrderId") String OrderId, @Field("Qty") String Qty);


    @POST()
    @FormUrlEncoded
    Call<ViewAddtoCartDetailsModel> viewAddtoCartDetails(@Url String url, @Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<ViewCartItemCountDetailsModel> viewCartItemCountDetails(@Url String url, @Field("UserId") String UserId);


    @POST()
    @FormUrlEncoded
    Call<SubmitTransactionModel> submitTransaction(@Url String url, @Field("FullName") String FullName, @Field("Address") String Address
            , @Field("City") String City, @Field("State") String State, @Field("ZipCode") String ZipCode, @Field("Phone") String Phone
            , @Field("UserId") String UserId,@Field("TotalShippingCharges") String TotalShippingCharges
            , @Field("GrandToal") String GrandToal,@Field("AdminUser") String AdminUser);

    @POST()
    @FormUrlEncoded
    Call<PaymentGatewayRequestModel> paymentGatewayRequest(@Url String url, @Field("FullName") String FullName, @Field("Address") String Address
            , @Field("City") String City, @Field("State") String State, @Field("ZipCode") String ZipCode, @Field("Phone") String Phone
            , @Field("UserId") String UserId, @Field("Paymode") String Paymode, @Field("TotalShippingCharges") String TotalShippingCharges
            , @Field("GrandToal") String GrandToal);


    @POST()
    @FormUrlEncoded
    Call<OrderDetailsModel> orderDetails(@Url String url, @Field("UserId") String UserId, @Field("TranNo") String TranNo);

    @POST()
    @FormUrlEncoded
    Call<ResendOTPModel> resendOTP(@Url String url, @Field("MobileNumber") String MobileNumber);

    @POST()
    Call<StateListModel> stateList(@Url String url);

    @POST()
    Call<StateListModel> userList(@Url String url);

    @POST()
    @FormUrlEncoded
    Call<CityListModel> cityList(@Url String url,@Field("StateId") String StateId);

    @POST()
    @FormUrlEncoded
    Call<VerifyRegisterOTPModel> verifyRegisterOTP(@Url String url, @Field("MobileNumber") String MobileNumber, @Field("OTP") String otp);


    @POST()
    @FormUrlEncoded
    Call<ProductdDescDetailsModel> ProductdDescDetails(@Url String url, @Field("ProductDescId") String ProductDescId,
                                                       @Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<OfferProductDescDetailsModel> OfferProductdDescDetails(@Url String url, @Field("ProductDescId") String ProductDescId);

    @POST()
    @FormUrlEncoded
    Call<SampleRequestModel> sampleRequest(@Url String url, @Field("ProductId") String ProductId, @Field("StoreId") String StoreId
    , @Field("FullName") String FullName, @Field("EmailId") String EmailId, @Field("Address") String Address, @Field("City") String City,
                                           @Field("State") String State, @Field("ZipCode") String ZipCode,
                                           @Field("Phone") String Phone, @Field("UserId") String UserId );

    @POST()
    @FormUrlEncoded
    Call<CategoryModel> productDetails(@Url String url, @Field("Storeid") String Storeid, @Field("SubCategoryId") String CategoryId,
                                       @Field("UserId") String UserId);
    @POST()
    @FormUrlEncoded
    Call<CategoryModel> productRangeDetails(@Url String url, @Field("FromPrice") String FromPrice, @Field("ToPrice") String ToPrice,
                                       @Field("UserId") String UserId);


    @POST()
    @FormUrlEncoded
    Call<SubCategoryModel> subProductDetails(@Url String url, @Field("CategoryId") String CategoryId);

    @POST()
    Call<UpdateAddToCartRemarksModel> updateAddToCartRemarks(@Url String url, @Body UpdateAddToCartRemarksParams val);

    @POST()
    @FormUrlEncoded
    Call<ProductSearchDetailsModel> productSearchDetails(@Url String url,@Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<SearchStockDetailsModel> searchstockdetails(@Url String url, @Field("StoreId") String StoreId);

    @POST()
    @FormUrlEncoded
    Call<AddToCartDetailsModel> addToCartDetails(@Url String url, @Field("UserId") String UserId, @Field("ProductDescId") String ProductDescId,
                                                 @Field("StoreId") String StoreId, @Field("ShippingCharges") String ShippingCharges,
                                                 @Field("Qty") String Qty);

    @POST()
    @FormUrlEncoded
    Call<RegisterModel> register(@Url String url, @Field("PortalLoginName") String PortalLoginName, @Field("Password") String Password,
                                 @Field("Address") String Address, @Field("MobileNumber") String MobileNumber, @Field("MobileNumber2") String MobileNumber2,@Field("MobileNumber3") String MobileNumber3,@Field("MobileNumber4") String MobileNumber4,@Field("MobileNumber5") String MobileNumber5, @Field("EmailId") String EmailId,
                                 @Field("GSTNumber") String GSTNumber,@Field("ZipCode") String ZipCode,
                                 @Field("State") String State,@Field("City") String City,@Field("FullName") String FullName,@Field("KTNumber") String KTNumber,@Field("RefrenceNameFirst") String RefrenceNameFirst,@Field("RefrenceNumberFirst") String RefrenceNumberFirst,@Field("RefrenceNameSecond") String RefrenceNameSecond,@Field("RefrenceNumberSecond") String RefrenceNumberSecond);

    @POST()
    @FormUrlEncoded
    Call<HomeModel> homeDetailsAPI(@Url String url, @Field("UserId") String UserId);

    @POST()
    @FormUrlEncoded
    Call<AdminHomeModel> adminHomeDetailsAPI(@Url String url, @Field("UserId") String UserId, @Field("StoreID") String StoreID);

    /*@POST()
    @FormUrlEncoded
    Call<EditStockProductDetailsModel> adminEditStockProductDetails(@Url String url, @Field("ProductDescId") String ProductDescId, @Field("StoreID") String StoreID, @Field("UserId") String UserId);
*/
    @POST()
    @FormUrlEncoded
    Call<EditStockProductDetailsModel> adminEditStockProductDetails(@Url String url, @Field("ProductDescId") String ProductDescId, @Field("StoreID") String StoreID);


    @POST()
    @FormUrlEncoded
    Call<AdminOrderdetailModel> adminOrderDetails(@Url String url, @Field("Tranno") String TranNo);

    @POST()
    @FormUrlEncoded
    Call<AdminPreOrderdetailModel> adminPreOrderDetails(@Url String url, @Field("TranNo") String TranNo);

    @POST()
    Call<UpdateStockProductDetailsModel> adminUpdateStockProductDetails(@Url String url, @Body EditStockProductDetailsModel val);


    @POST()
    @FormUrlEncoded
    Call<ActionRequestModel> auctionRequest(@Url String url, @Field("FullName") String FullName, @Field("ProductId") String ProductId,
                                            @Field("StoreId") String StoreId, @Field("SellingPrice") String SellingPrice,
                                            @Field("CustomerPrice") String CustomerPrice, @Field("Quantity") String Quantity,
                                            @Field("UserId") String UserId);


    interface Header {
        String AUTHORIZATION = "Authorization";
        String CONTENT_TYPE = "Content-Type";
    }

}
