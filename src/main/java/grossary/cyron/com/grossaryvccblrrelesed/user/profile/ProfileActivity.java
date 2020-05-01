package grossary.cyron.com.grossaryvccblrrelesed.user.profile;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.PreferenceManager;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;

public class ProfileActivity extends AppCompatActivity {

    private Button btnUpdate;
    private LoadingView load;
    private TextInputEditText etName, etMobile,etMobile2,etMobile3,etMobile4,etMobile5, etEmail, etAddress, etGst, etZip;
    private MaterialSpinner spinner_city,spinner_state;
    private HashMap<String, Integer> hashMap = new HashMap<>();
    private HashMap<String, Integer> hashMapCity = new HashMap<>();
    private List<String> categoriesState = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etName = findViewById(R.id.etName);

        etMobile = findViewById(R.id.etMobile);
        etMobile2 = findViewById(R.id.etMobile2);
        etMobile3 = findViewById(R.id.etMobile3);
        etMobile4 = findViewById(R.id.etMobile4);
        etMobile5 = findViewById(R.id.etMobile5);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etGst = findViewById(R.id.etGst);
        etZip=findViewById(R.id.etZip);

        spinner_state=findViewById(R.id.spinner_state);
        spinner_city=findViewById(R.id.spinner_city);

        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    callApiProfileUpdate();
                }
            }
        });

        spinner_state.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                callApiCity("" + hashMap.get(item), "", true);
            }
        });
        callApiState();
        spinner_city.setItems("Select City");
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
                    callApiProfile();
                } else {
                    Toast.makeText(ProfileActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ProfileActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

    private void callApiProfileUpdate() {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Profile/UpdateProfile";
        LoginModel res = new PreferenceManager(ProfileActivity.this).getLoginModel();

        Log.e("URl", "*** " + url);

        Call<GetUserProfileUpdateModel> call = RetrofitClient.getAPIInterface().getUserProfileUpdate(url,
                "" + res.getUserid(), "" + res.getLoginid(), ""+etName.getText().toString(),
                ""+etMobile.getText().toString(),""+etMobile2.getText().toString(),""+etMobile3.getText().toString(),""+etMobile4.getText().toString(),""+etMobile5.getText().toString(),""+etEmail.getText().toString(),
                ""+etAddress.getText().toString(), ""+etGst.getText().toString(),
                ""+etZip.getText().toString(),"" + hashMapCity.get(spinner_city.getText()), "" + hashMap.get(spinner_state.getText()));
        Request request = new RetrofitRequest<>(call, new ResponseListener<GetUserProfileUpdateModel>() {
            @Override
            public void onResponse(int code, GetUserProfileUpdateModel response, Headers headers) {
                load.dismissLoading();

                if (response.response.responseval) {
                    callApiProfile();

                } else {
                    Toast.makeText(ProfileActivity.this, "" + response.response.reason, Toast.LENGTH_SHORT).show();
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

    private void callApiProfile() {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Profile/GetUserProfile";
        LoginModel res = new PreferenceManager(ProfileActivity.this).getLoginModel();

        Log.e("URl", "*** " + url);
        Call<GetUserProfileModel> call = RetrofitClient.getAPIInterface().getUserProfile(url, "" + res.getUserid());
        Request request = new RetrofitRequest<>(call, new ResponseListener<GetUserProfileModel>() {
            @Override
            public void onResponse(int code, GetUserProfileModel response, Headers headers) {
                load.dismissLoading();

                if (response.getResponse().getResponseval()) {
                    LoginModel res = new PreferenceManager(ProfileActivity.this).getLoginModel();
                    res.setFullname(response.getFullname());
                    res.setMobile(response.getMobileno());
                    res.setEmail(response.getEmail());
                    res.setAddress(response.getAddress());

                    etName.setText("" + response.getFullname());
                    etMobile.setText("" + response.getMobileno());
                    etMobile2.setText("" + response.getMobileno2());
                    etMobile3.setText("" + response.getMobileno3());
                    etMobile4.setText("" + response.getMobileno4());
                    etMobile5.setText("" + response.getMobileno5());
                    etEmail.setText("" + response.getEmail());
                    etAddress.setText("" + response.getAddress());
                    etGst.setText("" + response.getGstnumber());
                    etZip.setText(""+response.getZipcode());
                    Iterator it = hashMap.entrySet().iterator();
                    int pos=0;
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        if (response.getState()
                                .equalsIgnoreCase("" + pair.getValue())) {
                            for (int i = 0; i < categoriesState.size(); i++) {
                                if (categoriesState.get(i).equalsIgnoreCase("" + pair.getKey())) {
                                    pos = i;
                                    i = categoriesState.size();
                                }
                            }
                        }
                    }
                    spinner_state.setSelectedIndex(pos);
                    callApiCity(response.getState(),""+response.getCity(),false);
                } else {
                    Toast.makeText(ProfileActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

        if (TextUtils.isEmpty(etName.getText().toString())) {
            Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(this, "Please Enter Emal-id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etAddress.getText().toString())) {
            Toast.makeText(this, "Please Enter address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etMobile.getText().toString())) {
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etMobile.getText().toString().length() < 10) {
            Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            Toast.makeText(this, "Invalid email Id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etGst.getText().toString())) {
            Toast.makeText(this, "Please Enter GST Number", Toast.LENGTH_SHORT).show();
            return false;
        }else if (etZip.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter Zip Code", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etZip.getText().toString().length()!=6){
            Toast.makeText(this, "Enter Valid Zip Code", Toast.LENGTH_SHORT).show();
            return false;
        } else if (spinner_state.getSelectedIndex() == 0) {
            Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
            return false;
        } else if (spinner_city.getSelectedIndex() == 0) {
            Toast.makeText(this, "Please Select City ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
