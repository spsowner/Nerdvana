package com.sps;

/**
 * A class that depends on a configuration property to be set.
 */
public interface ConfigDependent {
	/**
	 * Validates that its config property has been set.
	 * Called when server has started.
	 * If config-dependency is not satisfied, throws {@link RuntimeException}.
	 */
	void validate();
}
