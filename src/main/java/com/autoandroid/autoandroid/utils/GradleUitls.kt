package com.autoandroid.autoandroid.utils

import groovy.lang.Binding
import groovy.lang.GroovyShell
import org.codehaus.groovy.util.StringUtil
import java.io.File
import java.util.*
import java.util.concurrent.Executors
import groovy.lang.GroovyObject
import groovy.lang.GroovyCodeSource
import org.codehaus.groovy.ast.decompiled.AsmDecompiler.parseClass
import groovy.lang.GroovyClassLoader
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory


object GradleUitls {

    fun test() {
        //groovy.lang.Binding
        var binding: Binding? = Binding()
        var shell: GroovyShell? = GroovyShell(binding)
        binding!!.setVariable("name", "zhangsan")
        shell!!.evaluate("println 'Hello World! I am ' + name;")
        //在script中,声明变量,不能使用def,否则scrope不一致.
        shell.evaluate("date = new Date();")
        val date = binding!!.getVariable("date") as Date
        System.out.println("Date:" + date.getTime())
        //以返回值的方式,获取script内部变量值,或者执行结果
        //一个shell实例中,所有变量值,将会在此"session"中传递下去."date"可以在此后的script中获取
        val time = shell.evaluate("def time = date.getTime(); return time;") as Long
        println("Time:$time")
        binding!!.setVariable("list", arrayOf<String>("A", "B", "C"))
        //invoke method
        val joinString = shell.evaluate("def call(){return list.join(' - ')};call();") as String
        println("Array join:$joinString")
        shell = null
        binding = null
    }


}