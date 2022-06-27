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

class ReplyHomeViewModel :
    ViewModel() {

    /** UI state exposed to the UI **/
    private val _uiState = MutableStateFlow(ReplyHomeUIState())
    val uiState: StateFlow<ReplyHomeUIState> = _uiState

    init {
        initializeUIState()
    }

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
            ReplyHomeUIState(inboxEmails, sentEmails, draftsEmails, spamEmails)
    }

    fun updateSelectedEmailIndex(mailboxType: MailboxType, newIndex: Int) {
        _uiState.update {
            it.copy(
                selectedEmailIndex = it.selectedEmailIndex.mapValues {
                    if (it.key == mailboxType) newIndex else it.value
                }
            )
        }
    }

    fun updateCurrentMailbox(mailboxType: MailboxType) {
        _uiState.update {
            it.copy(
                currentMailbox = mailboxType)
        }
    }
}

data class ReplyHomeUIState(
    val inboxEmails: List<Email> = emptyList(),
    val sentEmails: List<Email> = emptyList(),
    val draftsEmails: List<Email> = emptyList(),
    val spamEmails: List<Email> = emptyList(),
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val selectedEmailIndex: Map<MailboxType, Int> = mapOf(
        MailboxType.Inbox to 0,
        MailboxType.Sent to 0,
        MailboxType.Drafts to 0,
        MailboxType.Spam to 0
    )
) {
    fun getEmailsForMailbox(): List<Email> {
        val emails = when (currentMailbox) {
            MailboxType.Inbox -> inboxEmails
            MailboxType.Sent -> sentEmails
            MailboxType.Drafts -> draftsEmails
            MailboxType.Spam -> spamEmails
        }
        return emails
    }

    fun getSelectedEmailForCurrentMailbox(): Email {
        return getEmailsForMailbox()[selectedEmailIndex[currentMailbox] ?: 0]
    }
}
