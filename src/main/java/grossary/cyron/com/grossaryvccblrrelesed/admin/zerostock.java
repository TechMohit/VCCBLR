package grossary.cyron.com.grossaryvccblrrelesed.admin;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeListAdapter;
import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.admin.stock.AdminStockActivity;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.ADMIN_KEY_NAME.ACT_STOCK_DETAIL;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;


public class zerostock extends AppCompatActivity implements OnItemClickListener<AdminHomeModel.ProductlistEntity> {
    private AdminHomeModel homeModel;
    private RecyclerView recyclerView;
    private ProgressBar walletspinner;
    private ArrayList<zerostockModel> zerostocklist;
    String ProductId, ProductName, ProductDesc, ProductDescId, ProductDescName, MRPPrice, SellingPrice,
            ProductImage, Qty, MainProductsDesc, CategoryId, SubCategoryId, StoreName, AvailableStockQty,
            ProductRateType, EncryptedProductDetailsId, Type, VAT;
    private LoadingView load;
    private FrameLayout layConnection;
    private ArrayList<AdminHomeModel.ProductlistEntity> homeList = new ArrayList<>();
    private AdminHomeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zerostock);
        initview();
        callHomeApi();
        if (internetstate()) {
            //walletspinner.setVisibility(View.VISIBLE);
            //  getZerostock();
        } else {
            Toast.makeText(this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();
        }
        adapter = new AdminHomeListAdapter(this , this);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(adapter);

    }

    private void initview() {
        zerostocklist = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle_view);
        walletspinner = (ProgressBar) findViewById(R.id.walletloading);
        layConnection = findViewById(R.id.layConnection);


    }

    private boolean internetstate() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return connected = true;
        } else
            return connected = false;
    }

    private void callHomeApi() {

        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/VendorHome/ZeroStockDetail";
        layConnection.setVisibility(View.GONE);

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(zerostock.this).getLoginModel();

        Call<AdminHomeModel> call = RetrofitClient.getAPIInterface().adminHomeDetailsAPI(url,
                "" + res.getUserid(),""+res.getStoreid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<AdminHomeModel>() {
            @Override
            public void onResponse(int code, AdminHomeModel response, Headers headers) {
//                callApiCount();
                load.dismissLoading();
                setHomeModel(response);
                layConnection.setVisibility(View.GONE);
            }
            @Override
            public void onError(int error) {
                load.dismissLoading();
                layConnection.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Throwable throwable) {
                Log.e("HomeActivity", "failure ---->");
                load.dismissLoading();
                layConnection.setVisibility(View.VISIBLE);
            }
        });
        request.enqueue();
    }

    public void setHomeModel(AdminHomeModel response) {
        if (homeModel != null)
            homeModel = new AdminHomeModel();
        homeModel = response;

        setData(homeModel.getProductlist());


    }

    public void setData(List<AdminHomeModel.ProductlistEntity> data) {

        if(adapter==null || data==null)
            return;

        if(homeList.size()>0)
            homeList.clear();
        homeList.addAll(data);
        adapter.setAdapterData(homeList);

    }

    @Override
    public void onItemClick(AdminHomeModel.ProductlistEntity objstoredetailslist, View view, int position, String type) {
        Intent intent=new Intent(this, AdminStockActivity.class);
        intent.putExtra(ACT_STOCK_DETAIL,new Gson().toJson(objstoredetailslist));
        startActivity(intent);

    }



    /*private void getZerostock()  {

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(FileUploadService.BASE_URL_FOR_LOGIN).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        FileUploadService api = retrofit.create(FileUploadService.class);

        JsonObject obj = new JsonObject();
        try {

            obj.addProperty("StoreId", "0");


        } catch (Exception e) {
        }
        Log.d("requesrparam", obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), obj.toString());
        final Call<ResponseBody> response = api.ZeroStock(body);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                try {
                       String res = rawResponse.body().string();
                        Log.d("Zerostock", "Response: " + res);

                        JSONObject dataListcoin = new JSONObject(res);
                        JSONObject dataListcoin2 = new JSONObject(dataListcoin.getString("Response"));
                        Log.d("cvi11cdcdcdc", "Response: " + dataListcoin2.getString("ResponseVal"));

                        String status = dataListcoin2.getString("ResponseVal");
                        String resason = dataListcoin2.getString("Reason");


                        if (status.equalsIgnoreCase("false")) {
                            walletspinner.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), resason, Toast.LENGTH_LONG).show();
                            }
                         else {
                            JSONArray js = new JSONArray(dataListcoin.getString("ProductList"));
                            for (int i = 0; i < js.length(); i++) {
                                JSONObject jsObj = js.getJSONObject(i);
                                ProductId = jsObj.getString("ProductId");
                                ProductName = jsObj.getString("ProductName");
                                ProductImage = jsObj.getString("ProductImage");
                                ProductDesc = jsObj.getString("ProductDesc");
                                ProductDescId = jsObj.getString("ProductDescId");
                                ProductDescName = jsObj.getString("ProductDescName");
                                MRPPrice = jsObj.getString("MRPPrice");
                                SellingPrice = jsObj.getString("SellingPrice");
                                Qty = jsObj.getString("Qty");
                                CategoryId = jsObj.getString("CategoryId");
                                SubCategoryId = jsObj.getString("SubCategoryId");
                                StoreName = jsObj.getString("StoreName");
                                AvailableStockQty = jsObj.getString("AvailableStockQty");
                                ProductRateType = jsObj.getString("ProductRateType");
                                EncryptedProductDetailsId = jsObj.getString("EncryptedProductDetailsId");
                                Type = jsObj.getString("Type");
                                VAT = jsObj.getString("VAT");
                                MainProductsDesc = jsObj.getString("MainProductsDesc");
                                zerostockModel zerostock = new zerostockModel(ProductId,ProductName,ProductDesc,ProductDescId,ProductDescName,MRPPrice,SellingPrice,
                                        ProductImage,Qty,MainProductsDesc,CategoryId,SubCategoryId,StoreName,AvailableStockQty,
                                        ProductRateType,EncryptedProductDetailsId,Type,VAT);
                                zerostocklist.add(zerostock);

                            }
                            walletspinner.setVisibility(View.INVISIBLE);
                            layoutManager = new LinearLayoutManager(zerostock.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                            // namewiselist.setLayoutManager(layoutManager);
                            adapter = new Recyclerviewadapter(servicenamelist, getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        }



                } catch (Exception e) {
                    walletspinner.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(), "Service list successfully " + e, Toast.LENGTH_LONG).show();
                    e.printStackTrace();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                walletspinner.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "FAIL", Toast.LENGTH_LONG).show();
            }
        });

    }*/

}
