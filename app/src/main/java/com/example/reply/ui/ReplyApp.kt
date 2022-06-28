/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.reply.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reply.data.MailboxType
import com.example.reply.ui.utils.ReplyContentType
import com.example.reply.ui.utils.ReplyNavigationType

/**
 * Main composable that serves as container (NavHost)
 * which displays content according to [replyHomeUIState] and [windowSize]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyApp(
    replyHomeUIState: ReplyUIState,
    windowSize: WindowWidthSizeClass,
    viewModel: ReplyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    /**
     * This will help us select type of navigation and content type depending on window size
     */
    val navigationType: ReplyNavigationType
    val contentType: ReplyContentType

    val displayHomeScreenIfWindowSizeExpanded = {
        /**
         * If [windowSize] changes to be expanded when user is not on home screen
         * screen will adjusts to home screen
         */
        if (windowSize == WindowWidthSizeClass.Expanded) {
            if (navController.currentBackStackEntry?.destination?.route != ReplyScreens.Home.name) {
                navController.navigate(ReplyScreens.Home.name)
            }
        }
    }

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ReplyContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
    }
    NavHost(navController = navController, startDestination = ReplyScreens.Home.name, modifier = Modifier) {
        composable(ReplyScreens.Home.name) {
            ReplyHomeScreen(
                navigationType = navigationType,
                contentType = contentType,
                replyHomeUIState = replyHomeUIState,
                onTabPressed = { mailboxType: MailboxType ->
                    viewModel.updateCurrentMailbox(mailboxType)
                },
                onEmailCardPressed =
                if (windowSize == WindowWidthSizeClass.Expanded)
                    { mailboxType: MailboxType, index: Int ->
                        viewModel.updateSelectedEmailIndex(
                            mailboxType, index
                        )
                    }
                else
                    { mailboxType: MailboxType, index: Int ->
                        viewModel.updateSelectedEmailIndex(
                            mailboxType, index
                        )
                        navController.navigate(ReplyScreens.Details.name)
                    }
            )
        }
        composable(ReplyScreens.Details.name) {
            ReplyEmailDetailsScreen(
                email = replyHomeUIState.getSelectedEmailForCurrentMailbox(),
                mailboxType = replyHomeUIState.currentMailbox,
                isFullScreen = true,
                onBackButtonClicked = {
                    navController.popBackStack()
                },
                displayHomeScreenIfWindowSizeExpanded = displayHomeScreenIfWindowSizeExpanded,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}
