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

    val userAccount =
        Account(
            id = 1,
            firstName = "Jeff",
            lastName = "Hansen",
            email = "hikingfan@gmail.com",
            avatar = R.drawable.avatar_10
        )

    private val allUserContactAccounts = listOf(
        Account(
            id = 4,
            firstName = "Tracy",
            lastName = "Alvarez",
            email = "tracealvie@gmail.com",
            avatar = R.drawable.avatar_1
        ),
        Account(
            id = 5,
            firstName = "Allison",
            lastName = "Trabucco",
            email = "atrabucco222@gmail.com",
            avatar = R.drawable.avatar_3
        ),
        Account(
            id = 6,
            firstName = "Ali",
            lastName = "Connors",
            email = "aliconnors@gmail.com",
            avatar = R.drawable.avatar_5
        ),
        Account(
            id = 7,
            firstName = "Alberto",
            lastName = "Williams",
            email = "albertowilliams124@gmail.com",
            avatar = R.drawable.avatar_0
        ),
        Account(
            id = 8,
            firstName = "Kim",
            lastName = "Alen",
            email = "alen13@gmail.com",
            avatar = R.drawable.avatar_7
        ),
        Account(
            id = 9,
            firstName = "Google",
            lastName = "Express",
            email = "express@google.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 10,
            firstName = "Sandra",
            lastName = "Adams",
            email = "sandraadams@gmail.com",
            avatar = R.drawable.avatar_2
        ),
        Account(
            id = 11,
            firstName = "Trevor",
            lastName = "Hansen",
            email = "trevorhandsen@gmail.com",
            avatar = R.drawable.avatar_8
        ),
        Account(
            id = 12,
            firstName = "Sean",
            lastName = "Holt",
            email = "sholt@gmail.com",
            avatar = R.drawable.avatar_6
        ),
        Account(
            id = 13,
            firstName = "Frank",
            lastName = "Hawkins",
            email = "fhawkank@gmail.com",
            avatar = R.drawable.avatar_4
        )
    )

    /**
     * Get the contact of the current user with the given [accountId].
     */
    fun getContactAccountById(accountId: Int): Account {
        return allUserContactAccounts.firstOrNull { it.id == accountId }
            ?: allUserContactAccounts.first()
    }
}
