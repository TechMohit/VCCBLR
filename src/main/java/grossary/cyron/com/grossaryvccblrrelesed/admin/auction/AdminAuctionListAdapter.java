package grossary.cyron.com.grossaryvccblrrelesed.admin.auction;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;


import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;
import grossary.cyron.com.grossaryvccblrrelesed.R;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ONCLICK;


public class AdminAuctionListAdapter extends RecyclerView.Adapter {

    private ArrayList<AdminHomeModel.AuctionlistEntity> dataSet;
    private Activity activity;
    private List<AdminHomeModel.DiscounttypeEntity> discounttype=new ArrayList<>();
    private OnItemClickListener<AdminHomeModel.AuctionlistEntity> clickListener;

    public AdminAuctionListAdapter(Activity activity, OnItemClickListener<AdminHomeModel.AuctionlistEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final AdminHomeModel.AuctionlistEntity object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).tvProductName.setText(String.format("%s", "Product Name : "+object.getProductname()));
        ((ImageTypeViewHolder) holder).tvStoreName.setText(String.format("%s", "Store Name : "+object.getStorename()));
        ((ImageTypeViewHolder) holder).tvCusName.setText(String.format("%s", "Customer Name : "+object.getCustomername()));
        ((ImageTypeViewHolder) holder).tvSellingPrice.setText(String.format("%s", "Selling Price : "+object.getSellingprice()));
        ((ImageTypeViewHolder) holder).etCusPrice.setText(String.format("%s",""+ object.getCustomerprice()));
        ((ImageTypeViewHolder) holder).tvQty.setText(String.format("%s", "Qty : "+object.getQty()));

        ArrayList<String> list=new ArrayList<>();
        int p=0;
        list.add("Select Discount");
        for(int i=0;i<discounttype.size();i++){
            if(discounttype.get(i).getDstoreid()==object.getStoreid()) {
                if(discounttype.get(i).getDiscounttypeid()==object.getDiscounttypeid()){
                    p=i+1;
                }
                list.add(discounttype.get(i).getDiscount());
            }
        }
        ((ImageTypeViewHolder) holder).spinnerDis.setItems(list);
        ((ImageTypeViewHolder) holder).spinnerDis.setSelectedIndex(p);

        ((ImageTypeViewHolder) holder).spinnerStatus.setItems("Approve","Reject");

        ((ImageTypeViewHolder) holder).card_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition,ONCLICK);
            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list_item_auction, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(ArrayList<AdminHomeModel.AuctionlistEntity> sellerList, List<AdminHomeModel.DiscounttypeEntity> discounttype) {
        dataSet = sellerList;
        this.discounttype=discounttype;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName,tvStoreName,tvCusName,tvSellingPrice,tvQty;
        private CardView card_parent;
        private EditText etCusPrice;
        private MaterialSpinner spinnerDis,spinnerStatus;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvProductName = itemView.findViewById(R.id.tvProductName);
            this.tvStoreName=itemView.findViewById(R.id.tvStoreName);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.tvCusName=itemView.findViewById(R.id.tvCusName);
            this.tvSellingPrice=itemView.findViewById(R.id.tvSellingPrice);
            this.etCusPrice=itemView.findViewById(R.id.etCusPrice);
            this.tvQty=itemView.findViewById(R.id.tvQty);
            this.spinnerDis=itemView.findViewById(R.id.spinnerDis);
            this.spinnerStatus=itemView.findViewById(R.id.spinnerStatus);
        }
    }

}