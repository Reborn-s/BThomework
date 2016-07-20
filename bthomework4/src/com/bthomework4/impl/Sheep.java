package com.bthomework4.impl;

import com.bthomework4.inter.IEat;

public class Sheep extends Biology implements IEat {

	public Sheep(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("oh no!The sheep eats the vegetable!");

	}

	public void initialize() {
		// TODO Auto-generated method stub
		this.isNorth = false;
	}

}
