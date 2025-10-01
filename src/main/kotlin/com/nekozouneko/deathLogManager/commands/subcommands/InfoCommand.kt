package com.nekozouneko.deathLogManager.commands.subcommands

import com.nekozouneko.deathLogManager.DeathLogManager
import com.nekozouneko.deathLogManager.commands.DeathLogCommand
import com.nekozouneko.deathLogManager.wrapper.Languages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InfoCommand : DeathLogCommand.SubCommand {
    override fun handle(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if(p0 !is Player){
            p0.sendMessage(Languages.Command.ERROR_EXECUTE_ONLY_PLAYER)
            return true
        }

        val database = DeathLogManager.userDatabase
        val userData = database.getString(p0.uniqueId.toString(), DeathLogManager.configurations.getDefaultType())

        if(userData == null) {
            p0.sendMessage(Languages.General.ERROR_DATABASE)
            return true
        }

        p0.sendMessage(Languages.InfoCommand.CURRENT_TYPE(userData))

        return true
    }
}