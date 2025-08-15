package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_verifyUsingBottomNavigation() {
        // Set up compact window
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }

        // Assert
        composeTestRule.onNodeWithTagForStringId(
            com.example.reply.R.string.navigation_bottom
        ).assertExists()
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        // Arrange
        // Act
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }
        // Assert
        composeTestRule.onNodeWithTagForStringId(
            com.example.reply.R.string.navigation_rail
        ).assertExists()
    }

    @Test
    fun expandedDevice_verifyUsingNavigationDrawer() {
        // Arrange
        // Act
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }

        // Assert
        composeTestRule.onNodeWithTagForStringId(
            com.example.reply.R.string.navigation_drawer
        ).assertExists()
    }
}