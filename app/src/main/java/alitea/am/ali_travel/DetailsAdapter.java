package alitea.am.ali_travel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import alitea.am.ali_travel.api_wrapper.rese_planerare.Leg;
import alitea.am.ali_travel.api_wrapper.rese_planerare.Trip;

/**
 * Created by luka on 15.03.17.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    private ArrayList<Trip> dataList;
    public Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView test;
        TextView est;

        public ViewHolder(View itemView) {
            super(itemView);
            this.test = (TextView)itemView.findViewById(R.id.info_text);
            this.est = (TextView)itemView.findViewById(R.id.estimated);
        }
    }

    public DetailsAdapter(ArrayList<Trip> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_view, parent, false);

        DetailsAdapter.ViewHolder vh = new DetailsAdapter.ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(DetailsAdapter.ViewHolder holder, int position) {
        TextView test = holder.test;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        String orig = format.format(dataList.get(position).getLegList().get(0).getOrigin().getDate().getTime());
        String dest = format.format(dataList.get(position).getLegList().get(dataList.get(position).getLegList().size()-1).getDestination().getDate().getTime());
        test.setText(orig+" - "+dest);

        TextView est = holder.est;
        est.setText(context.getString(R.string.estimated, dataList.get(position).getDuration()));

        LinearLayout mos = (LinearLayout)holder.itemView.findViewById(R.id.mos_types);

        for (Leg leg:dataList.get(position).getLegList()) {

            if (dataList.get(position).getLegList().indexOf(leg) > 0) {
                ImageView icon = new ImageView(context);
                icon.setImageResource(R.drawable.ic_arrow_forward_black_24dp);
                mos.addView(icon);
            }

            ImageView icon = new ImageView(context);

            if (leg.isWalk()) {
                icon.setImageResource(R.drawable.ic_directions_walk_black_24dp);
            }

            else {

                try{
                    Log.i("nigger", String.valueOf(leg.getProduct().getOperatorCode()));
                } catch(NullPointerException ex) {

                }
                try {
                    switch (leg.getProduct().getCatCode()) {
                        case 1:
                            icon.setImageResource(R.drawable.ic_train_blue_24dp);
                            break;
                        case 2:
                            icon.setImageResource(R.drawable.ic_train_blue_24dp);
                            break;
                        case 3:
                            icon.setImageResource(R.drawable.ic_directions_bus_yellow_24dp);
                            break;
                        case 4:
                            icon.setImageResource(R.drawable.ic_train_blue_24dp);
                            break;
                        case 5:
                            icon.setImageResource(R.drawable.ic_subway_blue_24dp);
                            break;
                        case 6:
                            icon.setImageResource(R.drawable.ic_tram_red_24dp);
                            break;
                        case 7:
                            icon.setImageResource(R.drawable.ic_directions_bus_red_24dp);
                            break;
                        case 8:
                            icon.setImageResource(R.drawable.ic_train_blue_24dp);
                            break;
                    }
                } catch (NullPointerException exp) {
                    icon.setImageResource(R.drawable.ic_directions_walk_black_24dp);
                }
            }

            mos.addView(icon);

            if (leg.isWalk() == false) {
                try {
                    TextView line = new TextView(context);
                    line.setText(String.valueOf(leg.getProduct().getNum()));
                    mos.addView(line);
                } catch (NullPointerException exp) {

                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
