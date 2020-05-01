package grossary.cyron.com.grossaryvccblrrelesed.user.PriceRange;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.Gson;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.brands.OfferProductDescDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.ActionRequestModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.CategoryModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.ProductdDescDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.SampleRequestModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.home.HomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.search.ProductSearchDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.GlideApp;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.AUCTION_PRODUCTS;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.CHECKOUT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.BRAND_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.CATG_LIST_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.OFFER_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.SEARCH_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.CURRENT_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class PriceListDetailsFragment extends Fragment {

    private TextView tvProductName, tvDesc, tvSellingPrice,txtCountAuc,txtqAu;
    private EditText txtCount;
    private LoadingView load;
    private Context context;
    private Button btnAddCart,  btReqSample,btnAuctionReq;
    private ImageView imgProduct;
    private ProductdDescDetailsModel responseMain;
    private OfferProductDescDetailsModel responseMainBrand;
    private String current;



    public PriceListDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_price_list_details, container, false);
        initView(view);

        ((ProductViewBasedPrice) getActivity()).txtCheckout.setText(CHECKOUT);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validation()) {
                    if (current.equalsIgnoreCase(BRAND_FRG)) {
                        ((ProductViewBasedPrice) getActivity()).callApiAddtoCart("" + responseMainBrand.getProductDescId(),
                                "" + responseMainBrand.getStoreId(), "" + responseMainBrand.getShippingCharges(),
                                "" + txtCount.getText());
                    } else {
                        ((ProductViewBasedPrice) getActivity()).callApiAddtoCart("" + responseMain.getProductdescid(),
                                "" + responseMain.getStoreid(), "" + responseMain.getShippingcharges(),
                                "" + txtCount.getText());
                    }
                }
            }
        });

        btnAuctionReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCountAuc.getText().toString().isEmpty()){
                    Toast.makeText(context, "Enter My Price", Toast.LENGTH_SHORT).show();
                }else   if(Integer.parseInt(txtCountAuc.getText().toString())==0){
                    Toast.makeText(context, "My Price should be greater than 0", Toast.LENGTH_SHORT).show();

                }else{
                    if (current.equalsIgnoreCase(BRAND_FRG)) {
                        callApiReqAuc(""+responseMainBrand.getProductDescId(),
                                ""+responseMainBrand.getStoreId());
                    }else{
                        callApiReqAuc(""+responseMain.getProductdescid(),
                                ""+responseMain.getStoreid());
                    }

                }

            }
        });

        btReqSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current.equalsIgnoreCase(BRAND_FRG)) {
                    callApiReqSample(""+responseMainBrand.getProductDescId(),
                            ""+responseMainBrand.getStoreId());
                }else{
                    callApiReqSample(""+responseMain.getProductdescid(),
                            ""+responseMain.getStoreid());
                }
            }
        });

        ((ProductViewBasedPrice) getActivity()).callApiCount();


        return  view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        current = (getArguments().getString(CURRENT_FRG));

            callApi();

    }

    private void initView(View view) {

        tvProductName = view.findViewById(R.id.tvProductName);
        tvDesc = view.findViewById(R.id.tvDesc);
        tvSellingPrice = view.findViewById(R.id.tvSellingPrice);
        btnAddCart = view.findViewById(R.id.btnAddCart);
        txtCount = view.findViewById(R.id.txtCount);
        imgProduct = view.findViewById(R.id.imgProduct);
        btReqSample = view.findViewById(R.id.btReqSample);
        btnAuctionReq=view.findViewById(R.id.btnAuctionReq);
        txtCountAuc=view.findViewById(R.id.txtCountAuc);
        txtqAu=view.findViewById(R.id.txtqAu);
    }

    private void callApi() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Home/ProductdDescDetails";

        Log.e("URl", "*** " + url);

        String value = (getArguments().getString(FRAG_PARAMETER));

        String productDescId = "0";
        LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        if (current.equalsIgnoreCase(CATG_LIST_FRG)) {
            CategoryModel.ObjproductdetailslistEntity product = new Gson().fromJson(value, CategoryModel.ObjproductdetailslistEntity.class);
            productDescId = "" + product.getProductdescid();
        } else if (current.equalsIgnoreCase(OFFER_FRG)) {
            HomeModel.ObjOfferDetailsListEntity product = new Gson().fromJson(value, HomeModel.ObjOfferDetailsListEntity.class);
            productDescId = "" + product.getProductDescId();
        } else if (current.equalsIgnoreCase(BRAND_FRG)) {
            HomeModel.ObjOfferProdListEntity product = new Gson().fromJson(value, HomeModel.ObjOfferProdListEntity.class);
            productDescId = "" + product.getProductDescId();
        } else if (current.equalsIgnoreCase(SEARCH_FRG)) {
            ProductSearchDetailsModel.ObjproductsearchdetailsEntity product = new Gson().fromJson(value, ProductSearchDetailsModel.ObjproductsearchdetailsEntity.class);
            productDescId = "" + product.getProductdescid();
        }

        Call<ProductdDescDetailsModel> call = RetrofitClient.getAPIInterface().ProductdDescDetails(url, "" + productDescId, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ProductdDescDetailsModel>() {
            @Override
            public void onResponse(int code, ProductdDescDetailsModel response, Headers headers) {
                load.dismissLoading();
                responseMain = response;
                if (response.getResponse().getResponseval()) {

                    if(AUCTION_PRODUCTS.equalsIgnoreCase(response.getCategoryName())){

                        btnAuctionReq.setVisibility(View.VISIBLE);
                        btnAddCart.setVisibility(View.INVISIBLE);

                        txtqAu.setVisibility(View.VISIBLE);
                        txtCountAuc.setVisibility(View.VISIBLE);
                    }else{
                        btnAuctionReq.setVisibility(View.INVISIBLE);
                        btnAddCart.setVisibility(View.VISIBLE);

                        txtqAu.setVisibility(View.GONE);
                        txtCountAuc.setVisibility(View.GONE);

                    }
                    tvProductName.setText(String.format("%s", response.getProductname()));
                    tvDesc.setText(String.format("%s", "Store Name - " + response.getStorename()));
                    tvSellingPrice.setText("" + response.getSellingprice()+"\n"+response.getSubproductqty());

                    GlideApp.with(getActivity())
                            .load(response.getProductimage())
                            .centerInside()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .placeholder(R.mipmap.logo_pink)
                            .error(R.drawable.ic_launcher_background)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imgProduct);


                } else {
                    Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

    private boolean validation() {
        if(txtCount.getText().toString().isEmpty()){
            Toast.makeText(context, "Please Enter Item Number ", Toast.LENGTH_SHORT).show();
            return false;
        }else if(Integer.parseInt(txtCount.getText().toString())==0){
            Toast.makeText(context, "Please Add Item Number ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void callApiReqSample(String ProductId,String StoreId) {

        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Payment/SampleRequest";

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        Call<SampleRequestModel> call = RetrofitClient.getAPIInterface().sampleRequest(url,ProductId,StoreId,""+res.getFullname(),""+res.getEmail(),""+res.getAddress()
                ,""+res.getCity(),""+res.getState(), ""+res.getZipcode(),""+res.getMobile(),
                ""+res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<SampleRequestModel>() {
            @Override
            public void onResponse(int code, SampleRequestModel response, Headers headers) {
                load.dismissLoading();

                Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();

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

    private void callApiReqAuc(String ProductId,String StoreID) {

        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Payment/AuctionRequest";

        Log.e("URl", "*** " + url);
        LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        Call<ActionRequestModel> call = RetrofitClient.getAPIInterface().auctionRequest(url,""+res.getFullname(),
                ""+ProductId,""+StoreID,"0",
                ""+txtCountAuc.getText().toString(),""+txtCount.getText().toString(),""+res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ActionRequestModel>() {
            @Override
            public void onResponse(int code, ActionRequestModel response, Headers headers) {
                load.dismissLoading();

                Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();

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
