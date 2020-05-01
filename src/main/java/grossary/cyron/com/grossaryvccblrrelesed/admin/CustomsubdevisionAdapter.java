package grossary.cyron.com.grossaryvccblrrelesed.admin;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;



import grossary.cyron.com.grossaryvccblrrelesed.R;


public class CustomsubdevisionAdapter extends BaseAdapter implements SpinnerAdapter {

    Context mContext;

    ArrayList<Userlistmodel> leaves_types;
    String colorcode;

    public CustomsubdevisionAdapter(Context mContext, ArrayList<Userlistmodel> arrayList, String colorcode) {
        this.mContext=mContext;
        this.leaves_types = arrayList;
        this.colorcode = colorcode;

    }

    @Override
    public int getCount() {
        return leaves_types.size();
    }

    @Override
    public Object getItem(int position) {
        return leaves_types.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Userlistmodel listmodel = leaves_types.get(position);
        TextView txt = new TextView(mContext);
        txt.setGravity(Gravity.CENTER);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(16);

        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_circle_black_24dp, 0);

        txt.setText(listmodel.getLoginId());
        txt.setTextColor(Color.parseColor(colorcode));
        return  txt;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Userlistmodel listmodel = leaves_types.get(position);
        TextView txt = new TextView(mContext);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(16);
        txt.setGravity(Gravity.CENTER_VERTICAL);

        txt.setText(""+listmodel.getLoginId());
        txt.setTextColor(Color.parseColor("#fe3652"));
        return  txt;
    }

}

