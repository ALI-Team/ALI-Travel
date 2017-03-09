package alitea.am.ali_travel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import alitea.am.ali_travel.ui_elements.MultiSelectionSpinner;

public class SearchActivity extends AppCompatActivity {

    int dateType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void selectDateTime(View v) {

        final Context that = (Context) this;

        final int mYear, mMonth, mDay, mHour, mMinute;
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        DatePickerDialog date = new DatePickerDialog(that, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, final int month, final int dayOfMonth) {
                TimePickerDialog time = new TimePickerDialog(that, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                /*arrival.setText(getString(R.string.arrival_date));
                                rbutton.setText(getString(R.string.departure_date)+" - "+month+"/"+dayOfMonth+" "+hourOfDay+":"+minute);*/
                    }
                }, mHour, mMinute, false);

                time.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                                    /*if (arrival.getText() != (String)getString(R.string.arrival_date)) {
                                        arrival.setChecked(true);
                                    }
                                    rbutton.setChecked(false);*/
                        }
                    }
                });

                time.show();
            }
        }, mYear, mMonth, mDay);

        date.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.modes_of_transport) {
            new MaterialDialog.Builder(this)
                    .alwaysCallMultiChoiceCallback()
                    .title(R.string.modes_of_transport)
                    .items(R.array.transport_types)
                    .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                            /**
                             * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                             * returning false here won't allow the newly selected check box to actually be selected
                             * (or the newly unselected check box to be unchecked).
                             * See the limited multi choice dialog example in the sample project for details.
                             **/
                            return true;
                        }
                    })
                    .positiveText(R.string.choose)
                    .positiveColor(getResources().getColor(R.color.colorPrimary))
                    .choiceWidgetColor(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)))
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.departure_date_radio:
                if (checked) {

                    if (dateType == 1) {
                        dateType = 0;

                        RadioButton arrival = (RadioButton) findViewById(R.id.arrival_date_radio);
                        arrival.setChecked(false);
                    }
                }
                break;
            case R.id.arrival_date_radio:
                if (checked) {

                    if (dateType == 0) {
                        dateType = 1;

                        RadioButton departure = (RadioButton) findViewById(R.id.departure_date_radio);
                        departure.setChecked(false);
                    }
                }
                break;
        }
    }
}
