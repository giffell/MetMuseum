package com.test.metmuseum

import androidx.test.espresso.Espresso
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class MetMuseumUiTest : TestCase() {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkSearchScreen() = run {
        val searchQuery = "sunflower"
        val searchResultTotalCount = 89
        step("Start SearchFragment and check searchView visibility") {
            SearchFragmentScreen {
                searchView.isVisible()
            }
        }
        step("Type `sunflower`") {
            SearchFragmentScreen {
                searchView.click()
                searchView.typeText(searchQuery)
                Espresso.closeSoftKeyboard()
            }
        }
        step("Check input text") {
            SearchFragmentScreen {
                searchView.hasText(searchQuery)
            }
        }
        step("Click on search button") {
            SearchFragmentScreen {
                searchActionButton.click()
            }
        }
        step("Check search results") {
            SearchFragmentScreen {
                flakySafely(timeoutMs = 5000L) {
                    assert(searchResultList.getSize() == searchResultTotalCount)
                }
            }
        }
        step("Scroll to the end of list and click on last item") {
            SearchFragmentScreen {
                searchResultList.scrollToEnd()
                flakySafely(timeoutMs = 2000L) {
                    searchResultList.childAt<SearchResultItem>(searchResultTotalCount - 1) {
                        click()
                        sleep(2000L)
                    }
                }
            }
        }
    }
}