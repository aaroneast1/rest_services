package uk.co.and.comments.model;

public class CacheConfig {
	
	private final int initialCapacity;
	private final float loadFactor;
	private final int concurrencyLevel;
	
	public CacheConfig(int initialCapacity, float loadFactor, int concurrencyLevel){
		this.initialCapacity = initialCapacity;
		this.loadFactor = loadFactor;
		this.concurrencyLevel = concurrencyLevel;
	}

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public float getLoadFactor() {
		return loadFactor;
	}

	public int getConcurrencyLevel() {
		return concurrencyLevel;
	}

}
