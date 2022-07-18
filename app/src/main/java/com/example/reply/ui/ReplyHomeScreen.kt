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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType
import com.example.reply.ui.utils.ReplyContentType
import com.example.reply.ui.utils.ReplyNavigationType

/**
 * Composable that displays home screen that adapt depending on [navigationType]
 * and [contentType]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyHomeScreen(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    replyUIState: ReplyUIState,
    onTabPressed: (MailboxType) -> Unit = {},
    onEmailCardPressed: (Email) -> Unit = {},
    onDetailScreenBackPressed: () -> Unit = {}
) {
    if (navigationType == ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                NavigationDrawerContent(
                    selectedDestination = replyUIState.currentMailbox,
                    onTabPressed = onTabPressed
                )
            }
        ) {
            ReplyAppContent(
                navigationType = navigationType,
                contentType = contentType,
                replyUIState = replyUIState,
                onTabPressed = onTabPressed,
                onEmailCardPressed = onEmailCardPressed
            )
        }
    } else {
        if (replyUIState.isShowingHomepage) {
            ReplyAppContent(
                navigationType = navigationType,
                contentType = contentType,
                replyUIState = replyUIState,
                onTabPressed = onTabPressed,
                onEmailCardPressed = onEmailCardPressed
            )
        } else {
            ReplyDetailsScreen(
                replyUIState = replyUIState,
                isFullScreen = true,
                onBackButtonClicked = onDetailScreenBackPressed,
            )
        }

    }
}

/**
 * Composable that displays content at home screen.
 * It displays different navigation component depending on [navigationType],
 * It displays different number of panes depending on [contentType]
 */
@Composable
fun ReplyAppContent(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    replyUIState: ReplyUIState,
    onTabPressed: ((MailboxType) -> Unit) = {},
    onEmailCardPressed: (Email) -> Unit = {}
) {
    Row(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == ReplyNavigationType.NAVIGATION_RAIL) {
            ReplyNavigationRail(
                currentTab = replyUIState.currentMailbox,
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
                    replyUIState = replyUIState,
                    onEmailCardPressed = onEmailCardPressed,
                    modifier = Modifier.weight(1f)
                )
            } else {
                ReplyListOnlyContent(
                    replyUIState = replyUIState,
                    onEmailCardPressed = onEmailCardPressed,
                    modifier = Modifier.weight(1f)
                )
            }

            AnimatedVisibility(visible = navigationType == ReplyNavigationType.BOTTOM_NAVIGATION) {
                ReplyBottomNavigationBar(
                    currentTab = replyUIState.currentMailbox,
                    onTabPressed = onTabPressed
                )
            }
        }
    }
}

/**
 * Component that displays Navigation Rail
 */
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

/**
 * Component that displays Bottom Navigation Bar
 */
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

/**
 * Component that displays Navigation Drawer
 */
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
            .padding(12.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReplyLogo()
            ReplyProfileImage(
                drawableResource = R.drawable.avatar_6,
                description = stringResource(id = R.string.profile),
                modifier = Modifier
                    .size(28.dp)
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
