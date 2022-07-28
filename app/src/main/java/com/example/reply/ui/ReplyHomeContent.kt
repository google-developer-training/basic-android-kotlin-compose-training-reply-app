/*
 * Copyright 2022 The Android Open Source Project
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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.local.LocalAccountsDataProvider

/**
 * Component that displays a single pane of list of emails
 */
@Composable
fun ReplyListOnlyContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    val emails = replyUiState.currentMailboxEmails

    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item {
            ReplyHomeTopBar(modifier = Modifier.fillMaxWidth())
        }
        items(emails, key = { email -> email.id }) { email ->
            ReplyEmailListItem(
                email = email,
                onCardClick = {
                    onEmailCardPressed(email)
                }
            )
        }
    }
}

/**
 * Component that displays two panes of list of emails and email details
 */
@Composable
fun ReplyListAndDetailContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    val emails = replyUiState.currentMailboxEmails
    Row(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp, top = 20.dp)
        ) {
            items(emails, key = { email -> email.id }) { email ->
                ReplyEmailListItem(
                    email = email,
                    onCardClick = {
                        onEmailCardPressed(email)
                    }
                )
            }
        }
        ReplyDetailsScreen(
            replyUiState = replyUiState,
            modifier = Modifier.weight(1f),
            onBackPressed = {}
        )
    }
}

/**
 * Component that displays a single email list item
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyEmailListItem(
    email: Email,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(vertical = 4.dp),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            ReplyEmailItemHeader(email)
            Text(
                text = email.subject,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = email.body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ReplyEmailItemHeader(email: Email, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            description = email.sender.fullName,
            modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = email.sender.firstName,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = email.createdAt,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

/**
 * Component that displays profile image
 */
@Composable
fun ReplyProfileImage(
    @DrawableRes drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier.clip(CircleShape),
        painter = painterResource(drawableResource),
        contentDescription = description,
    )
}

/**
 * Component that displays Reply logo
 */
@Composable
fun ReplyLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.logo),
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}

/**
 * Component that displays top bar.
 */
@Composable
private fun ReplyHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        ReplyLogo(
            modifier = Modifier
                .size(64.dp)
                .padding(start = 4.dp)
        )
        ReplyProfileImage(
            drawableResource = LocalAccountsDataProvider.userAccount.avatar,
            description = stringResource(R.string.profile),
            modifier = Modifier
                .padding(end = 8.dp)
                .size(48.dp)
        )
    }
}
