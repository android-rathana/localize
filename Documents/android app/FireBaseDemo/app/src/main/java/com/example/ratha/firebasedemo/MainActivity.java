package com.example.ratha.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ratha.firebasedemo.entity.Comment;
import com.example.ratha.firebasedemo.entity.Post;
import com.example.ratha.firebasedemo.entity.User;
import com.example.ratha.firebasedemo.entity.UserComment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageButton btnSend,btnComment;
    EditText etMessage,edComment;
    TextView tvMessage,tvUser;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref;
    static final String MESSAGE="MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend=findViewById(R.id.imageButton);
        etMessage=findViewById(R.id.etMessage);
        tvMessage=findViewById(R.id.tvMessage);
        tvUser=findViewById(R.id.tvUser);
        edComment=findViewById(R.id.edComment);
        //ref=database.getReference(MESSAGE);
        //getMessage(ref);
        getUserRef();

    }
    public void onSendMessage(View view) {

        //ref.setValue(etMessage.getText().toString());
        //getMessage(ref);
    }


    private  void getMessage(DatabaseReference ref , final String userId){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //tvMessage.setText(dataSnapshot.getValue(User.class));
                User user=dataSnapshot.child(userId).getValue(User.class);
                if(null!=user)
                tvUser.setText(user.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onSaveUser(View view) {
        String userId="user1";
        saveUser(userId, "rathana","rathana@gmail.com");
        getMessage(ref,userId);
    }

    private void getUserRef(){
        ref=database.getReference("users");
    }
    private void saveUser(String id, String name, String email) {
        User user=new User(name,email);
        ref.child(id).setValue(user);
    }

    private void updateUserName(String id,String name){
        ref.child(id).child("name").setValue(name);
    }

    public void onUpdateUserName(View view) {
        String userId="user1";
        updateUserName(userId,"voy Rathana");
        getMessage(ref,userId);
    }


    void createNewPostMap(String userId,String name,String title,String body){
        String key=ref.child("posts").push().getKey();
        Post post=new Post(userId,name,title,body);
        Map<String,Object> postValues=post.toMap();

        Map<String ,Object> childUpdates=new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        ref.updateChildren(childUpdates);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onUpdateSpecificField(View view) {
        String userId="user1";
        createNewPostMap(userId,"rathana",
                "Have a nice day@@@@",
                "we are enjoy this day with our family.");

    }

    public void onPostComment(View view) {
        userComment("user1",edComment.getText().toString());
    }
    public void userComment(String userId,String comment){
        String commentKey= ref.child("comment").push().getKey();
        Comment com=new Comment("1",comment);
        Map<String ,Object> commentValue=com.toMap();

        Map<String,Object> updateValues=new HashMap<>();
        updateValues.put("/"+userId+"/comment/"+commentKey,commentValue);
        ref.updateChildren(updateValues);
        getListComment(ref,commentKey);
    }

    public void getListComment(DatabaseReference ref, final String key){
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Comment comment=dataSnapshot.child("comment").child(key).getValue(Comment.class);
                //if(null!=comment) tvUser.setText(comment.toString());
                Iterable<DataSnapshot> userComments= dataSnapshot.child("comment").getChildren();
                List<Comment> comments=new ArrayList<>();
                for(DataSnapshot data: userComments){
                    comments.add(data.getValue(Comment.class));
                    //Log.e("onChildAdded", "onChildAdded: "+data.child(key).getValue(Comment.class).toString());
                }
                UserComment uComment=new UserComment().setComments(comments);
                //Log.e("onChildAdded", "onChildAdded: "+uComment.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
