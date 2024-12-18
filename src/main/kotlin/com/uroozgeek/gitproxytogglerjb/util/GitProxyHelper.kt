package com.uroozgeek.gitproxytogglerjb.util

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object GitProxyHelper {
    @JvmStatic
    val currentProxyUrl: String?
        get() {
            try {
                val process = ProcessBuilder("git", "config", "--global", "--get", "http.proxy").start()
                val reader = BufferedReader(InputStreamReader(process.inputStream))
                var line: String?
                val proxyUrl = StringBuilder()
                while (reader.readLine().also { line = it } != null) {
                    proxyUrl.append(line)
                }
                reader.close()
                return proxyUrl.toString().trim { it <= ' ' }
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }

    @JvmStatic
    fun setGitProxy(proxyUrl: String) {
        try {
            val process = ProcessBuilder("git", "config", "--global", "http.proxy", proxyUrl).start()
            process.waitFor()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun unsetGitProxy() {
        try {
            val process = ProcessBuilder("git", "config", "--global", "--unset", "http.proxy").start()
            process.waitFor()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
