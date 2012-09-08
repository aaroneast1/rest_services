package uk.co.and.comments.modules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class PropertiesModule extends AbstractModule {

	@Override
	protected void configure() {
		try{
			Properties databaseProperties = loadProperties("db.properties");
			Names.bindProperties(binder(), databaseProperties);
		}catch(RuntimeException e){
			addError("Could not configure database properties",e);
		}
	}
	
	private static Properties loadProperties(String name){
		Properties properties = new Properties();
		InputStream is = ClassLoader.getSystemResourceAsStream(name);
		try {
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load properties",e);
		}finally{
			if(is != null){
				try{
					is.close();
				}catch(IOException e){
					//don't care
				}
			}
		}
		
		return properties;
	}
	
}
