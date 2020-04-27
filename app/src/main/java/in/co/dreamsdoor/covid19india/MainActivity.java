package in.co.dreamsdoor.covid19india;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import in.co.dreamsdoor.covid19india.pojo.CovidData;
import in.co.dreamsdoor.covid19india.pojo.Regional;
import in.co.dreamsdoor.covid19india.utils.BaseActivity;
import in.co.dreamsdoor.covid19india.utils.Config;
import in.co.dreamsdoor.covid19india.utils.RegionalAdapter;

public class MainActivity extends BaseActivity {


    ConstraintLayout rootLayout;
    CovidData covidData;
    private TextView tv_cases, tv_deaths, tv_recovered, tv_refreshed_on, title;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    private List<Regional> regionalList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.LayoutRootId);
        tv_cases = findViewById(R.id.cases);
        tv_recovered = findViewById(R.id.discharged);
        tv_deaths = findViewById(R.id.deaths);
        tv_refreshed_on = findViewById(R.id.refreshedOn);
        recyclerView = findViewById(R.id.stateWiseData);
        title = findViewById(R.id.title);

        mAdapter = new RegionalAdapter(this, regionalList);
        RecyclerView.LayoutManager mLayoutManager =
            new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        requestCovidData();

    }


    private void requestCovidData() {
        @SuppressLint("SetTextI18n")
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
            new Config().getCASE_COUNT_STATE_WISE(),
            response -> {

                try {
                    Gson gson = new Gson();
                    covidData = gson.fromJson(response,
                        CovidData.class);
                    if (covidData.getSuccess()) {
                        this.prepareRegionalData(covidData.getData().getRegional());
                        tv_cases.setText("Case : " + covidData.getData().getSummary().getTotal());
                        tv_deaths.setText("Deaths : " + covidData.getData().getSummary().getDeaths());
                        tv_recovered.setText("Recovered : " + covidData.getData().getSummary().getDischarged());
                        tv_refreshed_on.setText("Refreshed On " + covidData.getLastRefreshed());
                        title.append(" " + getActiveCases(covidData.getData().getSummary().getTotal(),
                            covidData.getData().getSummary().getDischarged(),
                            covidData.getData().getSummary().getDeaths()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },
            error -> {
                Snackbar.make(rootLayout,
                    Objects.requireNonNull(error.getMessage()),
                    BaseTransientBottomBar.LENGTH_SHORT
                ).setAction("Action", null).show();
                //displaying the error in toast if occurrs
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    private void prepareRegionalData(List<Regional> regionals) {

        int datasize = regionals.size();

        for (int i = 1; i < datasize; i++) {
            Regional reg = new Regional(regionals.get(i).getLoc(),
                regionals.get(i).getConfirmedCasesIndian(),
                regionals.get(i).getDischarged(),
                regionals.get(i).getDeaths(),
                regionals.get(i).getConfirmedCasesForeign());
            regionalList.add(reg);
        }
        mAdapter.notifyDataSetChanged();

    }

    private String getActiveCases(int cases, int dischaged, int deaths) {
        int activeCases = cases - (dischaged + deaths);
        return "Active Cases : " + activeCases;
    }
}
