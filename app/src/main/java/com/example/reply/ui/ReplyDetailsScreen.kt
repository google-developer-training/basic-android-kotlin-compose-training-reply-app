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

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType

/**
 * Component that displays a single card containing one [email]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyEmailDetailsScreen(
    email: Email,
    mailboxType: MailboxType,
    isFullScreen: Boolean = false,
    onBackButtonClicked: () -> Unit = {},
    displayHomeScreenIfWindowSizeExpanded: () -> Unit = {},
    modifier: Modifier = Modifier.padding(end = 16.dp)
) {
    if (isFullScreen) displayHomeScreenIfWindowSizeExpanded()

    val context = LocalContext.current
    val displayToast = { text: String ->
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        if (isFullScreen) {
            IconButton(
                onClick = { onBackButtonClicked() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigation_back)
                )
            }
        }
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
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )

            Text(
                text = email.body,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            if (mailboxType == MailboxType.Drafts) {
                OneActionButton(
                    text = stringResource(id = R.string.continue_composing),
                    onButtonClicked = displayToast
                )
            } else {
                if (mailboxType == MailboxType.Spam) {
                    TwoActionButtons(
                        primaryText = stringResource(id = R.string.delete),
                        secondaryText = stringResource(id = R.string.move_to_inbox),
                        onPrimaryButtonClicked = displayToast,
                        onSecondaryButtonClicked = displayToast,
                        containIrreversibleAction = true
                    )
                } else {
                    TwoActionButtons(
                        primaryText = stringResource(id = R.string.reply_all),
                        secondaryText = stringResource(id = R.string.reply),
                        onPrimaryButtonClicked = displayToast,
                        onSecondaryButtonClicked = displayToast
                    )
                }
            }
        }
    }
}

@Composable
private fun OneActionButton(text: String, onButtonClicked: (String) -> Unit) {
    Button(
        onClick = { onButtonClicked(text) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun TwoActionButtons(
    primaryText: String,
    secondaryText: String,
    onPrimaryButtonClicked: (String) -> Unit,
    onSecondaryButtonClicked: (String) -> Unit,
    containIrreversibleAction: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Button(
            onClick = { onSecondaryButtonClicked(secondaryText) },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface
            )
        ) {
            Text(
                text = secondaryText,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Button(
            onClick = { onPrimaryButtonClicked(primaryText) },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                if (!containIrreversibleAction)
                    MaterialTheme.colorScheme.inverseOnSurface
                else MaterialTheme.colorScheme.onErrorContainer
            )
        ) {
            Text(
                text = primaryText,
                color =
                if (!containIrreversibleAction)
                    MaterialTheme.colorScheme.onSurfaceVariant
                else MaterialTheme.colorScheme.onError
            )
        }
    }
}
