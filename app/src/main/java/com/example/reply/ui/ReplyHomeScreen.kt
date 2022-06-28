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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Drafts
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reply.R
import com.example.reply.data.MailboxType
import com.example.reply.ui.utils.ReplyContentType
import com.example.reply.ui.utils.ReplyNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyNavHost(
    navController: NavHostController,
    replyHomeUIState: ReplyHomeUIState,
    windowSize: WindowWidthSizeClass,
    viewModel: ReplyHomeViewModel
) {
    /**
     * This will help us select type of navigation and content type depending on window size
     */
    val navigationType: ReplyNavigationType
    val contentType: ReplyContentType

    val onTabPressed = { mailboxType: MailboxType ->
        viewModel.updateCurrentMailbox(mailboxType)
    }
    var onEmailCardPressed: (MailboxType, Int) -> Unit = { mailboxType: MailboxType, index: Int ->
        viewModel.updateSelectedEmailIndex(
            mailboxType, index
        )
        navController.navigate(ReplyScreens.Details.name)
    }

    val checkWindowSize = {
        if(windowSize == WindowWidthSizeClass.Expanded) {
            navController.navigate(ReplyScreens.Home.name)
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
            onEmailCardPressed = { mailboxType: MailboxType, index: Int ->
                viewModel.updateSelectedEmailIndex(
                    mailboxType, index
                )
            }
        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
    }
    NavHost(navController = navController, startDestination = "Home", modifier = Modifier) {
        composable(ReplyScreens.Home.name) {
            ReplyHomeScreen(
                navigationType,
                contentType,
                replyHomeUIState,
                onTabPressed,
                onEmailCardPressed
            )
        }
        composable(ReplyScreens.Details.name) {
            ReplyEmailDetailItem(
                email = replyHomeUIState.getSelectedEmailForCurrentMailbox(),
                mailboxType = replyHomeUIState.currentMailbox,
                checkWindowSize = checkWindowSize
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReplyHomeScreen(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    replyHomeUIState: ReplyHomeUIState,
    onTabPressed: ((MailboxType) -> Unit) = {},
    onEmailCardPressed: (MailboxType, Int) -> Unit = { _: MailboxType, _: Int -> }
) {
    var selectedTab = replyHomeUIState.currentMailbox

    if (navigationType == ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                NavigationDrawerContent(
                    selectedDestination = selectedTab,
                    onTabPressed = onTabPressed
                )
            }
        ) {
            ReplyAppContent(
                navigationType = navigationType,
                contentType = contentType,
                replyHomeUIState = replyHomeUIState,
                onTabPressed = onTabPressed,
                onEmailCardPressed = onEmailCardPressed
            )
        }
    } else {
        ReplyAppContent(
            navigationType = navigationType,
            contentType = contentType,
            replyHomeUIState = replyHomeUIState,
            onTabPressed = onTabPressed,
            onEmailCardPressed = onEmailCardPressed
        )
    }
}

@Composable
fun ReplyAppContent(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    replyHomeUIState: ReplyHomeUIState,
    onTabPressed: ((MailboxType) -> Unit) = {},
    onEmailCardPressed: (MailboxType, Int) -> Unit = { _: MailboxType, _: Int -> }
) {
    Row(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == ReplyNavigationType.NAVIGATION_RAIL) {
            ReplyNavigationRail(
                currentTab = replyHomeUIState.currentMailbox,
                onTabPressed = onTabPressed
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            if (contentType == ReplyContentType.LIST_AND_DETAIL) {
                ReplyListAndDetailContent(
                    replyHomeUIState = replyHomeUIState,
                    modifier = Modifier.weight(1f),
                    onEmailCardPressed = onEmailCardPressed
                )
            } else {
                ReplyListOnlyContent(
                    replyHomeUIState = replyHomeUIState,
                    modifier = Modifier.weight(1f),
                    onEmailCardPressed = onEmailCardPressed
                )
            }

            AnimatedVisibility(visible = navigationType == ReplyNavigationType.BOTTOM_NAVIGATION) {
                ReplyBottomNavigationBar(
                    currentTab = replyHomeUIState.currentMailbox,
                    onTabPressed = onTabPressed
                )
            }
        }
    }
}

@Composable
fun ReplyNavigationRail(
    currentTab: MailboxType,
    onTabPressed: ((MailboxType) -> Unit) = {}
) {
    NavigationRail(modifier = Modifier.fillMaxHeight()) {
        NavigationRailItem(
            selected = currentTab == MailboxType.Inbox,
            onClick = { onTabPressed(MailboxType.Inbox) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Inbox,
                    contentDescription = stringResource(id = R.string.tab_inbox)
                )
            }
        )
        NavigationRailItem(
            selected = currentTab == MailboxType.Sent,
            onClick = { onTabPressed(MailboxType.Sent) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    stringResource(id = R.string.tab_sent)
                )
            }
        )
        NavigationRailItem(
            selected = currentTab == MailboxType.Drafts,
            onClick = { onTabPressed(MailboxType.Drafts) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Drafts, stringResource(id = R.string.tab_drafts)
                )
            }
        )
        NavigationRailItem(
            selected = currentTab == MailboxType.Spam,
            onClick = { onTabPressed(MailboxType.Spam) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Report,
                    stringResource(id = R.string.tab_spam)
                )
            }
        )
    }
}

@Composable
fun ReplyBottomNavigationBar(
    currentTab: MailboxType,
    onTabPressed: ((MailboxType) -> Unit) = {},
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = currentTab == MailboxType.Inbox,
            onClick = { onTabPressed(MailboxType.Inbox) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Inbox,
                    contentDescription = stringResource(id = R.string.tab_inbox)
                )
            }
        )
        NavigationBarItem(
            selected = currentTab == MailboxType.Sent,
            onClick = { onTabPressed(MailboxType.Sent) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(id = R.string.tab_sent)
                )
            }
        )
        NavigationBarItem(
            selected = currentTab == MailboxType.Drafts,
            onClick = { onTabPressed(MailboxType.Drafts) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Drafts,
                    contentDescription = stringResource(id = R.string.tab_drafts)
                )
            }
        )
        NavigationBarItem(
            selected = currentTab == MailboxType.Spam,
            onClick = { onTabPressed(MailboxType.Spam) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Report,
                    contentDescription = stringResource(id = R.string.tab_spam)
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerContent(
    selectedDestination: MailboxType,
    modifier: Modifier = Modifier,
    onTabPressed: ((MailboxType) -> Unit) = {}
) {
    Column(
        modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(24.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.app_name).uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        NavigationDrawerItem(
            selected = selectedDestination == MailboxType.Inbox,
            label = {
                Text(
                    text = stringResource(id = R.string.tab_inbox),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Inbox,
                    contentDescription = stringResource(id = R.string.tab_inbox)
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            onClick = { onTabPressed(MailboxType.Inbox) }
        )
        NavigationDrawerItem(
            selected = selectedDestination == MailboxType.Sent,
            label = {
                Text(
                    text = stringResource(id = R.string.tab_sent),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(id = R.string.tab_sent)
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            onClick = { onTabPressed(MailboxType.Sent) }
        )
        NavigationDrawerItem(
            selected = selectedDestination == MailboxType.Drafts,
            label = {
                Text(
                    text = stringResource(id = R.string.tab_drafts),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Drafts,
                    contentDescription = stringResource(id = R.string.tab_drafts)
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            onClick = { onTabPressed(MailboxType.Drafts) }
        )
        NavigationDrawerItem(
            selected = selectedDestination == MailboxType.Spam,
            label = {
                Text(
                    text = stringResource(id = R.string.tab_spam),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Report,
                    contentDescription = stringResource(id = R.string.tab_spam)
                )
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            onClick = { onTabPressed(MailboxType.Spam) }
        )
    }
}
