package org.expressivesoftware.authentication;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SessionKeyTest {
	
	@Test
	public void testSuccessfulRetrievalOfKey(){
		SessionKey sk = new SessionKey();
		assertNotNull(sk.getKey());
	}
	
	@Test
	public void testUniquenessOfKeys() {
		SessionKey sk = new SessionKey();
		assertNotSame(sk.getKey(), sk.getKey());
	}
	
	@Test
	public void testExceedingOneHundredKeys() {
		SessionKey sk = new SessionKey();
		Set<String> uniqueKeys = new HashSet<String>();
		int i = 0;
		while(i<200){
			uniqueKeys.add(sk.getKey());
			i++;
		}
	}

}
