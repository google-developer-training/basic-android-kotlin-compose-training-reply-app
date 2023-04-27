package com.example.reply.data

import androidx.annotation.DrawableRes

/**
 * A class which represents an account
 */
data class Account(
    /** Unique ID of a user **/
    val id: Long,
    /** User's first name **/
    val firstName: Int,
    /** User's last name **/
    val lastName: Int,
    /** User's email address **/
    val email: Int,
    /** User's avatar image resource id **/
    @DrawableRes val avatar: Int
) {
    /** User's full name **/
    val fullName: String = "$firstName $lastName"
}