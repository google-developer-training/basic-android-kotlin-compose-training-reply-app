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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * A class which represents an account
 */
data class Account(
    /** Unique ID of a user **/
    val id: Long,
    /** User's first name **/
    @StringRes val firstName: Int,
    /** User's last name **/
    @StringRes val lastName: Int,
    /** User's email address **/
    @StringRes val email: Int,
    /** User's avatar image resource id **/
    @DrawableRes val avatar: Int
)
