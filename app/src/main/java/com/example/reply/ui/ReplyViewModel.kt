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

import androidx.lifecycle.ViewModel
import com.example.reply.data.MailboxType
import com.example.reply.data.local.LocalEmailsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * View Model for Reply app
 */
class ReplyViewModel : ViewModel() {

    /** UI state exposed to the UI **/
    private val _uiState = MutableStateFlow(ReplyUIState())
    val uiState: StateFlow<ReplyUIState> = _uiState

    init {
        initializeUIState()
    }

    /**
     * Initializing mailbox emails by getting them from [LocalEmailsDataProvider]
     */
    private fun initializeUIState() {
        val inboxEmails = LocalEmailsDataProvider.allEmails.filter {
            it.mailbox == MailboxType.Inbox
        }
        val sentEmails = LocalEmailsDataProvider.allEmails.filter {
            it.mailbox == MailboxType.Sent
        }
        val draftsEmails = LocalEmailsDataProvider.allEmails.filter {
            it.mailbox == MailboxType.Drafts
        }
        val spamEmails = LocalEmailsDataProvider.allEmails.filter {
            it.mailbox == MailboxType.Spam
        }
        _uiState.value =
            ReplyUIState(inboxEmails, sentEmails, draftsEmails, spamEmails)
    }

    /**
     * Update [selectedEmailIndex] state for the [mailboxType]
     */
    fun updateSelectedEmailIndex(mailboxType: MailboxType, newIndex: Int?) {
        _uiState.update { it ->
            it.copy(
                selectedEmailIndex = it.selectedEmailIndex.mapValues {
                    if (it.key == mailboxType) newIndex else it.value
                }
            )
        }
    }

    /**
     * Reset [selectedEmailIndex] state for the [mailboxType] to null
     */
    fun resetSelectedEmailIndex(mailboxType: MailboxType) {
        updateSelectedEmailIndex(mailboxType, null)
    }

    /**
     * Update [currentMailbox]
     */
    fun updateCurrentMailbox(mailboxType: MailboxType) {
        _uiState.update {
            it.copy(
                currentMailbox = mailboxType
            )
        }
    }
}
