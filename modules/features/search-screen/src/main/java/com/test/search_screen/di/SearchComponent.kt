package com.test.search_screen.di

import com.test.search_screen.SearchFragment
import dagger.Component

@Component(
    dependencies = [SearchDependencies::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)
}