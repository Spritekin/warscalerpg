package com.spritekin.warscale.utils;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class WarscaleConf {

	private static String DEFAULT_CONFIG_FILE = "/Users/Guimo/var/warscale/warscale.conf";
	private static Properties props = null;

	public static void setPath(String path) {
		DEFAULT_CONFIG_FILE = path;
	}

	private static Properties getProperties() {
		if(props == null) {
			props = new Properties();
			try {
	
				File config_file = new File(DEFAULT_CONFIG_FILE);
				if (config_file.exists()) {
					// Load the configuration file if it exists
					Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		            logger.info("WarscaleConf::getProperties - Loading configuration from " + DEFAULT_CONFIG_FILE + "...");
					
					FileInputStream in = new FileInputStream(config_file);
					props.load(in);
		            logger.info("WarscaleConf::getProperties - Configuration loaded");
					System.out.println("Configuration loaded.");
					in.close();
				}
			} catch (IOException e) {
				System.out.println("Config::load IOException when loading configuration");
				props = null;
			}
		}
		return props;
	}

	public static String getProperty(String key, String defaultValue) {
		if(getProperties() == null)
			return "";
		String res = getProperties().getProperty(key, defaultValue);
		return res;
	}
}