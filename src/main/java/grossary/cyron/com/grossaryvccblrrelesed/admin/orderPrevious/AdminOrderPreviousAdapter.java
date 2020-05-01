package grossary.cyron.com.grossaryvccblrrelesed.admin.orderPrevious;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import grossary.cyron.com.grossaryvccblrrelesed.R;
import grossary.cyron.com.grossaryvccblrrelesed.admin.home.AdminHomeModel;
import grossary.cyron.com.grossaryvccblrrelesed.utility.callback.OnItemClickListener;

import static grossary.cyron.com.grossaryvccblrrelesed.utility.Constant.CATEGORY.ONCLICK;


public class AdminOrderPreviousAdapter extends RecyclerView.Adapter {

    private ArrayList<AdminHomeModel.OrderperviousmodelEntity> dataSet;
    private Activity activity;
    private OnItemClickListener<AdminHomeModel.OrderperviousmodelEntity> clickListener;

    public AdminOrderPreviousAdapter(Activity activity, OnItemClickListener<AdminHomeModel.OrderperviousmodelEntity> clickListener) {
        this.activity = activity;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        final AdminHomeModel.OrderperviousmodelEntity object = dataSet.get(listPosition);
        ((ImageTypeViewHolder) holder).tvOrderId.setText("" + object.getTranno());
//        if(object.getDeliverycharges().equalsIgnoreCase("0.00")){
//            ((ImageTypeViewHolder) holder).tvDeliveryCharge.setText("Free");
//
//        }else {
//            ((ImageTypeViewHolder) holder).tvDeliveryCharge.setText("₹" + object.getDeliverycharges());
//        }
        ((ImageTypeViewHolder) holder).tvDatePlaced.setText("Place on " + object.getTrandate());
        ((ImageTypeViewHolder) holder).tvAddress.setText("" + object.getBuyername());
//        ((ImageTypeViewHolder) holder).tvCharge.setText("₹"+ object.getOrderamount());
        ((ImageTypeViewHolder) holder).tvPrice.setText("₹"+object.getOrderamount()+" (Per Quintal)");
        ((ImageTypeViewHolder) holder).tvProductName.setText( object.getProductname());
        ((ImageTypeViewHolder) holder).tvQty.setText(String.format("%s", "Qty : "+object.getQty()));
        ((ImageTypeViewHolder) holder).tvStoreName.setText(object.getStorename());
        ((ImageTypeViewHolder) holder).tvType.setText("Type : "+object.getType());
        ((ImageTypeViewHolder) holder).tvInvoiceNo.setText("Invoice No : "+object.getInvoiceno());
            ((ImageTypeViewHolder) holder).tvInvoiceDate.setText("Invoice Date : "+object.getInvoicedate());
            ((ImageTypeViewHolder) holder).tvremarks.setText("Remarks : "+object.getRemarks());
//        ((ImageTypeViewHolder) holder).tvMrpPrice.setPaintFlags(((ImageTypeViewHolder) holder).tvMrpPrice.getPaintFlags()
//                | Paint.STRIKE_THRU_TEXT_FLAG);
//
//
//        ((ImageTypeViewHolder) holder).layoutConst.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).layoutConst, listPosition,ONCLICK);
//            }
//        });

        ((ImageTypeViewHolder) holder).btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(object, ((ImageTypeViewHolder) holder).layoutConst, listPosition, ONCLICK);
            }
        });


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list_item_order_pre, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }

    public void setAdapterData(ArrayList<AdminHomeModel.OrderperviousmodelEntity> sellerList) {
        dataSet = sellerList;
        notifyDataSetChanged();

    }


    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOrderId, tvDatePlaced, tvAddress, tvAmount;
        private CardView layoutConst;
        private Button btnDetails;
        private TextView tvProductName,tvPrice,tvQty,tvStoreName,tvType,tvInvoiceNo,tvInvoiceDate,tvremarks;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.tvOrderId = itemView.findViewById(R.id.tvOrderId);
            this.tvAddress = itemView.findViewById(R.id.tvAddress);
            this.tvDatePlaced = itemView.findViewById(R.id.tvDatePlaced);
            this.layoutConst = itemView.findViewById(R.id.card_parent);
            this.tvAmount = itemView.findViewById(R.id.tvAmount);
            this.btnDetails=itemView.findViewById(R.id.btnDetails);
            this.tvProductName=itemView.findViewById(R.id.tvProductName1);
            this.tvPrice=itemView.findViewById(R.id.tvPrice);
            this.tvQty=itemView.findViewById(R.id.tvQty);
            this.tvStoreName=itemView.findViewById(R.id.tvStoreName);
            this.tvType=itemView.findViewById(R.id.tvType);
            this.tvInvoiceNo=itemView.findViewById(R.id.tvInvoiceNo);
            this.tvInvoiceDate=itemView.findViewById(R.id.tvInvoiceDate);
            this.tvremarks=itemView.findViewById(R.id.tvremarks);
        }
    }


}