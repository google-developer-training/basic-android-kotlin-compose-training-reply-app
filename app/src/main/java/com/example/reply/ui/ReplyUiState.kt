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

import com.example.reply.data.Email
import com.example.reply.data.MailboxType
import com.example.reply.data.local.LocalEmailsDataProvider

/**
 * Data class that represents current UI State
 */
data class ReplyUiState(
    /** Emails map for all type of [MailboxType] **/
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(),
    /** Current mailbox being displayed **/
    val currentMailbox: MailboxType = MailboxType.Inbox,
    /** Current selected email for the mailbox being displayed **/
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    /** Whether currently displaying homepage **/
    val isShowingHomepage: Boolean = true
) {
    /** Current list of emails for the mailbox being displayed **/
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! }
}
