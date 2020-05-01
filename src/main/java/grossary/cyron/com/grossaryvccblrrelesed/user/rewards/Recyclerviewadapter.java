package grossary.cyron.com.grossaryvccblrrelesed.user.rewards;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;


import grossary.cyron.com.grossaryvccblrrelesed.R;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.ViewHolder>{


    private static final String TAG = "Recyclerviewadapter";



    private ArrayList<Servicenamemodel> modelsArrayList;


    private Context mcontext;

    public Recyclerviewadapter(ArrayList<Servicenamemodel> list, Context mcontext) {
        this.modelsArrayList = list;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerrow,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Servicenamemodel listmodel = modelsArrayList.get(position);
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


        holder.textView.setText(listmodel.getRewardType());
        holder.imageView.setText(listmodel.getRewardPoint());




    }

    @Override
    public int getItemCount() {
        return modelsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView imageView;
        TextView textView;
        LinearLayout parentlayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvview);
            imageView = itemView.findViewById(R.id.imgview);
            parentlayout = itemView.findViewById(R.id.parent_layout);

        }
    }

    private boolean internetstate(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return connected = true;
        }
        else
            return  connected = false;
    }

}