package com.utkarsh.practice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class comment_Fragment extends Fragment implements  View.OnClickListener {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference messageBase;
    MessageAdapter messageAdapter;
    User user;
    List <Message> message;

    RecyclerView recyclerViewMsg;

    EditText editMessage;
    ImageButton imageButtonSend;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container , savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_comment_, container, false);
        intit (rootView);

        return  rootView;


       // return inflater.inflate(R.layout.fragment_comment_, container, false);
    }

    private void intit(View view) {

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user = new User();

        recyclerViewMsg = view.findViewById(R.id.rvMessage);
        editMessage = view.findViewById(R.id.msgboard);
        imageButtonSend = view.findViewById(R.id.send);

        imageButtonSend.setOnClickListener(this);
        message = new ArrayList<>();

        recyclerViewMsg.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageAdapter = new MessageAdapter(getContext(), message, messageBase);
        recyclerViewMsg.setAdapter(messageAdapter);
    }

    @Override
    public void onClick(View v) {
        if(!TextUtils.isEmpty(editMessage.getText().toString())){
            Message message = new Message(editMessage.getText().toString(), user.getUserName());
            messageBase.push().setValue(message);
            editMessage.setText("");
        }else
            Toast.makeText(getActivity(), "You Cannot Send Empty Message", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();
        user.setUserID(currentUser.getUid());
        user.setUserEmail(currentUser.getEmail());

        database.getReference("Users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                user.setUserID(currentUser.getUid());
                AllMethods.name = user.getUserName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        messageBase = database.getReference("messages");
        messageBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message messages = snapshot.getValue(Message.class);
                messages.setKey(snapshot.getKey());
                message.add(messages);
                messageAdapter.refreshList();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message messages = snapshot.getValue(Message.class);
                messages.setKey(snapshot.getKey());

                List<Message> newMessage = new ArrayList<>();
                for (Message m: message){
                    if(m.getKey().equals(messages.getKey())){
                        newMessage.add(messages);
                    }else{
                        newMessage.add(m);
                    }
                }
                message = newMessage;
                messageAdapter.refreshList();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                Message messages = snapshot.getValue(Message.class);
                messages.setKey(snapshot.getKey());

                List<Message> newMessage = new ArrayList<>();
                for (Message m: message) {
                    if (!m.getKey().equals(messages.getKey())) {
                        newMessage.add(m);
                    }

                }
                message = newMessage;
                messageAdapter.refreshList();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}