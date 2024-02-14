package com.jetpackcomposecourse.ui.practice.replyapp

import com.jetpackcomposecourse.data.Email
import com.jetpackcomposecourse.data.MailboxType
import com.jetpackcomposecourse.data.local.LocalEmailsDataProvider

data class ReplyUiState(
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(),
    val currentMailbox: MailboxType = MailboxType.Inbox,
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    val isShowingHomepage: Boolean = true
) {
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! }
}