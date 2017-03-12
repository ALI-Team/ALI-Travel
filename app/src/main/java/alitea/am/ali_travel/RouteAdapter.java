package alitea.am.ali_travel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import alitea.am.ali_travel.api_wrapper.rese_planerare.Trip;

/**
 * Created by luka on 12.03.17.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {

    private ArrayList<Trip> dataList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView test;

        public ViewHolder(View itemView) {
            super(itemView);
            this.test = (TextView)itemView.findViewById(R.id.info_text);
        }
    }

    public RouteAdapter(ArrayList<Trip> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_view, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView test = holder.test;

        test.setText(DateFormat.getTimeInstance().format(dataList.get(position).getLegList().get(0).getOrigin().getDate().getTime()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
