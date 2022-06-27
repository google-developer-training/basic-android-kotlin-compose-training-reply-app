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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.MailboxType
import com.example.reply.ui.utils.ReplyContentType
import com.example.reply.ui.utils.ReplyNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyApp(
    replyHomeUIState: ReplyHomeUIState,
    windowSize: WindowWidthSizeClass,
    onEmailCardClick: (MailboxType, Int) -> Unit = { _: MailboxType, _: Int -> }
) {
    /**
     * This will help us select type of navigation and content type depending on window size
     */
    val navigationType: ReplyNavigationType
    val contentType: ReplyContentType

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
    ReplyNavigationWrapperUI(navigationType, contentType, replyHomeUIState, onEmailCardClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReplyNavigationWrapperUI(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    replyHomeUIState: ReplyHomeUIState,
    onEmailCardClick: (MailboxType, Int) -> Unit = { _: MailboxType, _: Int -> }
) {
    var selectedTab by rememberSaveable { mutableStateOf(MailboxType.Inbox) }
    val onTabClicked: (MailboxType) -> Unit = { mailboxType -> selectedTab = mailboxType }

    if (navigationType == ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                NavigationDrawerContent(
                    selectedDestination = selectedTab,
                    onTabClicked = onTabClicked
                )
            }
        ) {
            ReplyAppContent(
                navigationType = navigationType,
                contentType = contentType,
                replyHomeUIState = replyHomeUIState,
                currentTab = selectedTab,
                onEmailCardClick = onEmailCardClick
            )
        }
    } else {
        ReplyAppContent(
            navigationType = navigationType,
            contentType = contentType,
            replyHomeUIState = replyHomeUIState,
            currentTab = selectedTab,
        )
    }
}

@Composable
fun ReplyAppContent(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    replyHomeUIState: ReplyHomeUIState,
    currentTab: MailboxType,
    onEmailCardClick: (MailboxType, Int) -> Unit = { _: MailboxType, _: Int -> }
) {
    Row(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == ReplyNavigationType.NAVIGATION_RAIL) {
            ReplyNavigationRail()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            if (contentType == ReplyContentType.LIST_AND_DETAIL) {
                ReplyListAndDetailContent(
                    replyHomeUIState = replyHomeUIState,
                    mailboxType = currentTab,
                    modifier = Modifier.weight(1f),
                    onCardClick = onEmailCardClick
                )
            } else {
                ReplyListOnlyContent(
                    replyHomeUIState = replyHomeUIState,
                    mailboxType = currentTab,
                    modifier = Modifier.weight(1f)
                )
            }

            AnimatedVisibility(visible = navigationType == ReplyNavigationType.BOTTOM_NAVIGATION) {
                ReplyBottomNavigationBar()
            }
        }
    }
}

@Composable
fun ReplyNavigationRail() {
    NavigationRail(modifier = Modifier.fillMaxHeight()) {
        NavigationRailItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Inbox,
                    contentDescription = stringResource(id = R.string.tab_inbox)
                )
            }
        )
        NavigationRailItem(
            selected = false,
            onClick = {/*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    stringResource(id = R.string.tab_sent)
                )
            }
        )
        NavigationRailItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Drafts, stringResource(id = R.string.tab_drafts)
                )
            }
        )
        NavigationRailItem(
            selected = false,
            onClick = { /*TODO*/ },
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
fun ReplyBottomNavigationBar() {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Inbox,
                    contentDescription = stringResource(id = R.string.tab_inbox)
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(id = R.string.tab_sent)
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Drafts,
                    contentDescription = stringResource(id = R.string.tab_drafts)
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
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
    onTabClicked: ((MailboxType) -> Unit) = {}
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
            onClick = { onTabClicked(MailboxType.Inbox) }
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
            onClick = { onTabClicked(MailboxType.Sent) }
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
            onClick = { onTabClicked(MailboxType.Drafts) }
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
            onClick = { onTabClicked(MailboxType.Spam) }
        )
    }
}
