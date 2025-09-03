package com.nekozouneko.deathLogManager

import org.bukkit.plugin.java.JavaPlugin

class DeathLogManager : JavaPlugin() {
    companion object {
        lateinit var instance: JavaPlugin
    }

    override fun onEnable() {
        instance = this
    }

    override fun onDisable() {}
}
