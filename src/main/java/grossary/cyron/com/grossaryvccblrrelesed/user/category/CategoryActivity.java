package grossary.cyron.com.grossaryvccblrrelesed.user.category;

import android.app.Dialog;
import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.category.UpdateAddToCartRemarksModel;
import grossary.cyron.com.grossaryvccblrrelesed.category.UpdateAddToCartRemarksParams;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.adress.AddressFragment;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.ViewAddtoCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.ViewCartFragment;
import grossary.cyron.com.grossaryvccblrrelesed.user.order.MyOrderDetailFragment;
import grossary.cyron.com.grossaryvccblrrelesed.user.order.MyOrdersFragment;
import grossary.cyron.com.grossaryvccblrrelesed.user.search.ProductSearchDetailsAdapter;
import grossary.cyron.com.grossaryvccblrrelesed.user.search.ProductSearchDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.FragmentHelper;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ADDRESS;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.LIST;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ORDER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ORDER_DETAIL;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.SUB_LIST;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.VIEW_CART;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.CHECKOUT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.MAKE_PAYMENT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.PLACE_YOUR_ORDER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.ADDRESS_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.BRAND_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.HOME_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.MY_ORDER_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.OFFER_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.SEARCH_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.SELLER_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.VIEW_CART_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.ACT_HOME_PARAMETER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Util.openKeyPad;

public class CategoryActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener,
        OnItemClickListener<ProductSearchDetailsModel.ObjproductsearchdetailsEntity> {

    private LoadingView load;
    private RelativeLayout revBottom;
    public TextView txtCheckout, tvTotal, tvCount;
    private Dialog dialog;
    private ImageView tvBack, img_cart;

    private RecyclerView recyclerView;
    private ProductSearchDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        txtCheckout = findViewById(R.id.txtCheckout);
        revBottom = findViewById(R.id.revBottom);
        tvTotal = findViewById(R.id.tvTotal);
        tvCount = findViewById(R.id.tvCount);
        tvBack = findViewById(R.id.tvBack);
        img_cart = findViewById(R.id.img_cart);

        String current = getIntent().getStringExtra(CURRENT_FRG);
        if (current.equalsIgnoreCase(HOME_FRG)) {
            selectFrag(SUB_LIST, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);
        } else if (current.equalsIgnoreCase(SELLER_FRG)) {
            selectFrag(LIST, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);
        } else if (current.equalsIgnoreCase(OFFER_FRG)) {
            selectFrag(LIST_DETAILS, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);
        } else if (current.equalsIgnoreCase(BRAND_FRG)) {
            selectFrag(LIST_DETAILS, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);
        } else if (current.equalsIgnoreCase(VIEW_CART_FRG)) {
            selectFrag(VIEW_CART, "1", VIEW_CART_FRG);
        } else if (current.equalsIgnoreCase(MY_ORDER_FRG)) {
            selectFrag(ORDER, "", MY_ORDER_FRG);
        } else if (current.equalsIgnoreCase(SEARCH_FRG)) {
            selectFrag(LIST_DETAILS, getIntent().getStringExtra(ACT_HOME_PARAMETER), current);

        }
        getFragmentManager().addOnBackStackChangedListener(this);

        txtCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtCheckout.getText().toString().equalsIgnoreCase(CHECKOUT)) {
                    selectFrag(VIEW_CART, "2", VIEW_CART_FRG);
                } else if (txtCheckout.getText().toString().equalsIgnoreCase(PLACE_YOUR_ORDER)) {
                    String count = new PreferenceManager(CategoryActivity.this).getCount();

                    if (count.equals("0")) {
                        Toast.makeText(CategoryActivity.this, "Please Add few Items in cart", Toast.LENGTH_SHORT).show();
                    } else {
                        ViewCartFragment fragment = (ViewCartFragment) FragmentHelper.getFragment(CategoryActivity.this, VIEW_CART);
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
                                Toast.makeText(CategoryActivity.this, "Select Discount for Each Items", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else if (txtCheckout.getText().toString().equalsIgnoreCase(MAKE_PAYMENT)) {

                    AddressFragment fragment = (AddressFragment) FragmentHelper.getFragment(CategoryActivity.this, ADDRESS);
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

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final ImageView imgSearch = findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(CategoryActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.custom_search);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                openKeyPad(CategoryActivity.this, imgSearch);
                //This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(lp);
                dialog.setCancelable(true);
                TextView imgSearch = dialog.findViewById(R.id.imgSearch);
                ImageView imgBack = dialog.findViewById(R.id.imgBack);
                final EditText etSearch = dialog.findViewById(R.id.etSearch);
                etSearch.requestFocus();
                recyclerView = dialog.findViewById(R.id.recycle_view);

                recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
                setAdapter();

                //openKeyPad(CategoryActivity.this, etSearch);

                imgBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                callApiProductSearchDetails();
                dialog.show();

            }
        });
    }

    private void callApiRemarks(UpdateAddToCartRemarksParams param) {

        load = new LoadingView(CategoryActivity.this);
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
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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
        adapter = new ProductSearchDetailsAdapter(CategoryActivity.this, this);
        recyclerView.setAdapter(adapter);
    }

    private void callApiProductSearchDetails() {
        load = new LoadingView(CategoryActivity.this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Home/ProductSearchDetails";
        LoginModel res = new PreferenceManager(CategoryActivity.this).getLoginModel();

        Log.e("URl", "*** " + url);

        Call<ProductSearchDetailsModel> call = RetrofitClient.getAPIInterface().productSearchDetails(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ProductSearchDetailsModel>() {
            @Override
            public void onResponse(int code, ProductSearchDetailsModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    adapter.setAdapterData(response.getObjproductsearchdetails());

                } else {
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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


    public void selectFrag(String tag, String response, String current) {

        revBottom.setVisibility(View.VISIBLE);
        Fragment fragment = null;
        Bundle arguments = null;
        switch (tag) {

            case SUB_LIST:

                fragment = new SubCategoryListFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);

                FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                break;
            case LIST:

                fragment = new CategoryListFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                if (current.equalsIgnoreCase(SELLER_FRG)) {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);
                } else {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);
                }
                break;
            case LIST_DETAILS:

                fragment = new CategoryListDetailsFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                if (current.equalsIgnoreCase(OFFER_FRG) || current.equalsIgnoreCase(BRAND_FRG) ||
                        current.equalsIgnoreCase(SEARCH_FRG)) {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                } else {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);
                }
                break;
            case VIEW_CART:

                fragment = new ViewCartFragment();
                if (response.equalsIgnoreCase("1")) {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                } else {
                    FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);
                }

                break;
            case ORDER:
                revBottom.setVisibility(View.GONE);
                fragment = new MyOrdersFragment();
                FragmentHelper.replaceFragment(this, R.id.container, fragment, false, tag);

                break;

            case ADDRESS:
                fragment = new AddressFragment();
                FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);

                break;
            case ORDER_DETAIL:
                revBottom.setVisibility(View.GONE);
                fragment = new MyOrderDetailFragment();
                arguments = new Bundle();
                arguments.putString(CURRENT_FRG, current);
                arguments.putString(FRAG_PARAMETER, response);
                fragment.setArguments(arguments);
                FragmentHelper.replaceFragment(this, R.id.container, fragment, true, tag);

                break;
        }
    }

    public void callApiAddtoCart(String productDescId, String stroeId, String ShippingCharges, String qty) {

        load = new LoadingView(CategoryActivity.this);
        load.setCancalabe(false);
        load.showLoading();

        String url = BASE_URL + "/ShoppingCart/AddToCartDetails";

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(CategoryActivity.this).getLoginModel();


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
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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


    public void callApiCount() {

//        load = new LoadingView(CategoryActivity.this);
//        load.setCancalabe(false);
//        load.showLoading();

        String url = BASE_URL + "/ShoppingCart/ViewCartItemCountDetails";

        Log.e("URl", "*** " + url);
        final LoginModel res = new PreferenceManager(CategoryActivity.this).getLoginModel();

        Call<ViewCartItemCountDetailsModel> call = RetrofitClient.getAPIInterface().viewCartItemCountDetails(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ViewCartItemCountDetailsModel>() {
            @Override
            public void onResponse(int code, ViewCartItemCountDetailsModel response, Headers headers) {
//                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    tvTotal.setText("₹" + response.getGrandtoal());
                    tvCount.setText("" + response.getTotalitemcount());
                    new PreferenceManager(CategoryActivity.this).setCount("" + response.getTotalitemcount());
                    new PreferenceManager(CategoryActivity.this).setGrandtoal("" + response.getGrandtoal());

                } else {
                    Toast.makeText(CategoryActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackStackChanged() {

    }


    @Override
    public void onItemClick(ProductSearchDetailsModel.ObjproductsearchdetailsEntity objproductsearchdetailsEntity, View view, int position, String type) {

        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        selectFrag(LIST_DETAILS, new Gson().toJson(objproductsearchdetailsEntity), SEARCH_FRG);

    }
}
