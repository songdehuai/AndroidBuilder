package com.autoandroid.autoandroid.flavors

import com.autoandroid.autoandroid.entity.Flavor
import com.autoandroid.autoandroid.io.FileUtils
import com.autoandroid.autoandroid.utils.ProcessUtils
import groovy.lang.Binding
import java.io.File
import java.io.IOException
import java.lang.StringBuilder
import java.util.*

object FlavorTools {

    val BUILDCONFIGFIELD = "buildConfigField"

    /**
     * 生成构建配置字段
     */
    fun genBuildConfigField(flavor: Flavor): String {
        return " $BUILDCONFIGFIELD \"${flavor.type}\", \"${flavor.key}\", '\"${flavor.value} \"'"
    }

    /**
     * 生成多条构建配置字段
     */
    fun genBuildConfigFields(flavors: LinkedHashSet<Flavor>): String {
        val stringBuilder = StringBuilder()
        flavors.forEach {
            stringBuilder.append(genBuildConfigField(it))
        }
        return stringBuilder.toString()
    }

    fun getProductFlavors(filePath: String) {
        if (filePath.isNullOrEmpty()) {
            throw IOException("文件路径不能为空!")
        }
        val file = File(filePath)
        if (!file.exists()) {
            throw IOException("文件不存在!")
        }
        if (!file.isFile) {
            throw IOException("${filePath}不是文件")
        }
    }

    /**
     * 获取一个渠道
     */
    fun getFlavorStr(flavor: Flavor): String {
        val flavorStr = StringBuffer()
        //拼接头
        flavorStr.append("productFlavors { \n")
        //拼接渠道name
        flavorStr.append("    ${flavor.name} {")
        //增加渠道 buildConfigField
        flavorStr.append(genBuildConfigField(flavor))
        //拼接渠道结尾
        flavorStr.append("}\n")
        //拼接尾
        flavorStr.append("}")
        return flavorStr.toString()
    }

    /**
     * 获取一个渠道
     */
    fun getFlavorStrNoHead(flavor: Flavor): String {
        val flavorStr = StringBuffer()
        //拼接头
        //flavorStr.append("productFlavors { \n")
        //拼接渠道name
        flavorStr.append("    ${flavor.name} {")
        //增加渠道 buildConfigField
        flavorStr.append(genBuildConfigField(flavor))
        //拼接渠道结尾
        flavorStr.append("}")
        //拼接尾
        //   flavorStr.append("\n}")
        return flavorStr.toString()
    }

    /**
     * 获取一个渠道
     */
    fun getFlavorStr(flavors: ArrayList<Flavor>): String {
        val flavorStr = StringBuffer()
        //拼接头
        flavorStr.append("productFlavors { \n")

        flavors.forEach {
            flavorStr.append(getFlavorStrNoHead(it))
            flavorStr.append("\n")
        }
        //拼接尾
        flavorStr.append("}")
        return flavorStr.toString()
    }

    /**
     * 从默认build文件注入渠道后，生成新文件
     */
    fun inject(data: String) {
        val newFileName = "PluginTest/app/build.gradle"
        val file = File("files/default.build.gradle")
        val configArray = ArrayList<String>()
        var startLine = -1
        file.readLines().forEachIndexed { index, str ->
            configArray.add(str)
            if (str.contains("android {")) {
                startLine = index
            }
        }
        //插入代码
        val newFile = File(newFileName)
        if (newFile.exists()) {
            newFile.delete()
            newFile.createNewFile()
        }
        val strBuffer = StringBuffer()
        configArray.forEachIndexed { index, s ->
            if (index == (startLine + 1)) {
                strBuffer.append("\n$data")
            } else {
                strBuffer.append("\n$s")
            }
        }
        FileUtils.writeStringToFile(newFile, strBuffer.toString())
        print(strBuffer.toString())
    }

    fun buildApk(flavorName: String) {
        val shFileName = "/Users/songdehuai/IdeaProjects/AutoAndroid/files/builder.sh"
        val flavorLastStr = flavorName.substring(0, 1).toUpperCase()
        val name = flavorName.replaceFirst(flavorLastStr.toLowerCase(), flavorLastStr)
        val shFile = File(shFileName)
        val shStr = "cd /Users/songdehuai/AndroidStudioProjects/PluginTest/ && gradle clean assemble" + name + "Release"
        FileUtils.writeStringToFile(shFile, shStr)
        val r = ProcessUtils.run("sh $shFileName")
        println("code:" + r.code + "\ndata:" + r.data)
    }


}