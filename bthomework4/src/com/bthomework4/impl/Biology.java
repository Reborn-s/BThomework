package com.bthomework4.impl;

public class Biology {
	boolean isNorth = false;
	String name;

	public Biology(String s) {
		this.name = s;
	}

	public void acrossRiver() {
		if (this.isNorth == false) {
			this.isNorth = true;
		} else {
			this.isNorth = false;
		}
	}

	public void initialize() {
		this.isNorth = false;
	}

}
