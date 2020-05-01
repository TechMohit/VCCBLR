package grossary.cyron.com.grossaryvccblrrelesed.admin.stock;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;


public class AdminEditStockDetailsdapter extends RecyclerView.Adapter {

    private List<EditStockProductDetailsModel.ProductrangelistEntity> dataSet=new ArrayList<>();
    private Activity activity;

    public AdminEditStockDetailsdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        holder.setIsRecyclable(false);
        final EditStockProductDetailsModel.ProductrangelistEntity object = dataSet.get(listPosition);
        ((ImageTypeViewHolder)holder).tvName.setText(""+object.getRatename());
        ((ImageTypeViewHolder)holder).spinner.setItems("Builty","Delivery");
        ((ImageTypeViewHolder)holder).etVal.setText(""+object.getSellingprice());
        if(object.getTypename().equalsIgnoreCase("Delivery")){
            ((ImageTypeViewHolder)holder).spinner.setSelectedIndex(1);
        }

        ((ImageTypeViewHolder) holder).spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                dataSet.get(listPosition).setTypename(item);
            }
        });
        ((ImageTypeViewHolder) holder).etVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s!=null && s.toString().length()>0) {
                    dataSet.get(listPosition).setSellingprice(Integer.parseInt(s.toString()));
                }else{
                    dataSet.get(listPosition).setSellingprice(0);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list_item_edit_stock_detail, parent, false);
            return new ImageTypeViewHolder(view);
    }

    public List<EditStockProductDetailsModel.ProductrangelistEntity> getList(){

        return dataSet;
    }
    @Override
    public int getItemCount() {
        if (dataSet == null && (dataSet.size()==0))
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(List<EditStockProductDetailsModel.ProductrangelistEntity> sellerList) {
        dataSet = sellerList;
        notifyDataSetChanged();
    }
    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        public MaterialSpinner spinner;
        public TextView tvName;
        public EditText etVal;
        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            spinner=itemView.findViewById(R.id.spinner);
            tvName=itemView.findViewById(R.id.tvName);
            etVal=itemView.findViewById(R.id.etVal);
        }
    }

}