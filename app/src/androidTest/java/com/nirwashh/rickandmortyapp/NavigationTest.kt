package com.nirwashh.rickandmortyapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.nirwashh.rickandmortyapp.core.presentation.MainActivity
import com.nirwashh.rickandmortyapp.locations.presentation.list.adapters.LocationAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NavigationTest {
    private lateinit var characters: Page.CharactersListPage
    private lateinit var characterDetails: Page.CharacterDetailsPage
    private lateinit var episodes: Page.EpisodeListPage
    private lateinit var episodeDetails: Page.EpisodeDetailsPage
    private lateinit var locations: Page.LocationListPage
    private lateinit var locationDetails: Page.LocationDetailsPage
    private lateinit var activity: Page.MainActivity

    @get: Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        characters = Page.CharactersListPage()
        characterDetails = Page.CharacterDetailsPage()
        episodes = Page.EpisodeListPage()
        episodeDetails = Page.EpisodeDetailsPage()
        locations = Page.LocationListPage()
        locationDetails = Page.LocationDetailsPage()
        activity = Page.MainActivity()
    }


    @Test
    fun navigation_to_character_detail_test() {
        onView(RecyclerViewMatcher(characters.recyclerView).atPosition(0)).perform(click())
    }

    @Test
    fun navigation_back_from_character_detail_test() {
        onView(RecyclerViewMatcher(characters.recyclerView).atPosition(0)).perform(click())
        onView(withId(characterDetails.buttonBack)).perform(click())
    }

    @Test
    fun navigation_to_episode_screen() {
        onView(withId(activity.episodePage)).perform(click())
    }

    @Test
    fun navigation_to_location_screen() {
        onView(withId(activity.locationPage)).perform(click())
    }

    @Test
    fun navigation_to_episode_detail_test() {
        onView(withId(activity.episodePage)).perform(click())
        onView(RecyclerViewMatcher(episodes.recyclerView).atPosition(0)).perform(click())
    }

    @Test
    fun navigation_back_from_episode_detail_test() {
        onView(withId(activity.episodePage)).perform(click())
        onView(RecyclerViewMatcher(episodes.recyclerView).atPosition(0)).perform(click())
        onView(withId(episodeDetails.buttonBack)).perform(click())
    }

    @Test
    fun navigation_to_location_detail_test() {
        onView(withId(activity.locationPage)).perform(click())
        onView(withId(locations.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<LocationAdapter.LocationViewHolder>(
                3
            )
        )
        onView(withId(locations.recyclerView)).perform(
            actionOnItemAtPosition<LocationAdapter.LocationViewHolder>(
                3,
                click()
            )
        )
    }

    @Test
    fun navigation_back_from_location_detail_test() {
        onView(withId(activity.locationPage)).perform(click())
        onView(RecyclerViewMatcher(locations.recyclerView).atPosition(0)).perform(click())
        onView(withId(locationDetails.buttonBack)).perform(click())
    }
}