package com.uroozgeek.gitproxytogglerjb.util

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import com.uroozgeek.gitproxytogglerjb.bundle.GitProxyTogglerBundle.message

object NotificationHelper {
    @JvmStatic
    fun showNotification(project: Project?, content: String?) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup(message("git.proxy.toggler.notification.group"))
                .createNotification(content!!, NotificationType.INFORMATION)
                .notify(project)
    }
}
