package alitea.am.ali_travel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RouteDetailsActivity extends AppCompatActivity {

    RecyclerView                recyclerView;
    RecyclerView.LayoutManager  layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DetailsAdapter adapter = new DetailsAdapter(getIntent().get);
        recyclerView.setAdapter(adapter);
    }
}
