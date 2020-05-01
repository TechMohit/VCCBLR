package grossary.cyron.com.grossaryvccblrrelesed.user.PriceRange;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.CategoryListAdapter;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.CategoryModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.LIST_DETAILS;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ONCLICK;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.CHECKOUT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.CATG_LIST_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;

public class ProductPriceFragment extends Fragment implements OnItemClickListener<CategoryModel.ObjproductdetailslistEntity> {

    private RecyclerView recyclerView;
    private CategoryListAdapter adapter;
    private LoadingView load;
    private Context context;
    String minPriceSelectvalue;
    String maxPriceSelectvalue;
    String tag;


    public ProductPriceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_price, container, false);
        initView(view);

        ((ProductViewBasedPrice)getActivity()).txtCheckout.setText(CHECKOUT);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        setAdapter();
        return  view;
    }

    private void setAdapter() {
        adapter = new CategoryListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         minPriceSelectvalue = (getArguments().getString("minPriceSelectvalue"));
         maxPriceSelectvalue = (getArguments().getString("maxPriceSelectvalue"));
        callApi();
        ((ProductViewBasedPrice)getActivity()).callApiCount();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);
    }

    private void callApi() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Home/ProductRangeDetails";

        Log.e("URl", "*** " + url);

        LoginModel res = new PreferenceManager(getActivity()).getLoginModel();


        Call<CategoryModel> call = RetrofitClient.getAPIInterface().productRangeDetails(url, minPriceSelectvalue,maxPriceSelectvalue,""+res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<CategoryModel>() {
            @Override
            public void onResponse(int code, CategoryModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    adapter.setAdapterData(response.getObjproductdetailslist());

                }else{
                    Toast.makeText(getActivity(), ""+response.getResponse().getReason() , Toast.LENGTH_SHORT).show();
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


    @Override
    public void onItemClick(CategoryModel.ObjproductdetailslistEntity categoryModel, View view, int position,String type) {

        if(type.equalsIgnoreCase(ONCLICK)) {
            ((ProductViewBasedPrice) getActivity()).selectFrag(LIST_DETAILS, new Gson().toJson(categoryModel), CATG_LIST_FRG);

        }

    }
}
