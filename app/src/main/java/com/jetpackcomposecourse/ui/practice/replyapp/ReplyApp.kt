package com.jetpackcomposecourse.ui.practice.replyapp

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackcomposecourse.data.Email
import com.jetpackcomposecourse.data.MailboxType
import com.jetpackcomposecourse.ui.practice.replyapp.theme.ReplyTheme
import com.jetpackcomposecourse.ui.practice.replyapp.util.ReplyContentType
import com.jetpackcomposecourse.ui.practice.replyapp.util.ReplyNavigationType

@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    val navigationType: ReplyNavigationType
    val contentType: ReplyContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ReplyContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
    }

    ReplyTheme {
        ReplyHomeScreen(
            navigationType = navigationType,
            contentType = contentType,
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