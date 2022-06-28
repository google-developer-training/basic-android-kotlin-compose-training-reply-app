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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType

@Composable
fun ReplyListOnlyContent(
    replyHomeUIState: ReplyHomeUIState,
    onEmailCardPressed: (MailboxType, Int) -> Unit = { _: MailboxType, _: Int -> },
    modifier: Modifier = Modifier
) {
    val emails = replyHomeUIState.getEmailsForMailbox()
    val onCardClick: (Int) -> () -> Unit =
        { selectedCardIndex ->
            {
                onEmailCardPressed(
                    replyHomeUIState.currentMailbox,
                    selectedCardIndex
                )
            }
        }

    LazyColumn(modifier = modifier) {
        item {
            ReplyTopBar(modifier = Modifier.fillMaxWidth())
        }
        itemsIndexed(emails) { index, email ->
            ReplyEmailListItem(email = email, onCardClick = onCardClick(index))
        }
    }
}

@Composable
fun ReplyListAndDetailContent(
    replyHomeUIState: ReplyHomeUIState,
    onEmailCardPressed: (MailboxType, Int) -> Unit = { _: MailboxType, _: Int -> },
    modifier: Modifier = Modifier
) {
    val emails = replyHomeUIState.getEmailsForMailbox()
    var selectedItemIndex =
        replyHomeUIState.selectedEmailIndex[replyHomeUIState.currentMailbox] ?: 0

    val onCardClick: (Int) -> () -> Unit =
        { selectedCardIndex ->
            {
                onEmailCardPressed(
                    replyHomeUIState.currentMailbox,
                    selectedCardIndex
                )
            }
        }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                ReplyTopBar(modifier = Modifier.fillMaxWidth())
            }
            itemsIndexed(emails) { index, email ->
                ReplyEmailListItem(email = email, onCardClick = onCardClick(index))
            }
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                ReplyEmailDetailItem(
                    email = emails[selectedItemIndex],
                    mailboxType = replyHomeUIState.currentMailbox
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyEmailListItem(
    email: Email,
    onCardClick: () -> Unit = {},
    modifier: Modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
) {
    Card(
        modifier = modifier,
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                ReplyProfileImage(
                    drawableResource = email.sender.avatar,
                    description = email.sender.fullName,
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
                        text = email.createAt,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

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
fun ReplyProfileImage(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier.size(40.dp),
) {
    Image(
        modifier = modifier.clip(CircleShape),
        painter = painterResource(id = drawableResource),
        contentDescription = description,
    )
}

@Composable
fun ReplyTopBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(bottom = 12.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Box(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(
                    text = stringResource(id = R.string.app_name).uppercase(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                ReplyProfileImage(
                    drawableResource = R.drawable.avatar_6,
                    description = stringResource(id = R.string.profile),
                    modifier = Modifier
                        .size(48.dp)
                )
            }
        }
        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}
