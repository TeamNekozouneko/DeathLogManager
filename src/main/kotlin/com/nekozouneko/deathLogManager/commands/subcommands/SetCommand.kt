package com.nekozouneko.deathLogManager.commands.subcommands

import com.nekozouneko.deathLogManager.DeathLogManager
import com.nekozouneko.deathLogManager.commands.DeathLogCommand
import com.nekozouneko.deathLogManager.wrapper.Languages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetCommand : DeathLogCommand.SubCommand {
    override fun handle(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if(p0 !is Player){
            p0.sendMessage(Languages.Command.ERROR_EXECUTE_ONLY_PLAYER)
            return true
        }
        if(p3.size <= 1){
            p0.sendMessage(Languages.SetCommand.ERROR_ENTER_RECEIVE_TYPE)
            return true
        }
        if(!DeathLogManager.receiveTypes.contains(p3[1])){
            p0.sendMessage(Languages.General.ERROR_INVALID_RECEIVE_TYPE)
            return true
        }

        val database = DeathLogManager.userDatabase
        database.set(p0.uniqueId.toString(), p3[1])

        p0.sendMessage(Languages.SetCommand.SET_RECEIVE_TYPE(p3[1]))
        return true
    }
}