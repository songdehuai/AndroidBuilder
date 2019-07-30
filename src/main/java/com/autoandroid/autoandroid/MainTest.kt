package com.autoandroid.autoandroid

import com.autoandroid.autoandroid.entity.Flavor
import com.autoandroid.autoandroid.flavors.FlavorTools
import com.autoandroid.autoandroid.utils.GradleUitls
import com.autoandroid.autoandroid.utils.ProcessUtils
import com.autoandroid.autoandroid.utils.TerminalUtils
import java.io.File

fun main() {

//    FlavorTools.buildApk("projectTest2")

    val flavors = ArrayList<Flavor>()
    for (i in 1..10) {
        flavors.add(Flavor("nihao" + i, "String", "Test111", "哈喽啊" + i))
    }

    print(FlavorTools.getFlavorStr(flavors))
}