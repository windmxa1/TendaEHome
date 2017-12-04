package org.util;

public class Coordinate {
	private double latitude; // 维度
	private double longitude; // 经度
	private String key; // 账户ID
	public static final double radius; // 半径

	static {
		radius = 10000;
	}
	public Coordinate(){
		
	}
	public Coordinate(double latitude, double longitude, String key) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.key = key;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public static double getRadius() {
		return radius;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}