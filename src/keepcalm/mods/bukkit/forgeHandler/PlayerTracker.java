package keepcalm.mods.bukkit.forgeHandler;

import keepcalm.mods.bukkit.bukkitAPI.BukkitServer;
import keepcalm.mods.bukkit.bukkitAPI.entity.BukkitPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.TcpConnection;
import net.minecraft.util.ChunkCoordinates;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import cpw.mods.fml.common.IPlayerTracker;

public class PlayerTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		PlayerJoinEvent ev = new PlayerJoinEvent(new BukkitPlayer((EntityPlayerMP) player), player.username + " joined the game");
		Bukkit.getPluginManager().callEvent(ev);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		PlayerQuitEvent ev = new PlayerQuitEvent(new BukkitPlayer((EntityPlayerMP) player), player.username + " left the game");
		Bukkit.getPluginManager().callEvent(ev);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		EntityPlayerMP dude = (EntityPlayerMP) player;
		PlayerChangedWorldEvent c = new PlayerChangedWorldEvent(new BukkitPlayer(dude), Bukkit.getWorld(dude.worldObj.getWorldInfo().getWorldName()));
		Bukkit.getPluginManager().callEvent(c);
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		ChunkCoordinates j = player.getHomePosition();
		PlayerRespawnEvent c = new PlayerRespawnEvent(new BukkitPlayer((EntityPlayerMP) player), new Location(Bukkit.getWorld(player.worldObj.getWorldInfo().getWorldName()), j.posX, j.posY, j.posZ), player.hasHome());
		Bukkit.getPluginManager().callEvent(c);
	}

}
