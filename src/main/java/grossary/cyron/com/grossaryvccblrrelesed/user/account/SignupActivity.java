package grossary.cyron.com.grossaryvccblrrelesed.user.account;

import android.Manifest;
import android.app.Dialog;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.utility.LoadingView;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitClient;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.RetrofitRequest;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.Request;
import grossary.cyron.com.grossaryvccblrrelesed.utility.retrofit.callbacks.ResponseListener;
import okhttp3.Headers;
import retrofit2.Call;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.URL.BASE_URL;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Util.validatePassword;

public class SignupActivity extends AppCompatActivity {

    private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private final static int PERMISSION_REQUEST_READ_SMS = 100;
    private SMSReceiver smsReceiver;
    private static final String SMS_ORIGIN_GROSSARY = "PLATRD";
    public static final String OTP_DELIMITER = "OTP Code:";
    private MaterialSpinner spinner_state,spinner_city;
    private Button btn_register;
    private EditText etUserName,etFullName,etPassword,etConfirmPassword, etMobile,etMobile2,etMobile3,etMobile4,etMobile5,etkt,etEmail, etAddress, etGst,etZip,etrefname1,etrefname2,etrefmobnumber1,etrefmobnumber2;
    private LoadingView load;
    private Dialog dialog;
    private HashMap<String,Integer> hashMap=new HashMap<>();
    private HashMap<String,Integer> hashMapCity=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUserName = findViewById(R.id.etUserName);
        etFullName = findViewById(R.id.etFullName);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etMobile = findViewById(R.id.etMobile);
        etMobile2 = findViewById(R.id.etMobile2);
        etMobile3 = findViewById(R.id.etMobile3);
        etMobile4 = findViewById(R.id.etMobile4);
        etMobile5 = findViewById(R.id.etMobile5);




        etkt = findViewById(R.id.etkt);
        etrefname1 = findViewById(R.id.etrefname1);
        etrefname2 = findViewById(R.id.etrefname2);
        etrefmobnumber1 = findViewById(R.id.etrefmobnumber1);
        etrefmobnumber2 = findViewById(R.id.etrefmobnumber2);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etGst = findViewById(R.id.etGst);
        etZip=findViewById(R.id.etZip);

        spinner_state=findViewById(R.id.spinner_state);
        spinner_city=findViewById(R.id.spinner_city);

        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                   String Password = etPassword.getText().toString();
                   String ConfirmPassword = etConfirmPassword.getText().toString();
                   if(Password.equalsIgnoreCase(ConfirmPassword)) {
                       callApiRegister();
                       }
                   else{
                       Toast.makeText(SignupActivity.this, "Please Enter Same Password" , Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
        spinner_state.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                callApiCity(""+hashMap.get(item));
            }
        });
        chaeckPermission();
        callApiState();
        spinner_city.setItems("Select City");


    }
    private void unregisterSMSReceiver() {
        if (smsReceiver == null)
            return;
        this.unregisterReceiver(smsReceiver);
    }


    private void registerSMSReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SMS_RECEIVED);
        intentFilter.setPriority(999);
        this.registerReceiver(smsReceiver = createReceiver(), intentFilter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registerSMSReceiver();
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                }
                return;
            }

        }
    }

    private SMSReceiver createReceiver() {
        return new SMSReceiver(new SMSReceiver.OnMessageListener() {
            @Override
            public void onMessage(String from, String text) {
                if (!from.toLowerCase().contains(SMS_ORIGIN_GROSSARY.toLowerCase()))
                    return;
                String code = null;
                int index = text.indexOf(OTP_DELIMITER) + 1;
                if (index != -1 && ((index + 7) < text.length())) {
                    int start = index + OTP_DELIMITER.length();
                    int length = text.length();
                    code = text.substring(start, length);
                    callApiOtpVerify(code);

                }
            }
        });
    }

    private void openDilogOtp() {
        dialog = new Dialog(SignupActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dilog_otp);
        dialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.show();
        final PinEntryEditText pin=dialog.findViewById(R.id.pin);

        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button btn_retry = dialog.findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pin.setText("");
                callApiRetry();
            }
        });
        pin.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                if(str!=null && str.length()==6){
                    callApiOtpVerify(str.toString());
                }
            }
        });

    }

    private void callApiCity(String city){

        String url = BASE_URL + "/Home/CityList";

        Log.e("URl", "*** " + url);
        Call<CityListModel> call = RetrofitClient.getAPIInterface().cityList(url,city);
        Request request = new RetrofitRequest<>(call, new ResponseListener<CityListModel>() {
            @Override
            public void onResponse(int code, CityListModel response, Headers headers) {
                if (response.getResponse().getResponseval()) {
                    List<String> categories = new ArrayList<String>();
                    categories.add("Select City");
                    hashMapCity=new HashMap<>();
                    for(int i=0;i<response.getCitylist().size();i++){
                        categories.add(""+response.getCitylist().get(i).getCityname());
                        hashMapCity.put(response.getCitylist().get(i).getCityname(),response.getCitylist().get(i).getCityid());

                    }
                    spinner_city.setItems(categories);
                }else{
                    Toast.makeText(SignupActivity.this, ""+response.getResponse().getReason() , Toast.LENGTH_SHORT).show();
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


    private void callApiState(){

        String url = BASE_URL + "/Home/StateList";

        Log.e("URl", "*** " + url);
        Call<StateListModel> call = RetrofitClient.getAPIInterface().stateList(url);
        Request request = new RetrofitRequest<>(call, new ResponseListener<StateListModel>() {
            @Override
            public void onResponse(int code, StateListModel response, Headers headers) {
                if (response.getResponse().getResponseval()) {
                    List<String> categories = new ArrayList<String>();
                    categories.add("Select State");
                    hashMap=new HashMap<>();
                    for(int i=0;i<response.getStatelist().size();i++){
                        categories.add(""+response.getStatelist().get(i).getStatename());
                        hashMap.put(response.getStatelist().get(i).getStatename(),response.getStatelist().get(i).getStateid());

                    }
                    spinner_state.setItems(categories);
                }else{
                    Toast.makeText(SignupActivity.this, ""+response.getResponse().getReason() , Toast.LENGTH_SHORT).show();
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

    private void callApiRetry() {

        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Login/ResendOTP";

        Log.e("URl", "*** " + url);
        Call<ResendOTPModel> call = RetrofitClient.getAPIInterface().resendOTP(url, etMobile.getText().toString());
        Request request = new RetrofitRequest<>(call, new ResponseListener<ResendOTPModel>() {
            @Override
            public void onResponse(int code, ResendOTPModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    Toast.makeText(SignupActivity.this, "OTP send" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignupActivity.this, ""+response.getResponse().getReason() , Toast.LENGTH_SHORT).show();
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

    private void callApiOtpVerify(String otp) {
        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Login/VerifyRegisterOTP";

        Log.e("URl", "*** " + url);
        Call<VerifyRegisterOTPModel> call = RetrofitClient.getAPIInterface().verifyRegisterOTP(url, etMobile.getText().toString(),otp);
        Request request = new RetrofitRequest<>(call, new ResponseListener<VerifyRegisterOTPModel>() {
            @Override
            public void onResponse(int code, VerifyRegisterOTPModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().getResponseval()) {
                    dialog.dismiss();
                    finish();
                }else{
                    Toast.makeText(SignupActivity.this, ""+response.getResponse().getReason() , Toast.LENGTH_SHORT).show();
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

    private void callApiRegister() {

        load = new LoadingView(this);
        load.setCancalabe(false);
        load.showLoading();
        String url = BASE_URL + "/Login/Register";

        Log.e("URl", "*** " + url);
        Call<RegisterModel> call = RetrofitClient.getAPIInterface().register(url, etUserName.getText().toString(),
                etPassword.getText().toString(), etAddress.getText().toString(), etMobile.getText().toString(),etMobile2.getText().toString(),etMobile3.getText().toString(),etMobile4.getText().toString(),etMobile5.getText().toString(),
                etEmail.getText().toString(), etGst.getText().toString(),etZip.getText().toString(),
                ""+hashMap.get(spinner_state.getText()),""+hashMapCity.get(spinner_city.getText()),etFullName.getText().toString(),etkt.getText().toString(),etrefname1.getText().toString(),etrefmobnumber1.getText().toString(),etrefname2.getText().toString(),etrefmobnumber2.getText().toString());
        Request request = new RetrofitRequest<>(call, new ResponseListener<RegisterModel>() {
            @Override
            public void onResponse(int code, RegisterModel response, Headers headers) {
                load.dismissLoading();
                if (response.getResponse().isResponseval()) {
                    openDilogOtp();
                }else{
                    Toast.makeText(SignupActivity.this, "" + response.getResponse().getReason(), Toast.LENGTH_SHORT).show();
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

        if (TextUtils.isEmpty(etUserName.getText().toString())) {
            Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (TextUtils.isEmpty(etFullName.getText().toString())) {
            Toast.makeText(this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!validatePassword(etPassword.getText().toString())) {
            Toast.makeText(this, "Password must contain atleast 1 lower case, 1 upper case, 1 number & 1 special character", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etMobile.getText().toString())) {
            Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etMobile.getText().toString().length() < 10) {
            Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        } /*else if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(this, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()) {
            Toast.makeText(this, "Invalid email Id", Toast.LENGTH_SHORT).show();
            return false;
        }*/ else if (TextUtils.isEmpty(etAddress.getText().toString())) {
            Toast.makeText(this, "Please Enter Address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(etGst.getText().toString())) {
            Toast.makeText(this, "Please Enter GST Number", Toast.LENGTH_SHORT).show();
            return false;
        }/*else if(etGst.getText().toString().length()!=15){
            Toast.makeText(this, "Please Enter Valid GST Number", Toast.LENGTH_SHORT).show();
            return false;
        }*/else if (TextUtils.isEmpty(etZip.getText().toString())) {
            Toast.makeText(this, "Please Enter Zip Code", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etZip.getText().toString().length()!=6){
            Toast.makeText(this, "Please Enter Valid Zip Code", Toast.LENGTH_SHORT).show();
            return false;
        }else if (spinner_state.getSelectedIndex()==0) {
            Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
            return false;
        }else if (spinner_city.getSelectedIndex()==0) {
            Toast.makeText(this, "Please Select City ", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        chaeckPermission();

    }

    private void chaeckPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_SMS)) {


            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_SMS},
                        PERMISSION_REQUEST_READ_SMS);

            }
        } else {
            registerSMSReceiver();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterSMSReceiver();

    }

}
