package com.bthomework4.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AcrossRiver {

	/**
	 * @param args
	 * @throws IOException
	 */

	static Farmer farmer = new Farmer("farmer");
	static Wolf wolf = new Wolf("wolf");
	static Sheep sheep = new Sheep("sheep");
	static Vegetable vegetable = new Vegetable("vegetable");

	static ArrayList<Biology> northList = new ArrayList<Biology>();
	static ArrayList<Biology> southList = new ArrayList<Biology>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		startAcrossRiver();

	}

	public static void startAcrossRiver() throws IOException {
		southList.add(farmer);
		southList.add(wolf);
		southList.add(sheep);
		southList.add(vegetable);

		// BufferedReader br=new BufferedReader(new
		// InputStreamReader(System.in));
		System.out.println("North:");
		System.out.println("------------------------------------------");
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("South:   Farmer  Wolf  Sheep  Vegetable");
		System.out.println("Farmer:1,Wolf:2,Sheep:3,Vegetable:4");
		System.out
				.println("please input one or two numbers to let them across the river(split the numbers with the whitespace)：");

		while ((northList.size() != 4)) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String input = br.readLine();
			String north = printEachSide(northList);
			String south = printEachSide(southList);
			String temp[] = input.split("\\s");
			if (temp.length > 2) {
				System.out
						.println("The farmer can only bring one to the opposite side!Please input again!");
				System.out.println("North:   " + north);
				System.out
						.println("------------------------------------------");
				System.out.println();
				System.out
						.println("------------------------------------------");
				System.out.println("South:   " + south);
				System.out.println("Farmer:1,Wolf:2,Sheep:3,Vegetable:4");
				System.out
						.println("please input one or two numbers to let them across the river(split the numbers with the whitespace)：");
				continue;
			}

			analyseInput(temp);
			if (isSafe()) {
				String north1 = printEachSide(northList);
				String south1 = printEachSide(southList);
				System.out.println("North:   " + north1);
				System.out
						.println("------------------------------------------");
				System.out.println();
				System.out
						.println("------------------------------------------");
				System.out.println("South:   " + south1);
				System.out.println("Farmer:1,Wolf:2,Sheep:3,Vegetable:4");
				System.out
						.println("please input one or two numbers to let them across the river(split the numbers with the whitespace)：");
			} else {
				System.out.println("Please try again!Come On!");
				System.out.println("North:");
				System.out
						.println("------------------------------------------");
				System.out.println();
				System.out
						.println("------------------------------------------");
				System.out.println("South:   Farmer  Wolf  Sheep  Vegetable");
				System.out.println("Farmer:1,Wolf:2,Sheep:3,Vegetable:4");
				System.out
						.println("please input one or two numbers to let them across the river(split the numbers with the whitespace)：");

				initializeList();
			}
		}
		System.out.println("North:   Farmer  Wolf  Sheep  Vegetable");
		System.out.println("------------------------------------------");
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("South:");
		System.out.println("You make it!Congratulations!");
	}

	// 打印在北岸和南岸的生物
	public static String printEachSide(ArrayList<Biology> a) {
		String side = "";
		for (int i = 0; i < a.size(); i++) {
			side = side + a.get(i).name + "  ";
		}
		return side;
	}

	// 分析用户的输入，将数字对号入座
	public static void analyseInput(String[] temp) {

		for (int i = 0; i < temp.length; i++) {
			if (temp[i].equals("1")) {
				acrossRiver(farmer);
			} else if (temp[i].equals("2")) {
				acrossRiver(wolf);
			} else if (temp[i].equals("3")) {
				acrossRiver(sheep);
			} else if (temp[i].equals("4")) {
				acrossRiver(vegetable);
			}
		}
	}

	// 每个生物过河
	public static void acrossRiver(Biology biology) {
		if (biology.isNorth == false) {
			northList.add(biology);
			southList.remove(biology);

		} else {
			southList.add(biology);
			northList.remove(biology);
		}

		biology.acrossRiver();
		//System.out.println("------" + biology.name + "  isNorth  "+ biology.isNorth);
	}

	// 判断是否安全
	public static boolean isSafe() {
		if ((northList.contains(farmer) && southList.contains(wolf) && southList
				.contains(sheep))
				|| (southList.contains(farmer) && northList.contains(wolf) && northList
						.contains(sheep))) {
			wolf.eat();
			return false;
		}
		if ((northList.contains(farmer) && southList.contains(vegetable) && southList
				.contains(sheep))
				|| (southList.contains(farmer) && northList.contains(vegetable) && northList
						.contains(sheep))) {
			sheep.eat();
			return false;
		}
		return true;
	}

	// ，如果发生了不安全的情况，则初始化各个数组和对象
	public static void initializeList() {
		northList.clear();
		southList.clear();
		farmer.initialize();
		wolf.initialize();
		sheep.initialize();
		vegetable.initialize();
		southList.add(farmer);
		southList.add(wolf);
		southList.add(sheep);
		southList.add(vegetable);
	}

}
