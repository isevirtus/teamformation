package project;

import java.io.Serializable;

public class Tag implements Comparable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340583871982353714L;
	private String key;
	private double value;

	public Tag(String key, double value) {
		setKey(key.toLowerCase().trim());
		setValue(value);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[" + key + " = " + value + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tag) {
			return ((Tag) obj).key.equalsIgnoreCase(key);
		}
		return false;
	}

	@Override
	public int compareTo(Object tag) {
		if (getKey().equalsIgnoreCase(((Tag) tag).getKey())) {
			return 0;
		}
		return 1;
	}

}
