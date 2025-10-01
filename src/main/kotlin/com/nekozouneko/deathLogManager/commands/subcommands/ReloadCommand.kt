package com.nekozouneko.deathLogManager.commands.subcommands

import com.nekozouneko.deathLogManager.DeathLogManager
import com.nekozouneko.deathLogManager.commands.DeathLogCommand
import com.nekozouneko.deathLogManager.wrapper.Languages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class ReloadCommand : DeathLogCommand.SubCommand{
    override fun handle(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {

        val isSuccess = DeathLogManager.configurations.reloadConfig()
        if(isSuccess){
            p0.sendMessage(Languages.ReloadCommand.RELOADED_SUCCESSFULLY)
        }else{
            p0.sendMessage(Languages.ReloadCommand.RELOADED_CONTAINS_INVALID)
        }

        return true
    }
}