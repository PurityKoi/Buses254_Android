package Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.buses254.R;

import java.util.List;

import Model.oneWayScheduleModel;

/**
 * Created by USER on 10/31/2017.
 */

public class OneWayScheduleAdapter extends RecyclerView.Adapter<OneWayScheduleAdapter.OneWayScheduleViewHolder> {

    private Context mCtx;
    private List<oneWayScheduleModel> scheduleList;

    //ADAPTER CONSTRUCTOR
    public OneWayScheduleAdapter(Context mCtx, List<oneWayScheduleModel> scheduleList) {
        this.mCtx = mCtx;
        this.scheduleList = scheduleList;
        Log.d("size",""+ scheduleList.size());
    }


//THIS METHOD IS USED TO REFERENCE THE CARDVIEW LAYOUT
    @Override
    public OneWayScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view  = inflater.inflate(R.layout.schedule_cardview, parent, false);
        return new OneWayScheduleViewHolder(view);
    }

    //THIS METHOD IS USED TO BIND THE RECEIVED DATA TO THE WIDGETS OF THE CARDVIEW
    @Override
    public void onBindViewHolder(OneWayScheduleViewHolder holder, int position) {
        oneWayScheduleModel schedule = scheduleList.get(position);
        Log.d("schedule","" + schedule.getBus_id());

        holder.tvBusId.setText(String.valueOf(schedule.getBus_id()));
        holder.tvCountyDept.setText(schedule.getDeparture());
        holder.tvCountyDest.setText(schedule.getArrival());
        holder.tvTravelDate.setText(schedule.getDate());
        holder.tvTravelTime.setText(schedule.getTime());
        holder.tvTravelCost.setText(String.valueOf(schedule.getCost()));
    }

    //THIS METHOD CHECKS IF THERE ARE ANY ITEMS TO DISPLAY ON RECYCLER VIEW
    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    class OneWayScheduleViewHolder extends RecyclerView.ViewHolder{
        TextView tvBusId , tvCountyDept , tvCountyDest , tvTravelDate , tvTravelTime, tvTravelCost;

        public OneWayScheduleViewHolder(View itemView) {
            super(itemView);
            tvBusId = (TextView) itemView.findViewById(R.id.tvBusId );
            tvCountyDept = (TextView) itemView.findViewById(R.id.tvCountyDept );
            tvCountyDest = (TextView) itemView.findViewById(R.id.tvCountyDest);
            tvTravelDate = (TextView) itemView.findViewById(R.id.tvTravelDate);
            tvTravelTime = (TextView) itemView.findViewById(R.id.tvTravelTime);
            tvTravelCost = (TextView) itemView.findViewById(R.id.tvTravelCost);
        }
    }

}
