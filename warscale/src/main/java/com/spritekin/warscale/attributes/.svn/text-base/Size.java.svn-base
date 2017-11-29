package com.spritekin.warscale.attributes;

public class Size {

	public static String SIZE_TINY 			= "Tiny";
	public static String SIZE_SMALL 		= "Small";
	public static String SIZE_MEDIUM 		= "Medium";
	public static String SIZE_BIG 			= "Big";
	public static String SIZE_LARGE 		= "Large";
	public static String SIZE_HEROIC 		= "Heroic";
	public static String SIZE_HUGE 			= "Huge";
	public static String SIZE_ENORMOUS 		= "Enormous";
	public static String SIZE_HERCULEAN 	= "Herculean";
	public static String SIZE_MONUMENTAL 	= "Monumental";
	public static String SIZE_GIGANTIC 		= "Gigantic";
	public static String SIZE_MONSTROUS 	= "Monstrous";
	public static String SIZE_PRODIGIOUS 	= "Prodigious";
	public static String SIZE_TITANIC 		= "Titanic";
	public static String SIZE_HUMONGOUS 	= "Humongous";
	public static String SIZE_STUPENDOUS 	= "Stupendous";
	public static String SIZE_GARGANTUAN 	= "Gargantuan";
	public static String SIZE_MOUNTAINOUS 	= "Mountainous";
	public static String SIZE_COLOSSAL 		= "Colossal";
	public static String SIZE_IMMENSE 		= "Immense";
	public static String SIZE_DESCOMUNAL 	= "Descomunal";
	public static String SIZE_SIDERAL 		= "Sideral";
	public static String SIZE_GALACTIC 		= "Galactic";

	private static java.util.HashMap<Integer, Size> sizeSet = new java.util.HashMap<Integer, Size>();	
	private static java.util.HashMap<String, Integer> nameSet = new java.util.HashMap<String, Integer>();

	static {
		int n;
		n = 0; 	nameSet.put(SIZE_TINY, n);			sizeSet.put(n, new Size(n, SIZE_TINY)); 	
		n = 1; 	nameSet.put(SIZE_SMALL, n);			sizeSet.put(n, new Size(n, SIZE_SMALL)); 
		n = 2; 	nameSet.put(SIZE_MEDIUM, n);		sizeSet.put(n, new Size(n, SIZE_MEDIUM)); 
		n = 3; 	nameSet.put(SIZE_BIG, n);			sizeSet.put(n, new Size(n, SIZE_BIG)); 
		n = 4; 	nameSet.put(SIZE_LARGE, n);			sizeSet.put(n, new Size(n, SIZE_LARGE));
		n = 5; 	nameSet.put(SIZE_HEROIC, n);		sizeSet.put(n, new Size(n, SIZE_HEROIC)); 
		n = 6; 	nameSet.put(SIZE_HUGE, n);			sizeSet.put(n, new Size(n, SIZE_HUGE)); 
		n = 7; 	nameSet.put(SIZE_ENORMOUS, n);		sizeSet.put(n, new Size(n, SIZE_ENORMOUS)); 
		n = 8; 	nameSet.put(SIZE_HERCULEAN, n);		sizeSet.put(n, new Size(n, SIZE_HERCULEAN)); 
		n = 9; 	nameSet.put(SIZE_MONUMENTAL, n);	sizeSet.put(n, new Size(n, SIZE_MONUMENTAL)); 
		n = 10; nameSet.put(SIZE_GIGANTIC, n);		sizeSet.put(n, new Size(n, SIZE_GIGANTIC)); 
		n = 11; nameSet.put(SIZE_MONSTROUS, n);		sizeSet.put(n, new Size(n, SIZE_MONSTROUS)); 	
		n = 12; nameSet.put(SIZE_PRODIGIOUS, n);	sizeSet.put(n, new Size(n, SIZE_PRODIGIOUS)); 
		n = 13; nameSet.put(SIZE_TITANIC, n);		sizeSet.put(n, new Size(n, SIZE_TITANIC)); 
		n = 14; nameSet.put(SIZE_HUMONGOUS, n);		sizeSet.put(n, new Size(n, SIZE_HUMONGOUS)); 
		n = 15; nameSet.put(SIZE_STUPENDOUS, n);	sizeSet.put(n, new Size(n, SIZE_STUPENDOUS));
		n = 16; nameSet.put(SIZE_GARGANTUAN, n);	sizeSet.put(n, new Size(n, SIZE_GARGANTUAN)); 
		n = 32; nameSet.put(SIZE_MOUNTAINOUS, n);	sizeSet.put(n, new Size(n, SIZE_MOUNTAINOUS)); 
		n = 64; nameSet.put(SIZE_COLOSSAL, n);		sizeSet.put(n, new Size(n, SIZE_COLOSSAL)); 
		n = 128;nameSet.put(SIZE_IMMENSE, n);		sizeSet.put(n, new Size(n, SIZE_IMMENSE)); 
		n = 256;nameSet.put(SIZE_DESCOMUNAL, n);	sizeSet.put(n, new Size(n, SIZE_DESCOMUNAL)); 
		n = 512;nameSet.put(SIZE_GALACTIC, n);		sizeSet.put(n, new Size(n, SIZE_GALACTIC)); 

		for(n = 0; n < 512; n++) {
			if(!sizeSet.containsKey(n))
				sizeSet.put(n, new Size(n, ""));
		}

	}

	private int sz = 0;
	private String name = "";
	private float avgFire = 0;
	private float maxFire = 0;
	private int baseAP = 0;
	private int weight = 0;
	private int step = 0;
	
	public Size(int sz, String name) {
		super();
		this.sz = sz;
		this.name = name;
		this.avgFire = sz * 1.5f;
		this.maxFire = sz * 3f;
		this.baseAP = sz + 4;
		this.weight = 16 * sz * sz * sz;
		this.step = sz / 2;
	}

	public int getSz() {
		return sz;
	}

	public String getName() {
		return name;
	}

	public float getAvgFire() {
		return avgFire;
	}

	public float getMaxFire() {
		return maxFire;
	}

	public int getBaseAP() {
		return baseAP;
	}

	public int getWeight() {
		return weight;
	}

	public int getStep() {
		return step;
	}
	
	public static Size get(int sz) {
		return sizeSet.get(sz);
	}

}
