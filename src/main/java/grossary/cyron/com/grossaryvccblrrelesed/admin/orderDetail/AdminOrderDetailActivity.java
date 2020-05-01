package grossary.cyron.com.grossaryvccblrrelesed.admin.orderDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.ViewCartModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.ADMIN_KEY_NAME.ACT_ORDER_DETAIL;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;


public class AdminOrderDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ViewCartModel> list = new ArrayList<>();
    private AdminOrderDetailsdapter adapter;
    private LoadingView load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_detail);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminOrderDetailActivity.this));
        setAdapter();
        callApiViewCart();

    }

    private void callApiViewCart() {
        load = new LoadingView(AdminOrderDetailActivity.this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/VendorHome/OrderviewDetails";
        LoginModel res = new PreferenceManager(AdminOrderDetailActivity.this).getLoginModel();

        Log.e("URl", "*** " + url);

        String value = getIntent().getStringExtra(ACT_ORDER_DETAIL);

        String tranNo = "";

        AdminHomeModel.RecondetailslistEntity product = new Gson().fromJson(value, AdminHomeModel.RecondetailslistEntity.class);
        tranNo = product.getTranno();

        Call<AdminOrderdetailModel> call = RetrofitClient.getAPIInterface().adminOrderDetails(url, "" + tranNo);
        Request request = new RetrofitRequest<>(call, new ResponseListener<AdminOrderdetailModel>() {
            @Override
            public void onResponse(int code, AdminOrderdetailModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {

                    adapter.setAdapterData(response.getOrderreconmodel(), response);
                } else {
                    Toast.makeText(AdminOrderDetailActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

    private void setAdapter() {
        adapter = new AdminOrderDetailsdapter(AdminOrderDetailActivity.this);
        recyclerView.setAdapter(adapter);
    }

}
