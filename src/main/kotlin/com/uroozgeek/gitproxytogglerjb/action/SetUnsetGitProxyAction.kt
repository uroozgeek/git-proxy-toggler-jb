package com.uroozgeek.gitproxytogglerjb.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.WindowManager
import com.uroozgeek.gitproxytogglerjb.settings.ProxyConfigDialog
import com.uroozgeek.gitproxytogglerjb.settings.ProxyConfigPanel
import com.uroozgeek.gitproxytogglerjb.util.GitProxyHelper
import com.uroozgeek.gitproxytogglerjb.util.GitProxyHelper.currentProxyUrl
import com.uroozgeek.gitproxytogglerjb.util.NotificationHelper
import com.uroozgeek.gitproxytogglerjb.widget.GitProxyTogglerStatusBarWidget

class SetUnsetGitProxyAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        if (currentProxyUrl!!.isBlank()) {
            val choice = Messages.showDialog(
                    "Git proxy is not set. Do you want to set it?",
                    "Toggle Git Proxy",
                    arrayOf("Set Git Proxy", "Cancel"),
                    0,
                    null
            )

            if (choice != 0) {
                NotificationHelper.showNotification(e.project, "Git proxy not changed.")
            } else {
                val configPanel = ProxyConfigPanel()
                val dialog = ProxyConfigDialog(configPanel)
                if (dialog.showAndGet()) {
                    val customProxyUrl = configPanel.proxyUrl
                    GitProxyHelper.setGitProxy(customProxyUrl)
                    NotificationHelper.showNotification(e.project, "Git proxy set to $customProxyUrl")
                }
            }
        } else {
            val choice = Messages.showDialog(
                    "Git proxy is currently set to $currentProxyUrl",
                    "Toggle Git Proxy",
                    arrayOf("Unset Git Proxy", "Cancel"),
                    0,
                    null
            )
            if (choice != 0) {
                NotificationHelper.showNotification(e.project, "Git proxy not changed.")
            } else {
                GitProxyHelper.unsetGitProxy()
                NotificationHelper.showNotification(e.project, "Git proxy unset.")
            }
        }
        // update the icon and tooltip for all open projects
        for (project in ProjectManager.getInstance().openProjects) {
            val statusBar = WindowManager.getInstance().getStatusBar(project)
            statusBar?.updateWidget(GitProxyTogglerStatusBarWidget.ID)
        }
    }
}


