package com.example.android.locationbasedcomment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LandmarkAdapter extends RecyclerView.Adapter {

    public static Context mContext;
    private ArrayList<Landmark> mLandmarks;

    public LandmarkAdapter(Context context, ArrayList<Landmark> landmarks) {
        mContext = context;
        mLandmarks = landmarks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.landmark_cell_layout, parent, false);
        return new LandmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Landmark landmarks = mLandmarks.get(position);
        ((LandmarkViewHolder) holder).bind(landmarks);
    }
    @Override
    public int getItemCount() {
        return mLandmarks.size();
    }
}

class LandmarkViewHolder extends RecyclerView.ViewHolder {
    public RelativeLayout mBearBubbleLayout;
    public ImageView mLocationPictureImageView;
    public TextView mLocationNameTextView;
    public TextView mDistanceTextView;
    public LandmarkViewHolder(View itemView) {
        super(itemView);
        mBearBubbleLayout = itemView.findViewById(R.id.bear_cell_layout);
        mLocationPictureImageView = mBearBubbleLayout.findViewById(R.id.location_picture);
        mLocationNameTextView = mBearBubbleLayout.findViewById(R.id.location_name);
        mDistanceTextView = mBearBubbleLayout.findViewById(R.id.distance_info);

        mBearBubbleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDistanceTextView.getText() == "less than 10 meters away") {
                    Context oContext =LandmarkAdapter.mContext;
                    Intent i = new Intent(oContext, CommentFeedActivity.class);
                    i.putExtra("username", LandmarkFeedActivity.username);
                    i.putExtra("locationName", mLocationNameTextView.getText());
                    oContext.startActivity(i);
                } else {
                    Toast.makeText(LandmarkAdapter.mContext, "You must be within 10 meters of a landmark to access its comment board", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void bind(Landmark landmarks) {
        mLocationPictureImageView.setImageResource(landmarks.landmarkPicture);
        mLocationNameTextView.setText(landmarks.landmarkName);
        mDistanceTextView.setText(landmarks.coordinates);
    }
}
