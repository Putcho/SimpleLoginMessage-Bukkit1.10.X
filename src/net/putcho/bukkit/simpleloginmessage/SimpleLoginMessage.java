package net.putcho.bukkit.simpleloginmessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.putcho.bukkit.simpleloginmessage.event.LoginListener;

public class SimpleLoginMessage extends JavaPlugin{
	// static Instance
	public static SimpleLoginMessage MAIN;

	// enabled flag
	public int flag;
	public String[] pumes, prmes;

	@Override
	public void onEnable(){
		init();
		readConfig();
	}

	@Override
	public void onDisable(){
	}

	private void init(){
		MAIN = this;
		flag = 0;

		this.getServer().getPluginManager().registerEvents(new LoginListener(), this);
	}

	private void readConfig(){
		this.saveDefaultConfig();

		FileConfiguration config = this.getConfig();
		flag = config.getBoolean("public.enabled")? flag | 1: flag & ~1;
		flag = config.getBoolean("public.visibleDefault")? flag | 2: flag & ~2;
		pumes = config.getStringList("public.messages").toArray(new String[0]);
		flag = config.getBoolean("private.enabled")? flag | 4: flag & ~4;
		prmes = config.getStringList("private.messages").toArray(new String[0]);
	}

	private String[] formatMessage(String[] msgs, String name){
		String[] newmsgs = new String[msgs.length];
		for(int i = 0; i < newmsgs.length; i++){
			newmsgs[i] = msgs[i].replaceAll("\\$p", name);
		}
		return newmsgs;
	}

	public String[] formatPublicMessage(String name){
		return formatMessage(pumes, name);
	}

	public String[] formatPrivateMessage(String name){
		return formatMessage(prmes, name);
	}

	public void tellAllPlayer(String... msgs){
		for(Player p: this.getServer().getOnlinePlayers()){
			p.sendMessage(msgs);
		}
	}
}
