package grossary.cyron.com.grossaryvccblrrelesed.admin;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

 public interface FileUploadService {
      String BASE_URL_FOR_LOGIN ="http://Mobileapi.vccb2b.com/api/";
     //String BASE_URL_FOR_LOGIN ="http://demo.mobileapi.vccb2b.com/api/";

    @Headers("Content-Type: application/json")
    @POST("Login/LoadUserDetails")
    Call<ResponseBody> LoadUser(@Body RequestBody requestdata);

    @Headers("Content-Type: application/json")
    @POST("VendorHome/ZeroStockDetail")
    Call<ResponseBody> ZeroStock(@Body RequestBody requestdata);

     @Headers("Content-Type: application/json")
     @POST("VendorHome/CustomerRewards")
     Call<ResponseBody> Myreward(@Body RequestBody requestdata);


     @Headers("Content-Type: application/json")
     @POST("VendorHome/CustomerPendingPayment")
     Call<ResponseBody> Mypendingpayment(@Body RequestBody requestdata);

     @Headers("Content-Type: application/json")
     @POST("VendorHome/PaymentCollection")
     Call<ResponseBody> PaymentCollection(@Body RequestBody requestdata);
}
