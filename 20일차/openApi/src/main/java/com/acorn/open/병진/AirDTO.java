package com.acorn.open.병진;




public class AirDTO {
	String informCause;
	String dataTime;
	String informGrade;
	String image1;
	String image2;
	String image3;

	public AirDTO() {
		// TODO Auto-generated constructor stub
	}

	public AirDTO(String informCause, String dataTime, String informGrade, String image1, String image2,
			String image3) {
		super();
		this.informCause = informCause;
		this.dataTime = dataTime;
		this.informGrade = informGrade;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
	}

	public String getInformCause() {
		return informCause;
	}

	public void setInformCause(String informCause) {
		this.informCause = informCause;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getInformGrade() {
		return informGrade;
	}

	public void setInformGrade(String informGrade) {
		this.informGrade = informGrade;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	@Override
	public String toString() {
		return "AirDTO [informCause=" + informCause + ", dataTime=" + dataTime + ", informGrade=" + informGrade
				+ ", image1=" + image1 + ", image2=" + image2 + ", image3=" + image3 + "]";
	}
	
}
