package phannguyen.com.trackingsystem.ui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import phannguyen.com.trackingsystem.R;

public class ContractListViewAdapter extends ArrayAdapter<String> {
    int groupid;
    String[] item_list;
    ArrayList<String> desc;
    Context context;

    public ContractListViewAdapter(Context context, int vg, int id, String[] item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;

    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView textname;
        public TextView texttime;
        public TextView textcontract;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the rowlayout.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textname = (TextView) rowView.findViewById(R.id.txtname);
            viewHolder.textcontract = (TextView) rowView.findViewById(R.id.txtcontractid);
            viewHolder.texttime = (TextView) rowView.findViewById(R.id.txttime);
            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        String[] items=item_list[position].split("_");
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textcontract.setText(items[0]);
        holder.textname.setText(items[1]);
        holder.texttime.setText(items[2]);
        return rowView;
    }

}
