package com.nirwashh.rickandmortyapp.locations.presentation.detail.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi;
import com.nirwashh.rickandmortyapp.core.App;
import com.nirwashh.rickandmortyapp.core.presentation.Navigation;
import com.nirwashh.rickandmortyapp.core.utils.StringParser;
import com.nirwashh.rickandmortyapp.databinding.FragmentLocationDetailsBinding;
import com.nirwashh.rickandmortyapp.locations.presentation.detail.adapters.LocationDetailAdapter;
import com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel.LocationDetailViewModel;
import com.nirwashh.rickandmortyapp.locations.presentation.detail.viewmodel.LocationDetailViewModelFactory;
import com.nirwashh.rickandmortyapp.locations.presentation.model.LocationUi;

import java.util.ArrayList;

import javax.inject.Inject;

public class LocationDetailsFragment extends Fragment implements LocationDetailAdapter.Listener {
    private FragmentLocationDetailsBinding binding;
    private Navigation navigation;
    private LocationDetailViewModel viewModel;
    private LocationUi location;
    private LocationDetailAdapter locationDetailAdapter;
    private static final String LOCATION = "location";

    @Inject
    LocationDetailViewModelFactory vmFactory;

    @Override
    public void onAttach(@NonNull Context context) {
        App app = (App) context.getApplicationContext();
        app.appComponent.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigation = (Navigation) getActivity();
        viewModel = new ViewModelProvider(this, vmFactory).get(LocationDetailViewModel.class);
        location = getArguments().getParcelable(LOCATION);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLocation();
        setupRecyclerView();
        setLiveData();
        observeLiveData();
        binding.btnBackLocationDetail.setOnClickListener(view1 ->
                getParentFragmentManager().popBackStack()
        );

    }

    private void observeLiveData() {
        viewModel.charactersLiveData.observe(getViewLifecycleOwner(), characters -> {
            setupCharacters((ArrayList<CharacterUi>) characters);
        });
    }

    private void setLiveData() {
        if (!location.getResidents().isEmpty()) {
            viewModel.setCharactersLiveData(StringParser.idsListParser(location.getResidents()));
        }
    }

    private void setupRecyclerView() {
        locationDetailAdapter = new LocationDetailAdapter(new ArrayList<>(), this);
        binding.rvCharacters.setAdapter(locationDetailAdapter);
        binding.rvCharacters.setLayoutManager(new GridLayoutManager(requireContext(), 2));

    }

    private void setupLocation() {
        binding.tvTitle.setText(location.getName());
        binding.created.setText(location.getCreated());
        binding.type.setText(location.getType());
        binding.dimension.setText(location.getDimension());
    }

    private void setupCharacters(ArrayList<CharacterUi> characters) {
        locationDetailAdapter.characters.addAll(characters);
        locationDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int characterId) {
        navigation.navigateToCharacterDetails(viewModel.getCharacter(characterId));
    }

    public static LocationDetailsFragment newInstance(LocationUi location) {
        LocationDetailsFragment myFragment = new LocationDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(LOCATION, location);
        myFragment.setArguments(args);
        return myFragment;
    }
}
