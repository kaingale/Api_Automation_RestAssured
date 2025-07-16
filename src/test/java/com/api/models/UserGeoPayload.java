package com.api.models;

public class UserGeoPayload {
	
	private String lat;
	private String lng;
	
	public UserGeoPayload() {
		super();
	}

	public UserGeoPayload(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Geo [lat=" + lat + ", lng=" + lng + "]";
	}
	
}
