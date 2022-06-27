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

package com.example.reply.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.reply.data.MailboxType
import com.example.reply.ui.theme.ReplyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReplyTheme {
                val navController = rememberNavController()
                val windowSize = calculateWindowSizeClass(this)
                val uiState = viewModel.uiState.collectAsState().value
                ReplyNavHost(
                    navController = navController,
                    replyHomeUIState = uiState,
                    windowSize = windowSize.widthSizeClass,
                    viewModel = viewModel
                )
            }
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun ReplyAppPreview() {
//     ReplyTheme {
//         ReplyNavHost(
//             replyHomeUIState = ReplyHomeUIState(inboxEmails = LocalEmailsDataProvider.allEmails),
//             windowSize = WindowWidthSizeClass.Compact
//         )
//     }
// }
//
// @Preview(showBackground = true, widthDp = 700)
// @Composable
// fun ReplyAppPreviewTablet() {
//     ReplyTheme {
//         ReplyNavHost(
//             replyHomeUIState = ReplyHomeUIState(inboxEmails = LocalEmailsDataProvider.allEmails),
//             windowSize = WindowWidthSizeClass.Medium
//         )
//     }
// }
//
// @Preview(showBackground = true, widthDp = 1000)
// @Composable
// fun ReplyAppPreviewDesktop() {
//     ReplyTheme {
//         ReplyNavHost(
//             replyHomeUIState = ReplyHomeUIState(inboxEmails = LocalEmailsDataProvider.allEmails),
//             windowSize = WindowWidthSizeClass.Expanded
//         )
//     }
// }
