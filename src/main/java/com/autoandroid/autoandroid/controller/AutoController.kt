package com.autoandroid.autoandroid.controller

import com.autoandroid.autoandroid.entity.Flavor
import com.autoandroid.autoandroid.entity.Result
import com.autoandroid.autoandroid.flavors.FlavorTools
import com.autoandroid.autoandroid.utils.ProcessUtils
import com.autoandroid.autoandroid.utils.TerminalUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("auto")
@RestController
class AutoController {

    @PostMapping("test")
    fun test(str: String): Result {
        return if (str.isNotEmpty()) {
            Result(200, TerminalUtils.cmd(str))
        } else {
            Result(400)
        }
    }

    @PostMapping("build")
    fun buildApk(@RequestBody flavor: Flavor) {
        FlavorTools.inject(FlavorTools.getFlavorStr(flavor))
        val r = ProcessUtils.run("sh /Users/songdehuai/IdeaProjects/AutoAndroid/files/builder.sh")
        println("code:" + r.code + "\ndata:" + r.data)
    }

}
