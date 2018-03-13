package com.purplesweetbox.piyushjain.View;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.purplesweetbox.piyushjain.R;
import com.purplesweetbox.piyushjain.model.Trail;
import com.purplesweetbox.piyushjain.model.User;
import com.purplesweetbox.piyushjain.utils.TrailGridAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TrailActivity extends AppCompatActivity {

    private static final String TAG = "Trail Activity";
    private MenuItem mRoleAction;
    private MenuItem mAddTrailAction;
    private User thisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail);
        thisUser = getIntent().getExtras().getParcelable("user");
        if (getActionBar()!=null) {
            getActionBar().setTitle(thisUser.userName + " ("+ thisUser.userRole+")");
        }else if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(thisUser.userName + " ("+ thisUser.userRole+")");
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference trailsRef = db.collection("trails");
        Query query = trailsRef.whereEqualTo("trainerId", thisUser.userId);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Trail> trails = new ArrayList<>();

                    for(DocumentSnapshot doc : task.getResult()){
                        Trail e = doc.toObject(Trail.class);
                        e.trailId = doc.getId();
                        e.trailName = doc.getString("trailName");
                        e.trainerId = doc.getString("trainerId");
                        trails.add(e);
                    }
                    Log.d("event list ", String.valueOf(trails));
                    //do something with list of pojos retrieved
                    GridView gv = (GridView) findViewById(R.id.gv_trail_list);
                    TrailGridAdapter adapter = new TrailGridAdapter(TrailActivity.this, trails);
                    gv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mRoleAction = menu.findItem(R.id.action_switch_role);
        mAddTrailAction = menu.findItem(R.id.action_add_trail);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.trail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_add_trail)
        {
            if (thisUser.userRole.equals("Trainer")) {
                addTrail();
            }else{
                addPost();
            }
        }else if (id == R.id.action_switch_role){
            Intent intent = new Intent(this,UserRoleSelectionActivity.class);
            intent.putExtra("user",thisUser);
            startActivity(intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    private void addPost() {
    }

    private void addTrail() {
        Intent intent = new Intent(this,AddTrailActivity.class);
        intent.putExtra("user",thisUser);
        startActivity(intent);

    }
}
