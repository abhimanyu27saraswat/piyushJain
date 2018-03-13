package com.purplesweetbox.piyushjain.View;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.purplesweetbox.piyushjain.R;
import com.purplesweetbox.piyushjain.model.Trail;
import com.purplesweetbox.piyushjain.model.User;

import java.util.HashMap;
import java.util.Map;

public class AddTrailActivity extends AppCompatActivity implements View.OnClickListener {

    private User thisUser;
    private String TAG = "Add Trail Activity";
    private Trail thisTrail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trail);
        Button addTrailButton = findViewById(R.id.btn_add_trail);
        thisUser = getIntent().getExtras().getParcelable("user");
        addTrailButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add_trail){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            final EditText trailName = findViewById(R.id.trail_name);
            Map<String, Object> trail = new HashMap<>();
            trail.put("trainerId", thisUser.userId);
            trail.put("trailName", String.valueOf(trailName.getText()));
            final ProgressBar pb = findViewById(R.id.pb_add_trail);
            pb.setVisibility(View.VISIBLE);
            db.collection("trails")
                    .add(trail)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            pb.setVisibility(View.GONE);
                            thisTrail = new Trail();
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            thisTrail.trailId = documentReference.getId();
                            thisTrail.trainerId = thisUser.userId;
                            thisTrail.trailName = String.valueOf(trailName.getText());
                            findViewById(R.id.ll_add_trail_view).setVisibility(View.GONE);
                            TextView tv_add_trail_title =findViewById(R.id.tv_add_trail_title);
                            tv_add_trail_title.setText(thisTrail.trailName);
                            tv_add_trail_title.setVisibility(View.VISIBLE);
                            findViewById(R.id.cv_trail_station).setVisibility(View.VISIBLE);
                            findViewById(R.id.cv_trail_station).setOnClickListener(AddTrailActivity.this);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                            trailName.setError(e.getMessage());
                        }
                    });


        }else{
            Intent intent = new Intent(this,AddTrailStationActivity.class);
            intent.putExtra("trail",thisTrail);
            intent.putExtra("user",thisUser);
            startActivity(intent);
        }

    }
}
