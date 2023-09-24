package com.uroozgeek.gitproxytogglerjb.widget

import com.intellij.icons.AllIcons
import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.WindowManager
import com.intellij.util.Consumer
import com.uroozgeek.gitproxytogglerjb.bundle.GitProxyTogglerBundle.message
import com.uroozgeek.gitproxytogglerjb.settings.AppSettingsState.Companion.instance
import com.uroozgeek.gitproxytogglerjb.util.GitProxyHelper.currentProxyUrl
import com.uroozgeek.gitproxytogglerjb.util.GitProxyHelper.setGitProxy
import com.uroozgeek.gitproxytogglerjb.util.GitProxyHelper.unsetGitProxy
import com.uroozgeek.gitproxytogglerjb.util.NotificationHelper.showNotification
import java.awt.event.MouseEvent
import javax.swing.Icon

class GitProxyTogglerStatusBarWidget : StatusBarWidget, StatusBarWidget.IconPresentation {
    companion object {
        val ID: String = message("git.proxy.toggler.widget.id")
    }

    override fun ID(): String {
        return ID
    }

    override fun getPresentation(): StatusBarWidget.WidgetPresentation {
        return this
    }

    override fun install(statusBar: StatusBar) {
        statusBar.updateWidget(ID())
    }

    override fun getClickConsumer(): Consumer<MouseEvent> {
        return Consumer { mouseEvent: MouseEvent ->
            val project = PlatformDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext(mouseEvent.component))
            if (project != null) {
                if (currentProxyUrl!!.isBlank()) {
                    val defaultProxyUrl = instance.defaultProxyUrl
                    setGitProxy(defaultProxyUrl)
                    showNotification(project, "Git proxy set to $defaultProxyUrl")
                } else {
                    unsetGitProxy()
                    showNotification(project, "Git proxy unset.")
                }
            }
            // update the icon and tooltip for all open projects
            for (project in ProjectManager.getInstance().openProjects) {
                val statusBar = WindowManager.getInstance().getStatusBar(project)
                statusBar?.updateWidget(ID())
            }
        }
    }

    override fun getTooltipText(): String {
        return if (currentProxyUrl!!.isBlank()) "Git proxy not set." else "Git proxy is set to $currentProxyUrl"
    }

    override fun getIcon(): Icon {
        return if (currentProxyUrl!!.isBlank()) AllIcons.Debugger.Db_invalid_breakpoint else AllIcons.Nodes.Related
    }

    override fun dispose() {
    }
}
