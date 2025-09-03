package com.nekozouneko.deathLogManager

import com.nekozouneko.deathLogManager.commands.DeathLogCommand
import com.nekozouneko.deathLogManager.listener.PlayerDeathEvent
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.function.Consumer

class DeathLogManager : JavaPlugin() {
    companion object {
        const val FLUSH_INTERVAL = 20L * 300

        lateinit var instance: JavaPlugin
        lateinit var dataFilePath: String
        lateinit var userDatabase: YamlConfiguration

        val isFolia: Boolean = isClassExists("io.papermc.paper.threadedregions.RegionizedServer") || isClassExists("io.papermc.paper.threadedregions.RegionizedServerInitEvent")

        val receiveTypes = listOf(
            "each",
            "near",
            "self",
            "simple"
        )
        val defaultReceiveType = receiveTypes[0]

        private fun isClassExists(clazz: String) : Boolean {
            try{
                Class.forName(clazz)
                return true
            }catch (_: Exception) { return false }
        }
    }

    override fun onEnable() {
        instance = this
        dataFilePath = dataFolder.path + File.separator + "users.yml"

        //Generate Not Enough Json
        if(!databaseInitialize()){
            logger.severe("データファイルの読み込みに失敗しました。")
            server.pluginManager.disablePlugin(this)
        }

        //Register Commands
        getCommand("deathlog")?.setExecutor(DeathLogCommand())

        //Scheduler
        if(!isFolia){
            server.scheduler.runTaskTimer(this, Runnable { databaseFlush() }, 0, FLUSH_INTERVAL)
        }else{
            server.globalRegionScheduler.runAtFixedRate(this, { databaseFlush() }, 0, FLUSH_INTERVAL)
        }

        //Register Listeners
        server.pluginManager.registerEvents(PlayerDeathEvent(), this)
    }

    override fun onDisable() {
        userDatabase.save(dataFilePath)
    }

    private fun databaseInitialize() : Boolean{
        val dataFile = File(dataFilePath)
        dataFile.parentFile?.mkdirs()
        if(!dataFile.exists()) dataFile.createNewFile()

        userDatabase = YamlConfiguration()
        try {
            userDatabase.load(dataFile)
        }catch (_: Exception) { return false }
        return true
    }

    private fun databaseFlush(){
        userDatabase.save(dataFilePath)
        databaseInitialize()
    }
}
