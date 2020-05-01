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


public class SubCategoryListAdapter extends RecyclerView.Adapter {

    private List<SubCategoryModel.ObjcategorylistEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<SubCategoryModel.ObjcategorylistEntity> clickListener;

    public SubCategoryListAdapter(Activity activity, OnItemClickListener<SubCategoryModel.ObjcategorylistEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,  int listPosition) {

        final SubCategoryModel.ObjcategorylistEntity object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).tvProductName.setText(String.format("%s", object.getSubcatergoryname()));
        GlideApp.with(activity)
                .load(object.getSubcatergoryimage())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sub_category, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(List<SubCategoryModel.ObjcategorylistEntity> sellerList) {
        dataSet = sellerList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName;
        private CardView card_parent;
        private ImageView imgView;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvProductName = itemView.findViewById(R.id.tvProductName);
            this.card_parent = itemView.findViewById(R.id.card_parent);
            this.imgView = itemView.findViewById(R.id.imgView);
        }
    }

}