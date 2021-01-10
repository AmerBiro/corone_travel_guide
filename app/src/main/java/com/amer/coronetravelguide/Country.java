package com.amer.coronetravelguide;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amer.coronetravelguide.databinding.CountryCountryBinding;
import com.amer.coronetravelguide.mvvm.ContinentListModel;
import com.amer.coronetravelguide.mvvm.ContinentListViewModel;
import com.amer.coronetravelguide.mvvm.CountryListAdapter;
import com.amer.coronetravelguide.mvvm.CountryListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class Country extends Fragment implements CountryListAdapter.OnCountryListItemClicked {
    private @NonNull
    CountryCountryBinding
            binding;
    private NavController controller;
    private FirebaseFirestore firebaseFirestore;
    private ContinentListViewModel continentListViewModel;
    private int position, countryPosition;
    private String continentId, coountryId;
    private List<CountryListModel> countryListModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private CountryListAdapter adapter;
    private Boolean status = false;
    private ImageHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CountryCountryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        position = CountryArgs.fromBundle(getArguments()).getPosition();
        controller = Navigation.findNavController(view);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerViewSetup();
        handler = new ImageHandler();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        continentListViewModel = new ViewModelProvider(getActivity()).get(ContinentListViewModel.class);
        continentListViewModel.getContinentListModelData().observe(getViewLifecycleOwner(), new Observer<List<ContinentListModel>>() {
            @Override
            public void onChanged(List<ContinentListModel> continentListModels) {
                continentId = continentListModels.get(position).getContinentListId();

                Query countryListRef = firebaseFirestore
                        .collection("ContinentList").document(continentId)
                        .collection("CountryList").orderBy("name");

                countryListRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        Log.d(TAG, "onEvent: " + value.getDocumentChanges().toString());
                        countryListModels = value.toObjects(CountryListModel.class);

//                        Log.d(TAG, "daySize: " + dayListModels.size());
                        adapter.setCountryListsModels(countryListModels);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void recyclerViewSetup() {
        recyclerView = binding.recyclerview;
        adapter = new CountryListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        String link = countryListModels.get(position).getLink();
        if (link.equals("") || link == null){
            Toast.makeText(getContext(), "Error loading data", 0).show();
            return;
        }
            Intent brower = new Intent(Intent.ACTION_VIEW);
            brower.setData(Uri.parse(link));
            getActivity().startActivity(brower);


        countryPosition = position;
        coountryId = countryListModels.get(position).getCountryListId();
//        openGallery(1000);
        Log.d(TAG, "countryListOnItemClicked: " + position);
    }

//    public void openGallery(int requestCode){
//        Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(openGallery, requestCode);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1000) {
//            if (resultCode == Activity.RESULT_OK) {
//                Log.d(TAG, "onActivityResult: "
//                        + position + ", "
//                        + countryPosition + ", "
//                        + continentId + ", "
//                        + coountryId);
//                handler.uploadeImageToFirebase(data, getActivity(),
//                        continentId, coountryId);
//            }
//        }
//    }
}



