package com.acorn.open.uis;

public class DataDto {
	String informGrade  ;
    String informCause ;
    String dataTime ;
    
	public DataDto(String informGrade, String informCause, String dataTime) {
		this.informGrade = informGrade;
		this.informCause = informCause;
		this.dataTime = dataTime;
	}

	@Override
	public String toString() {
		return "DataDto [informGrade=" + informGrade + ", informCause=" + informCause + ", dataTime=" + dataTime + "]";
	}

	public String getInformGrade() {
		return informGrade;
	}

	public void setInformGrade(String informGrade) {
		this.informGrade = informGrade;
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
    
    
   
}
