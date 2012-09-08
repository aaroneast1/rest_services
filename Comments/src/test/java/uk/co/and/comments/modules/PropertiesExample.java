package uk.co.and.comments.modules;

import org.junit.Ignore;

import com.google.inject.Inject;
import com.google.inject.name.Named;

@Ignore
public class PropertiesExample {
	
	private String url;
	private String username;
	private String password;
	
	@Inject
	public void databaseUrl(@Named("db.url") String url){
		this.url = url;
	}
	
	@Inject
	public void username(@Named("db.username") String username){
		this.username = username;
	}
	
	@Inject
	public void password(@Named("db.password") String password){
		this.password = password;
	}
	
	public String getUrl(){
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
