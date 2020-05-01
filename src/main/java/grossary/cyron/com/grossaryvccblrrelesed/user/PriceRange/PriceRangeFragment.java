package grossary.cyron.com.grossaryvccblrrelesed.user.PriceRange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import grossary.cyron.com.grossaryvccblrrelesed.R;


public class PriceRangeFragment extends Fragment {


    private Spinner minprice,maxprice;
    private String[] SPINNERMINPRICELIST = {"1000", "2000", "3000", "4000","5000","6000", "7000", "8000", "9000","10000","11000", "12000", "13000", "14000","15000","16000", "17000", "18000", "19000","20000"};
    private String[] SPINNERMAXPRICELIST = {"2000", "3000", "4000","6000","7000", "8000", "9000", "10000","11000","12000", "13000", "14000", "15000","16000","17000", "18000", "19000", "20000","21000"};
    private String minPriceSelect = "";
    private int minPriceSelectint = 0;
    private String maxPriceSelect = "";
    private int maxPriceSelectint = 0;
    private EditText etminPrice,etmaxPrice;
    Button Submit;




    public PriceRangeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_price_range, container, false);
        initViews(view);

   /*     minprice.setPrompt("Min Price");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, SPINNERMINPRICELIST);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        minprice.setAdapter(dataAdapter);

        maxprice.setPrompt("Max Price");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, SPINNERMAXPRICELIST);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        maxprice.setAdapter(dataAdapter1);

        minprice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minPriceSelect = parent.getItemAtPosition(position).toString();
                minPriceSelectint = Integer.parseInt(minPriceSelect);
                Log.d("minPriceSelect", minPriceSelect);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        maxprice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maxPriceSelect = parent.getItemAtPosition(position).toString();
                maxPriceSelectint = Integer.parseInt(maxPriceSelect);
                Log.d("maxPriceSelect", maxPriceSelect);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Username", minPriceSelect);
                Log.d("Username", maxPriceSelect);
                if (validation()) {
                    minPriceSelect = etminPrice.getText().toString();
                    maxPriceSelect = etmaxPrice.getText().toString();
                    minPriceSelectint = Integer.parseInt(minPriceSelect);
                    maxPriceSelectint = Integer.parseInt(maxPriceSelect);
                    if (maxPriceSelectint <= minPriceSelectint) {
                        Toast.makeText(getActivity(), "MaxPrice should be greater than MinPrice", Toast.LENGTH_LONG).show();

                    } else {
                        ProductLoad(minPriceSelect, maxPriceSelect);
                    }
                }
            }
        });
        return view;
    }




    private void initViews(View view) {
      /*  minprice = view.findViewById(R.id.spin_minprice);
        maxprice = view.findViewById(R.id.spin_maxprice);*/
        Submit = view.findViewById(R.id.search);
        etminPrice = view.findViewById(R.id.etMin);
        etmaxPrice = view.findViewById(R.id.etMax);

    }

    private void ProductLoad(String minPriceSelect, String maxPriceSelect) {
        Log.d("ProductLoad","ProductLoad method");
        Intent intent=new Intent(getActivity(), ProductViewBasedPrice.class);
        intent.putExtra("minPriceSelectvalue",minPriceSelect);
        intent.putExtra("maxPriceSelectvalue",maxPriceSelect);
        startActivity(intent);
    }

    private boolean validation(){
        if (TextUtils.isEmpty(etminPrice.getText().toString())) {
            Toast.makeText(getActivity(), "Please Enter Minimum Price", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (TextUtils.isEmpty(etmaxPrice.getText().toString())) {
            Toast.makeText(getActivity(), "Please Enter Maximum Price", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
