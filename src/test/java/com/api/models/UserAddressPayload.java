package com.api.models;

public class UserAddressPayload {
		private String street;
		private String suite;
		private String city;
		private String zipcode;
		private UserGeoPayload geo;
		
		public UserAddressPayload() {
			super();
		}

		public UserAddressPayload(String street, String suite, String city, String zipcode, UserGeoPayload geo) {
			super();
			this.street = street;
			this.suite = suite;
			this.city = city;
			this.zipcode = zipcode;
			this.geo = geo;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getSuite() {
			return suite;
		}

		public void setSuite(String suite) {
			this.suite = suite;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public UserGeoPayload getGeo() {
			return geo;
		}

		public void setGeo(UserGeoPayload geo) {
			this.geo = geo;
		}

		@Override
		public String toString() {
			return "Address [street=" + street + ", suite=" + suite + ", city=" + city + ", zipcode=" + zipcode + ", geo="
					+ geo + "]";
		}	
		
}
