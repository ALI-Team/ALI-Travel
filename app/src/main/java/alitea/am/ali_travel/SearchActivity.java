package alitea.am.ali_travel;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import alitea.am.ali_travel.api_wrapper.APIError;
import alitea.am.ali_travel.api_wrapper.plats_uppslag.PlatsUppslagRequest;
import alitea.am.ali_travel.api_wrapper.plats_uppslag.Stop;

public class SearchActivity extends AppCompatActivity {

    AutoCompleteTextView fromTF;
    AutoCompleteTextView toTF;

    int dateType, year = 0, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Context that = (Context)this;

        FloatingActionButton search = (FloatingActionButton)findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (year == 0) {
                    //TODO add fallback if no date & time is selected
                }

                new PlatsUppslagRequest.Builder(that).input(String.valueOf(fromTF.getText())).fetch(new PlatsUppslagRequest.ResponseHandler() {
                    @Override
                    public void handleResponse(ArrayList<Stop> stops) {

                        final String origID = stops.get(0).getId();

                        new PlatsUppslagRequest.Builder(that).input(String.valueOf(toTF.getText())).fetch(new PlatsUppslagRequest.ResponseHandler() {
                            @Override
                            public void handleResponse(ArrayList<Stop> stops) {

                                final String destID = stops.get(0).getId();

                                Intent results = new Intent(SearchActivity.this, ResultsActivity.class);
                                results.putExtra("year", year);
                                results.putExtra("month", month);
                                results.putExtra("day", day);
                                results.putExtra("hour", hour);
                                results.putExtra("minute", minute);
                                results.putExtra("originID", origID);
                                results.putExtra("destID", destID);
                                results.putExtra("dateType", dateType);
                                startActivity(results);

                            }
                        }, new PlatsUppslagRequest.ErrorHandler() {

                            @Override
                            public void handleError(APIError error) {
                                Log.e("error", error.toString());
                            }
                        });

                    }
                }, new PlatsUppslagRequest.ErrorHandler() {

                    @Override
                    public void handleError(APIError error) {
                        Log.e("error", error.toString());
                    }
                });
            }
        });

        RadioButton departure = (RadioButton)findViewById(R.id.departure_date_radio);
        departure.setChecked(true);

        fromTF = (AutoCompleteTextView)findViewById(R.id.fromTF);
        toTF = (AutoCompleteTextView)findViewById(R.id.toTF);

        fromTF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchString = s.toString();

                if (searchString.length() > 3) {
                    new PlatsUppslagRequest.Builder(that).input(searchString).fetch(new PlatsUppslagRequest.ResponseHandler() {
                        @Override
                        public void handleResponse(ArrayList<Stop> stops) {

                            ArrayList<String> stopNames = new ArrayList<String>();

                            //Suck my cock Java 7
                            /*stopNames = stops.stream().map(stop -> {
                                return stop.getName();
                            });*/

                            for (Stop stop:stops) {
                                stopNames.add(stop.getName());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(that, android.R.layout.simple_dropdown_item_1line, stopNames);
                            fromTF.setAdapter(adapter);
                        }
                    }, new PlatsUppslagRequest.ErrorHandler() {

                        @Override
                        public void handleError(APIError error) {
                            Log.e("error", error.toString());
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        toTF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchString = s.toString();

                if (searchString.length() > 3) {
                    new PlatsUppslagRequest.Builder(that).input(searchString).fetch(new PlatsUppslagRequest.ResponseHandler() {
                        @Override
                        public void handleResponse(ArrayList<Stop> stops) {

                            ArrayList<String> stopNames = new ArrayList<String>();

                            //Suck my cock Java 7
                            /*stopNames = stops.stream().map(stop -> {
                                return stop.getName();
                            });*/

                            for (Stop stop:stops) {
                                stopNames.add(stop.getName());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(that, android.R.layout.simple_spinner_dropdown_item, stopNames);
                            toTF.setAdapter(adapter);
                        }
                    }, new PlatsUppslagRequest.ErrorHandler() {

                        @Override
                        public void handleError(APIError error) {
                            Log.e("error", error.toString());
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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

        DatePickerDialog date = new DatePickerDialog(that, R.style.DatePickerDialogDarkText, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                TimePickerDialog time = new TimePickerDialog(that, R.style.DatePickerDialogDarkText, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        TextView tv = (TextView)findViewById(R.id.current_date_time);
                        tv.setText(year+"-"+month+"-"+dayOfMonth+" "+hourOfDay+":"+minute);
                    }
                }, mHour, mMinute, false);

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
                    .positiveColor(ContextCompat.getColor(this, R.color.colorPrimary))
                    .negativeText(android.R.string.cancel)
                    .negativeColor(ContextCompat.getColor(this, R.color.colorPrimary))
                    .choiceWidgetColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)))
                    .show();
        }

        if (id == R.id.swap) {
            String departure = String.valueOf(fromTF.getText());
            String arrival = String.valueOf(toTF.getText());
            fromTF.setText(arrival);
            toTF.setText(departure);
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
