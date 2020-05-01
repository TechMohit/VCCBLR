package grossary.cyron.com.grossaryvccblrrelesed.admin.drawer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.Constant;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.NAV_DRAWER.MY_HOME;

public class AdminDrawerFragment extends Fragment implements OnItemClickListener {

    private AdminDrawerAdapter adapter = null;
    private DrawerListener drawerListener;
    private RecyclerView recyclerView;
    private TextView navName,navEmailId;

    public AdminDrawerFragment() {
        // Required empty public constructor
    }

    public interface DrawerListener {
        void drawerOnItemClicked(String tag);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        drawerListener = (DrawerListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_drawer, container, false);
        initView(view);
        setValue();

        LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        navName.setText(""+res.getFullname());
        navEmailId.setText(""+res.getEmail());

        return view;
    }

    private void initView(View view) {
        recyclerView=view.findViewById(R.id.recycler_view);
        navEmailId=view.findViewById(R.id.navEmailId);
        navName=view.findViewById(R.id.navName);

    }

    private void setValue() {
        List<AdminDrawerItem> newsList = new ArrayList<>();

        AdminDrawerItem drawerItem = new AdminDrawerItem();
        drawerItem.setText("Home");
        drawerItem.setTag(MY_HOME);
        drawerItem.setIcon(R.mipmap.home_icon_pink);
        drawerItem.setIconUnSelect(R.mipmap.home_icon);
        newsList.add(drawerItem);


        AdminDrawerItem drawerItem3 = new AdminDrawerItem();
        drawerItem3.setText("Terms & Condition");
        drawerItem3.setTag("TERMS_&_CONDITION");
        drawerItem3.setIcon(R.mipmap.terms_pink);
        drawerItem3.setIconUnSelect(R.mipmap.terms);
        newsList.add(drawerItem3);

        AdminDrawerItem drawerItem4 = new AdminDrawerItem();
        drawerItem4.setText("Privacy Policy");
        drawerItem4.setTag(Constant.NAV_DRAWER.PRIVICY);
        drawerItem4.setIcon(R.mipmap.keyhole_pink);
        drawerItem4.setIconUnSelect(R.mipmap.keyhole);
        newsList.add(drawerItem4);

        AdminDrawerItem drawerItem5 = new AdminDrawerItem();
        drawerItem5.setText("About Us");
        drawerItem5.setTag(Constant.NAV_DRAWER.ABOUT);
        drawerItem5.setIcon(R.mipmap.online_shop_pink);
        drawerItem5.setIconUnSelect(R.mipmap.online_shop);
        newsList.add(drawerItem5);

        AdminDrawerItem drawerItem6 = new AdminDrawerItem();
        drawerItem6.setText("Place Order");
        drawerItem6.setTag(Constant.NAV_DRAWER.LOADUSER);
        drawerItem6.setIcon(R.mipmap.logout_pink);
        drawerItem6.setIconUnSelect(R.mipmap.logout);
        newsList.add(drawerItem6);

        AdminDrawerItem drawerItem9 = new AdminDrawerItem();
        drawerItem9.setText("Payment Collection");
        drawerItem9.setTag(Constant.NAV_DRAWER.PAYMENTCOLLECTION);
        drawerItem9.setIcon(R.mipmap.logout_pink);
        drawerItem9.setIconUnSelect(R.mipmap.logout);
        newsList.add(drawerItem9);

        AdminDrawerItem drawerItem8 = new AdminDrawerItem();
        drawerItem8.setText("Zero Stock Details");
        drawerItem8.setTag(Constant.NAV_DRAWER.ZERO_STOCK);
        drawerItem8.setIcon(R.mipmap.online_shop_pink);
        drawerItem8.setIconUnSelect(R.mipmap.online_shop);
        newsList.add(drawerItem8);

        AdminDrawerItem drawerItem7 = new AdminDrawerItem();
        drawerItem7.setText("Log Out");
        drawerItem7.setTag(Constant.NAV_DRAWER.LOG_OUT);
        drawerItem7.setIcon(R.mipmap.logout_pink);
        drawerItem7.setIconUnSelect(R.mipmap.logout);
        newsList.add(drawerItem7);
        
        adapter = new AdminDrawerAdapter(getActivity(), newsList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Object o, View view, int position, String type) {
        drawerListener.drawerOnItemClicked(((AdminDrawerItem) o).getTag());
        adapter.selectedPosition(position);
    }

    public void setHighlitedPosition(int position) {
        if (adapter != null)
            adapter.selectedPosition(position);
    }

    public void focus() {
        if (recyclerView != null && adapter !=null)
            recyclerView.smoothScrollToPosition(adapter.selectedPosition);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
