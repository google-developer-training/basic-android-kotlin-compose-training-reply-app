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
import com.example.reply.data.Email
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
    fun updateSelectedEmailIndex(mailboxType: MailboxType, newIndex: Int) {
        _uiState.update {
            it.copy(
                selectedEmailIndex = it.selectedEmailIndex.mapValues {
                    if (it.key == mailboxType) newIndex else it.value
                }
            )
        }
    }

    /**
     * Update [currentMailbox]
     */
    fun updateCurrentMailbox(mailboxType: MailboxType) {
        _uiState.update {
            it.copy(
                currentMailbox = mailboxType)
        }
    }
}

/**
 * Data class that represents current UI State
 */
data class ReplyUIState(
    /** Emails in the inbox tab **/
    val inboxEmails: List<Email> = emptyList(),
    /** Emails in the Sent tab **/
    val sentEmails: List<Email> = emptyList(),
    /** Emails in the Drafts tab **/
    val draftsEmails: List<Email> = emptyList(),
    /** Emails in the Spam tab **/
    val spamEmails: List<Email> = emptyList(),
    /** Current mailbox being displayed **/
    val currentMailbox: MailboxType = MailboxType.Inbox,
    /** A map of indexes of selected item corresponding to each mailbox type **/
    val selectedEmailIndex: Map<MailboxType, Int> = mapOf(
        MailboxType.Inbox to 0,
        MailboxType.Sent to 0,
        MailboxType.Drafts to 0,
        MailboxType.Spam to 0
    )
) {
    /**
     * Returns List of [Email] according to the [currentMailbox]
     */
    fun getEmailsForMailbox(): List<Email> {
        val emails = when (currentMailbox) {
            MailboxType.Inbox -> inboxEmails
            MailboxType.Sent -> sentEmails
            MailboxType.Drafts -> draftsEmails
            MailboxType.Spam -> spamEmails
        }
        return emails
    }

    /**
     * Returns an [Email] which is selected from the list according to the [currentMailbox]
     */
    fun getSelectedEmailForCurrentMailbox(): Email {
        return getEmailsForMailbox()[selectedEmailIndex[currentMailbox] ?: 0]
    }
}
