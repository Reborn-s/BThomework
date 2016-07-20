package com.bthomework4.impl;

import com.bthomework4.inter.IEat;

public class Wolf extends Biology implements IEat {

	public Wolf(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("oh no!The wolf eats the sheep!");
	}

	public void initialize() {
		// TODO Auto-generated method stub
		this.isNorth = false;
	}
}
