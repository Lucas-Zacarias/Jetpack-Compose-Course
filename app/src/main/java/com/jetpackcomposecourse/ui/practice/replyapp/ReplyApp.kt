package com.jetpackcomposecourse.ui.practice.replyapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetpackcomposecourse.data.Email
import com.jetpackcomposecourse.data.MailboxType
import com.jetpackcomposecourse.ui.practice.replyapp.theme.ReplyTheme

@Composable
fun ReplyApp(
    modifier: Modifier = Modifier,
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    ReplyTheme {
        ReplyHomeScreen(
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