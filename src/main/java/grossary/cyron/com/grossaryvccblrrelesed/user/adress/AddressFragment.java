package grossary.cyron.com.grossaryvccblrrelesed.user.adress;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.CityListModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.LoginModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.account.StateListModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.cart.ViewAddtoCartDetailsModel;
import grossary.cyron.com.grossaryvccblrrelesed.user.category.CategoryActivity;
import grossary.cyron.com.grossaryvccblrrelesed.user.payment.SubmitTransactionModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.Constant;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CONSTANT.MAKE_PAYMENT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {


    private LoadingView load;
    private TextInputEditText etAddress, etZip, etPhone;
    private Context context;
    private MaterialSpinner spinner_state, spinner_city, spinner_name;
    private HashMap<String, Integer> hashMap = new HashMap<>();
    private HashMap<String, Integer> hashMapCity = new HashMap<>();
    private BranchDetailsModel responseMain;
    private List<String> categoriesState = new ArrayList<String>();
    private ViewAddtoCartDetailsModel response;

    public AddressFragment() {
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
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        ((CategoryActivity) getActivity()).txtCheckout.setText(MAKE_PAYMENT);
        etAddress = view.findViewById(R.id.etAddress);
        etZip = view.findViewById(R.id.etZip);
        etPhone = view.findViewById(R.id.etPhone);

        spinner_state = view.findViewById(R.id.spinner_state);
        spinner_city = view.findViewById(R.id.spinner_city);
        spinner_name = view.findViewById(R.id.spinner_name);

        spinner_state.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                callApiCity("" + hashMap.get(item), "", true);
            }
        });
        spinner_name.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                selectProfile(position);
            }
        });
        callApiState();
        spinner_city.setItems("Select City");
        return view;
    }

    private void selectProfile(int position) {

        etAddress.setText("" + responseMain.getObjbranchlist().get(position).getAddress());
        etZip.setText("" + responseMain.getObjbranchlist().get(position).getZipcode());
        etPhone.setText("" + responseMain.getObjbranchlist().get(position).getPhone());

        int pos = 0;
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (responseMain.getObjbranchlist().get(position).getState()
                    .equalsIgnoreCase("" + pair.getValue())) {
                callApiCity("" + pair.getValue(), responseMain.getObjbranchlist().get(position).getCity(), false);
                for (int i = 0; i < categoriesState.size(); i++) {
                    if (categoriesState.get(i).equalsIgnoreCase("" + pair.getKey())) {
                        pos = i;
                        i = categoriesState.size();
                    }
                }
            }
        }
        spinner_state.setSelectedIndex(pos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void callApiState() {

        String url = BASE_URL + "/Home/StateList";

        Log.e("URl", "*** " + url);
        Call<StateListModel> call = RetrofitClient.getAPIInterface().stateList(url);
        Request request = new RetrofitRequest<>(call, new ResponseListener<StateListModel>() {
            @Override
            public void onResponse(int code, StateListModel response, Headers headers) {
                if (response.getResponse().getResponseval()) {
                    categoriesState = new ArrayList<String>();
                    categoriesState.add("Select State");
                    hashMap = new HashMap<>();
                    for (int i = 0; i < response.getStatelist().size(); i++) {
                        categoriesState.add("" + response.getStatelist().get(i).getStatename());
                        hashMap.put(response.getStatelist().get(i).getStatename(), response.getStatelist().get(i).getStateid());

                    }
                    spinner_state.setItems(categoriesState);
                    callApiBranchDetails();

                } else {
                    Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("respond", "failure ---->");
            }
        });
        request.enqueue();
    }

    private void callApiCity(String city, final String st, final boolean val) {

        String url = BASE_URL + "/Home/CityList";

        Log.e("URl", "*** " + url);
        Call<CityListModel> call = RetrofitClient.getAPIInterface().cityList(url, city);
        Request request = new RetrofitRequest<>(call, new ResponseListener<CityListModel>() {
            @Override
            public void onResponse(int code, CityListModel response, Headers headers) {
                if (response.getResponse().getResponseval()) {
                    List<String> categories = new ArrayList<String>();
                    categories.add("Select City");
                    hashMapCity = new HashMap<>();
                    int pos = 0;
                    for (int i = 0; i < response.getCitylist().size(); i++) {
                        categories.add("" + response.getCitylist().get(i).getCityname());
                        hashMapCity.put(response.getCitylist().get(i).getCityname(), response.getCitylist().get(i).getCityid());

                        if (!val) {
                            if (st.equalsIgnoreCase("" + response.getCitylist().get(i).getCityid())) {
                                pos = i+1;
                            }
                        }
                    }
                    spinner_city.setItems(categories);
                    if (!val) {
                        spinner_city.setSelectedIndex(pos);
                    }
                } else {
                    Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("respond", "failure ---->");
            }
        });
        request.enqueue();
    }

    private void callApiBranchDetails() {
        load = new LoadingView(getActivity());
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Payment/BranchDetails";
        final LoginModel res = new PreferenceManager(getActivity()).getLoginModel();

        Log.e("URl", "*** " + url);
        Call<BranchDetailsModel> call = RetrofitClient.getAPIInterface().branchDetails(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<BranchDetailsModel>() {
            @Override
            public void onResponse(int code, BranchDetailsModel response, Headers headers) {
                load.dismissLoading();
                responseMain = response;
                if (response.getResponse().getResponseval() && response.getObjbranchlist()!=null && response.getObjbranchlist().size()>0) {

                    List<String> listName=new ArrayList<>();
                    for (int i = 0; i < response.getObjbranchlist().size(); i++) {
                        listName.add(response.getObjbranchlist().get(i).getFullname());
                    }
                    spinner_name.setItems(listName);
                    selectProfile(0);
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

    public void callApiSubmitTransaction() {
        String address = etAddress.getText().toString();
        String zipcode = etZip.getText().toString();
        String phone = etPhone.getText().toString();
        String shippinfCharge = new PreferenceManager(getActivity()).getShippingCharges();
        String totalCharge = new PreferenceManager(getActivity()).getGrandtoal();
        //int totalCharge = response.getGrandtoal();


        if (etAddress.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(context, "Enter Address", Toast.LENGTH_SHORT).show();
        } else if (etZip.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(context, "Enter Zip Code", Toast.LENGTH_SHORT).show();
        }else if(etZip.getText().toString().length()!=6){
            Toast.makeText(context, "Enter Valid Zip Code", Toast.LENGTH_SHORT).show();
        } else if (etPhone.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(context, "Enter Phone", Toast.LENGTH_SHORT).show();
        } else if (spinner_state.getSelectedIndex() == 0) {
            Toast.makeText(context, "Please Select State", Toast.LENGTH_SHORT).show();
        } else if (spinner_city.getSelectedIndex() == 0) {
            Toast.makeText(context, "Please Select City ", Toast.LENGTH_SHORT).show();
        } else {

            load = new LoadingView(getActivity());
            load.setCancalabe(false);
            load.showLoading();

            String url = BASE_URL + "/Home/SubmitTransaction";

            Log.e("URl", "*** " + url);
            final LoginModel res = new PreferenceManager(getActivity()).getLoginModel();
            Call<SubmitTransactionModel> call = RetrofitClient.getAPIInterface().submitTransaction(url,
                    "" + spinner_name.getText(), "" + address, "" + hashMapCity.get(spinner_city.getText()), "" + hashMap.get(spinner_state.getText()),
                    "" + zipcode, "" + phone, "" + res.getUserid(),
                    "" + shippinfCharge, "" + totalCharge,""+ Constant.adminuser);
            Request request = new RetrofitRequest<>(call, new ResponseListener<SubmitTransactionModel>() {
                @Override
                public void onResponse(int code, SubmitTransactionModel response, Headers headers) {
                    load.dismissLoading();
                    if (response.getResponse().getResponseval()) {

                        Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
                        new PreferenceManager(getActivity()).setShippingCharges("0");
                        new PreferenceManager(getActivity()).setGrandtoal("0");
                        new PreferenceManager(getActivity()).setCount("0");
                        showDilogSucess(response);

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
//        else {
//
//            load = new LoadingView(getActivity());
//            load.setCancalabe(false);
//            load.showLoading();
//
//            String url = BASE_URL + "/Payment/PaymentGatewayRequest";
//
//            Log.e("URl", "*** " + url);
//            final LoginModel res = new PreferenceManager(getActivity()).getLoginModel();
//            Call<PaymentGatewayRequestModel> call = RetrofitClient.getAPIInterface().paymentGatewayRequest(url, fullName, address, city, state, zipcode, phone
//                    , "" + res.getUserid(), paymode, shippinfCharge, totalCharge);
//            Request request = new RetrofitRequest<>(call, new ResponseListener<PaymentGatewayRequestModel>() {
//                @Override
//                public void onResponse(int code, PaymentGatewayRequestModel response, Headers headers) {
//                    load.dismissLoading();
//                    if (response.getResponse().getResponseVal()) {
//
//                        String tempurl = response.getGatewayURL();
//                        openDilog(tempurl, getActivity());
//
//                    } else {
//                        Toast.makeText(getActivity(), "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onError(int error) {
//                    load.dismissLoading();
//
//                }
//
//                @Override
//                public void onFailure(Throwable throwable) {
//                    Log.e("respond", "failure ---->");
//                    load.dismissLoading();
//                }
//            });
//            request.enqueue();
//        }
    }

    private void showDilogSucess(SubmitTransactionModel response) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dilog_payment_sucess);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.setCancelable(false);
        dialog.show();

        Button btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                getActivity().finish();
            }
        });

        TextView txtTotalAmount = dialog.findViewById(R.id.txtTotalAmount);
        txtTotalAmount.setText("Total Amount : ₹" + response.getTotalamount());
        TextView txtTranDate = dialog.findViewById(R.id.txtTranDate);
        txtTranDate.setText("Transation Date : " + response.getTransactiondate());
        TextView txtTransNo = dialog.findViewById(R.id.txtTransNo);
        txtTransNo.setText("Order Number : " + response.getTranno());


    }

    public void openDilog(String url, final Context context) {

        Log.e("URl", "***Dilog " + url);

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dilog_web);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        dialog.setCancelable(false);
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        ImageView img_close = dialog.findViewById(R.id.img_close);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        WebView webView = dialog.findViewById(R.id.webview);
        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Loading...", true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
        });

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
                dialog.show();
                //Toast.makeText(context, "Page Load Finished", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressDialog.dismiss();
                dialog.show();
            }
        });


    }


}
