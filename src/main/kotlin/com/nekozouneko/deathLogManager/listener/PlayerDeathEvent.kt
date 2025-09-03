package com.nekozouneko.deathLogManager.listener

import com.nekozouneko.deathLogManager.DeathLogManager
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathEvent : Listener {
    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent){
        val player = e.player
        val killer = player.killer
        val deathLocation = player.location

        val database = DeathLogManager.userDatabase
        val playerData = database.getString(player.uniqueId.toString(), DeathLogManager.defaultReceiveType) ?: return
        var killerData: String? = null
        if (killer != null) killerData = database.getString(killer.uniqueId.toString(), DeathLogManager.defaultReceiveType) ?: return

        val receivePlayers: MutableSet<Player> = mutableSetOf()

        //Each
        if (playerData == ReceiveType.EACH) receivePlayers.add(player)
        if (killer != null && killerData == ReceiveType.EACH) receivePlayers.add(killer)

        //Near
        val nearPlayers = deathLocation.getNearbyPlayers(10.0).filter {
            val receiveType = database.getString(it.uniqueId.toString(), DeathLogManager.defaultReceiveType)
            receiveType == ReceiveType.NEAR
        }
        for (nearPlayer in nearPlayers) receivePlayers.add(nearPlayer)

        //Self
        if (playerData == ReceiveType.SELF) receivePlayers.add(player)

        //Simple
        val simpleReceivers = DeathLogManager.instance.server.onlinePlayers.filter {
            val receiveType = database.getString(it.uniqueId.toString(), DeathLogManager.defaultReceiveType)
            receiveType == ReceiveType.SIMPLE
        }.toMutableSet()
        receivePlayers.removeAll(simpleReceivers)

        //Send Death Message
        val originalMessage = e.deathMessage() ?: return
        e.deathMessage(null)
        for (receiver in receivePlayers) receiver.sendMessage(originalMessage)
        for (simpleReceiver in simpleReceivers) simpleReceiver.sendMessage(getSimpleDeathMessage(e))

    }

    private fun getSimpleDeathMessage(e: PlayerDeathEvent) : Component{
        val player = e.player
        val killer = player.killer ?: return Component.text("${player.name}は死んだ。")
        return Component.text("${player.name}は${killer.name}によって殺された。")

    }

    object ReceiveType{
        val EACH = "each"
        val NEAR = "near"
        val SELF = "self"
        val SIMPLE = "simple"
    }
}