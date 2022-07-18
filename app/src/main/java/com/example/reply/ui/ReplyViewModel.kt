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
        var mailboxes: Map<MailboxType, List<Email>> =
            LocalEmailsDataProvider.allEmails.groupBy { it.mailbox }
        _uiState.value =
            ReplyUIState(
                mailboxes = mailboxes,
                currentSelectedEmail = mailboxes[MailboxType.Inbox]!![0]
            )
    }

    /**
     * Update [currentSelectedEmail] state
     * and [isShowingHomepage] to false
     */
    fun updateSelectedEmail(email: Email) {
        _uiState.update {
            it.copy(
                currentSelectedEmail = email,
                isShowingHomepage = false
            )
        }
    }

    /**
     * Reset [currentSelectedEmail] state to first email
     * and [isShowingHomepage] to true
     */
    fun resetSelectedEmailIndex() {
        _uiState.update {
            it.copy(
                currentSelectedEmail = it.mailboxes[it.currentMailbox]!![0],
                isShowingHomepage = true
            )
        }
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