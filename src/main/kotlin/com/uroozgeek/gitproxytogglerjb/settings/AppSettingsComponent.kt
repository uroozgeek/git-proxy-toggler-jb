package com.uroozgeek.gitproxytogglerjb.settings

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val panel: JPanel
    private val defaultProxyUrlText = JBTextField()

    init {
        panel = FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel("Default proxy url: "), defaultProxyUrlText, 1, false)
                .addComponentFillVertically(JPanel(), 0)
                .panel
    }

    val preferredFocusedComponent: JComponent
        get() = defaultProxyUrlText

    fun getDefaultProxyUrlText(): String {
        return defaultProxyUrlText.getText()
    }

    fun setDefaultProxyUrlText(newText: String) {
        defaultProxyUrlText.text = newText
    }
}