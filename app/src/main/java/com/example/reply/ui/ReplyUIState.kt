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

import com.example.reply.data.Email
import com.example.reply.data.MailboxType

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
    val selectedEmailIndex: Map<MailboxType, Int?> = mapOf(
        MailboxType.Inbox to null,
        MailboxType.Sent to null,
        MailboxType.Drafts to null,
        MailboxType.Spam to null
    )
) {
    /** Current list of emails for the mailbox being displayed **/
    val currentMailboxEmails: List<Email> = when (currentMailbox) {
        MailboxType.Inbox -> inboxEmails
        MailboxType.Sent -> sentEmails
        MailboxType.Drafts -> draftsEmails
        MailboxType.Spam -> spamEmails
    }

    /** Current selected email for the mailbox being displayed **/
    val currentSelectedEmail: Email? =
        if (currentMailboxEmails.isEmpty() || selectedEmailIndex[currentMailbox] == null) {
            null
        } else {
            currentMailboxEmails[selectedEmailIndex[currentMailbox]!!]
        }
}
