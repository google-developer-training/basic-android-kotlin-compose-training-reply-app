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
package com.example.reply.data

import androidx.annotation.StringRes

/**
 * A simple data class to represent an Email
 */
data class Email(
    /** Unique ID of the email **/
    val id: Long,
    /** Sender of the email **/
    val sender: Account,
    /** Recipient(s) of the email **/
    val recipients: List<Account> = emptyList(),
    /** Title of the email **/
    @StringRes val subject: Int = -1,
    /** Content of the email **/
    @StringRes val body: Int = -1,
    /** Which mailbox it is in **/
    var mailbox: MailboxType = MailboxType.Inbox,
    /**
     * Relative duration in which it was created. (e.g. 20 mins ago)
     * It should be calculated from relative time in the future.
     * For now it's hard coded to a [String] value.
     */
    var createdAt: Int = -1
)
