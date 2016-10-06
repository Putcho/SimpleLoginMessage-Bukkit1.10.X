package net.putcho.bukkit.simpleloginmessage.event;

import static net.putcho.bukkit.simpleloginmessage.SimpleLoginMessage.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginListener implements Listener{
	@EventHandler
	public void onLogin(PlayerJoinEvent e){
		Player p = e.getPlayer();

		if((MAIN.flag & 1) == 1){
			String[] msgs = MAIN.formatPublicMessage(p.getDisplayName());
			MAIN.tellAllPlayer(msgs);
		}
		if((MAIN.flag & 2) != 2){
			// output log
			MAIN.getServer().getConsoleSender().sendMessage(e.getJoinMessage());
			e.setJoinMessage(null);
		}

		if((MAIN.flag & 4) == 4){
			String[] msgs = MAIN.formatPrivateMessage(p.getDisplayName());
			p.sendMessage(msgs);
		}
	}
}
