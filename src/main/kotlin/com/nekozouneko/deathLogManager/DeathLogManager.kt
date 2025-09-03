package com.nekozouneko.deathLogManager

import com.nekozouneko.deathLogManager.commands.DeathLogManager
import org.bukkit.plugin.java.JavaPlugin

class DeathLogManager : JavaPlugin() {
    companion object {
        lateinit var instance: JavaPlugin
    }

    override fun onEnable() {
        instance = this

        getCommand("deathlog")?.setExecutor(DeathLogManager())
    }

    override fun onDisable() {}
}
