package grossary.cyron.com.grossaryvccblrrelesed.user.PriceRange;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.category.UpdateAddToCartRemarksModel;
import grossary.cyron.com.grossaryvccblrrelesed.category.UpdateAddToCartRemarksParams;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.ViewAddtoCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.AddToCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.ViewCartItemCountDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.FragmentHelper;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ADDRESS;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.VIEW_CART;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.CHECKOUT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.MAKE_PAYMENT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.PLACE_YOUR_ORDER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.ADDRESS_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.VIEW_CART_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;


public class ProductViewBasedPrice extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private ImageView tvBack,img_cart;
    String tag;
    public TextView txtCheckout,tvTotal, tvCount;
    private LoadingView load;
    private RelativeLayout revBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view_based_price);
        tvBack = findViewById(R.id.tvBack);
        txtCheckout = findViewById(R.id.txtCheckout);
        tvTotal = findViewById(R.id.tvTotal);
        tvCount = findViewById(R.id.tvCount);
        img_cart = findViewById(R.id.img_cart);
        revBottom = findViewById(R.id.revBottom);


        String minPriceSelectvalue = getIntent().getStringExtra("minPriceSelectvalue");
        String maxPriceSelectvalue = getIntent().getStringExtra("maxPriceSelectvalue");
        getFragmentManager().addOnBackStackChangedListener(this);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Fragment fragment = null;
        fragment = new ProductPriceFragment();
        Bundle arguments = null;
        arguments = new Bundle();
        arguments.putString("minPriceSelectvalue", minPriceSelectvalue);
        arguments.putString("maxPriceSelectvalue", maxPriceSelectvalue);
        fragment.setArguments(arguments);
        FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

        txtCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtCheckout.getText().toString().equalsIgnoreCase(CHECKOUT)) {
                    selectFrag(VIEW_CART, "2", VIEW_CART_FRG);
                } else if (txtCheckout.getText().toString().equalsIgnoreCase(PLACE_YOUR_ORDER)) {
                    String count = new PreferenceManager(ProductViewBasedPrice.this).getCount();

                    if (count.equals("0")) {
                        Toast.makeText(ProductViewBasedPrice.this, "Please Add few Items in cart", Toast.LENGTH_SHORT).show();
                    } else {
                        PriceViewCartFragment fragment = (PriceViewCartFragment) FragmentHelper.getFragment(ProductViewBasedPrice.this, VIEW_CART);
                        if (fragment != null) {
                            if (fragment.validate()) {

                                List<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> dataSet = fragment.getAdapter();

                                UpdateAddToCartRemarksParams res = new UpdateAddToCartRemarksParams();
                                List<UpdateAddToCartRemarksParams.RemarklistEntity> list = new ArrayList<>();
                                if (dataSet != null && dataSet.size() > 0) {
                                    for (int i = 0; i < dataSet.size(); i++) {
                                        UpdateAddToCartRemarksParams.RemarklistEntity item = new UpdateAddToCartRemarksParams.RemarklistEntity();
                                        item.setOrderid(dataSet.get(i).getOrderid());
                                        item.setRemark(dataSet.get(i).getRemarks());
                                        list.add(item);
                                    }
                                    res.setRemarklist(list);
                                    callApiRemarks(res);
                                }

                            } else {
                                Toast.makeText(ProductViewBasedPrice.this, "Select Discount for Each Items", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else if (txtCheckout.getText().toString().equalsIgnoreCase(MAKE_PAYMENT)) {

                    PriceAddressFragment fragment = (PriceAddressFragment) FragmentHelper.getFragment(ProductViewBasedPrice.this, ADDRESS);
                    if (fragment != null)
                        fragment.callApiSubmitTransaction();

                }
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtCheckout.getText().toString().equalsIgnoreCase(CHECKOUT))
                    selectFrag(VIEW_CART, "2", VIEW_CART_FRG);

            }
        });


    }


    @Override
    public void onBackStackChanged() {

    }
    public void selectFrag(String tag, String response, String current){
        revBottom.setVisibility(View.VISIBLE);
        Fragment fragment = null;
        Bundle arguments = null;
        switch (tag) {
            case LIST_DETAILS:
                    fragment = new PriceListDetailsFragment();

                    arguments = new Bundle();
                    arguments.putString(CURRENT_FRG, current);
                    arguments.putString(FRAG_PARAMETER, response);
                    fragment.setArguments(arguments);
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);
                    break;

            case VIEW_CART:
                fragment = new PriceViewCartFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);
                break;



            case ADDRESS:
                fragment = new PriceAddressFragment();
                FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);

                break;




        }
    }

    public void callApiCount() {

//        load = new LoadingView(CategoryActivity.this);
//        load.setCancalabe(false);
//        load.showLoading();

        String url = BASE_URL + "/ShoppingCart/ViewCartItemCountDetails";

        Log.e("URl", "*** " + url);
        final LoginModel res = new PreferenceManager(ProductViewBasedPrice.this).getLoginModel();

        Call<ViewCartItemCountDetailsModel> call = RetrofitClient.getAPIInterface().viewCartItemCountDetails(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ViewCartItemCountDetailsModel>() {
            @Override
            public void onResponse(int code, ViewCartItemCountDetailsModel response, Headers headers) {
//                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    tvTotal.setText("₹" + response.getGrandtoal());
                    tvCount.setText("" + response.getTotalitemcount());
                    new PreferenceManager(ProductViewBasedPrice.this).setCount("" + response.getTotalitemcount());
                    new PreferenceManager(ProductViewBasedPrice.this).setGrandtoal("" + response.getGrandtoal());

                } else {
                    Toast.makeText(ProductViewBasedPrice.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int error) {
//                load.dismissLoading();

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("respond", "failure ---->");
//                load.dismissLoading();
            }
        });
        request.enqueue();
    }

    public void callApiAddtoCart(String productDescId, String stroeId, String ShippingCharges, String qty) {

        load = new LoadingView(ProductViewBasedPrice.this);
        load.setCancalabe(false);
        load.showLoading();

        String url = BASE_URL + "/ShoppingCart/AddToCartDetails";

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(ProductViewBasedPrice.this).getLoginModel();


        Call<AddToCartDetailsModel> call = RetrofitClient.getAPIInterface().addToCartDetails(url, "" + res.getUserid(),
                "" + productDescId,
                "" + stroeId,
                "" + ShippingCharges, "" + qty);
        Request request = new RetrofitRequest<>(call, new ResponseListener<AddToCartDetailsModel>() {
            @Override
            public void onResponse(int code, AddToCartDetailsModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    callApiCount();
                    Toast.makeText(ProductViewBasedPrice.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ProductViewBasedPrice.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

    private void callApiRemarks(UpdateAddToCartRemarksParams param) {

        load = new LoadingView(ProductViewBasedPrice.this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/UpdateAddToCartRemarks";
        Log.e("URl", "*** " + url);

        Call<UpdateAddToCartRemarksModel> call = RetrofitClient.getAPIInterface().updateAddToCartRemarks(url, param);
        Request request = new RetrofitRequest<>(call, new ResponseListener<UpdateAddToCartRemarksModel>() {
            @Override
            public void onResponse(int code, UpdateAddToCartRemarksModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {

                    selectFrag(ADDRESS, "", ADDRESS_FRG);

                } else {
                    Toast.makeText(ProductViewBasedPrice.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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
