package in.co.dreamsdoor.covid19india.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.co.dreamsdoor.covid19india.BuildConfig;
import in.co.dreamsdoor.covid19india.R;
import in.co.dreamsdoor.covid19india.pojo.Regional;

public class RegionalAdapter extends RecyclerView.Adapter<RegionalAdapter.ViewHolder> {
    private Context context;
    private List<Regional> regionalList;
    private final String TAG = "RegionalAdapter";

    public RegionalAdapter(Context context, List<Regional> regionalList) {
        this.context = context;
        this.regionalList = regionalList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "ViewHolder -> onCreateViewHolder");
        }

        View view = LayoutInflater.from(context)
            .inflate(R.layout.subactivity_state_wise_data_list, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int statewiseCount = getStatewiseTotalActiveCases(regionalList.get(position).getConfirmedCasesForeign(), regionalList.get(position).getConfirmedCasesIndian());
        holder.stateName.setText(regionalList.get(position).getLoc());
        holder.stateConfirmedCases.setText(String.valueOf(statewiseCount));
        holder.stateDeaths.setText(String.valueOf(regionalList.get(position).getDeaths()));
        holder.stateDischarge.setText(String.valueOf(regionalList.get(position).getDischarged()));


    }

    private int getStatewiseTotalActiveCases(int confirmedIndia, int confirmedForeigner) {
        return confirmedForeigner + confirmedIndia;
    }

    @Override
    public int getItemCount() {
        return regionalList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final String TAG = "RegionalAdapter.ViewHolder";
        TextView stateName, stateConfirmedCases,
            stateDeaths, stateDischarge;

        @SuppressLint("LongLogTag")
        ViewHolder(@NonNull View itemView) {

            super(itemView);
            stateName = itemView.findViewById(R.id.stateNameId);
            stateConfirmedCases = itemView.findViewById(R.id.confirmedCasesState);
            stateDeaths = itemView.findViewById(R.id.statewise_death_id);
            stateDischarge = itemView.findViewById(R.id.statewise_recovered_id);
        }
    }
}
