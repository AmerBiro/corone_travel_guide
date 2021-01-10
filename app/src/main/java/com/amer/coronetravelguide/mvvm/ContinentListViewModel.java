package com.amer.coronetravelguide.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContinentListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {

    private MutableLiveData<List<ContinentListModel>> continentListModelData = new MutableLiveData<>();

    public LiveData<List<ContinentListModel>> getContinentListModelData() {
        return continentListModelData;
    }

    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public ContinentListViewModel() {
        firebaseRepository.getContinentListData();
    }


    @Override
    public void continentListDataAdded(List<ContinentListModel> continentListModels) {
        continentListModelData.setValue(continentListModels);
    }

    @Override
    public void onError(Exception e) {

    }
}
