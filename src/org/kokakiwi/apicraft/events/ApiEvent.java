package org.kokakiwi.apicraft.events;

import java.util.Properties;

import org.bukkit.event.Event;
import org.kokakiwi.apicraft.ApiCraft;

public class ApiEvent extends Event {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uri;
	public String[] path;
	private String format = "plaintext";
	private Properties params;
	private boolean action = false;
	private Object response;
	private ApiCraft plugin;

	public ApiEvent(String uri, Properties params, ApiCraft plugin) {
		super("ApiEvent");
		this.uri = uri.substring(1);
		this.path = this.uri.split("/");
		this.params = params;
		this.plugin = plugin;
	}
	
	public boolean isActionTaken() {
		return action;
	}

	public void setActionTaken(boolean action) {
		this.action = action;
	}

	public String getUri() {
		return uri;
	}

	public Properties getParams() {
		return params;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String[] getPath() {
		return path;
	}
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public ApiCraft getPlugin() {
		return plugin;
	}

}
