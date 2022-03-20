package com.utkarsh.practice;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class setting_Fragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container , savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_setting_, container, false);
        TextView textView = rootView.findViewById(R.id.optionLogout);
       // TextView textView1 = rootView.findViewById(R.id.viewProfile)
        textView.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            getActivity().startActivity(new Intent(getContext(), MainActivity.class));
        });
        return  rootView;
       // return inflater.inflate(R.layout.fragment_setting_, container, false);
    }
}