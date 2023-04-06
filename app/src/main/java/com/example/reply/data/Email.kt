package com.example.reply.data

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
    val subject: String = "",
    /** Content of the email **/
    val body: String = "",
    /** Which mailbox it is in **/
    var mailbox: MailboxType = MailboxType.Inbox,
    /**
     * Relative duration in which it was created. (e.g. 20 mins ago)
     * It should be calculated from relative time in the future.
     * For now it's hard coded to a [String] value.
     */
    var createdAt: String
)
