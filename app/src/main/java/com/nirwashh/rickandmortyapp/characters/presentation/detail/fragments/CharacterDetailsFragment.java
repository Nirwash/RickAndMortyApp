package com.nirwashh.rickandmortyapp.characters.presentation.detail.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nirwashh.rickandmortyapp.characters.data.model.Character;
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.CharacterDetailsAdapter;
import com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters.DetailsRecyclerViewItem;
import com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel.CharacterDetailViewModel;
import com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel.CharacterDetailViewModelFactory;
import com.nirwashh.rickandmortyapp.core.App;
import com.nirwashh.rickandmortyapp.core.presentation.Navigation;
import com.nirwashh.rickandmortyapp.core.utils.StringParser;
import com.nirwashh.rickandmortyapp.databinding.FragmentCharacterDetailsBinding;
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode;
import com.nirwashh.rickandmortyapp.locations.data.model.Location;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

public class CharacterDetailsFragment extends Fragment implements CharacterDetailsAdapter.Listener {
    private FragmentCharacterDetailsBinding binding;
    private Navigation navigation;
    private CharacterDetailViewModel viewModel;
    private Character character;
    private CharacterDetailsAdapter characterDetailAdapter;
    private ArrayList<Episode> episodes;
    private Location location;
    private Location origin;
    @Inject
    public CharacterDetailViewModelFactory vmFactory;
    private static final String CHARACTER = "character";
    private static final String LOCATION = "LOCATION: ";
    private static final String ORIGIN = "ORIGIN: ";
    private static final String EPISODES = "EPISODES: ";

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
        viewModel = new ViewModelProvider(this, vmFactory).get(CharacterDetailViewModel.class);
        character = getArguments().getParcelable(CHARACTER);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setsLiveData();
        observeLiveData();
        binding.btnBack.setOnClickListener(view1 ->
                getParentFragmentManager().popBackStack()
        );
    }

    private void observeLiveData() {
        viewModel.episodesLiveData.observe(getViewLifecycleOwner(), it -> {
            episodes = (ArrayList<Episode>) it;
            updateUi();
        });
        viewModel.locationLiveData.observe(getViewLifecycleOwner(), it -> {
            location = it;
        });
        viewModel.originLiveData.observe(getViewLifecycleOwner(), it -> {
            origin = it;
        });
    }

    private void updateUi() {
        characterDetailAdapter.getViewItems().clear();
        characterDetailAdapter.getViewItems().addAll(createViewItems(character));
        characterDetailAdapter.notifyDataSetChanged();
    }

    private Collection<? extends DetailsRecyclerViewItem> createViewItems(Character character) {
        ArrayList<DetailsRecyclerViewItem> list = new ArrayList<>();
        list.add(
                new DetailsRecyclerViewItem.CharacterViewItem(
                        character.getCreated(),
                        character.getGender(),
                        character.getImage(),
                        character.getName(),
                        character.getSpecies(),
                        character.getSpecies(),
                        character.getType()
                )
        );
        list.add(
                new DetailsRecyclerViewItem.TitleViewItem(LOCATION)
        );
        list.add(
                new DetailsRecyclerViewItem.LocationViewItem(
                        character.getLocation().getName(),
                        character.getLocation().getId()
                )
        );

        list.add(
                new DetailsRecyclerViewItem.TitleViewItem(ORIGIN)
        );
        list.add(
                new DetailsRecyclerViewItem.OriginViewItem(
                        character.getOrigin().getName(),
                        character.getOrigin().getId()
                )
        );
        list.add(
                new DetailsRecyclerViewItem.TitleViewItem(EPISODES)
        );
        for (Episode episode : episodes) {
            list.add(
                    new DetailsRecyclerViewItem.EpisodeViewItem(
                            episode.getName(),
                            episode.getEpisode(),
                            episode.getId()
                    )
            );
        }
        return list;
    }

    private void setsLiveData() {
        if (!character.getEpisode().isEmpty()) {
            viewModel.setEpisodesLiveData(StringParser.idsParser(character.getEpisode()));
        }
        if (!character.getLocation().getName().equals("unknown")) {
            viewModel.setLocationLiveData(StringParser.idParser(character.getLocation().getUrl()));
        }
        if (!character.getOrigin().getName().equals("unknown")) {
            viewModel.setOriginLiveData(StringParser.idParser(character.getOrigin().getUrl()));
        }
    }

    private void setupRecyclerView() {
        characterDetailAdapter = new CharacterDetailsAdapter(new ArrayList<>(), this);
        binding.rvCharacterDetails.setAdapter(characterDetailAdapter);
        binding.rvCharacterDetails.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onClickEpisode(@NonNull DetailsRecyclerViewItem.EpisodeViewItem episodeViewItem) {
        for (Episode episode : episodes) {
            if (episode.getId() == episodeViewItem.getId()) {
                navigation.navigateToEpisodeDetails(episode);
            }
        }
    }

    @Override
    public void onClickLocation() {
        if (location != null) {
            navigation.navigateToLocationDetails(location);
        }
    }

    @Override
    public void onClickOrigin() {
        if (origin != null) {
            navigation.navigateToLocationDetails(origin);
        }
    }

    public static CharacterDetailsFragment newInstance(Character character) {
        CharacterDetailsFragment fragment = new CharacterDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(CHARACTER, character);
        fragment.setArguments(args);
        return fragment;
    }
}