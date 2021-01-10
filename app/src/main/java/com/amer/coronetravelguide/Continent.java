package com.amer.coronetravelguide;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amer.coronetravelguide.databinding.ContinentContinentBinding;
import com.amer.coronetravelguide.mvvm.ContinentListAdapter;
import com.amer.coronetravelguide.mvvm.ContinentListModel;
import com.amer.coronetravelguide.mvvm.ContinentListViewModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Continent extends Fragment implements ContinentListAdapter.OnContinentListItemClicked, View.OnClickListener {

    private @NonNull
    ContinentContinentBinding
            binding;
    private NavController controller;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firebaseFirestore;

    private RecyclerView recyclerView;
    private ContinentListViewModel continentListViewModel;
    private ContinentListAdapter adapter;
    private String continentListId;


//    private InterstitialAd mInterstitialAd;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ContinentContinentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = binding.recyclerview;
        adapter = new ContinentListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView1.loadAd(adRequest);
        binding.adView2.loadAd(adRequest);
        binding.adView3.loadAd(adRequest);

        AdView adView1 = new AdView(getActivity());
        adView1.setAdSize(AdSize.BANNER);
        adView1.setAdUnitId("ca-app-pub-4985467669699847/3318502135");

        AdView adView2 = new AdView(getActivity());
        adView2.setAdSize(AdSize.BANNER);
        adView2.setAdUnitId("ca-app-pub-4985467669699847/4789891120");

        AdView adView3 = new AdView(getActivity());
        adView3.setAdSize(AdSize.BANNER);
        adView3.setAdUnitId("ca-app-pub-4985467669699847/3506204390");


        recyclerView.setAdapter(adapter);
//        mInterstitialAd = newInterstitialAd();
//        loadInterstitial();

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        continentListViewModel = new ViewModelProvider(getActivity()).get(ContinentListViewModel.class);
        continentListViewModel.getContinentListModelData().observe(getViewLifecycleOwner(), new Observer<List<ContinentListModel>>() {
            @Override
            public void onChanged(List<ContinentListModel> continentListModels) {
                adapter.setContinentListsModels(continentListModels);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
//        showInterstitial();
        Log.d(TAG, "continentListOnItemClicked: " + position);
        ContinentDirections.ActionContinentToCountry action =
                ContinentDirections.actionContinentToCountry();
        action.setPosition(position);
        controller.navigate(action);
        adapter.notifyItemChanged(position);
    }

    @Override
    public void onClick(View v) {

    }

//    private InterstitialAd newInterstitialAd() {
//        InterstitialAd interstitialAd = new InterstitialAd(getActivity());
//        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
////        interstitialAd.setAdUnitId("ca-app-pub-4985467669699847/5041172569");
//        interstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//
//            }
//
//            @Override
//            public void onAdClosed() {
//
//            }
//        });
//        return interstitialAd;
//    }
//
//    private void loadInterstitial() {
//
//        AdRequest adRequest = new AdRequest.Builder()
//                .setRequestAgent("android_studio:ad_template").build();
//        mInterstitialAd.loadAd(adRequest);
//    }
//
//    private void showInterstitial() {
//        // Show the ad if it"s ready. Otherwise toast and reload the ad.
//        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//
//        } else {
//            Toast.makeText(getActivity(), "Ad did not load", 0).show();
//
//        }
//    }


}