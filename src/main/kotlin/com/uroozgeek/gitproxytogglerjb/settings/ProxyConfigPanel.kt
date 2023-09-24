package com.uroozgeek.gitproxytogglerjb.settings

import com.intellij.ui.components.JBTextField
import com.uroozgeek.gitproxytogglerjb.settings.AppSettingsState.Companion.instance
import java.awt.BorderLayout
import javax.swing.JPanel

class ProxyConfigPanel {
    private val proxyUrlTextField = JBTextField()

    init {
        val settings = instance
        proxyUrlTextField.text = settings.defaultProxyUrl
    }

    val panel: JPanel
        get() {
            val panel = JPanel(BorderLayout())
            panel.add(proxyUrlTextField, BorderLayout.CENTER)
            return panel
        }

    val proxyUrl: String
        get() = proxyUrlTextField.getText()
}
