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

package com.example.reply.data.local

import com.example.reply.R
import com.example.reply.data.Account

/**
 * An static data store of [Account]s. This includes both [Account]s owned by the current user and
 * all [Account]s of the current user's contacts.
 */
object LocalAccountsDataProvider {
    val defaultAccount = Account(-1, "", "", "", R.drawable.avatar_1)

    val userAccount =
        Account(
            id = 1,
            firstName = "Angie",
            lastName = "Awesomeville",
            email = "aawesomeville@example.com",
            avatar = R.drawable.avatar_0
        )

    private val allUserContactAccounts = listOf(
        Account(
            id = 2,
            firstName = "Tracy",
            lastName = "Alvarez",
            email = "tracealvie@example.com",
            avatar = R.drawable.avatar_1
        ),
        Account(
            id = 3,
            firstName = "Maeve",
            lastName = "Trabucco",
            email = "mtrabucco222@example.com",
            avatar = R.drawable.avatar_2
        ),
        Account(
            id = 4,
            firstName = "Ali",
            lastName = "Connors",
            email = "aliconnors@example.com",
            avatar = R.drawable.avatar_3
        ),
        Account(
            id = 5,
            firstName = "Steve",
            lastName = "Nilam",
            email = "snilam124@example.com",
            avatar = R.drawable.avatar_4
        ),
        Account(
            id = 6,
            firstName = "Eric",
            lastName = "Alen",
            email = "ealen13@example.com",
            avatar = R.drawable.avatar_5
        ),
        Account(
            id = 7,
            firstName = "Shipping",
            lastName = "Express",
            email = "express-commerce@example.org",
            avatar = R.drawable.avatar_parcel
        ),
        Account(
            id = 8,
            firstName = "Sandra",
            lastName = "Adams",
            email = "sandraadams@example.com",
            avatar = R.drawable.avatar_6
        ),
        Account(
            id = 9,
            firstName = "Li",
            lastName = "Yang",
            email = "li.yang1988@example.com",
            avatar = R.drawable.avatar_7
        ),
        Account(
            id = 10,
            firstName = "Truffle",
            lastName = "Culinary Club",
            email = "tcc@example.com",
            avatar = R.drawable.avatar_8
        ),
        Account(
            id = 11,
            firstName = "Stef",
            lastName = "Chow",
            email = "schow@example.com",
            avatar = R.drawable.avatar_9
        ),
        Account(
            id = 12,
            firstName = "SpamMaster",
            lastName = "Bad",
            email = "spam@example.com",
            avatar = R.drawable.avatar_spam
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
