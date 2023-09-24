package com.uroozgeek.gitproxytogglerjb.settings

import com.intellij.openapi.options.Configurable
import com.uroozgeek.gitproxytogglerjb.bundle.GitProxyTogglerBundle.message
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
class AppSettingsConfigurable : Configurable {
    private var appSettingsComponent: AppSettingsComponent? = null

    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        return message("git.proxy.toggler.display.name")
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return appSettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent {
        appSettingsComponent = AppSettingsComponent()
        return appSettingsComponent!!.panel
    }


    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance
        return appSettingsComponent!!.getDefaultProxyUrlText() != settings.defaultProxyUrl
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.defaultProxyUrl = appSettingsComponent!!.getDefaultProxyUrlText()
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        appSettingsComponent!!.setDefaultProxyUrlText(settings.defaultProxyUrl)
    }

    override fun disposeUIResources() {
        appSettingsComponent = null
    }
}