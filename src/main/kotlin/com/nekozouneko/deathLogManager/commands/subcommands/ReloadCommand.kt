package com.nekozouneko.deathLogManager.commands.subcommands

import com.nekozouneko.deathLogManager.commands.DeathLogCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class ReloadCommand : DeathLogCommand.SubCommand{
    override fun handle(p0: CommandSender, p1: Command, p2: String, p3: Array<out String>): Boolean {
        return true
    }
}