package com.corn.market.town.domain;

public class TownCertification {
	
	private String user_id;
	private String town_code;
	private String latitude;
	private String longitude;
	
	public TownCertification() {}

	public TownCertification(String user_id, String town_code, String latitude, String longitude) {
		super();
		this.user_id = user_id;
		this.town_code = town_code;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTown_code() {
		return town_code;
	}

	public void setTown_code(String town_code) {
		this.town_code = town_code;
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
		return "TownCertification [user_id=" + user_id + ", town_code=" + town_code + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
