package com.uroozgeek.gitproxytogglerjb.settings

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

class ProxyConfigDialog(private val configPanel: ProxyConfigPanel) : DialogWrapper(true) {
    init {
        title = "Configure Proxy Url"
        init()
    }

    public override fun createCenterPanel(): JComponent {
        return configPanel.panel
    }
}
