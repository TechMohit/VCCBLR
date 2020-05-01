package grossary.cyron.com.grossaryvccblrrelesed.admin.preOrderDetail;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grossary.cyron.com.grossaryvccblrrelesed.R;


public class AdminPreOrderDetailsdapter extends RecyclerView.Adapter {

    private List<AdminPreOrderdetailModel.OrderperviousmodelEntity> dataSet;
    private Activity activity;
    private AdminPreOrderdetailModel response;

    public AdminPreOrderDetailsdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        if(listPosition==dataSet.size()){


        }else {

            final AdminPreOrderdetailModel.OrderperviousmodelEntity object = dataSet.get(listPosition);
            ((ImageTypeViewHolder) holder).tvProductName.setText(String.format("%s", object.getProductname()));
            ((ImageTypeViewHolder) holder).tvStoreName.setText(String.format("%s", object.getStorename()));

//            Float sum=(Float.valueOf(object.getProductprice())*Float.valueOf(object.getOrderitemqty()));
            ((ImageTypeViewHolder) holder).tvPrice.setText("₹"+object.getOrderamount()+" (Per Quintal)");
            ((ImageTypeViewHolder) holder).tvQty.setText("Qty : "+object.getQty()+" Quintals");
            ((ImageTypeViewHolder) holder).tvOrderID.setText("Remarks : "+object.getRemarks());
            ((ImageTypeViewHolder) holder).tvType.setText("Type : "+object.getType());
            ((ImageTypeViewHolder) holder).tvInvoiceNo.setText("Invoice No : "+object.getInvoiceno());
            ((ImageTypeViewHolder) holder).tvInvoiceDate.setText("Invoice Date : "+object.getInvoicedate());

//            GlideApp.with(activity)
//                    .load(object.getProductimage())
//                    .centerCrop()
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .placeholder(R.mipmap.logo_pink)
//                    .error(R.drawable.ic_launcher_background)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(((ImageTypeViewHolder) holder).imgView);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list_item_pre_order_detail, parent, false);
            return new ImageTypeViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order_detail_last, parent, false);
            return new LastTypeViewHolder(view);
        }

    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==dataSet.size())
            return 9;
        return 0;
    }

    public void setAdapterData(List<AdminPreOrderdetailModel.OrderperviousmodelEntity> sellerList, AdminPreOrderdetailModel response) {
        dataSet = sellerList;
        this.response=response;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName,tvPrice,tvQty,tvStoreName,tvOrderID,tvType,tvInvoiceNo,tvInvoiceDate;
        private ImageView imgView;


        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvProductName = itemView.findViewById(R.id.tvProductName);
            this.imgView = itemView.findViewById(R.id.imgView);
            this.tvPrice=itemView.findViewById(R.id.tvPrice);
            this.tvQty=itemView.findViewById(R.id.tvQty);
            this.tvStoreName=itemView.findViewById(R.id.tvStoreName);
            this.tvOrderID=itemView.findViewById(R.id.tvOrderID);
            this.tvType=itemView.findViewById(R.id.tvType);
            this.tvInvoiceNo=itemView.findViewById(R.id.tvInvoiceNo);
            this.tvInvoiceDate=itemView.findViewById(R.id.tvInvoiceDate);
        }
    }
    public static class LastTypeViewHolder extends RecyclerView.ViewHolder {



        public LastTypeViewHolder(View itemView) {
            super(itemView);

        }
    }

}