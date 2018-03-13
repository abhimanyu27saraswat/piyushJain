package com.purplesweetbox.piyushjain.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.gson.Gson;
import com.purplesweetbox.piyushjain.R;
import com.purplesweetbox.piyushjain.model.User;

public class UserRoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {


    private User thisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_role_selection);

        View trainerCard = findViewById(R.id.cv_user_selection_trainer);
        View participantCard = findViewById(R.id.cv_user_selection_participant);
        thisUser = getIntent().getExtras().getParcelable("user");
        trainerCard.setOnClickListener(this);
        participantCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(UserRoleSelectionActivity.this,TrailActivity.class);
        switch (view.getId()){
            case R.id.cv_user_selection_trainer:

                thisUser.userRole = "Trainer";
                intent.putExtra("user",thisUser);
                startActivity(intent);
                finish();
                break;

            case R.id.cv_user_selection_participant:

                thisUser.userRole = "Participant";
                intent.putExtra("user",thisUser);
                startActivity(intent);
                finish();
                break;

        }
    }
}
