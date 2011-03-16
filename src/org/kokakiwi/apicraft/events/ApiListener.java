package org.kokakiwi.apicraft.events;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;

public abstract class ApiListener extends CustomEventListener  {
	
	@Override
	public void onCustomEvent(Event event) {
		if(event.getType() != Event.Type.CUSTOM_EVENT)
			return;
		
		if(event.getEventName() != "ApiEvent")
			return;
		
		ApiEvent e = (ApiEvent) event;
		onApiEvent(e);
	}
	
	public abstract void onApiEvent(ApiEvent event);

}
