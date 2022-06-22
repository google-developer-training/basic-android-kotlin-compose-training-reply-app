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

package com.example.reply.data.local

import com.example.reply.R
import com.example.reply.data.Account

/**
 * An static data store of [Account]s. This includes both [Account]s owned by the current user and
 * all [Account]s of the current user's contacts.
 */
object LocalAccountsDataProvider {

    private val userAccount =
        Account(
            1L,
            "Jeff",
            "Hansen",
            "hikingfan@gmail.com",
            R.drawable.avatar_10
        )

    private val allUserContactAccounts = listOf(
        Account(
            4L,
            "Tracy",
            "Alvarez",
            "tracealvie@gmail.com",
            R.drawable.avatar_1
        ),
        Account(
            5L,
            "Allison",
            "Trabucco",
            "atrabucco222@gmail.com",
            R.drawable.avatar_3
        ),
        Account(
            6L,
            "Ali",
            "Connors",
            "aliconnors@gmail.com",
            R.drawable.avatar_5
        ),
        Account(
            7L,
            "Alberto",
            "Williams",
            "albertowilliams124@gmail.com",
            R.drawable.avatar_0
        ),
        Account(
            8L,
            "Kim",
            "Alen",
            "alen13@gmail.com",
            R.drawable.avatar_7
        ),
        Account(
            9L,
            "Google",
            "Express",
            "express@google.com",
            R.drawable.avatar_express
        ),
        Account(
            10L,
            "Sandra",
            "Adams",
            "sandraadams@gmail.com",
            R.drawable.avatar_2
        ),
        Account(
            11L,
            "Trevor",
            "Hansen",
            "trevorhandsen@gmail.com",
            R.drawable.avatar_8
        ),
        Account(
            12L,
            "Sean",
            "Holt",
            "sholt@gmail.com",
            R.drawable.avatar_6
        ),
        Account(
            13L,
            "Frank",
            "Hawkins",
            "fhawkank@gmail.com",
            R.drawable.avatar_4
        )
    )

    /**
     * Get the current user's default account.
     */
    fun getDefaultUserAccount() = userAccount


    /**
     * Get the contact of the current user with the given [accountId].
     */
    fun getContactAccountByUid(accountId: Long): Account {
        return allUserContactAccounts.firstOrNull { it.id == accountId }
            ?: allUserContactAccounts.first()
    }
}
