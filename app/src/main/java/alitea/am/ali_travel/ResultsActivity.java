package alitea.am.ali_travel;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import alitea.am.ali_travel.api_wrapper.APIError;
import alitea.am.ali_travel.api_wrapper.rese_planerare.ResePlanerareIllegalArgumentsException;
import alitea.am.ali_travel.api_wrapper.rese_planerare.ResePlanerareRequest;
import alitea.am.ali_travel.api_wrapper.rese_planerare.ResePlanerareResponse;
import alitea.am.ali_travel.api_wrapper.rese_planerare.Trip;

public class ResultsActivity extends AppCompatActivity {

    RecyclerView                recyclerView;
    RecyclerView.LayoutManager  layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ResePlanerareRequest planerareRequest;

        Log.i("origin", getIntent().getStringExtra("originID"));
        Log.i("dest", getIntent().getStringExtra("destID"));

        final Context that = (Context)this;

        try {
            new ResePlanerareRequest.Builder(this).originID(getIntent().getStringExtra("originID")).destID(getIntent().getStringExtra("destID")).build().fetch(new ResePlanerareRequest.ResponseHandler() {
                @Override
                public void handleResponse(ResePlanerareResponse rpr) {

                    RouteAdapter adapter = new RouteAdapter(rpr.tripList);
                    adapter.setContext(that);

                    recyclerView.setAdapter(adapter);
                }
            }, new ResePlanerareRequest.ErrorHandler() {
                @Override
                public void handleError(APIError error) {

                }
            });
        } catch (ResePlanerareIllegalArgumentsException e) {
            e.printStackTrace();
        }
    }
}
