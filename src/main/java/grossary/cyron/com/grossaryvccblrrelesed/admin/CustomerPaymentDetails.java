package grossary.cyron.com.grossaryvccblrrelesed.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerPaymentDetails extends AppCompatActivity {
    int sessionId;
    ProgressBar walletspinner;
    private RecyclerView namewiselist;
    private ArrayList<PaymentCollectionModel> paymentcollectionlist;
   String TranNo,InvoiceNo,InvoiceDate,BuyerName,ProductName,Qty,StoreName,InvoiceAmount,ProductPrice;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewPaymentDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment_details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sessionId = extras.getInt("Select_User_ID");
            //The key argument here must match that used in the other activity
        }
       // sessionId = getIntent().getIntExtra("Select_User_ID");
        initViews();
        getDetails();
    }

    private void initViews() {
        namewiselist = findViewById(R.id.rv_namewiselist);
        paymentcollectionlist = new ArrayList<>();
        walletspinner = (ProgressBar) findViewById(R.id.walletloading);

    }


    private void getDetails() {

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(FileUploadService.BASE_URL_FOR_LOGIN).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        FileUploadService api = retrofit.create(FileUploadService.class);

        JsonObject obj = new JsonObject();
        try {

            obj.addProperty("UserId", sessionId);


        } catch (Exception e) {
        }
        Log.d("requesrparam", obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), obj.toString());
        final Call<ResponseBody> response = api.PaymentCollection(body);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                try {
                    if (rawResponse.code() == 200) {

                        String res = rawResponse.body().string();
                        Log.d("vddscdscs", "Response: " + res);

                        JSONObject dataListcoin = new JSONObject(res);
                        JSONObject dataListcoin2 = new JSONObject(dataListcoin.getString("Response"));

                        Log.d("vddscdscs1", "Response: " + dataListcoin2.getString("ResponseVal"));

                        String status = dataListcoin2.getString("ResponseVal");
                        String resason = dataListcoin2.getString("Reason");


                        if (status.equalsIgnoreCase("false")) {
                            walletspinner.setVisibility(View.INVISIBLE);

                            Toast.makeText(getApplicationContext(), resason, Toast.LENGTH_LONG).show();
                        } else {
                            JSONArray js = new JSONArray(dataListcoin.getString("PaymentCollectionModel"));
                           for (int i = 0; i < js.length(); i++) {
                               JSONObject jsObj = js.getJSONObject(i);
                                 TranNo = jsObj.getString("TranNo");
                                InvoiceNo = jsObj.getString("InvoiceNo");
                                InvoiceDate = jsObj.getString("InvoiceDate");
                                BuyerName = jsObj.getString("BuyerName");
                               ProductName = jsObj.getString("ProductName");
                               Qty = jsObj.getString("Qty");
                               StoreName = jsObj.getString("StoreName");
                               InvoiceAmount = jsObj.getString("InvoiceAmount");
                               ProductPrice = jsObj.getString("ProductPrice");


                               PaymentCollectionModel paymentCollectionModel = new PaymentCollectionModel(TranNo,InvoiceNo,InvoiceDate,BuyerName,ProductName,Qty,StoreName,InvoiceAmount,ProductPrice);
                                paymentcollectionlist.add(paymentCollectionModel);

                            }
                            walletspinner.setVisibility(View.INVISIBLE);
                            layoutManager = new LinearLayoutManager(CustomerPaymentDetails.this, LinearLayoutManager.VERTICAL, false);
                          //  namewiselist.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                             namewiselist.setLayoutManager(layoutManager);
                            adapter = new RecyclerViewPaymentDetailsAdapter(paymentcollectionlist, getApplicationContext());
                            namewiselist.setAdapter(adapter);


                        }


                    }
                }

                 catch (Exception e) {
                    walletspinner.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(), "Service list successfully " + e, Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                walletspinner.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_LONG).show();
            }
        });

    }
}
