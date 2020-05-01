package grossary.cyron.com.grossaryvccblrrelesed.admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import grossary.cyron.com.grossaryvccblrrelesed.R;


public class RecyclerViewPaymentDetailsAdapter extends RecyclerView.Adapter<RecyclerViewPaymentDetailsAdapter.ViewHolder>{


    private static final String TAG = "Recyclerviewadapter";



    private ArrayList<PaymentCollectionModel> modelsArrayList;


    private Context mcontext;

    public RecyclerViewPaymentDetailsAdapter(  ArrayList<PaymentCollectionModel> list, Context mcontext) {
        this.modelsArrayList = list;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        PaymentCollectionModel listmodel = modelsArrayList.get(position);
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



        holder.storename.setText(listmodel.getStoreName());
        holder.TranNo.setText(listmodel.getTranNo());
        holder.ProductName.setText("Product Name: "+listmodel.getProductName());
        holder.Qty.setText("Qty: "+listmodel.getQty());
      //  holder.InvoiceNo.setText("Invoice No: "+listmodel.getInvoiceNo());
        holder.InvoiceDate.setText("Invoice Date: "+ listmodel.getInvoiceDate());
        holder.BuyerName.setText("BuyerName: "+listmodel.getBuyerName());
        holder.InvoiceAmount.setText("Invoice Amount: "+listmodel.getInvoiceAmount());
      //  holder.ProductPrice.setText("Product Price: "+listmodel.getProductPrice());
        holder.ProductPrice.setText("Invoice No: "+listmodel.getInvoiceNo());
        //  holder.imageView.setImageResource(Integer.parseInt(listmodel.getImage()));

        /*holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Servicenamemodel listmodeltest = modelsArrayList.get(position);
                    Log.d("serviceclicked", "details:" + listmodeltest.getServicename() + listmodeltest.getServiceparam() + listmodeltest.getImage());

                    String MobileAppView = listmodeltest.getMobileAppView();

                    if (MobileAppView.equalsIgnoreCase("ESCOMPAY")) {
                        Intent intent = new Intent(v.getContext(), PaymentService.class);
                        intent.putExtra("serviceparam", listmodeltest.getServiceparam());
                        v.getContext().startActivity(intent);

                    } else if (MobileAppView.equalsIgnoreCase("MESCOMDK")) {
                        Intent intent = new Intent(v.getContext(), Electricitybpruralservice.class);
                        intent.putExtra("serviceparam", listmodeltest.getServiceparam());
                        v.getContext().startActivity(intent);
                    }else if (MobileAppView.equalsIgnoreCase("WBBN")) {
                        Intent intent = new Intent(v.getContext(), WaterBillBNService.class);
                        intent.putExtra("serviceparam", listmodeltest.getServiceparam());
                        v.getContext().startActivity(intent);
                    }
                    else {
                        Toast.makeText(v.getContext(), "" + listmodeltest.getServicename() + "  service is not configured on this platform ", Toast.LENGTH_LONG).show();
                    }

                }





        });*/
    }

    @Override
    public int getItemCount() {
        return modelsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView storename,TranNo,ProductName,Qty,InvoiceNo,InvoiceDate,BuyerName,InvoiceAmount,ProductPrice;
        LinearLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);

            storename = itemView.findViewById(R.id.tvAddress);
            TranNo = itemView.findViewById(R.id.tvMrpPrice);
            ProductName = itemView.findViewById(R.id.tvProductName12);
            Qty = itemView.findViewById(R.id.tvQty);
            BuyerName = itemView.findViewById(R.id.tvLorryNo);
           // InvoiceNo = itemView.findViewById(R.id.tvDeliveryCharge);
            ProductPrice = itemView.findViewById(R.id.tvPrice);
            ProductPrice = itemView.findViewById(R.id.tvPrice);
            InvoiceAmount = itemView.findViewById(R.id.tvDriverNo);
            InvoiceDate = itemView.findViewById(R.id.tvDatePlaced);

            parentlayout = itemView.findViewById(R.id.parent_layout);

        }
    }



}