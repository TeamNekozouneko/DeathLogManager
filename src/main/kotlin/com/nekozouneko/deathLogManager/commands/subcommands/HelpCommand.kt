package com.nekozouneko.deathLogManager.commands.subcommands

import com.nekozouneko.deathLogManager.DeathLogManager
import com.nekozouneko.deathLogManager.commands.DeathLogCommand
import com.nekozouneko.deathLogManager.wrapper.Languages
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class HelpCommand : DeathLogCommand.SubCommand {
    override fun handle(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if(p3.isEmpty() || p3.size == 1) {
            //第二引数がない場合、コマンドヘルプを表示
            p0.sendMessage(Languages.HelpCommand.HELP_MESSAGE)
            return true
        }
        if(p3.size >= 2 && !DeathLogManager.receiveTypes.contains(p3[1])){
            //第二引数が指定されているが、存在しない受け取り方の場合
            p0.sendMessage(Languages.General.ERROR_INVALID_RECEIVE_TYPE)
            return true
        }

        if(p3[1] == "each") p0.sendMessage(Languages.HelpCommand.ReceiveHelps.EACH)
        if(p3[1] == "near") p0.sendMessage(Languages.HelpCommand.ReceiveHelps.NEAR)
        if(p3[1] == "self") p0.sendMessage(Languages.HelpCommand.ReceiveHelps.SELF)
        if(p3[1] == "simple") p0.sendMessage(Languages.HelpCommand.ReceiveHelps.SIMPLE)
        return true
    }
}