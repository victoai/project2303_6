package com.corn.market.town.domain;

public class TownInfo {
	
	private String town_name;
	private String latitude;
	private String longitude;
	
	public TownInfo() {}

	public TownInfo(String town_name, String latitude, String longitude) {
		super();
		this.town_name = town_name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getTown_name() {
		return town_name;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "TownInfo [town_name=" + town_name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
