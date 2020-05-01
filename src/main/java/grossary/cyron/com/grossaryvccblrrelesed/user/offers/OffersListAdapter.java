package grossary.cyron.com.grossaryvccblrrelesed.user.offers;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.user.home.HomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.GlideApp;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ADD_TO_CART;
import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ONCLICK;


public class OffersListAdapter extends RecyclerView.Adapter {

    private ArrayList<HomeModel.ObjOfferDetailsListEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<HomeModel.ObjOfferDetailsListEntity> clickListener;

    public OffersListAdapter(Activity activity, OnItemClickListener<HomeModel.ObjOfferDetailsListEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final HomeModel.ObjOfferDetailsListEntity object = dataSet.get(listPosition);

        ((ImageTypeViewHolder) holder).tvName.setText("" + object.getProductName() + "("+object.getStoreName()+")");
        ((ImageTypeViewHolder) holder).tvMrpPrice.setText("\u20B9" + object.getMRPPrice());

        ((ImageTypeViewHolder) holder).tvMrpPrice.setPaintFlags(((ImageTypeViewHolder) holder).tvMrpPrice.getPaintFlags()
                | Paint.STRIKE_THRU_TEXT_FLAG);
        ((ImageTypeViewHolder) holder).tvSellerPrice.setText("\u20B9" + object.getSellingPrice());

        GlideApp.with(activity)
                .load(object.getProductImage())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.mipmap.logo_pink)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_launcher_background)
                .into(((ImageTypeViewHolder) holder).imgView);

        ((ImageTypeViewHolder) holder).card_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition, ONCLICK);
            }
        });
        ((ImageTypeViewHolder) holder).btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, listPosition, ADD_TO_CART);
            }
        });

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_offers, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;

        return dataSet.size();
    }

    public void setAdapterData(ArrayList<HomeModel.ObjOfferDetailsListEntity> offersList) {

        dataSet = offersList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private CardView card_parent;
        private ImageView imgView;
        private TextView tvSellerPrice, tvMrpPrice, tvName;
        private Button btnAdd;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.imgView = itemView.findViewById(R.id.imgView);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvMrpPrice = itemView.findViewById(R.id.tvMrpPrice);
            this.tvSellerPrice = itemView.findViewById(R.id.tvSellerPrice);
            this.btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }

}