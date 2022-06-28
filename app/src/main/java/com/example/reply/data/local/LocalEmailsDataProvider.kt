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

import com.example.reply.data.Email
import com.example.reply.data.MailboxType

/**
 * A static data store of [Email]s.
 */

object LocalEmailsDataProvider {
    val allEmails = mutableListOf(
        Email(
            id = 0,
            sender = LocalAccountsDataProvider.getContactAccountById(9),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Package shipped!",
            body = """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            createAt = "20 mins ago"
        ),
        Email(
            id = 1,
            sender = LocalAccountsDataProvider.getContactAccountById(6),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Brunch this weekend?",
            body = """
                I'll be in your neighborhood doing errands and was hoping to catch you for a coffee this Saturday. If you don't have anything scheduled, it would be great to see you! It feels like it's been forever.

                If we do get a chance to get together, remind me to tell you about Kim. She stopped over at the house to say hey to the kids and told me all about her trip to Mexico.

                Talk to you soon,

                Ali
            """.trimIndent(),
            createAt = "40 mins ago",
        ),
        Email(
            id = 2,
            sender = LocalAccountsDataProvider.getContactAccountById(5),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Bonjour from Paris",
            body = "Here are some great shots from my trip...",
            createAt = "1 hour ago",
        ),
        Email(
            id = 3,
            sender = LocalAccountsDataProvider.getContactAccountById(8),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "High school reunion?",
            body = """
                Hi friends,

                I was at the grocery store on Sunday night.. when I ran into Genie Williams! I almost didn't recognize her afer 20 years!

                Anyway, it turns out she is on the organizing committee for the high school reunion this fall. I don't know if you were planning on going or not, but she could definitely use our help in trying to track down lots of missing alums. If you can make it, we're doing a little phone-tree party at her place next Saturday, hoping that if we can find one person, the more will...
            """.trimIndent(),
            createAt = "2 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 4,
            sender = LocalAccountsDataProvider.getContactAccountById(11),
            recipients = listOf(
                LocalAccountsDataProvider.userAccount,
                LocalAccountsDataProvider.getContactAccountById(8),
                LocalAccountsDataProvider.getContactAccountById(5)
            ),
            subject = "Brazil trip",
            body = """
                Thought we might be able to go over some details about our upcoming vacation.

                I've been doing a bit of research and have come across a few places in Northern Brazil that I think we should check out. One, the north has some of the most predictable wind on the planet. I'd love to get out on the ocean and kitesurf for a couple of days if we're going to be anywhere near or around Taiba. I hear it's beautiful there and if you're up for it, I'd love to go. Other than that, I haven't spent too much time looking into places along our road trip route. I'm assuming we can find places to stay and things to do as we drive and find places we think look interesting. But... I know you're more of a planner, so if you have ideas or places in mind, let's jot some ideas down!

                Maybe we can jump on the phone later today if you have a second.
            """.trimIndent(),
            createAt = "2 hours ago",
        ),
        Email(
            id = 5,
            sender = LocalAccountsDataProvider.getContactAccountById(13),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            "Update to Your Itinerary",
            "",
            createAt = "2 hours ago",
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 6,
            sender = LocalAccountsDataProvider.getContactAccountById(10),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Recipe to try",
            body = "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                "very quick to put together.",
            createAt = "2 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 7,
            sender = LocalAccountsDataProvider.getContactAccountById(9),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Delivered",
            body = "Your shoes should be waiting for you at home!",
            createAt = "2 hours ago",
        ),
        Email(
            id = 8,
            sender = LocalAccountsDataProvider.getContactAccountById(13),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Your update on Google Play Store is live!",
            body = """
              Your update, 0.1.1, is now live on the Play Store and available for your alpha users to start testing.
              Your alpha testers will be automatically notified. If you'd rather send them a link directly, go to your Google Play Console and follow the instructions for obtaining an open alpha testing link.
          """.trimIndent(),
            createAt = "3 hours ago",
        ),
        Email(
            id = 9,
            sender = LocalAccountsDataProvider.getContactAccountById(10),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "(No subject)",
            body = """
            Hey, 
            Wanted to email and see what you thought of
          """.trimIndent(),
            createAt = "3 hours ago",
            mailbox = MailboxType.Drafts,
        ),
        Email(
            id = 10,
            sender = LocalAccountsDataProvider.getContactAccountById(5),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Try a free Hiking Trail app",
            body = """
            Looking for the best hiking trails in your area? Hiking Trail gets you on the path to the outdoors faster than you can pack a sandwich.

            Whether you're an experienced hiker or just looking to get outside for the afternoon, there's a segment that suits you.
          """.trimIndent(),
            createAt = "3 hours ago",
            mailbox = MailboxType.Spam,
        ),
        Email(
            id = 11,
            sender = LocalAccountsDataProvider.getContactAccountById(5),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Free money",
            body = """
            You've been selected as a winner in our latest raffle! To claim your prize, click on the link.
          """.trimIndent(),
            createAt = "3 hours ago",
            mailbox = MailboxType.Spam,
        )
    )
}
