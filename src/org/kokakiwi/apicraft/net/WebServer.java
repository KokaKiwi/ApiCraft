package org.kokakiwi.apicraft.net;

import java.io.IOException;
import java.util.Properties;

import org.kokakiwi.apicraft.*;
import org.kokakiwi.apicraft.events.ApiEvent;
import org.kokakiwi.apicraft.utils.PlainFormat;
import org.kokakiwi.apicraft.utils.XMLFormat;

public class WebServer extends NanoHTTPD {
	
	private ApiCraft plugin;

	public WebServer(ApiCraft plugin, int port) throws IOException {
		super(port);
		this.plugin = plugin;
	}

	@Override
	public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
		ApiEvent event = new ApiEvent(uri, parms, this.plugin);
		plugin.getServer().getPluginManager().callEvent(event);
		
		if(event.isActionTaken())
		{
			if(parms.getProperty("format") != null)
			{
				String format = parms.getProperty("format");
				if(format.equalsIgnoreCase("xml") || event.getFormat().equalsIgnoreCase("xml"))
				{
					return new Response(HTTP_OK, MIME_XML, XMLFormat.format(event.getResponse()));
				}else {
					return new Response(HTTP_OK, MIME_PLAINTEXT, PlainFormat.format(event.getResponse()));
				}
			}else{
				return new Response(HTTP_OK, MIME_PLAINTEXT, PlainFormat.format(event.getResponse()));
			}
		}else{
			if(parms.getProperty("format") != null)
			{
				String format = parms.getProperty("format");
				if(format.equalsIgnoreCase("xml"))
				{
					return new Response(HTTP_NOTFOUND, MIME_PLAINTEXT, XMLFormat.format("Listener not found"));
				}else {
					return new Response(HTTP_NOTFOUND, MIME_PLAINTEXT, PlainFormat.format("Listener not found"));
				}
			}else{
				return new Response(HTTP_NOTFOUND, MIME_PLAINTEXT, "Listener not found");
			}
		}
	}

}
