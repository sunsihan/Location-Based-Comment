package com.example.android.locationbasedcomment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class CommentFeedActivity extends AppCompatActivity {

    private static final String TAG = CommentFeedActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Comment> mComments = new ArrayList<>();
    private ImageView detailedImage;
    EditText commentInputText;
    RelativeLayout layout;
    Button sendBtn;
    private String username;
    private String comment;
    public HashMap<String, String> commentThread;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_feed);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        comment = intent.getStringExtra("locationName");


        setTitle(comment + ": Posts");


        layout = findViewById(R.id.comment_layout);
        commentInputText = layout.findViewById(R.id.comment_input_edit_text);
        sendBtn =  layout.findViewById(R.id.send_button);
        mRecyclerView =  findViewById(R.id.comment_recycler);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       setOnClickForSendButton();

        mComments = new ArrayList<>();

       DatabaseReference commentRef = database.getReference(comment);

        ValueEventListener myDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                mComments = new ArrayList<>();
                commentThread = (HashMap<String, String>) dataSnapshot.getValue();
                for (com.google.firebase.database.DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.e(TAG, "children: " + data);
                    String time = data.getKey();
                    String comment = data.child("comment").getValue(String.class);
                    String user = data.child("user").getValue(String.class);
                    Comment newComment = new Comment(comment, user, new Date(time));
                    mComments.add(newComment);
                }
                setAdapterAndUpdateData();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("0", "cancelled");

            }



        };

        commentRef.addValueEventListener(myDataListener);
        setAdapterAndUpdateData();
    }


    private void setOnClickForSendButton() {
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentInputText.getText().toString();
                if (TextUtils.isEmpty(comment)) {
                    commentInputText.requestFocus();
                } else {
                    commentInputText.setText("");
                    postNewComment(comment);
                }
            }
        });
    }

    private void setAdapterAndUpdateData() {
        mAdapter = new CommentAdapter(this, mComments);
        mRecyclerView.setAdapter(mAdapter);
        if (mComments.size() == 0) {
            mRecyclerView.smoothScrollToPosition(0);
        } else {
            mRecyclerView.smoothScrollToPosition(mComments.size() - 1);
        }
    }

    private void postNewComment(String commentText) {
        Comment newComment = new Comment(commentText, username, new Date());
        mComments.add(newComment);
        DatabaseReference landmarkRef = database.getReference(comment);
        DatabaseReference time = landmarkRef.child(String.valueOf(new Date()));
        time.child("user").setValue(username);
        time.child("comment").setValue(commentText);
        setAdapterAndUpdateData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }}