package alitea.am.ali_travel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alitea.am.ali_travel.ui_elements.MultiSelectionSpinner;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Initialize spinner
        MultiSelectionSpinner trafikslag = (MultiSelectionSpinner) findViewById(R.id.spinner);
        trafikslag.setItems(Arrays.asList(getResources().getStringArray(R.array.transport_types)));
    }
}
