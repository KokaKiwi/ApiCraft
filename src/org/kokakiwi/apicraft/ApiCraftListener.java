package org.kokakiwi.apicraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.kokakiwi.apicraft.events.ApiEvent;
import org.kokakiwi.apicraft.events.ApiListener;

public class ApiCraftListener extends ApiListener {

	@Override
	public void onApiEvent(ApiEvent event) {
		if(event.path[0].equalsIgnoreCase("serverinfos"))
		{
			if(event.path.length > 1)
			{
				if(event.path[1].equalsIgnoreCase("online"))
				{
					Player[] online = event.getPlugin().getServer().getOnlinePlayers();
					event.setResponse(online.length + "");
					event.setActionTaken(true);	
				}else if(event.path[1].equalsIgnoreCase("players-online"))
				{
					Player[] online = event.getPlugin().getServer().getOnlinePlayers();
					List<String> players = new ArrayList<String>();
					for(Player p : online)
					{
						players.add(p.getName());
					}
					event.setResponse(players);
					event.setActionTaken(true);
				}else if(event.path[1].equalsIgnoreCase("whois")) {
					HashMap<String, Object> infos = new HashMap<String, Object>();
					Player player = event.getPlugin().getServer().getPlayer(event.path[2]);
					
					infos.put("pseudo", player.getName());
					infos.put("displayName", player.getDisplayName());
					infos.put("health", player.getHealth());
					
					event.setResponse(infos);
					event.setActionTaken(true);
				}else if(event.path[1].equalsIgnoreCase("max-players")) {
					event.setResponse(event.getPlugin().getServer().getMaxPlayers());
					event.setActionTaken(true);
				}
			}else {
				event.setResponse("APIs available for 'serverinfos' : online, players-online, whois");
				event.setActionTaken(true);
			}
		}
	}

}
