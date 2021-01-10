package com.amer.coronetravelguide.mvvm;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static android.content.ContentValues.TAG;


public class FirebaseRepository {


    private OnFirestoreTaskComplete onFirestoreTaskComplete;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private Query continentListRef = firebaseFirestore
            .collection("ContinentList").orderBy("number");

    public FirebaseRepository(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }

    public void getContinentListData() {
/*        continentListRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFirestoreTaskComplete.continentListDataAdded(task.getResult().toObjects(ContinentListModel.class));
                } else {
                    onFirestoreTaskComplete.onError(task.getException());
                }
            }
        });*/


        continentListRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                Log.d(TAG, "onEvent: " + value.getDocumentChanges().toString());
                List<ContinentListModel> continentListModels = value.toObjects(ContinentListModel.class);
                onFirestoreTaskComplete.continentListDataAdded(continentListModels);
            }
        });
    }


    public interface OnFirestoreTaskComplete {
        void continentListDataAdded(List<ContinentListModel> continentListModels);

        void onError(Exception e);
    }

}