package com.sai.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sai.constant.Constants;
import com.sai.dto.HallModelDTO;
import com.sai.dto.PersonDTO;
import com.sai.dto.PositionDTO;

public class Utility {

	private static Random random = new Random();
	
	public static HallModelDTO loadModelFromFile(String filePath) throws IOException {
		
		HallModelDTO hallModelDto = new HallModelDTO();
		
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		
		String line = "";
		int rowCounter = 0;
		char characterTracker = 'A' - 1;
		//	System.out.println("characterTracker : " + characterTracker);
		
		while((line = br.readLine()) != null) {
			
			line = line.trim();
			
			if(rowCounter == 0) {
				int rows = getRows(filePath);
				int cols = getCols(line);
				hallModelDto.setHallMatrix(new int[rows][cols]);
				hallModelDto.setRows(rows);
				hallModelDto.setCols(cols);
			}
			
			for(int i=0; i<line.length(); i+=2) {
				
				char presentCharacter = line.charAt(i);
				
				int presentCharacterInt = presentCharacter - '0';
				
				int column = i/2;
				
				hallModelDto.getHallMatrix()[rowCounter][column] = presentCharacterInt;
				
				if(Constants.wall == line.charAt(i) || Constants.emptySpace == line.charAt(i)) {
					
					continue;
					
				}
				else if(Constants.exit == line.charAt(i)) {
					
					if(hallModelDto.getExitPositions() == null) {
						
						hallModelDto.setExitPositions(new ArrayList<PositionDTO>());
						
					}
					
					hallModelDto.getExitPositions().add(new PositionDTO(rowCounter, column));
					
					
					if(hallModelDto.getColumnPositionToExitIdentifierMap() == null) {
						
						hallModelDto.setColumnPositionToExitIdentifierMap(new HashMap<Integer, Character>());
						
					}
					
					if(!(hallModelDto.getColumnPositionToExitIdentifierMap().containsKey(column - 1))) {
						
						characterTracker++;
						
					}
					
					
					
					hallModelDto.getColumnPositionToExitIdentifierMap().put(column, characterTracker);
					
					
				}
				else if(Constants.person == line.charAt(i)) {
					
					PositionDTO personPositionDto = new PositionDTO(rowCounter, column);
					PositionDTO personInitialPositionDto = new PositionDTO(rowCounter, column);
					int feetThatPersonCanMoveInSecond = random.nextInt(5) + 4;
					PersonDTO newPerson = new PersonDTO(personPositionDto, personInitialPositionDto, true, feetThatPersonCanMoveInSecond);
					
					if(hallModelDto.getPeopleInside() == null) {
						
						hallModelDto.setPeopleInside(new ArrayList<PersonDTO>());
						
					}
					
					hallModelDto.getPeopleInside().add(newPerson);
					hallModelDto.setTotalNumberOfPeople(hallModelDto.getTotalNumberOfPeople() + 1);
					hallModelDto.setTotalNumberOfPeopleStillInside(hallModelDto.getTotalNumberOfPeopleStillInside() + 1);
					
				}
				
			}
			
			rowCounter++;
			
		}
		
		br.close();
		
		return hallModelDto;
		
	}
	
	public static void printHall2DMatrix(HallModelDTO hallModelDto) throws IOException {
		
		String outFilePath = "C:\\Users\\sai goud\\Desktop\\ModelNew.txt";
		
		for(int i=0; i<hallModelDto.getRows(); i++) {
			
			for(int j=0;j<hallModelDto.getCols(); j++) {
				
				System.out.print(hallModelDto.getHallMatrix()[i][j]);
				
				if(j != (hallModelDto.getCols() - 1)) {
					System.out.print(",");
				}
			}
			
			System.out.println();
			
		}
		
	}
	
	public static void writeHall2DMatrix(String outFilePath, HallModelDTO hallModelDto) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath, true));
		
		for(int i=0; i<hallModelDto.getRows(); i++) {
			
			if(i==0) {
				
				bw.newLine();
				for(int j=0;j<hallModelDto.getCols(); j++) {
					bw.write("*");
				}
				bw.newLine();
				bw.newLine();
			}
			
			for(int j=0;j<hallModelDto.getCols(); j++) {
				
				bw.write("" + hallModelDto.getHallMatrix()[i][j]);
				
				if(j != (hallModelDto.getCols() - 1)) {
					bw.write(",");
				}
			}
			
			bw.newLine();
			
		}
		
		bw.close();
		
	}
	
public static void writeDebugMessage(String line) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\sai goud\\Desktop\\ModelOut.txt", true));
		
		bw.write(line);
		bw.newLine();
		
		
		bw.close();
		
	}
	
	public static void printTotalPersonsPositions(HallModelDTO hallModelDto) {
		
		for(int i=0; i<hallModelDto.getPeopleInside().size(); i++) {
			
			System.out.println("Person Row : " + (hallModelDto.getPeopleInside().get(i).getPersonPosition().getRow() + 1) + ", Person Column : " + (hallModelDto.getPeopleInside().get(i).getPersonPosition().getCol() + 1) + ", Column Position to Exit Identifier Map : " + hallModelDto.getColumnPositionToExitIdentifierMap());
			
		}
		
	}
	
	public static void printExitPositions(HallModelDTO hallModelDto) {
		
		for(int i=0; i<hallModelDto.getExitPositions().size(); i++) {
			
			System.out.println("Exit Row : " + (hallModelDto.getExitPositions().get(i).getRow() + 1) + ", Exit Column : " + (hallModelDto.getExitPositions().get(i).getCol() + 1));
			
		}
		
	}
	
	public static void printHallTotals(HallModelDTO hallModelDto) {
		
		System.out.println("Total Number of people that were in hall at start : " + hallModelDto.getTotalNumberOfPeople());
		System.out.println("Total Number of people still inside : " + hallModelDto.getTotalNumberOfPeopleStillInside());
		System.out.println("Exit Size : " + hallModelDto.getExitPositions().size());
		
	}
	
	public static void printExitIdentifierToExitCountMap(Map<Character, Integer> exitIdentifierToExitCountMap) {

		System.out.println("Exit Size : " + exitIdentifierToExitCountMap);
		
	}
	
	public static int getCols(String line) {
		
		if(line == null || line.trim().length() == 0) {
			return 0;
		}
		
		String[] splits = line.trim().split(",");
		
		return splits.length;
		
	}
	
	public static int getRows(String filePath) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		
		String line = "";
		int rowCounter = 0;
		
		while((line = br.readLine()) != null) {
			
			line = line.trim();
			
			if(line.length() > 0) {
				
				rowCounter++;
				
			}
			
		}
		
		br.close();
		
		return rowCounter;
		
	}
	
	public static boolean canThisPersonExit(HallModelDTO hallModelDto, int personIndex) {
		
		for(int i=0; i < hallModelDto.getExitPositions().size(); i++) {
			
			if(
						(	hallModelDto.getExitPositions().get(i).getRow() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getRow() + 1)
								&&
								hallModelDto.getExitPositions().get(i).getCol() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getCol())
								)
						||
						
						(	hallModelDto.getExitPositions().get(i).getRow() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getRow() - 1)
								&&
								hallModelDto.getExitPositions().get(i).getCol() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getCol())
								)
						||
						(	hallModelDto.getExitPositions().get(i).getRow() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getRow())
								&&
								hallModelDto.getExitPositions().get(i).getCol() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getCol() + 1)
								)
						||
						
						(	hallModelDto.getExitPositions().get(i).getRow() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getRow())
								&&
								hallModelDto.getExitPositions().get(i).getCol() == (hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getCol() - 1)
								)
						
					) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public static boolean isPersonInSameColumnAsExit(HallModelDTO hallModelDto, int personIndex) {
		
		for(int i=0; i < hallModelDto.getExitPositions().size(); i++) {
			if(hallModelDto.getExitPositions().get(i).getCol() == hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getCol()) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public static boolean isColumnAnExitColumn(HallModelDTO hallModelDto, int column) {
		
		for(int i=0; i < hallModelDto.getExitPositions().size(); i++) {
			
			if(hallModelDto.getExitPositions().get(i).getCol() == column) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	public static int getNearestExitColumnIncrementer(HallModelDTO hallModelDto, int personIndex) {
		
		int left = 0;
		int right = 0;
		
		int col = hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getCol();
		int tempCol;
		
		//go left
		for(tempCol = col;!isColumnAnExitColumn(hallModelDto, tempCol) && tempCol < hallModelDto.getCols();) {
			left++;
			tempCol -= 1;
		}
		
		//go right
		for(tempCol = col;!isColumnAnExitColumn(hallModelDto, tempCol) && tempCol > 0;) {
			right++;
			tempCol += 1;
		}
		
		return left > right ? 1 : -1;
	}
	
	public static int getNearestForwardMovableColumnIncrementer(HallModelDTO hallModelDto, int personIndex) throws IOException {
		
		int left = 0;
		int right = 0;
		
		int row = hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getRow();
		int col = hallModelDto.getPeopleInside().get(personIndex).getPersonPosition().getCol();
		int tempCol;
		
		//go left
		for(tempCol = col; tempCol > 0 && hallModelDto.getHallMatrix()[row + 1][tempCol] != 0   && !isColumnAnExitColumn(hallModelDto, tempCol);) {
			left++;
			tempCol -= 1;
		}
		
		if(tempCol == 0) {
			
			left = -1;
			
		}
		
		//go right
		for(tempCol = col;tempCol < hallModelDto.getCols() /*&& (row + 1) < hallModelDto.getRows() */ && hallModelDto.getHallMatrix()[row + 1][tempCol] != 0  && !isColumnAnExitColumn(hallModelDto, tempCol);) {
			right++;
			tempCol += 1;

			//Utility.writeDebugMessage("Inside Right For Loop");
			//Utility.writeDebugMessage("tempCol : " + tempCol);
		}
		
		if(tempCol == hallModelDto.getCols()) {
			
			right = -1;
			
		}
		
		if(left == -1) {
			return 1;
		}
		else if(right == -1) {
			return -1;
		}
		
		//Utility.writeDebugMessage("Left : " + left + " Right : " + right);
		
		
		return left > right ? 1 : (left < right ? -1 : (random.nextInt(2) == 1 ? -1 : 1));
	}


	public static void writeTOOutFile(String filePath, int second, Map<Character, Integer> exitIdentifierToExitCountMap) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath), true));
		
		int totalExitCount = 0;
		
		char characterTracker = 'A';
		
		String line = "";
		
		for(int i=0; i<3; i++) {
			
			line += ", " + exitIdentifierToExitCountMap.get(characterTracker);
			characterTracker++;
		}
		
		for(char key : exitIdentifierToExitCountMap.keySet()) {
			totalExitCount += exitIdentifierToExitCountMap.get(key);
		}
		
		bw.write(second + line + "," + totalExitCount);
		bw.newLine();
		
		bw.close();
		
	}
	
	public static void writeTOFinalFile(String filePath, int second, Map<Character, Integer> totalExitCountMap) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath), true));
		
		int totalExitCount = 0;
		
		char characterTracker = 'A';
		
		String line = "";
		
		for(int i=0; i<3; i++) {
			
			line += ", " + totalExitCountMap.get(characterTracker);
			characterTracker++;
		}
		
		for(char key : totalExitCountMap.keySet()) {
			totalExitCount += totalExitCountMap.get(key);
		}
		
		bw.write(second + line + "," + totalExitCount);
		bw.newLine();
		
		bw.close();
		
	}
	
	
	public static void createModel(HallModelDTO hallModelDto) {
		
		createWall(hallModelDto);
		int columnCounter = 1;
		//staring from 14 as index starts from 0
		for(int col=hallModelDto.getEmptySpaceToLeaveAtLeft()+1; col<hallModelDto.getCols() - hallModelDto.getEmptySpaceToLeaveAtRight();) {
			
			int rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + 1;
			
			
			for(int row=rowStart; row < rowStart + hallModelDto.getUpperDeckStallHeight(); row++) {
				
				//int stallGap = 0;
				
				for(int i=0; i<hallModelDto.getUpperDeckStallWidth() && (col + i)<hallModelDto.getCols(); i++) {
					
					/*if(i == (hallModelDto.getUpperDeckStallWidth() / 2)) {
						stallGap = 2;
					}
					
					hallModelDto.getHallMatrix()[row][col + i + stallGap] = 1;*/
					hallModelDto.getHallMatrix()[row][col + i] = 1;
					
				}
				
			}
			
			rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + hallModelDto.getUpperDeckStallHeight() + hallModelDto.getGapBetweeenAisles() + 1;
			
			int stallFeetToLeaveAtBottom = 0;
			
			if(columnCounter == 2 || columnCounter == 3 || columnCounter == 6 || columnCounter == 7 || columnCounter == 8 || columnCounter == 11 || columnCounter == 12) {
				
				stallFeetToLeaveAtBottom = 16;
				
			}
			
			for(int row=rowStart; row < rowStart + hallModelDto.getLowerDeckStallHeight() - stallFeetToLeaveAtBottom; row++) {
				
				//int stallGap = 0;
				
				for(int i=0; i<hallModelDto.getLowerDeckStallWidth() && (col + i)<hallModelDto.getCols(); i++) {
					
					/*if(i == (hallModelDto.getLowerDeckStallWidth() / 2)) {
						stallGap = 2;
					}
					
					hallModelDto.getHallMatrix()[row][col + i + stallGap] = 1;*/
					hallModelDto.getHallMatrix()[row][col + i] = 1;
				}
				
			}
			
			col += hallModelDto.getUpperDeckStallWidth() + hallModelDto.getGapBetweeenAisles();
			
			columnCounter++;
		}
		
	}
	
	public static void createWall(HallModelDTO hallModelDto) {
		
		int rows = hallModelDto.getRows();
		int cols = hallModelDto.getCols();
		
		for(int i=0; i<cols; i++) {
			hallModelDto.getHallMatrix()[0][i] = 1;
			hallModelDto.getHallMatrix()[rows - 1][i] = 1;
		}
		
		for(int i=1; i< (rows - 1); i++) {
			hallModelDto.getHallMatrix()[i][0] = 1;
			hallModelDto.getHallMatrix()[i][cols-1] = 1;
		}
		

	}
	
	public static void fillPeopleIntoHallMatrix(HallModelDTO hallModelDto, int peopleToFill) {
		
		int rows = hallModelDto.getRows();
		int cols = hallModelDto.getCols();
		
		int peopleFilledCounter = 0;
		while(peopleFilledCounter != peopleToFill) {
			
			int randomRow = random.nextInt(rows);
			int randomCol = random.nextInt(cols);
			
			if(hallModelDto.getHallMatrix()[randomRow][randomCol] == 0) {
				hallModelDto.getHallMatrix()[randomRow][randomCol] = 2;
				peopleFilledCounter++;
			}
			
		}
		

	}
	
	public static void fillStaffAtStallsIntoHallMatrix(HallModelDTO hallModelDto) {
		
		/*for(int col=hallModelDto.getEmptySpaceToLeaveAtLeft() + 1 + hallModelDto.getUpperDeckStallWidth()/2; col<hallModelDto.getCols();) {
			
			for(int k=0; k<2; k++) {
			
				int rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + 1;
				
				for(int i=0; i<hallModelDto.getUpperDeckStallHeight();) {
					
					for(int j=0; j<hallModelDto.getStaffCountAtEachStall(); j++) {
						
						int randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
						
						while(hallModelDto.getHallMatrix()[rowStart + randomRow][col + k] != 0) {
							randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
						}
						
						hallModelDto.getHallMatrix()[rowStart + randomRow][col + k] = 2;
						
						
					}
					
					
					i += hallModelDto.getStallUnitHeight();
					rowStart += hallModelDto.getStallUnitHeight();
					
				}
				
			
				rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + hallModelDto.getUpperDeckStallHeight() + hallModelDto.getGapBetweeenAisles();
			
				for(int i=0; i<hallModelDto.getLowerDeckStallHeight();) {
								
					for(int j=0; j<hallModelDto.getStaffCountAtEachStall(); j++) {
						
						int randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
						
						while(hallModelDto.getHallMatrix()[rowStart + randomRow][col + k] != 0) {
							randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
						}
						
						hallModelDto.getHallMatrix()[rowStart + randomRow][col + k] = 2;
						
						
					}
					
					i += hallModelDto.getStallUnitHeight();
					rowStart += hallModelDto.getStallUnitHeight();
					
				}
			
			}
			
			col += hallModelDto.getStallUnitWidth() * 2 + hallModelDto.getGapBetweeenAisles() + 1 + 1;
			
			System.out.println("Col : " + col);
		}
		*/
		
		boolean isEvenIncrement = false;
		int columnCounter = 1;
		for(int col=hallModelDto.getEmptySpaceToLeaveAtLeft(); col<hallModelDto.getCols() - hallModelDto.getEmptySpaceToLeaveAtRight();) {
			

			System.out.println("Col : " + col);
			
			int rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + 1;
			
			for(int i=0; i<hallModelDto.getUpperDeckStallHeight();) {
				
				for(int j=0; j<hallModelDto.getStaffCountAtEachStall(); j++) {
					
					int randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
					
					while(hallModelDto.getHallMatrix()[rowStart + randomRow][col] != 0) {
						randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
					}
					
					hallModelDto.getHallMatrix()[rowStart + randomRow][col] = 2;
					
					
				}
				
				
				i += hallModelDto.getStallUnitHeight();
				rowStart += hallModelDto.getStallUnitHeight();
				
			}
			
			int stallFeetToLeaveAtBottom = 0;
		
			rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + hallModelDto.getUpperDeckStallHeight() + hallModelDto.getGapBetweeenAisles() + 1;
		
			if(columnCounter == 2 || columnCounter == 3 || columnCounter == 6 || columnCounter == 7 || columnCounter == 8 || columnCounter == 11 || columnCounter == 12) {
				
				stallFeetToLeaveAtBottom = 16;
				
			}
			
			for(int i=0; i<hallModelDto.getLowerDeckStallHeight() - stallFeetToLeaveAtBottom;) {
							
				for(int j=0; j<hallModelDto.getStaffCountAtEachStall(); j++) {
					
					int randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
					
					while(hallModelDto.getHallMatrix()[rowStart + randomRow][col] != 0) {
						randomRow = random.nextInt(hallModelDto.getStallUnitHeight());
					}
					
					hallModelDto.getHallMatrix()[rowStart + randomRow][col] = 2;
					
					
				}
				
				
				i += hallModelDto.getStallUnitHeight();
				rowStart += hallModelDto.getStallUnitHeight();
				
			}
		
			if(isEvenIncrement) {
				col += hallModelDto.getGapBetweeenAisles() - 1;
				columnCounter++;
			}
			else {
				col += hallModelDto.getLowerDeckStallWidth() + 1;
			}
			
			isEvenIncrement = !isEvenIncrement;
			
		}
		
	}
	
	public static void fillPeopleAtStallsIntoHallMatrix(HallModelDTO hallModelDto) {
		
		boolean isEvenIncrement = false;
		int columnCounter = 1;
		for(int col=hallModelDto.getEmptySpaceToLeaveAtLeft() - 1; col<=hallModelDto.getCols() - hallModelDto.getEmptySpaceToLeaveAtRight();) {
			
			int rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + 1;
			
			for(int i=0; i<hallModelDto.getUpperDeckStallHeight();) {
				
				//for(int j=0; j<hallModelDto.getPeopleCountAtEachStall(); j++) {
					
					hallModelDto.getHallMatrix()[rowStart + i][col] = 2;
					
				//}
				
				
				//i += hallModelDto.getStallUnitHeight();
				i++;
				
			}
			
		
			rowStart = hallModelDto.getEmptySpaceToLeaveAtTop() + hallModelDto.getUpperDeckStallHeight() + hallModelDto.getGapBetweeenAisles() + 1;
		
			int stallFeetToLeaveAtBottom = 0;
			
			if(columnCounter == 2 || columnCounter == 3 || columnCounter == 6 || columnCounter == 7 || columnCounter == 8 || columnCounter == 11 || columnCounter == 12) {
				
				stallFeetToLeaveAtBottom = 16;
				
			}
			
			for(int i=0; i<hallModelDto.getLowerDeckStallHeight() - stallFeetToLeaveAtBottom;) {
							
				//for(int j=0; j<hallModelDto.getPeopleCountAtEachStall(); j++) {
						
					hallModelDto.getHallMatrix()[rowStart + i][col] = 2;
						
				//}
				
				//i += hallModelDto.getStallUnitHeight();
				i++;
				
			}
		
			if(isEvenIncrement) {
				col += hallModelDto.getGapBetweeenAisles() - 3;
				columnCounter++;
			}
			else {
				col += hallModelDto.getLowerDeckStallWidth() + 1 + 1 + 1;
			}
			
			isEvenIncrement = !isEvenIncrement;
			
			System.out.println("Col : " + col);
			
			
		}
		
	}

	
	public static void fillExitsIntoHallMatrix(HallModelDTO hallModelDto) {
		
		int rows = hallModelDto.getRows();
		
		int currentPosition = 42 + hallModelDto.getEmptySpaceToLeaveAtLeft();
		
		for(int i=0; i<hallModelDto.getTotalExitsCount(); i++) {
			
			for(int j=0; j<hallModelDto.getExitLength(); j++) {
				
				hallModelDto.getHallMatrix()[rows - 1][j + currentPosition] = 8;
				
			}
			
			if(i==0) {
				
				currentPosition += 104 + 12;
				
			}
			else if(i==1) {
				
				currentPosition += 108 + 12;
				
			}
			
		}
		

	}

	public static void writeTOPersonsInfoFile(String filePath, HallModelDTO hallModelDto) throws IOException {
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath), true));
		
		for (int i = hallModelDto.getPeopleInside().size() - 1; i >= 0 ; i--) {
			
			bw.write(hallModelDto.getPeopleInside().get(i).getInitialPosition().getRow() + ", " + hallModelDto.getPeopleInside().get(i).getInitialPosition().getCol() + ", " + hallModelDto.getPeopleInside().get(i).getExittedFrom() + ", " + hallModelDto.getPeopleInside().get(i).getSecondsTakenToExit() + ", ");
			bw.newLine();
			
		}

		bw.close();
		
	}
	
}