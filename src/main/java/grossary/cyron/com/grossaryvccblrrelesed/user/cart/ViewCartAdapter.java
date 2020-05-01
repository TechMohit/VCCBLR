package grossary.cyron.com.grossaryvccblrrelesed.user.cart;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.utility.GlideApp;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ADD;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.DELETE;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.UPDATE_DISCOUNT;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.UPDATE_ORDER_TYPE;


public class ViewCartAdapter extends RecyclerView.Adapter {

    private List<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> clickListener;
    private ViewAddtoCartDetailsModel response;
    private  List<ViewAddtoCartDetailsModel.DiscounttypeEntity> discounttype=new ArrayList<>();
    private List<ViewAddtoCartDetailsModel.PaymodeEntity> paymode=new ArrayList<>();

    public ViewCartAdapter(Activity activity, OnItemClickListener<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    public List<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> getClassResult() {
        return dataSet;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        if (listPosition == dataSet.size()) {
            ((LastTypeViewHolder) holder).tvTotal.setText("₹" + response.getGrandtoal());

        } else {

            holder.setIsRecyclable(false);

            final ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity object = dataSet.get(listPosition);
            ((ImageTypeViewHolder) holder).tvProductName.setText(String.format("%s", object.getProductname()));
            ((ImageTypeViewHolder) holder).tvPrice.setText("₹" + String.format("%s", object.getSellingprice()));

            //UnitQty in spinner
            ((ImageTypeViewHolder) holder).txtCount.setText("" + object.getUnitqty());

            ArrayList<String> list=new ArrayList<>();
            final HashMap<String,String> hashMapPay=new HashMap<>();

            int pos = 0;
            for(int i=0;i<paymode.size();i++){
                list.add(""+paymode.get(i).getPaymodedesc());
                hashMapPay.put(paymode.get(i).getPaymodedesc(),paymode.get(i).getPaymode());
                if (paymode.get(i).getPaymode().equalsIgnoreCase(object.getSelectedpaymode())) {
                    pos = i;
                }
            }
            ((ImageTypeViewHolder) holder).spinner_p.setItems(list);
            ((ImageTypeViewHolder) holder).spinner_p.setSelectedIndex(pos);

            list=new ArrayList<>();
           final HashMap<String,Integer> hashMapDis=new HashMap<>();
            pos = 0;
            int temp=0;
            list.add("Select Discount");
            for(int i=0;i<discounttype.size();i++){
                if(discounttype.get(i).getDstoreid()==object.getStoreid()) {
                    temp++;
                    list.add("" + discounttype.get(i).getDiscount());
                    hashMapDis.put(discounttype.get(i).getDiscount(),discounttype.get(i).getDiscounttypeid());
                    if (discounttype.get(i).getDiscounttypeid() == object.getDiscountypeid()) {
                        pos = temp;
                    }
                }
            }
            ((ImageTypeViewHolder) holder).spinner_dis.setItems(list);
            ((ImageTypeViewHolder) holder).spinner_dis.setSelectedIndex(pos);

            ((ImageTypeViewHolder) holder).etRemarkText.setText("" + object.getRemarks());
            ((ImageTypeViewHolder) holder).etRemarkText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    object.setRemarks(""+s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            ((ImageTypeViewHolder) holder).btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition, DELETE);
                }
            });


            ((ImageTypeViewHolder) holder).btnQty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if( ((ImageTypeViewHolder) holder).txtCount.getText().toString().isEmpty()){
                        Toast.makeText(activity, "Qty is Empty", Toast.LENGTH_SHORT).show();
                    }else if(Integer.parseInt(((ImageTypeViewHolder) holder).txtCount.getText().toString())==0){
                        Toast.makeText(activity, "Qty Must be Greater than 0", Toast.LENGTH_SHORT).show();

                    }else {
                        int i =Integer.parseInt(((ImageTypeViewHolder) holder).txtCount.getText().toString());
                        object.setUnitqty(i);
                        clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition, ADD);
                    }
                }
            });

            ((ImageTypeViewHolder) holder).spinner_dis.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                    Integer hashMapDisStr=hashMapDis.get(item);
                    if (hashMapDisStr != null && (hashMapDisStr!=0))
                        object.setDiscountypeid(hashMapDisStr);
                    else
                        object.setDiscountypeid(0);

                    clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).spinner_dis, listPosition, UPDATE_DISCOUNT);

                }
            });

            ((ImageTypeViewHolder) holder).spinner_p.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                @Override
                public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                    object.setSelectedpaymode("" + hashMapPay.get(item));
                    clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).spinner_p, listPosition, UPDATE_ORDER_TYPE);
                }
            });

            GlideApp.with(activity)
                    .load(object.getProductimage())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.mipmap.logo_pink)
                    .error(R.drawable.ic_launcher_background)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(((ImageTypeViewHolder) holder).imgView);

        }
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
            return new ImageTypeViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart_last, parent, false);
            return new LastTypeViewHolder(view);
        }

    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dataSet.size())
            return 9;
        return 0;
    }

    public void setAdapterData(List<ViewAddtoCartDetailsModel.ListaddtocartviewmodelEntity> sellerList,
                               ViewAddtoCartDetailsModel response,
                               List<ViewAddtoCartDetailsModel.DiscounttypeEntity> discounttype,
                               List<ViewAddtoCartDetailsModel.PaymodeEntity> paymode) {
        dataSet = sellerList;
        this.response = response;
        this.discounttype=discounttype;
        this.paymode=paymode;
        notifyDataSetChanged();

    }


    public class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName, tvPrice;
        private EditText txtCount;
        private CardView card_parent;
        private ImageView imgView;
        private Button btnDelete,btnQty;
        private MaterialSpinner spinner_p, spinner_dis;
        private EditText etRemarkText;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvProductName = itemView.findViewById(R.id.tvProductName);
            this.tvPrice = itemView.findViewById(R.id.tvPrice);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.imgView = itemView.findViewById(R.id.imgView);
            this.btnDelete = itemView.findViewById(R.id.btnDelete);
            this.txtCount = itemView.findViewById(R.id.txtCount);
            this.btnQty=itemView.findViewById(R.id.btnQty);
            this.spinner_p = itemView.findViewById(R.id.spinner_opt);
            this.spinner_dis = itemView.findViewById(R.id.spinner_dis);
            this.etRemarkText = itemView.findViewById(R.id.etRemarkText);

        }
    }

    public static class LastTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView  tvTotal;


        public LastTypeViewHolder(View itemView) {
            super(itemView);
            this.tvTotal = itemView.findViewById(R.id.tvTotal);
        }
    }

}