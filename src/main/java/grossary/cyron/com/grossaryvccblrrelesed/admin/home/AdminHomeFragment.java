package grossary.cyron.com.grossaryvccblrrelesed.admin.home;

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
import grossary.cyron.com.grossaryvccblrrelesed.admin.stock.AdminStockActivity;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.ADMIN_KEY_NAME.ACT_STOCK_DETAIL;


public class AdminHomeFragment extends Fragment implements OnItemClickListener<AdminHomeModel.ProductlistEntity> {

    private ArrayList<AdminHomeModel.ProductlistEntity> homeList = new ArrayList<>();
    private AdminHomeListAdapter adapter;
    private RecyclerView recyclerView;

    public AdminHomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        initView(view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        setAdapter();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(adapter.getItemCount()<=0)
        {
            if(((AdminHomeActivity)getActivity()).getAdminHomeModel()!=null)
            setData(((AdminHomeActivity)getActivity()).getAdminHomeModel().getProductlist());
        }
    }

    private void setAdapter() {
        adapter = new AdminHomeListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.recycle_view);

    }
    

    public void setData(List<AdminHomeModel.ProductlistEntity> data) {

        if(adapter==null || data==null)
            return;
        
        if(homeList.size()>0)
            homeList.clear();
        homeList.addAll(data);
        adapter.setAdapterData(homeList);
        
    }

    public List<AdminHomeModel.ProductlistEntity> getData() {
        return homeList;
    }

    @Override
    public void onItemClick(AdminHomeModel.ProductlistEntity objstoredetailslist, View view, int position, String type) {

        Intent intent=new Intent(getActivity(),AdminStockActivity.class);
        intent.putExtra(ACT_STOCK_DETAIL,new Gson().toJson(objstoredetailslist));
        startActivity(intent);


    }
}
