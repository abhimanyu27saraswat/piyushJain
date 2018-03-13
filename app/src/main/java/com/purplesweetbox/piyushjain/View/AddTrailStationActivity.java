package com.purplesweetbox.piyushjain.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.purplesweetbox.piyushjain.R;
import com.purplesweetbox.piyushjain.model.Trail;
import com.purplesweetbox.piyushjain.model.User;

import java.util.HashMap;
import java.util.Map;

public class AddTrailStationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Add Trail Station";
    private User thisUser;
    private Trail thisTrail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trail_station);
        Button addTrailStationButton = findViewById(R.id.btn_add_trail_station);
        thisUser = getIntent().getExtras().getParcelable("user");
        thisTrail = getIntent().getExtras().getParcelable("trail");
        addTrailStationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add_trail_station){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            final EditText sequence = findViewById(R.id.trail_station_sequence_no);
            final EditText loc = findViewById(R.id.trail_station_GPSLocation);
            final EditText name = findViewById(R.id.trail_station_name);
            final EditText instructions = findViewById(R.id.instructions);
            Map<String, Object> trail = new HashMap<>();
            trail.put("sequenceNo", Integer.parseInt(String.valueOf(sequence.getText())));
            trail.put("GPSLocation",String.valueOf(loc.getText() ));
            trail.put("trailStationName",String.valueOf( name.getText()));
            trail.put("learningTrailId", thisTrail.trailId);
            trail.put("instructions",String.valueOf(instructions.getText()));
            final ProgressBar pb = findViewById(R.id.pb_add_trail_station);
            pb.setVisibility(View.VISIBLE);
            db.collection("trails")
                    .add(trail)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            pb.setVisibility(View.GONE);
                            thisTrail = new Trail();
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(AddTrailStationActivity.this, "Trail Station Successfully Added", Toast.LENGTH_SHORT).show();
                            sequence.setText("");
                            loc.setText("");
                            name.setText("");
                            instructions.setText("");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                            Toast.makeText(AddTrailStationActivity.this, "Error adding document: "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
