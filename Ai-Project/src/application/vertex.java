package application;

public class vertex  {
	String name;
	 vertex previous;
	double latitude;
	double longitude;
	public vertex(String name,double longitude,double latitude) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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



	public vertex getPrevious() {
		return previous;
	}



	public void setPrevious(vertex previous) {
		this.previous = previous;
	}}

