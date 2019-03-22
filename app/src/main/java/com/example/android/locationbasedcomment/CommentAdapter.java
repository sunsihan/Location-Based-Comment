package com.example.android.locationbasedcomment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Comment> mComments;

    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        mContext = context;
        mComments = comments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_cell_layout, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        ((CommentViewHolder) holder).bind(comment);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}

class CommentViewHolder extends RecyclerView.ViewHolder {

    private TextView mUsernameTextView;
    private TextView mDateTextView;
    private TextView mCommentTextView;


    CommentViewHolder(View itemView) {
        super(itemView);
        RelativeLayout mCommentBubbleLayout = itemView.findViewById(R.id.comment_cell_layout);
        mUsernameTextView = mCommentBubbleLayout.findViewById(R.id.username_text_view);
        mDateTextView = mCommentBubbleLayout.findViewById(R.id.date_text_view);
        mCommentTextView = mCommentBubbleLayout.findViewById(R.id.comment_text_view);
    }

    void bind(Comment comment) {
        mUsernameTextView.setText(comment.username);
        mDateTextView.setText("posted " + comment.elapsedTimeString() + " ago");
        mCommentTextView.setText(comment.text);
    }
}