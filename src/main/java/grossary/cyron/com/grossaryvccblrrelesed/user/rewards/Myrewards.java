package grossary.cyron.com.grossaryvccblrrelesed.user.rewards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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


public class Myrewards extends AppCompatActivity {
    private ImageView tvBack;
    private RecyclerView namewiselist;
    private LoadingView load;
    private ArrayList<Servicenamemodel> servicenamelist;
    private Recyclerviewadapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    LoginModel res;

    String RewardType,RewardPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrewards);
        tvBack = findViewById(R.id.tvBack);
        namewiselist = findViewById(R.id.rv_namewiselist);
        load = new LoadingView(Myrewards.this);
        load.setCancalabe(false);
        load.showLoading();
         res = new PreferenceManager(Myrewards.this).getLoginModel();

        servicenamelist = new ArrayList<>();

        getPrewardPoint();


        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

    }

    private void getPrewardPoint() {

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(FileUploadService.BASE_URL_FOR_LOGIN).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        FileUploadService api = retrofit.create(FileUploadService.class);

        JsonObject obj = new JsonObject();
        try {




            obj.addProperty("UserId", res.getUserid());


        } catch (Exception e) {
        }
        Log.d("requesrparam", obj.toString());
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), obj.toString());
        final Call<ResponseBody> response = api.Myreward(body);

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
                            JSONArray js = new JSONArray(dataListcoin.getString("RewardsModel"));
                            for (int i = 0; i < js.length(); i++) {
                                JSONObject jsObj = js.getJSONObject(i);
                                RewardType = jsObj.getString("RewardType");
                                RewardPoint = jsObj.getString("RewardPoint");

                                Log.d("dkdnckdc", "ServiceName:" + RewardType + "ServiceParam:" + RewardPoint );
                                Servicenamemodel serviceName = new Servicenamemodel(RewardType, RewardPoint);
                                servicenamelist.add(serviceName);

                            }
                            load.dismissLoading();
                            layoutManager = new LinearLayoutManager(Myrewards.this, LinearLayoutManager.VERTICAL, false);
                            namewiselist.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                            // namewiselist.setLayoutManager(layoutManager);
                            adapter = new Recyclerviewadapter(servicenamelist, getApplicationContext());
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
