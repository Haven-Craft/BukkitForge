package keepcalm.mods.bukkit.forgeHandler.commands;

import keepcalm.mods.bukkit.BukkitContainer;

import net.minecraft.command.CommandServerStop;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import org.bukkit.Bukkit;
/**
 * Shuts down the server in a bukkit-friendly way.
 * @author keepcalm
 *
 */
public class BukkitCommandStop extends CommandBase {

	@Override
	public String getCommandName() {
		return "stop";
	}
	
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (Bukkit.getPlayerExact(sender.getCommandSenderName()).hasPermission("org.bukkit.stop") || (new net.minecraft.command.CommandServerStop()).canCommandSenderUseCommand(sender)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		BukkitContainer.bServer.shutdown();
		(new CommandServerStop()).processCommand(var1, var2);
	}

}
