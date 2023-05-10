package com.nirwashh.rickandmortyapp.characters.presentation.detail.adapters;

import org.jetbrains.annotations.NotNull;

public abstract class DetailsRecyclerViewItem {
    public static final class CharacterViewItem extends DetailsRecyclerViewItem {
        @NotNull
        private final String created;
        @NotNull
        private final String gender;
        @NotNull
        private final String image;
        @NotNull
        private final String name;
        @NotNull
        private final String species;
        @NotNull
        private final String status;
        @NotNull
        private final String type;

        public CharacterViewItem(
                @NotNull String created,
                @NotNull String gender,
                @NotNull String image,
                @NotNull String name,
                @NotNull String species,
                @NotNull String status,
                @NotNull String type) {
            this.created = created;
            this.gender = gender;
            this.image = image;
            this.name = name;
            this.species = species;
            this.status = status;
            this.type = type;
        }

        @NotNull
        public final String getCreated() {
            return this.created;
        }

        @NotNull
        public final String getGender() {
            return this.gender;
        }

        @NotNull
        public final String getImage() {
            return this.image;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final String getSpecies() {
            return this.species;
        }

        @NotNull
        public final String getStatus() {
            return this.status;
        }

        @NotNull
        public final String getType() {
            return this.type;
        }
    }

    public static final class LocationViewItem extends DetailsRecyclerViewItem {
        @NotNull
        private final String name;
        private final int id;

        @NotNull
        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.id;
        }

        public LocationViewItem(@NotNull String name, int id) {
            this.name = name;
            this.id = id;
        }
    }

    public static final class OriginViewItem extends DetailsRecyclerViewItem {
        @NotNull
        private final String name;
        private final int id;

        @NotNull
        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.id;
        }

        public OriginViewItem(@NotNull String name, int id) {
            this.name = name;
            this.id = id;
        }
    }

    public static final class TitleViewItem extends DetailsRecyclerViewItem {
        @NotNull
        private final String title;

        @NotNull
        public String getTitle() {
            return this.title;
        }

        public TitleViewItem(@NotNull String title) {
            this.title = title;
        }
    }

    public static final class EpisodeViewItem extends DetailsRecyclerViewItem {
        @NotNull
        private final String name;
        @NotNull
        private final String episode;
        private final int id;

        @NotNull
        public String getName() {
            return this.name;
        }

        @NotNull
        public String getEpisode() {
            return this.episode;
        }

        public int getId() {
            return this.id;
        }

        public EpisodeViewItem(@NotNull String name, @NotNull String episode, int id) {
            this.name = name;
            this.episode = episode;
            this.id = id;
        }
    }
}
