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
            firstName = "Jeff",
            lastName = "Hansen",
            email = "hikingfan@gmail.com",
            avatar = R.drawable.avatar_10
        )

    private val allUserContactAccounts = listOf(
        Account(
            id = 4L,
            firstName = "Tracy",
            lastName = "Alvarez",
            email = "tracealvie@gmail.com",
            avatar = R.drawable.avatar_1
        ),
        Account(
            id = 5L,
            firstName = "Allison",
            lastName = "Trabucco",
            email = "atrabucco222@gmail.com",
            avatar = R.drawable.avatar_3
        ),
        Account(
            id = 6L,
            firstName = "Ali",
            lastName = "Connors",
            email = "aliconnors@gmail.com",
            avatar = R.drawable.avatar_5
        ),
        Account(
            id = 7L,
            firstName = "Alberto",
            lastName = "Williams",
            email = "albertowilliams124@gmail.com",
            avatar = R.drawable.avatar_0
        ),
        Account(
            id = 8L,
            firstName = "Kim",
            lastName = "Alen",
            email = "alen13@gmail.com",
            avatar = R.drawable.avatar_7
        ),
        Account(
            id = 9L,
            firstName = "Google",
            lastName = "Express",
            email = "express@google.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 10L,
            firstName = "Sandra",
            lastName = "Adams",
            email = "sandraadams@gmail.com",
            avatar = R.drawable.avatar_2
        ),
        Account(
            id = 11L,
            firstName = "Trevor",
            lastName = "Hansen",
            email = "trevorhandsen@gmail.com",
            avatar = R.drawable.avatar_8
        ),
        Account(
            id = 12L,
            firstName = "Sean",
            lastName = "Holt",
            email = "sholt@gmail.com",
            avatar = R.drawable.avatar_6
        ),
        Account(
            id = 13L,
            firstName = "Frank",
            lastName = "Hawkins",
            email = "fhawkank@gmail.com",
            avatar = R.drawable.avatar_4
        )
    )

    /**
     * Get the contact of the current user with the given [accountId].
     */
    fun getContactAccountById(accountId: Long): Account {
        return allUserContactAccounts.firstOrNull { it.id == accountId }
            ?: allUserContactAccounts.first()
    }
}