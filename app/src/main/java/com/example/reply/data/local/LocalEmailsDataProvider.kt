/*
 * Copyright (C) 2023 The Android Open Source Project
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
package com.example.reply.data.local

import com.example.reply.R
import com.example.reply.data.Email
import com.example.reply.data.MailboxType

/**
 * A static data store of [Email]s.
 */

object LocalEmailsDataProvider {

    val allEmails = listOf(
        Email(
            id = 0L,
            sender = LocalAccountsDataProvider.getContactAccountById(9L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_0_subject,
            body = R.string.email_0_body,
            createdAt = R.string.email_0_time,
        ),
        Email(
            id = 1L,
            sender = LocalAccountsDataProvider.getContactAccountById(6L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_1_subject,
            body = R.string.email_1_body,
            createdAt = R.string.email_1_time,
        ),
        Email(
            2L,
            LocalAccountsDataProvider.getContactAccountById(5L),
            listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_2_subject,
            body = R.string.email_2_body,
            createdAt = R.string.email_2_time,
        ),
        Email(
            3L,
            LocalAccountsDataProvider.getContactAccountById(8L),
            listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_3_subject,
            body = R.string.email_3_body,
            createdAt = R.string.email_3_time,
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 4L,
            sender = LocalAccountsDataProvider.getContactAccountById(11L),
            subject = R.string.email_4_subject,
            body = R.string.email_4_body,
            createdAt = R.string.email_4_time,
        ),
        Email(
            id = 5L,
            sender = LocalAccountsDataProvider.getContactAccountById(13L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_5_subject,
            body = R.string.email_5_body,
            createdAt = R.string.email_5_time,
        ),
        Email(
            id = 6L,
            sender = LocalAccountsDataProvider.getContactAccountById(10L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_6_subject,
            body = R.string.email_6_body,
            createdAt = R.string.email_6_time,
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 7L,
            sender = LocalAccountsDataProvider.getContactAccountById(9L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_7_subject,
            body = R.string.email_7_body,
            createdAt = R.string.email_7_time,
        ),
        Email(
            id = 8L,
            sender = LocalAccountsDataProvider.getContactAccountById(13L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_8_subject,
            body = R.string.email_8_body,
            createdAt = R.string.email_8_time,
        ),
        Email(
            id = 9L,
            sender = LocalAccountsDataProvider.getContactAccountById(10L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_9_subject,
            body = R.string.email_9_body,
            createdAt = R.string.email_9_time,
            mailbox = MailboxType.Drafts,
        ),
        Email(
            id = 10L,
            sender = LocalAccountsDataProvider.getContactAccountById(5L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_10_subject,
            body = R.string.email_10_body,
            createdAt = R.string.email_10_time,
        ),
        Email(
            id = 11L,
            sender = LocalAccountsDataProvider.getContactAccountById(5L),
            recipients = listOf(LocalAccountsDataProvider.defaultAccount),
            subject = R.string.email_11_subject,
            body = R.string.email_11_body,
            createdAt = R.string.email_11_time,
            mailbox = MailboxType.Spam,
        )
    )

    /**
     * Get an [Email] with the given [id].
     */
    fun get(id: Long): Email? {
        return allEmails.firstOrNull { it.id == id }
    }

    val defaultEmail = Email(
        id = -1,
        sender = LocalAccountsDataProvider.defaultAccount,
    )
}
