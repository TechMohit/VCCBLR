package grossary.cyron.com.grossaryvccblrrelesed.user.cart;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.CategoryActivity;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.UpdateAddToCartDiscountModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ADD;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.DELETE;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.UPDATE_DISCOUNT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.UPDATE_ORDER_TYPE;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.PLACE_YOUR_ORDER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCartFragment extends Fragment implements OnItemClickListener<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> {

    private RecyclerView recyclerView;
    private ViewCartAdapter adapter;
    private LoadingView load;
    private Context context;
    private ArrayList<ViewCartModel> list = new ArrayList<>();

    private HashMap<String, Integer> hashMapPay = new HashMap<>();

    public ViewCartFragment() {
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
        // Inflate the layout for getActivity() fragment
        View view = inflater.inflate(R.layout.fragment_view_cart, container, false);
        initView(view);

        ((CategoryActivity) getActivity()).txtCheckout.setText(PLACE_YOUR_ORDER);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        callApiViewCart();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);

    }

    private void callApiViewCart() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/ViewAddtoCartDetails";
        final LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        Log.e("URl", "*** " + url);

        Call<ViewAddtoCartDetailsModel> call = RetrofitClient.getAPIInterface().viewAddtoCartDetails(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ViewAddtoCartDetailsModel>() {
            @Override
            public void onResponse(int code, ViewAddtoCartDetailsModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {

                    ((CategoryActivity) getActivity()).callApiCount();


                    adapter.setAdapterData(response.getObjviewaddcartlist().getListaddtocartviewmodel(), response,
                            response.getObjviewaddcartlist().getDiscounttype(),response.getObjviewaddcartlist().getPaymode());
                    new PreferenceManager(getActivity()).setShippingCharges("" + response.getTotalshippingcharges());

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

    private void setAdapter() {
        adapter = new ViewCartAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity obj, View view, int position, String type) {

        if (type.equalsIgnoreCase(DELETE)) {
            callApiDeleteCart(obj);
        } else if (type.equalsIgnoreCase(ADD)) {
            callApiUpdateValue(obj);
        }else if (type.equalsIgnoreCase(UPDATE_ORDER_TYPE)
                ||type.equalsIgnoreCase(UPDATE_DISCOUNT)
                ) {
//            Toast.makeText(context, "  "+obj.getSelectedpaymode()+"  "+ obj.getDiscountypeid(), Toast.LENGTH_SHORT).show();
            callApiUpdateAppToDis(obj);
        }

    }

    private void callApiUpdateAppToDis(ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity obj) {

        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/UpdateAddToCartDiscount";

        Log.e("URl", "*** " + url);

        Call<UpdateAddToCartDiscountModel> call = RetrofitClient.getAPIInterface().updateAddToCartDiscount(url,
                "" + obj.getOrderid(), "" + obj.getDiscountypeid(),
                ""+obj.getSelectedpaymode(),""+obj.getRemarks());
        Request request = new RetrofitRequest<>(call, new ResponseListener<UpdateAddToCartDiscountModel>() {
            @Override
            public void onResponse(int code, UpdateAddToCartDiscountModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {
                    Toast.makeText(getActivity(), "" + response.getStatusmessage(), Toast.LENGTH_SHORT).show();

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

    private void callApiUpdateValue(ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity obj) {

        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/UpdateFromCartDetails";

        Log.e("URl", "*** " + url);

        Call<UpdateFromCartDetailsModel> call = RetrofitClient.getAPIInterface().updateFromCartDetails(url,
                "" + obj.getOrderid(), "" + obj.getUnitqty());
        Request request = new RetrofitRequest<>(call, new ResponseListener<UpdateFromCartDetailsModel>() {
            @Override
            public void onResponse(int code, UpdateFromCartDetailsModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {
                    callApiViewCart();
                    Toast.makeText(getActivity(), "" + response.getStatusmessage(), Toast.LENGTH_SHORT).show();

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

    private void callApiDeleteCart(ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity obj) {
        if (load != null) {
            load.dismissLoading();
        }
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/ShoppingCart/DeleteFromCartDetails";

        Log.e("URl", "*** " + url);

        Call<DeleteFromCartDetailsModel> call = RetrofitClient.getAPIInterface().deleteFromCartDetails(url, "" + obj.getOrderid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<DeleteFromCartDetailsModel>() {
            @Override
            public void onResponse(int code, DeleteFromCartDetailsModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {
                    callApiViewCart();
                    Toast.makeText(getActivity(), "" + response.getStatusmessage(), Toast.LENGTH_SHORT).show();

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

    public boolean validate() {
        List<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> dataSet=adapter.getClassResult();
        if(dataSet!=null && dataSet.size()>0){
            for (int i=0;i<dataSet.size();i++){
                if(dataSet.get(i).getDiscountypeid()==0){
                    return false;
                }
            }
        }
        return true;
    }

    public List<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> getAdapter(){
     return adapter.getClassResult();
    }
}
