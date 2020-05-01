package grossary.cyron.com.grossaryvccblrrelesed.admin.stock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.ADMIN_KEY_NAME.ACT_STOCK_DETAIL;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;

public class AdminStockActivity extends AppCompatActivity {

    private LoadingView load;
    private AdminEditStockDetailsdapter adapter;
    private RecyclerView recyclerView;
    private EditText etNam,etStock,etQty;
    private Button btnUpdate;
    private  EditStockProductDetailsModel responseMain;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_stock);
        recyclerView=findViewById(R.id.recycle_view);
        etNam=findViewById(R.id.etNam);
        etStock=findViewById(R.id.etStock);
        etQty=findViewById(R.id.etQty);
        btnUpdate=findViewById(R.id.btnUpdate);

        recyclerView.setLayoutManager(new LinearLayoutManager(AdminStockActivity.this));
        setAdapter();
        callApiEditStockDetail();
        LoginModel res1 = new PreferenceManager(AdminStockActivity.this).getLoginModel();
         userid = res1.getUserid();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid == 45) {
                    Toast.makeText(getApplicationContext(), "You are not valid user to update the stock", Toast.LENGTH_LONG).show();
                } else if (userid == 359) {
                    Toast.makeText(getApplicationContext(), "You are not valid user to update the stock", Toast.LENGTH_LONG).show();
                } else if (userid == 441) {
                    Toast.makeText(getApplicationContext(), "You are not valid user to update the stock", Toast.LENGTH_LONG).show();
                } else if (userid == 442) {
                    Toast.makeText(getApplicationContext(), "You are not valid user to update the stock", Toast.LENGTH_LONG).show();
                } else if (userid == 443) {
                    Toast.makeText(getApplicationContext(), "You are not valid user to update the stock", Toast.LENGTH_LONG).show();
                } else if (userid == 444) {
                    Toast.makeText(getApplicationContext(), "You are not valid user to update the stock", Toast.LENGTH_LONG).show();
                } else {
                    if (etQty.getText().toString().equalsIgnoreCase("")) {
                        responseMain.setStockqty(0);
                    } else {
                        responseMain.setStockqty(Integer.parseInt(etQty.getText().toString()));
                    }
                    responseMain.setProductrangelist(adapter.getList());
                    Log.e("Tag", "" + new Gson().toJson(responseMain));

                    callAPiUpdateStock();
                }
            }
        });

    }


    private void callAPiUpdateStock() {

        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Home/UpdateStockProductDetails";

        Call<UpdateStockProductDetailsModel> call = RetrofitClient.getAPIInterface().adminUpdateStockProductDetails(url,responseMain);
        Request request = new RetrofitRequest<>(call, new ResponseListener<UpdateStockProductDetailsModel>() {
            @Override
            public void onResponse(int code, UpdateStockProductDetailsModel response, Headers headers) {
                load.dismissLoading();

                Log.e("TAG","**** "+new Gson().toJson(response));
                if (response.getResponse().getResponseval()) {
                    Toast.makeText(AdminStockActivity.this, "" + response.getStatus(), Toast.LENGTH_SHORT).show();
                    callApiEditStockDetail();
                } else {
                    Toast.makeText(AdminStockActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    /* Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
                     startActivity(intent);*/
                }
            }
            @Override
            public void onError(int error) {
                load.dismissLoading();
            }
            @Override
            public void onFailure(Throwable throwable) {
                load.dismissLoading();
            }
        });
        request.enqueue();
    }

    private void setAdapter() {
        adapter = new AdminEditStockDetailsdapter(AdminStockActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void callApiEditStockDetail() {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Home/EditStockProductDetails";

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(AdminStockActivity.this).getLoginModel();

        String value = getIntent().getStringExtra(ACT_STOCK_DETAIL);
        AdminHomeModel.ProductlistEntity product = new Gson().fromJson(value, AdminHomeModel.ProductlistEntity.class);

        /*Call<EditStockProductDetailsModel> call = RetrofitClient.getAPIInterface().adminEditStockProductDetails(url,
                "" + product.getProductdescid(),""+res.getStoreid(),""+res.getUserid());*/


        Call<EditStockProductDetailsModel> call = RetrofitClient.getAPIInterface().adminEditStockProductDetails(url,
                "" + product.getProductdescid(),""+res.getStoreid());

        Request request = new RetrofitRequest<>(call, new ResponseListener<EditStockProductDetailsModel>() {
            @Override
            public void onResponse(int code, EditStockProductDetailsModel response, Headers headers) {
                load.dismissLoading();
                responseMain=response;
                Log.e("TAG","**** "+new Gson().toJson(response));
                if (response.getResponse().getResponseval()) {
                    etNam.setText(""+response.getSubproductdesc());
                    etStock.setText(""+response.getAvailablestockqty());
                    etQty.setText("0");

                    adapter.setAdapterData(response.getProductrangelist());
                } else {
                    Toast.makeText(AdminStockActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(int error) {
                load.dismissLoading();
            }
            @Override
            public void onFailure(Throwable throwable) {
                load.dismissLoading();
            }
        });
        request.enqueue();

    }
}
