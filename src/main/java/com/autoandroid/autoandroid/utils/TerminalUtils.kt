package com.autoandroid.autoandroid.utils

import java.io.IOException
import java.io.InputStreamReader
import java.io.LineNumberReader

object TerminalUtils {

    @Synchronized
    fun cmd(str: String): String {
        val stringBuffer = StringBuilder()
        try {
            val p = Runtime.getRuntime().exec(str)
            val ir = InputStreamReader(p.inputStream)
            val input = LineNumberReader(ir)      //创建IO管道，准备输出命令执行后的显示内容
            input.readLines().forEach {
                stringBuffer.append(it).append("|")
                println(it)
            }
            stringBuffer.deleteCharAt(stringBuffer.length - 1);
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return stringBuffer.toString()
    }
}
