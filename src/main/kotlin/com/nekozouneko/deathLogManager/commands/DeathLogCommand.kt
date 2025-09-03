package com.nekozouneko.deathLogManager.commands

import com.nekozouneko.deathLogManager.DeathLogManager
import com.nekozouneko.deathLogManager.commands.subcommands.HelpCommand
import com.nekozouneko.deathLogManager.commands.subcommands.InfoCommand
import com.nekozouneko.deathLogManager.commands.subcommands.SetCommand
import com.nekozouneko.deathLogManager.wrapper.Languages
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class DeathLogCommand : CommandExecutor, TabExecutor {
    private val subCommands = mapOf<String, SubCommand>(
        "help" to HelpCommand(),
        "info" to InfoCommand(),
        "set" to SetCommand()
    )

    interface SubCommand {
        fun handle(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>) : Boolean
    }

    override fun onCommand(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        if(p3.isEmpty()){
            //引数入力がない場合、コマンドヘルプを表示
            return subCommands["help"]?.handle(p0, p1, p2, p3) ?: return false
        }
        if(!subCommands.containsKey(p3[0])){
            //引数入力なし or 第一引数が存在しないサブコマンドだった場合
            p0.sendMessage(Languages.Command.ERROR_INVALID_SUBCOMMAND)
            return true
        }
        return subCommands[p3[0]]?.handle(p0, p1, p2, p3) ?: return false
    }

    override fun onTabComplete(
        p0: CommandSender,
        p1: Command,
        p2: String,
        p3: Array<out String>
    ): MutableList<String> {
        if(p3.size == 1) return subCommands.keys.toMutableList()
        if(p3.size == 2 &&
            (p3[0] == "help" || p3[0] == "set")
            ) return DeathLogManager.receiveTypes.filter {
                it.startsWith(p3[1]) && p0.hasPermission("deathlog.switch.${it}")
        }.toMutableList()
        return mutableListOf()
    }
}