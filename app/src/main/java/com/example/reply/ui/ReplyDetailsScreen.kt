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

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType

/**
 * Component that displays details screen
 */
@Composable
fun ReplyDetailsScreen(
    replyUiState: ReplyUiState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            .padding(top = 24.dp)
    ) {
        item {
            ReplyDetailsScreenTopBar(onBackPressed, replyUiState)
            ReplyEmailDetailsCard(
                email = replyUiState.currentSelectedEmail,
                mailboxType = replyUiState.currentMailbox,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun ReplyDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    replyUiState: ReplyUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 40.dp)
        ) {
            Text(
                text = replyUiState.currentSelectedEmail.subject,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReplyEmailDetailsCard(
    email: Email,
    mailboxType: MailboxType,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val displayToast = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            DetailsScreenHeader(email)
            Text(
                text = email.subject,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = email.body,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            DetailsScreenButtonBar(mailboxType, displayToast)
        }
    }
}

@Composable
private fun DetailsScreenButtonBar(
    mailboxType: MailboxType,
    displayToast: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (mailboxType) {
        MailboxType.Drafts ->
            ActionButton(
                text = stringResource(id = R.string.continue_composing),
                onButtonClicked = displayToast,
                modifier = modifier
            )
        MailboxType.Spam ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                ActionButton(
                    text = stringResource(id = R.string.move_to_inbox),
                    onButtonClicked = displayToast,
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = stringResource(id = R.string.delete),
                    onButtonClicked = displayToast,
                    containIrreversibleAction = true,
                    modifier = Modifier.weight(1f)
                )
            }
        MailboxType.Sent, MailboxType.Inbox ->
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                ActionButton(
                    text = stringResource(id = R.string.reply),
                    onButtonClicked = displayToast,
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = stringResource(id = R.string.reply_all),
                    onButtonClicked = displayToast,
                    modifier = Modifier.weight(1f)
                )
            }
    }
}

@Composable
private fun DetailsScreenHeader(email: Email, modifier: Modifier = Modifier) {
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

@Composable
private fun ActionButton(
    text: String,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    containIrreversibleAction: Boolean = false
) {
    Button(
        onClick = { onButtonClicked(text) },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor =
            if (!containIrreversibleAction)
                MaterialTheme.colorScheme.inverseOnSurface
            else MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Text(
            text = text,
            color =
            if (!containIrreversibleAction)
                MaterialTheme.colorScheme.onSurfaceVariant
            else MaterialTheme.colorScheme.onError
        )
    }
}
