package grossary.cyron.com.grossaryvccblrrelesed.user.pendingpayment;

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import java.util.ArrayList;
        import grossary.cyron.com.grossaryvccblrrelesed.R;

public class Recyclerviewadapterpendingpayment extends RecyclerView.Adapter<Recyclerviewadapterpendingpayment.ViewHolder>{


    private static final String TAG = "Recyclerviewadapter";



    private ArrayList<Pendingpaymentmodel> modelsArrayList;


    private Context mcontext;

    public Recyclerviewadapterpendingpayment(ArrayList<Pendingpaymentmodel> list, Context mcontext) {
        this.modelsArrayList = list;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerrowpendingpayment,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Pendingpaymentmodel listmodel = modelsArrayList.get(position);
       /* if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#F3F3F3"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }*/


       // holder.tvOrderId.setText("Order no:"+listmodel.getOrderNo());
        holder.tvDatePlaced.setText("Place on:"+listmodel.getTranDate());
        holder.tvAddress.setText("StoreName:"+listmodel.getStoreName());
        holder.tvProductName1.setText("TranNo:"+listmodel.getTranNo());
        holder.tvQty.setText("Qty:"+listmodel.getQty());
        holder.tvType.setText("Type:"+listmodel.getType());
      //  holder.tvremarks.setText("Remarks:"+listmodel.getRemarks());
        holder.tvPrice.setText("InvoiceAmount:"+listmodel.getInvoiceAmount());
        holder.tvInvoiceNo.setText("InvoiceNo:"+listmodel.getInvoiceNo());
        holder.tvInvoiceDate.setText("InvoiceDate:"+listmodel.getInvoiceDate());
     //   holder.buyername.setText("BuyerName:"+listmodel.getBuyerName());
        holder.tvProductName2.setText("ProductName:"+listmodel.getProductName());
        holder.Delivered.setText("OrderAmount:"+listmodel.getOrderAmount());





    }

    @Override
    public int getItemCount() {
        return modelsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDatePlaced,tvAddress,tvProductName1,tvQty,tvType,tvremarks,tvPrice,tvInvoiceNo,tvInvoiceDate,buyername,tvProductName2,
                Delivered,NoInvoice;
        TextView tvOrderId;
        LinearLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);

            tvOrderId = itemView.findViewById(R.id.tvOrderId);
            tvDatePlaced = itemView.findViewById(R.id.tvDatePlaced);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvProductName1 = itemView.findViewById(R.id.tvProductName1);
            tvProductName2 = itemView.findViewById(R.id.tvInvoiceDate1);
            tvQty = itemView.findViewById(R.id.tvQty);
            tvType = itemView.findViewById(R.id.tvType);
            tvremarks = itemView.findViewById(R.id.tvremarks);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvInvoiceNo = itemView.findViewById(R.id.tvInvoiceNo);
            tvInvoiceDate = itemView.findViewById(R.id.tvInvoiceDate);
            buyername = itemView.findViewById(R.id.tvInvoiceDate2);

            Delivered = itemView.findViewById(R.id.tvDelivered);



            parentlayout = itemView.findViewById(R.id.parent_layout);

        }
    }



}