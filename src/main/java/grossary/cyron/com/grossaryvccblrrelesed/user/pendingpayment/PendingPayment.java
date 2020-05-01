package grossary.cyron.com.grossaryvccblrrelesed.user.pendingpayment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.admin.FileUploadService;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;

import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PendingPayment extends AppCompatActivity {

    private ImageView tvBack;
    private RecyclerView namewiselist;
    private LoadingView load;
    private ArrayList<Pendingpaymentmodel> servicenamelist;
    private Recyclerviewadapterpendingpayment adapter;
    private RecyclerView.LayoutManager layoutManager;
    LoginModel res;
    String RewardType,RewardPoint;
    String  OrderNo,TranNo,TranDate,InvoiceNo,InvoiceDate,Delivered,BuyerName,ProductName,DeliveredDate,
            Qty,StoreName,NoInvoice,InvoiceQty,InvoiceAmount,BranchId,BalInvoiceQty,InvoiceId,StoreId,
            LorryNumber,DriverMobileNo,Remarks,OrderAmount,OrderType,LoadingDate,Discount,Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_payment);
        tvBack = findViewById(R.id.tvBack);
        namewiselist = findViewById(R.id.rv_namewiselist);
        load = new LoadingView(PendingPayment.this);
        load.setCancalabe(false);
        load.showLoading();
        res = new PreferenceManager(PendingPayment.this).getLoginModel();

        servicenamelist = new ArrayList<>();
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        getPendingPayment();
    }

    private void getPendingPayment() {


        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(FileUploadService.BASE_URL_FOR_LOGIN).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        FileUploadService api = retrofit.create(FileUploadService.class);

        JsonObject obj = new JsonObject();
        try {




            obj.addProperty("UserId", res.getUserid());
            //obj.addProperty("UserId", "62");


        } catch (Exception e) {
        }
        Log.d("requesrparam", obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), obj.toString());
        final Call<ResponseBody> response = api.Mypendingpayment(body);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                try {

                    if (rawResponse.code() == 200) {

                        String res = rawResponse.body().string();
                        Log.d("service", "Response: " + res);

                        JSONObject dataListcoin = new JSONObject(res);
                        String StatusMessage = dataListcoin.getString("StatusMessage");
                        JSONObject dataListcoin2 = new JSONObject(dataListcoin.getString("Response"));
                        Log.d("cvi11cdcdcdc", "Response: " + dataListcoin2.getString("ResponseVal"));

                        String status = dataListcoin2.getString("ResponseVal");
                        String resason = dataListcoin2.getString("Reason");


                        if (status.equalsIgnoreCase("false")) {
                            load.dismissLoading();

                            Toast.makeText(getApplicationContext(), StatusMessage, Toast.LENGTH_LONG).show();

                        } else {
                            JSONArray js = new JSONArray(dataListcoin.getString("OrderPerviousModel"));
                            for (int i = 0; i < js.length(); i++) {
                                JSONObject jsObj = js.getJSONObject(i);
                                OrderNo = jsObj.getString("OrderNo");
                                TranNo = jsObj.getString("TranNo");
                                TranDate = jsObj.getString("TranDate");
                                InvoiceNo = jsObj.getString("InvoiceNo");
                                InvoiceDate = jsObj.getString("InvoiceDate");
                                Delivered = jsObj.getString("Delivered");
                                BuyerName = jsObj.getString("BuyerName");
                                ProductName = jsObj.getString("ProductName");
                                DeliveredDate = jsObj.getString("DeliveredDate");
                                Qty = jsObj.getString("Qty");
                                StoreName = jsObj.getString("StoreName");
                                NoInvoice = jsObj.getString("NoInvoice");
                                InvoiceQty = jsObj.getString("InvoiceQty");
                                InvoiceAmount = jsObj.getString("InvoiceAmount");
                                BranchId = jsObj.getString("BranchId");
                                BalInvoiceQty = jsObj.getString("BalInvoiceQty");
                                InvoiceId = jsObj.getString("InvoiceId");
                                StoreId = jsObj.getString("StoreId");
                                LorryNumber = jsObj.getString("LorryNumber");
                                DriverMobileNo = jsObj.getString("DriverMobileNo");
                                Remarks = jsObj.getString("Remarks");
                                OrderAmount = jsObj.getString("OrderAmount");
                                OrderType = jsObj.getString("OrderType");
                                LoadingDate = jsObj.getString("LoadingDate");
                                Discount = jsObj.getString("Discount");
                                Type = jsObj.getString("Type");

                                Log.d("dkdnckdc", "ServiceName:" + RewardType + "ServiceParam:" + RewardPoint );
                                Pendingpaymentmodel serviceName = new Pendingpaymentmodel(OrderNo,TranNo,TranDate,InvoiceNo,InvoiceDate,Delivered,
                                        BuyerName,ProductName,DeliveredDate,
                                        Qty,StoreName,NoInvoice,InvoiceQty,InvoiceAmount,BranchId,BalInvoiceQty,InvoiceId,StoreId,
                                        LorryNumber,DriverMobileNo,Remarks,OrderAmount,OrderType,LoadingDate,Discount,Type);
                                 servicenamelist.add(serviceName);

                            }
                            load.dismissLoading();
                            layoutManager = new LinearLayoutManager(PendingPayment.this, LinearLayoutManager.VERTICAL, false);
                            //namewiselist.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                             namewiselist.setLayoutManager(layoutManager);
                            adapter = new Recyclerviewadapterpendingpayment(servicenamelist, getApplicationContext());
                            namewiselist.setAdapter(adapter);


                        }


                    }
                } catch (Exception e) {
                    load.dismissLoading();
                    Toast.makeText(getApplicationContext(), "Reward Successfully " + e, Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                load.dismissLoading();
                Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_LONG).show();
            }
        });

    }
}
