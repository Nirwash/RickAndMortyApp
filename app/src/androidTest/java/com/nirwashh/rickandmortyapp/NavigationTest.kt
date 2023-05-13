package com.nirwashh.rickandmortyapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import org.junit.Test

class NavigationTest {


    @Test
    fun navigation_to_character_detail_test() {
        val characters = CharactersListPage()
        val characterDetails = CharacterDetailsPage()

        onView(RecyclerViewMatcher(characters.recyclerView).atPosition(0)).perform(ViewActions.click())


    }
}