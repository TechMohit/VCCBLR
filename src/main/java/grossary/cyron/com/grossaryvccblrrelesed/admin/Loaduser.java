package grossary.cyron.com.grossaryvccblrrelesed.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.user.HomeActivity;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.Constant;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;


public class Loaduser extends AppCompatActivity {
    private ArrayList<Userlistmodel> userlist;
    private Spinner usertypespin;
    private LoadingView load;
    Button Submit;
    String UserName,LoginId;
    private List<String> categoriesState = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaduser);

        initViews();
        userlist = new ArrayList<>();
        loaduser();
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Username",UserName);
                AuthenticateLoadUser(UserName);
            }
        });

    }



    private void initViews() {
        usertypespin = findViewById(R.id.spin_usertype);
        Submit =findViewById(R.id.submit);
    }

    private void loaduser() {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(FileUploadService.BASE_URL_FOR_LOGIN).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        FileUploadService api = retrofit.create(FileUploadService.class);

        JsonObject obj = new JsonObject();


        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "");
        final Call<ResponseBody> response = api.LoadUser(body);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse) {
                try {

                    load.dismissLoading();
                    String res = rawResponse.body().string();
                    Log.d("LoadUser", "response: " + res);


                    JSONObject dataListcoin = new JSONObject(res);
                    JSONObject dataListcoin1 = new JSONObject(dataListcoin.getString("Response"));

                    String status = dataListcoin1.getString("ResponseVal");
                    String resason = dataListcoin1.getString("Reason");
                    Log.d("LoadUser", "MescomServiceresparam: " + dataListcoin1.getString("ResponseVal"));
                    if (status.equalsIgnoreCase("false")) {

                        Toast.makeText(getApplicationContext(), resason, Toast.LENGTH_LONG).show();

                    } else {
                        JSONArray js = new JSONArray(dataListcoin.getString("objUserList"));

                        for (int i = 0; i < js.length(); i++) {
                            JSONObject jsObj = js.getJSONObject(i);
                             LoginId = jsObj.getString("LoginId");
                             UserName = jsObj.getString("UserName");
                            Userlistmodel userlistmodel = new Userlistmodel(LoginId, UserName);
                            userlist.add(userlistmodel);


                        }

                        CustomsubdevisionAdapter customSpinnerAdaptervehicle = new CustomsubdevisionAdapter(getApplicationContext(), userlist, "#4caf50");
                        usertypespin.setAdapter(customSpinnerAdaptervehicle);
                        usertypespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Userlistmodel listmodel = userlist.get(position);
                                 UserName = listmodel.getUserName();
                                 LoginId = listmodel.getLoginId();
                                Log.d("spineeritem", "UserName:" + UserName + "LoginId:" + LoginId);


                                  /*  if(!(str_clcname.equalsIgnoreCase("Select Report Type"))){
                                        reporttypeclicked = true;
                                    }
                                    else{
                                        reporttypeclicked = false;
                                    }*/

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }


                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Sorry,Failed to Load user List " + e, Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                load.dismissLoading();
                Toast.makeText(getApplicationContext(), " Please Try Again", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void AuthenticateLoadUser(String loginId) {

        /*load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();*/
        String url = BASE_URL + "/Login/AuthenticateLoadUser";

        Log.e("URl", "*** " + url);
        Call<LoginModel> call = RetrofitClient.getAPIInterface().authenticateLoadUser(url,
                loginId);
        Request request = new RetrofitRequest<>(call, new ResponseListener<LoginModel>() {
            @Override
            public void onResponse(int code, LoginModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    Log.e("Testloaduser", "" + response.getFullname());
                    new PreferenceManager(Loaduser.this).setLoginModel(response);
                    new PreferenceManager(Loaduser.this).setAutoLogin(true);
                    Constant.adminuser = true;
                    startActivity(new Intent(Loaduser.this, HomeActivity.class));
                }

                 else {
                    Constant.adminuser = false;
                    Toast.makeText(Loaduser.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int error) {
                load.dismissLoading();

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("respond", "failure ---->");
                load.dismissLoading();
            }
        });
        request.enqueue();
    }




}
