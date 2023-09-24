package com.uroozgeek.gitproxytogglerjb.widget

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.openapi.wm.WindowManager
import com.uroozgeek.gitproxytogglerjb.bundle.GitProxyTogglerBundle.message

class GitProxyTogglerStatusBarWidgetFactory : StatusBarWidgetFactory {
    init {
        for (project in ProjectManager.getInstance().openProjects) {
            val statusBar = WindowManager.getInstance().getStatusBar(project)
            statusBar?.updateWidget(message("git.proxy.toggler.widget.id"))
        }
    }

    override fun getId(): String {
        return message("git.proxy.toggler.widget.factory.id")
    }

    override fun getDisplayName(): String {
        return message("git.proxy.toggler.display.name")
    }

    override fun isAvailable(project: Project): Boolean {
        return true
    }

    override fun createWidget(project: Project): StatusBarWidget {
        return GitProxyTogglerStatusBarWidget()
    }

    override fun disposeWidget(statusBarWidget: StatusBarWidget) {
        Disposer.dispose(statusBarWidget)
    }

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean {
        return true
    }

    override fun isEnabledByDefault(): Boolean {
        return true
    }
}