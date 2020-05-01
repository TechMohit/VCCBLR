package grossary.cyron.com.grossaryvccblrrelesed.admin.preOrderDetail;

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


public class AdminPreOrderDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ViewCartModel> list = new ArrayList<>();
    private AdminPreOrderDetailsdapter adapter;
    private LoadingView load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_detail);
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminPreOrderDetailActivity.this));
        setAdapter();
        callApiViewCart();

    }

    private void callApiViewCart() {
        load = new LoadingView(AdminPreOrderDetailActivity.this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/VendorHome/PerviousOrderviewDetails";
        LoginModel res = new PreferenceManager(AdminPreOrderDetailActivity.this).getLoginModel();

        Log.e("URl", "*** " + url);

        String value = getIntent().getStringExtra(ACT_ORDER_DETAIL);

        AdminHomeModel.OrderperviousmodelEntity product = new Gson().fromJson(value, AdminHomeModel.OrderperviousmodelEntity.class);

        Call<AdminPreOrderdetailModel> call = RetrofitClient.getAPIInterface().adminPreOrderDetails(url, "" + product.getTranno());
        Request request = new RetrofitRequest<>(call, new ResponseListener<AdminPreOrderdetailModel>() {
            @Override
            public void onResponse(int code, AdminPreOrderdetailModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {

                    adapter.setAdapterData(response.getOrderperviousmodel(), response);
                } else {
                    Toast.makeText(AdminPreOrderDetailActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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
        adapter = new AdminPreOrderDetailsdapter(AdminPreOrderDetailActivity.this);
        recyclerView.setAdapter(adapter);
    }

}
