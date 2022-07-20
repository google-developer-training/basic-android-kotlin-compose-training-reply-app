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

import com.example.reply.data.Email
import com.example.reply.data.MailboxType

/**
 * A static data store of [Email]s.
 */

object LocalEmailsDataProvider {
    val allEmails = mutableListOf(
        Email(
            id = 0,
            sender = LocalAccountsDataProvider.getContactAccountById(7),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Package shipped!",
            body = """
           Cucumber facial mask has been shipped.

           Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

           As always, thank you for shopping with us and we hope you love our specially formulated Cucumber mask!
       """.trimIndent(),
            createdAt = "20 mins ago"
        ),
        Email(
            id = 1,
            sender = LocalAccountsDataProvider.getContactAccountById(4),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Brunch this weekend?",
            body = """
           I'll be in your neighborhood doing errands and was hoping to catch you for a coffee this Saturday. If you don't have anything scheduled, it would be great to see you! It feels like it's been forever.

           If we do get a chance to get together, remind me to tell you about Kim. She stopped over at the house to say hey to the kids and told me all about her trip to Mexico.

           Talk to you soon,

           Ali
       """.trimIndent(),
            createdAt = "40 mins ago",
        ),
        Email(
            id = 2,
            sender = LocalAccountsDataProvider.getContactAccountById(11),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Let’s meet up",
            body = """
           We’re having the best time on the coast! We just witnessed such an amazing sunset and sunrise on the beach. We’re going to be here for another week. Come join us!

           Cheers,
           Stef and Michael
       """.trimIndent(),
            createdAt = "1 hour ago",
        ),
        Email(
            id = 3,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(
                LocalAccountsDataProvider.getContactAccountById(11),
                LocalAccountsDataProvider.getContactAccountById(3)
            ),
            subject = "High school reunion?",
            body = """
           Hi friends,

           I was at the grocery store on Sunday night.. when I ran into Genie Williams! I almost didn't recognize her after 20 years!

           Anyway, it turns out she is on the organizing committee for the high school reunion this fall. I don't know if you were planning on going or not, but she could definitely use our help in trying to track down lots of missing alums. If you can make it, we're doing a little phone-tree party at her place next Saturday. It will also be fun to catch up!
       """.trimIndent(),
            createdAt = "2 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 4,
            sender = LocalAccountsDataProvider.getContactAccountById(9),
            recipients = listOf(
                LocalAccountsDataProvider.userAccount,
                LocalAccountsDataProvider.getContactAccountById(6),
                LocalAccountsDataProvider.getContactAccountById(3)
            ),
            subject = "Road trip",
            body = """
           Thought we might be able to go over some details about our upcoming road trip.

           Do you want to leave on Friday or Saturday? If we leave late on Friday night, then I think we can avoid the traffic.

           I've been doing a bit of research on places we can stay at and restaurants we can try. I’ll send you a list of them. I also have a friend whose place we can stay at for one of the weekends. If you have ideas on activities you want to do, let me know!

           Maybe we can jump on the phone later today if you have a second.
       """.trimIndent(),
            createdAt = "2 hours ago",
        ),
        Email(
            id = 5,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(11)),
            "Next week’s sprint planning",
            "",
            createdAt = "2 hours ago",
            mailbox = MailboxType.Drafts
        ),
        Email(
            id = 6,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(11)),
            subject = "Recipe to try",
            body = """
           Raspberry Pie: We should make this pie recipe tonight! The filling is very quick to put together.
       """.trimIndent(),
            createdAt = "2 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 7,
            sender = LocalAccountsDataProvider.getContactAccountById(7),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Delivered",
            body = "Your shoes should be waiting for you at home!",
            createdAt = "2 hours ago",
        ),
        Email(
            id = 8,
            sender = LocalAccountsDataProvider.getContactAccountById(11),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Gift for Mom",
            body = """
           I’m going to start gathering the photos for the album that we’ll give to Mom for her birthday. Can you check your photos to see if you have any good family photos?

           If I can get all the photos sorted by next week, I can place the order for the album the following week. I think it will be ready by the 5th, in time for her birthday party. We can get some flowers and a card to go with it too.
       """.trimIndent(),
            createdAt = "3 hours ago",
        ),
        Email(
            id = 9,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(11)),
            subject = "(No subject)",
            body = """
       Hey,
       Wanted to email and see what you thought of
     """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Drafts,
        ),
        Email(
            id = 10,
            sender = LocalAccountsDataProvider.getContactAccountById(12),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Try a new hiking trail app",
            body = """
           Looking for the best hiking trails in your area? This hiking trail app gets you on the path to the outdoors faster than you can pack a sandwich.

           Whether you're an experienced hiker or just looking to get outside for the afternoon, there's a segment that suits you.
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Spam,
        ),
        Email(
            id = 11,
            sender = LocalAccountsDataProvider.getContactAccountById(12),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "You won!",
            body = """
           You've been selected as a winner in our latest raffle! To claim your prize, click on the link.
     """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Spam,
        ),
        Email(
            id = 12,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(8)),
            subject = "Quick sync on launch",
            body = """
           Hi folks,

           I have some new amazing ideas for our launch after coming back from my vacation. Let’s have a quick sync up next Monday.

           Thanks!
           Angie
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 13,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(8)),
            subject = "Please help with technical support",
            body = """
           Hi there,

           I’m unable to login to the TCC portal. May I know how to reset my account? Would appreciate a quick support as I’m traveling next weekend.

           Thank you very much.
           Angie
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 14,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(3)),
            subject = "Meeting reschedule",
            body = """
           Hey Maeve,

           Hope you are well. Could we reschedule our next catch up to the week after? I have to travel next week for work. Sorry for the short notice.

           Regards,
           Angie
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 15,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(5)),
            subject = "Document review",
            body = """
           Hi Steve,

           I have taken a look at the document you shared and left some comments to be addressed. Well done and keep up the good work.

           Thanks!
           Angie
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Sent,
        ),
        Email(
            id = 16,
            sender = LocalAccountsDataProvider.getContactAccountById(10),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "[Application Open] Truffle Cooking Course",
            body = """
           Dear Angie,

           Thank you for continuing to subscribe to our platform. We are excited to announce our exclusive truffle cooking course is back with limited seats. Please register on our website before 12PM Today.

           We can’t wait for you to join us!
           Truffle Culinary Club
       """.trimIndent(),
            createdAt = "3 hours ago"
        ),
        Email(
            id = 17,
            sender = LocalAccountsDataProvider.getContactAccountById(9),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Outstanding Tasks",
            body = """
           Hi Angie,

           There are some outstanding questions unaddressed from our previous meeting. Let’s have another catch up so that we are on the same page.

           Thanks
           Yang Li
       """.trimIndent(),
            createdAt = "3 hours ago"
        ),
        Email(
            id = 18,
            sender = LocalAccountsDataProvider.getContactAccountById(6),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Please share the document",
            body = "Eric is requesting you to share the “Everyday planning 2022” doc",
            createdAt = "3 hours ago"
        ),
        Email(
            id = 19,
            sender = LocalAccountsDataProvider.getContactAccountById(12),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "Hi",
            body = "hi",
            createdAt = "3 hours ago",
            mailbox = MailboxType.Spam,
        ),
        Email(
            id = 20,
            sender = LocalAccountsDataProvider.getContactAccountById(12),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "New email",
            body = """
           Hello my name is Christopher. I am the CEO of a large enterprise with an important proposal for you. Could we arrange a meeting sometime this week?
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Spam,
        ),
        Email(
            id = 21,
            sender = LocalAccountsDataProvider.getContactAccountById(12),
            recipients = listOf(LocalAccountsDataProvider.userAccount),
            subject = "URGENT REPLY FOR MORE DETAILS",
            body = """
           Good morning!

           I met you last week. I’m here to offer you a once in a lifetime investment opportunity. Please reply for more details
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Spam,
        ),
        Email(
            id = 22,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(11)),
            subject = "(No subject)",
            body = """
           Hello Stef and Michael,

           I wish I could join you
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Drafts,
        ),
        Email(
            id = 23,
            sender = LocalAccountsDataProvider.userAccount,
            recipients = listOf(LocalAccountsDataProvider.getContactAccountById(8)),
            subject = "Next meeting",
            body = """
           I’m coming to the KL office this Friday. Let’s meet up in
       """.trimIndent(),
            createdAt = "3 hours ago",
            mailbox = MailboxType.Drafts,
        )
    )

    val defaultEmail = Email(
        id = -1,
        sender = LocalAccountsDataProvider.defaultAccount,
        createdAt = ""
    )
}
