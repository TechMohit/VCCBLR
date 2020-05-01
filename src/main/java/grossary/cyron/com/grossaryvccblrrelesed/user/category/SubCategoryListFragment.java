package grossary.cyron.com.grossaryvccblrrelesed.user.category;


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
import grossary.cyron.com.grossaryvccblrrelesed.user.home.HomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.LIST;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ONCLICK;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.CHECKOUT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CURRENT_STATE.CATG_LIST_FRG;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.KEY_NAME.FRAG_PARAMETER;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryListFragment extends Fragment implements OnItemClickListener<SubCategoryModel.ObjcategorylistEntity> {

    private RecyclerView recyclerView;
    private SubCategoryListAdapter adapter;
    private LoadingView load;
    private Context context;
    public SubCategoryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_category_list, container, false);
        initView(view);

        ((CategoryActivity)getActivity()).txtCheckout.setText(CHECKOUT);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setAdapter();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            callApi();
        ((CategoryActivity)getActivity()).callApiCount();

    }


    private void callApi() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Home/SubCategoryDetails";

        Log.e("URl", "*** " + url);
        String value=(getArguments().getString(FRAG_PARAMETER));
        HomeModel.ObjCategoryListEntity objstoredetailslist = new Gson().fromJson(value, HomeModel.ObjCategoryListEntity.class);

        Call<SubCategoryModel> call = RetrofitClient.getAPIInterface().subProductDetails(url,""+ objstoredetailslist.getCatergoryId());
        Request request = new RetrofitRequest<>(call, new ResponseListener<SubCategoryModel>() {
            @Override
            public void onResponse(int code, SubCategoryModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {
                    adapter.setAdapterData(response.getObjcategorylist());
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

    private void setAdapter() {
        adapter = new SubCategoryListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycle_view);

    }

    @Override
    public void onItemClick(SubCategoryModel.ObjcategorylistEntity categoryModel, View view, int position,String type) {

        if(type.equalsIgnoreCase(ONCLICK)) {
            ((CategoryActivity) getActivity()).selectFrag(LIST, new Gson().toJson(categoryModel), CATG_LIST_FRG);
        }

    }

}
