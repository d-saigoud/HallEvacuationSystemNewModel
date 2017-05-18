package com.sai.dto;

public class PersonDTO {
	
	private PositionDTO personPosition;
	
	private PositionDTO initialPosition;
	
	private boolean personInside;
	
	private char exittedFrom;
	private int secondsTakenToExit;
	
	//This indicates number of steps a person can move in any second
	//Note: Should be between 4-8feet
	private int feetThatPersonCanMoveInSecond;
	
	public PersonDTO(PositionDTO personPosition, PositionDTO initialPosition, boolean personInside, int feetThatPersonCanMoveInSecond) {
		this.personPosition = personPosition;
		this.initialPosition = initialPosition;
		this.personInside = personInside;
		this.feetThatPersonCanMoveInSecond = feetThatPersonCanMoveInSecond;
		this.secondsTakenToExit = 0;
	}
	public PositionDTO getPersonPosition() {
		return personPosition;
	}
	public void setPersonPosition(PositionDTO personPosition) {
		this.personPosition = personPosition;
	}
	public boolean isPersonInside() {
		return personInside;
	}
	public void setPersonInside(boolean personInside) {
		this.personInside = personInside;
	}
	public int getFeetThatPersonCanMoveInSecond() {
		return feetThatPersonCanMoveInSecond;
	}
	public void setFeetThatPersonCanMoveInSecond(int feetThatPersonCanMoveInSecond) {
		this.feetThatPersonCanMoveInSecond = feetThatPersonCanMoveInSecond;
	}
	public PositionDTO getInitialPosition() {
		return initialPosition;
	}
	public void setInitialPosition(PositionDTO initialPosition) {
		this.initialPosition = initialPosition;
	}
	public char getExittedFrom() {
		return exittedFrom;
	}
	public void setExittedFrom(char exittedFrom) {
		this.exittedFrom = exittedFrom;
	}
	public int getSecondsTakenToExit() {
		return secondsTakenToExit;
	}
	public void setSecondsTakenToExit(int secondsTakenToExit) {
		this.secondsTakenToExit = secondsTakenToExit;
	}
	
}
