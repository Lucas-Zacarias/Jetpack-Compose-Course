package com.jetpackcomposecourse.ui.practice.replyapp

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackcomposecourse.data.Email
import com.jetpackcomposecourse.data.MailboxType
import com.jetpackcomposecourse.ui.practice.replyapp.theme.ReplyTheme
import com.jetpackcomposecourse.ui.practice.replyapp.util.ReplyNavigationType

@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    val navigationType: ReplyNavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            ReplyNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            ReplyNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            ReplyNavigationType.BOTTOM_NAVIGATION
        }
    }

    ReplyTheme {
        ReplyHomeScreen(
            navigationType = navigationType,
            replyUiState = replyUiState,
            onTabPressed = { mailboxType: MailboxType ->
                viewModel.updateCurrentMailbox(mailboxType = mailboxType)
                viewModel.resetHomeScreenStates()
            },
            onEmailCardPressed = { email: Email ->
                viewModel.updateDetailsScreenStates(
                    email = email
                )
            },
            onDetailScreenBackPressed = {
                viewModel.resetHomeScreenStates()
            },
            modifier = modifier
        )
    }

}