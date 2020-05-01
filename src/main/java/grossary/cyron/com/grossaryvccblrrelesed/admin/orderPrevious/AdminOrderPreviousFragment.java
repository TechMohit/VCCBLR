package grossary.cyron.com.grossaryvccblrrelesed.admin.orderPrevious;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.admin.AdminHomeActivity;
import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.admin.preOrderDetail.AdminPreOrderDetailActivity;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.ADMIN_KEY_NAME.ACT_ORDER_DETAIL;


public class AdminOrderPreviousFragment extends Fragment implements OnItemClickListener<AdminHomeModel.OrderperviousmodelEntity> {

    private ArrayList<AdminHomeModel.OrderperviousmodelEntity> homeList = new ArrayList<AdminHomeModel.OrderperviousmodelEntity>();
    private AdminOrderPreviousAdapter adapter;
    private RecyclerView recyclerView;

    public AdminOrderPreviousFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_pre_order, container, false);
        initView(view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        setAdapter();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(adapter.getItemCount()<=0)
        {
            if(((AdminHomeActivity)getActivity()).getAdminHomeModel()!=null)
            setData(((AdminHomeActivity)getActivity()).getAdminHomeModel().getOrderperviousmodel());
        }
    }

    private void setAdapter() {
        adapter = new AdminOrderPreviousAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycle_view);

    }
    

    public void setData(List<AdminHomeModel.OrderperviousmodelEntity> data) {

        if(adapter==null || data==null)
            return;
        
        if(homeList.size()>0)
            homeList.clear();
        homeList.addAll(data);
        adapter.setAdapterData(homeList);
        
    }

    public List<AdminHomeModel.OrderperviousmodelEntity> getData() {
        return homeList;
    }

    @Override
    public void onItemClick(AdminHomeModel.OrderperviousmodelEntity objstoredetailslist, View view, int position, String type) {

        Intent intent=new Intent(getActivity(),AdminPreOrderDetailActivity.class);
        intent.putExtra(ACT_ORDER_DETAIL,new Gson().toJson(objstoredetailslist));
        startActivity(intent);

    }
}
