package Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.buses254.R;

import Interface.ItemClickListener;
import Model.RssObject;

/**
 * Created by USER on 9/8/2017.
 */

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView tvTitle, tvPubDate, tvContent;
    public ImageView feedImg;
    private ItemClickListener itemClickListener;

    public FeedViewHolder(View itemView) {
        super(itemView);
        CardView cardView = (CardView) itemView.findViewById(R.id.card);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvPubDate = (TextView) itemView.findViewById(R.id.tvpubDate);
        tvContent = (TextView) itemView.findViewById(R.id.tvcontent);


        //Set Event

        itemView.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Log.d("position", "" + getAdapterPosition());
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }


}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {
    private RssObject rssObject;
    private Context mContext;
    private LayoutInflater inflater;

    public FeedAdapter(RssObject rssObject, Context mContext) {
        this.rssObject = rssObject;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void OpenObject(int position) {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(rssObject.getItems().get(position).getLink()));
        mContext.startActivity(browser);

    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

        holder.tvTitle.setText(rssObject.getItems().get(position).getTitle());
        holder.tvPubDate.setText(rssObject.getItems().get(position).getPubDate());
        holder.tvContent.setText(rssObject.getItems().get(position).getContent());

    }

    @Override
    public int getItemCount() {

        return rssObject.items.size();
    }


}
