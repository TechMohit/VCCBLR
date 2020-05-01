package grossary.cyron.com.grossaryvccblrrelesed.user.category;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.utility.GlideApp;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ONCLICK;


public class CategoryListAdapter extends RecyclerView.Adapter {

    private List<CategoryModel.ObjproductdetailslistEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<CategoryModel.ObjproductdetailslistEntity> clickListener;

    public CategoryListAdapter(Activity activity, OnItemClickListener<CategoryModel.ObjproductdetailslistEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,  int listPosition) {

        final CategoryModel.ObjproductdetailslistEntity object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).tvProductName.setText(String.format("%s", object.getProductname()));
        ((ImageTypeViewHolder) holder).tvDesc.setText(""+object.getSellingprice()+ "\n"+object.getSubproductqty());
        ((ImageTypeViewHolder) holder).tvSellingPrice.setText(""+String.format("%s", object.getType()));
        ((ImageTypeViewHolder) holder).tvMrpPrice.setText("Available:"+object.getAvailablestockqty()+"");
        ((ImageTypeViewHolder) holder).tvStoreName.setText(""+String.format("%s", object.getStorename()));

        GlideApp.with(activity)
                .load(object.getProductimage())
                .centerInside()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.mipmap.logo_pink)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(((ImageTypeViewHolder) holder).imgView);


        ((ImageTypeViewHolder) holder).card_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).card_parent, holder.getAdapterPosition(),ONCLICK);
            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(List<CategoryModel.ObjproductdetailslistEntity> sellerList) {
        dataSet = sellerList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName,tvDesc,tvSellingPrice,tvMrpPrice,tvStoreName;
        private CardView card_parent;
        private ImageView imgView;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvProductName = itemView.findViewById(R.id.tvProductName);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.imgView = itemView.findViewById(R.id.imgView);
            this.tvDesc = itemView.findViewById(R.id.tvDesc);
            this.tvSellingPrice = itemView.findViewById(R.id.tvSellingPrice);
            this.tvMrpPrice = itemView.findViewById(R.id.tvMrpPrice);
            this.tvStoreName=itemView.findViewById(R.id.tvStoreName);
        }
    }

}