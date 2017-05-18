package com.sai.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallModelDTO {

	//Note: all units in this class are in feet
	
	//Hall representation in 2-Dimension 
	private int[][] hallMatrix;
	private int rows;
	private int cols;

	//Maximum number of People that were inside hall at the start
	private int totalNumberOfPeople;
	
	//Maximum number of People that are inside hall at given point 
	private int totalNumberOfPeopleStillInside;
	
	//Representation of each person inside hall
	private List<PersonDTO> peopleInside;
	
	private List<PositionDTO> exitPositions;
	
	private Map<Integer, Character> columnPositionToExitIdentifierMap;
	
	//Total Stalls
	private int totalStallsCount;
	
	//Aisle Spaces Count
	private int totalAisleSpacesCount;
	
	//Upper Deck Stalls
	private int upperDeckStallsCount;
		
	//Upper Deck Aisle Spaces Count
	private int upperDeckAisleSpacesCount;
	
	//Lower Deck Stalls
	private int lowerDeckStallsCount;
			
	//Lower Deck Aisle Spaces Count
	private int lowerDeckAisleSpacesCount;
	
	//Gap between aisles in feet
	private int gapBetweeenAisles;
	
	//Space to be left at top
	private int emptySpaceToLeaveAtTop;
	
	//Space to be left at bottom
	private int emptySpaceToLeaveAtBottom;
	
	//Space to be left at Left
	private int emptySpaceToLeaveAtLeft;
	
	//Space to be left at Right
	private int emptySpaceToLeaveAtRight;
	
	//Upper Deck Stall height in Feet
	private int upperDeckStallHeight;
	
	//Upper Deck Stall Width in Feet
	private int upperDeckStallWidth;
	
	//Lower Deck Stall height in Feet
	private int lowerDeckStallHeight;
		
	//Lower Deck Stall Width in Feet
	private int lowerDeckStallWidth;
	
	//Total number of exits
	private int totalExitsCount;
	
	//Exit Length in feet
	private int exitLength;
	
	//Exit Length in feet
	private int distanceBetweenExits;
	
	//Staff count at each Stall
	private int staffCountAtEachStall;
	
	//Stall Unit width
	private int stallUnitWidth;
	
	//Stall Unit height
	private int stallUnitHeight;
	
	//People at each stall
	private int peopleCountAtEachStall;
		
	
	public int[][] getHallMatrix() {
		return hallMatrix;
	}

	public void setHallMatrix(int[][] hallMatrix) {
		this.hallMatrix = hallMatrix;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getTotalNumberOfPeople() {
		return totalNumberOfPeople;
	}

	public void setTotalNumberOfPeople(int totalNumberOfPeople) {
		this.totalNumberOfPeople = totalNumberOfPeople;
	}

	public int getTotalNumberOfPeopleStillInside() {
		return totalNumberOfPeopleStillInside;
	}

	public void setTotalNumberOfPeopleStillInside(int totalNumberOfPeopleStillInside) {
		this.totalNumberOfPeopleStillInside = totalNumberOfPeopleStillInside;
	}

	public List<PersonDTO> getPeopleInside() {
		return peopleInside;
	}

	public void setPeopleInside(List<PersonDTO> peopleInside) {
		this.peopleInside = peopleInside;
	}

	public List<PositionDTO> getExitPositions() {
		return exitPositions;
	}

	public void setExitPositions(List<PositionDTO> exitPositions) {
		this.exitPositions = exitPositions;
	}

	public Map<Integer, Character> getColumnPositionToExitIdentifierMap() {
		return columnPositionToExitIdentifierMap;
	}

	public void setColumnPositionToExitIdentifierMap(Map<Integer, Character> columnPositionToExitIdentifierMap) {
		this.columnPositionToExitIdentifierMap = columnPositionToExitIdentifierMap;
	}

	public int getTotalStallsCount() {
		return totalStallsCount;
	}

	public void setTotalStallsCount(int totalStallsCount) {
		this.totalStallsCount = totalStallsCount;
	}

	public int getTotalAisleSpacesCount() {
		return totalAisleSpacesCount;
	}

	public void setTotalAisleSpacesCount(int totalAisleSpacesCount) {
		this.totalAisleSpacesCount = totalAisleSpacesCount;
	}

	public int getUpperDeckStallsCount() {
		return upperDeckStallsCount;
	}

	public void setUpperDeckStallsCount(int upperDeckStallsCount) {
		this.upperDeckStallsCount = upperDeckStallsCount;
	}

	public int getUpperDeckAisleSpacesCount() {
		return upperDeckAisleSpacesCount;
	}

	public void setUpperDeckAisleSpacesCount(int upperDeckAisleSpacesCount) {
		this.upperDeckAisleSpacesCount = upperDeckAisleSpacesCount;
	}

	public int getLowerDeckStallsCount() {
		return lowerDeckStallsCount;
	}

	public void setLowerDeckStallsCount(int lowerDeckStallsCount) {
		this.lowerDeckStallsCount = lowerDeckStallsCount;
	}

	public int getLowerDeckAisleSpacesCount() {
		return lowerDeckAisleSpacesCount;
	}

	public void setLowerDeckAisleSpacesCount(int lowerDeckAisleSpacesCount) {
		this.lowerDeckAisleSpacesCount = lowerDeckAisleSpacesCount;
	}

	public int getGapBetweeenAisles() {
		return gapBetweeenAisles;
	}

	public void setGapBetweeenAisles(int gapBetweeenAisles) {
		this.gapBetweeenAisles = gapBetweeenAisles;
	}

	public int getEmptySpaceToLeaveAtTop() {
		return emptySpaceToLeaveAtTop;
	}

	public void setEmptySpaceToLeaveAtTop(int emptySpaceToLeaveAtTop) {
		this.emptySpaceToLeaveAtTop = emptySpaceToLeaveAtTop;
	}

	public int getEmptySpaceToLeaveAtBottom() {
		return emptySpaceToLeaveAtBottom;
	}

	public void setEmptySpaceToLeaveAtBottom(int emptySpaceToLeaveAtBottom) {
		this.emptySpaceToLeaveAtBottom = emptySpaceToLeaveAtBottom;
	}

	public int getEmptySpaceToLeaveAtLeft() {
		return emptySpaceToLeaveAtLeft;
	}

	public void setEmptySpaceToLeaveAtLeft(int emptySpaceToLeaveAtLeft) {
		this.emptySpaceToLeaveAtLeft = emptySpaceToLeaveAtLeft;
	}

	public int getEmptySpaceToLeaveAtRight() {
		return emptySpaceToLeaveAtRight;
	}

	public void setEmptySpaceToLeaveAtRight(int emptySpaceToLeaveAtRight) {
		this.emptySpaceToLeaveAtRight = emptySpaceToLeaveAtRight;
	}

	public int getUpperDeckStallHeight() {
		return upperDeckStallHeight;
	}

	public void setUpperDeckStallHeight(int upperDeckStallHeight) {
		this.upperDeckStallHeight = upperDeckStallHeight;
	}

	public int getUpperDeckStallWidth() {
		return upperDeckStallWidth;
	}

	public void setUpperDeckStallWidth(int upperDeckStallWidth) {
		this.upperDeckStallWidth = upperDeckStallWidth;
	}

	public int getLowerDeckStallHeight() {
		return lowerDeckStallHeight;
	}

	public void setLowerDeckStallHeight(int lowerDeckStallHeight) {
		this.lowerDeckStallHeight = lowerDeckStallHeight;
	}

	public int getLowerDeckStallWidth() {
		return lowerDeckStallWidth;
	}

	public void setLowerDeckStallWidth(int lowerDeckStallWidth) {
		this.lowerDeckStallWidth = lowerDeckStallWidth;
	}

	public int getTotalExitsCount() {
		return totalExitsCount;
	}

	public void setTotalExitsCount(int totalExitsCount) {
		this.totalExitsCount = totalExitsCount;
	}

	public int getExitLength() {
		return exitLength;
	}

	public void setExitLength(int exitLength) {
		this.exitLength = exitLength;
	}

	public int getDistanceBetweenExits() {
		return distanceBetweenExits;
	}

	public void setDistanceBetweenExits(int distanceBetweenExits) {
		this.distanceBetweenExits = distanceBetweenExits;
	}

	public int getStaffCountAtEachStall() {
		return staffCountAtEachStall;
	}

	public void setStaffCountAtEachStall(int staffCountAtEachStall) {
		this.staffCountAtEachStall = staffCountAtEachStall;
	}

	public int getStallUnitWidth() {
		return stallUnitWidth;
	}

	public void setStallUnitWidth(int stallUnitWidth) {
		this.stallUnitWidth = stallUnitWidth;
	}

	public int getStallUnitHeight() {
		return stallUnitHeight;
	}

	public void setStallUnitHeight(int stallUnitHeight) {
		this.stallUnitHeight = stallUnitHeight;
	}

	public int getPeopleCountAtEachStall() {
		return peopleCountAtEachStall;
	}

	public void setPeopleCountAtEachStall(int peopleCountAtEachStall) {
		this.peopleCountAtEachStall = peopleCountAtEachStall;
	}


}
