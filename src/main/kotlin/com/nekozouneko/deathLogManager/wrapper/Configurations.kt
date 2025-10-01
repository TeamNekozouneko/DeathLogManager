package com.nekozouneko.deathLogManager.wrapper

import com.nekozouneko.deathLogManager.DeathLogManager
import org.bukkit.Bukkit

class Configurations {
    private var config = DeathLogManager.instance.config

    //Default Define
    private val defaultValues = mapOf(
        "DefaultType" to "each"
    )

    //Initialize
    private lateinit var defaultType: String

    //Getter
    fun getDefaultType(): String {
        return defaultType
    }

    //Initializer
    fun reloadConfig(skipReload: Boolean = false): Boolean{
        Bukkit.getLogger().info("Reloading configurations...")

        if(!skipReload) DeathLogManager.instance.reloadConfig()
        config = DeathLogManager.instance.config
        var isAppliedDefault = false

        // Checks
        val checks = mapOf(
            "DefaultType" to !DeathLogManager.receiveTypes.contains(config.getString("DefaultType"))
        )

        for (section in checks) {
            if(config.getString(section.key) == null || section.value){

                //Data Default Define
                if (section.key == "DefaultType") defaultType = defaultValues[section.key]!!

                Bukkit.getLogger().severe("Invalid Format: ${section.key}")
                isAppliedDefault = true
            }else{
                //Data Success Define
                if (section.key == "DefaultType") defaultType = config.getString(section.key)!!
            }
        }

        Bukkit.getLogger().info("Reloaded configurations successfully!")
        if (isAppliedDefault) return false
        return true
    }
}