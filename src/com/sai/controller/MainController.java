package com.sai.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sai.dto.HallModelDTO;
import com.sai.util.Utility;

//In a given second a person can move from four feet to eight feet ()
public class MainController {
	//Main for running simulation
	public static void main11(String[] args) throws IOException, InterruptedException {

		for (int run = 0; run < 50; run++) {

			Map<Character, Integer> exitIdentifierToExitCountMap = new HashMap<Character, Integer>();
			Map<Character, Integer> totalsExitIdentifierToExitCountMap = new HashMap<Character, Integer>();
			Random random = new Random();
			HallModelDTO hallModelDto = Utility.loadModelFromFile(
					"C:\\Users\\sai goud\\Documents\\Softwares\\eclipse-jee-mars-2-win32-x86_64\\eclipse\\workspace\\VamsiCivilUakron\\src\\com\\sai\\resource\\model4.txt");

			Utility.printHall2DMatrix(hallModelDto);
			Utility.printTotalPersonsPositions(hallModelDto);
			Utility.printHallTotals(hallModelDto);
			// Utility.printExitPositions(hallModelDto);
			System.out.println();
			System.out.println();

			// Thread.sleep(5000);

			int secondsCounter = 0;

			for (; hallModelDto.getTotalNumberOfPeopleStillInside() > 0;) {

				secondsCounter++;

				// Change this later to loop from last
				for (int i = hallModelDto.getPeopleInside().size() - 1; i >= 0; i--) {

					if (hallModelDto.getPeopleInside().get(i).isPersonInside()) {

						int feetPersonCanMoveInSecond = random
								.nextInt(hallModelDto.getPeopleInside().get(i).getFeetThatPersonCanMoveInSecond());

						if (feetPersonCanMoveInSecond < 2) {
							feetPersonCanMoveInSecond += 1;
						}

						for (int j = 0; hallModelDto.getPeopleInside().get(i).isPersonInside()
								&& j < feetPersonCanMoveInSecond; j++) {

							if (Utility.canThisPersonExit(hallModelDto, i)) {
								int randomExit = random.nextInt(3);
								if (randomExit == 1 || randomExit == 0) {
									int row = hallModelDto.getPeopleInside().get(i).getPersonPosition().getRow();
									int col = hallModelDto.getPeopleInside().get(i).getPersonPosition().getCol();
									hallModelDto.getHallMatrix()[row][col] = 0;
									hallModelDto.getPeopleInside().get(i).setPersonInside(false);
									hallModelDto.setTotalNumberOfPeopleStillInside(
											hallModelDto.getTotalNumberOfPeopleStillInside() - 1);

									char exitIdentifier = hallModelDto.getColumnPositionToExitIdentifierMap().get(col);
									if (!(exitIdentifierToExitCountMap.containsKey(exitIdentifier))) {

										exitIdentifierToExitCountMap.put(exitIdentifier, 0);

									}

									if (!(totalsExitIdentifierToExitCountMap.containsKey(exitIdentifier))) {

										totalsExitIdentifierToExitCountMap.put(exitIdentifier, 0);

									}

									exitIdentifierToExitCountMap.put(exitIdentifier,
											exitIdentifierToExitCountMap.get(exitIdentifier) + 1);

									totalsExitIdentifierToExitCountMap.put(exitIdentifier,
											totalsExitIdentifierToExitCountMap.get(exitIdentifier) + 1);

									hallModelDto.getPeopleInside().get(i).setExittedFrom(exitIdentifier);
									hallModelDto.getPeopleInside().get(i).setSecondsTakenToExit(secondsCounter);
								}
							} else {
								// replace this logic later to consider exit
								// anywhere

								// if person in exit column and not obstacle
								// ahead
								// in same column then keep moving in same
								// column to
								// approach exit
								if (Utility.isPersonInSameColumnAsExit(hallModelDto, i)) {

									if (hallModelDto.getHallMatrix()[hallModelDto.getPeopleInside().get(i)
											.getPersonPosition().getRow() + 1][hallModelDto.getPeopleInside().get(i)
													.getPersonPosition().getCol()] == 0) {
										int row = hallModelDto.getPeopleInside().get(i).getPersonPosition().getRow();
										int col = hallModelDto.getPeopleInside().get(i).getPersonPosition().getCol();

										hallModelDto.getHallMatrix()[row + 1][col] = 2;
										hallModelDto.getHallMatrix()[row][col] = 0;

										hallModelDto.getPeopleInside().get(i).getPersonPosition().setRow(row + 1);

									} else {
										int randomCol = random.nextInt(2);
										if (randomCol == 0) {

											if (hallModelDto.getHallMatrix()[hallModelDto.getPeopleInside().get(i)
													.getPersonPosition().getRow()][hallModelDto.getPeopleInside().get(i)
															.getPersonPosition().getCol() + 1] == 0) {

												int row = hallModelDto.getPeopleInside().get(i).getPersonPosition()
														.getRow();
												int col = hallModelDto.getPeopleInside().get(i).getPersonPosition()
														.getCol();

												hallModelDto.getHallMatrix()[row][col+1] = 2;
												hallModelDto.getHallMatrix()[row][col] = 0;

												hallModelDto.getPeopleInside().get(i).getPersonPosition()
														.setCol(col + 1);

											}

										} else if (randomCol == 1) {

											if (hallModelDto.getHallMatrix()[hallModelDto.getPeopleInside().get(i)
													.getPersonPosition().getRow()][hallModelDto.getPeopleInside().get(i)
															.getPersonPosition().getCol() - 1] == 0) {

												int row = hallModelDto.getPeopleInside().get(i).getPersonPosition()
														.getRow();
												int col = hallModelDto.getPeopleInside().get(i).getPersonPosition()
														.getCol();

												hallModelDto.getHallMatrix()[row][col-1] = 2;
												hallModelDto.getHallMatrix()[row][col] = 0;

												hallModelDto.getPeopleInside().get(i).getPersonPosition()
														.setCol(col - 1);
												
											}

										}

									}

								} else {

									int colIncrementer = Utility.getNearestForwardMovableColumnIncrementer(hallModelDto,
											i);

									if (hallModelDto.getHallMatrix()[hallModelDto.getPeopleInside().get(i)
											.getPersonPosition().getRow()][hallModelDto.getPeopleInside().get(i)
													.getPersonPosition().getCol() + colIncrementer] == 0) {

										int row = hallModelDto.getPeopleInside().get(i).getPersonPosition().getRow();
										int col = hallModelDto.getPeopleInside().get(i).getPersonPosition().getCol();

										hallModelDto.getHallMatrix()[row][col + colIncrementer] = 2;
										hallModelDto.getHallMatrix()[row][col] = 0;

										hallModelDto.getPeopleInside().get(i).getPersonPosition()
												.setCol(col + colIncrementer);

									}
									/*
									 * else if (hallModelDto.getHallMatrix()[
									 * hallModelDto.getPeopleInside().get(i)
									 * .getPersonPosition().getRow()][
									 * hallModelDto.getPeopleInside().get(i)
									 * .getPersonPosition().getCol() +
									 * (colIncrementer)] == 2) {
									 * 
									 * }
									 */
									else if (hallModelDto.getHallMatrix()[hallModelDto.getPeopleInside().get(i)
											.getPersonPosition().getRow()][hallModelDto.getPeopleInside().get(i)
													.getPersonPosition().getCol() + (-1 * colIncrementer)] == 0) {

										int row = hallModelDto.getPeopleInside().get(i).getPersonPosition().getRow();
										int col = hallModelDto.getPeopleInside().get(i).getPersonPosition().getCol();

										colIncrementer = -1 * colIncrementer;

										hallModelDto.getHallMatrix()[row][col + colIncrementer] = 2;
										hallModelDto.getHallMatrix()[row][col] = 0;

										hallModelDto.getPeopleInside().get(i).getPersonPosition()
												.setCol(col + colIncrementer);
									}

									// Utility.writeHall2DMatrix("C:\\Users\\sai
									// goud\\Desktop\\ModelOut.txt",
									// hallModelDto);

								}

							}
						}

					}

				}

				if (secondsCounter % 5 == 0) {
					// Utility.printHall2DMatrix(hallModelDto);
					// System.out.println();
					// System.out.println();
					System.out.println("Current Second : " + secondsCounter);
					System.out.println("Total People Inside : " + hallModelDto.getTotalNumberOfPeopleStillInside());
					System.out.println("Total People Evacuated : " + (hallModelDto.getTotalNumberOfPeople()
							- hallModelDto.getTotalNumberOfPeopleStillInside()));
					System.out.println("People left inside : " + hallModelDto.getTotalNumberOfPeopleStillInside());
					System.out.println("Total number of people Evacuated : " + (hallModelDto.getTotalNumberOfPeople()
							- hallModelDto.getTotalNumberOfPeopleStillInside()));
					// System.out.println("hallModelDto.getTotalNumberOfPeople()
					// : " + hallModelDto.getTotalNumberOfPeople());
					// Utility.printExitIdentifierToExitCountMap(exitIdentifierToExitCountMap);
					// System.out.println();
					// System.out.println();
					// Thread.sleep(1000);

					Utility.writeTOOutFile("C:\\Users\\sai goud\\Desktop\\Out.txt", secondsCounter,
							exitIdentifierToExitCountMap);

					exitIdentifierToExitCountMap = new HashMap<Character, Integer>();

					Utility.writeHall2DMatrix("C:\\Users\\sai goud\\Desktop\\ModelOut.txt", hallModelDto);

				}

			}

			// if(secondsCounter%5 != 0) {
			Utility.printHall2DMatrix(hallModelDto);
			System.out.println();
			System.out.println();
			System.out.println("Current Second : " + secondsCounter);
			Utility.printExitIdentifierToExitCountMap(exitIdentifierToExitCountMap);
			Utility.printExitIdentifierToExitCountMap(totalsExitIdentifierToExitCountMap);
			System.out.println("People left inside : " + hallModelDto.getTotalNumberOfPeopleStillInside());
			System.out.println("Total number of people Evacuated : "
					+ (hallModelDto.getTotalNumberOfPeople() - hallModelDto.getTotalNumberOfPeopleStillInside()));
			System.out.println();
			System.out.println();

			Utility.writeTOOutFile("C:\\Users\\sai goud\\Desktop\\Out.txt", secondsCounter,
					exitIdentifierToExitCountMap);
			// }

			Utility.writeTOFinalFile("C:\\Users\\sai goud\\Desktop\\FinalOut.txt", secondsCounter,
					totalsExitIdentifierToExitCountMap);

			Utility.writeTOPersonsInfoFile("C:\\Users\\sai goud\\Desktop\\PersonsInfo.txt", hallModelDto);

		}

	}

	//Main for creating New Model
	public static void main3(String[] args) throws IOException {

		HallModelDTO hallModelDto = new HallModelDTO();

		hallModelDto.setTotalStallsCount(13);
		int rows = 208 + 2; // 3 extra rows because of wall
		int cols = 360;

		hallModelDto.setRows(rows);
		hallModelDto.setCols(cols);
		hallModelDto.setHallMatrix(new int[rows][cols]);

		hallModelDto.setEmptySpaceToLeaveAtTop(15 - 8);
		hallModelDto.setEmptySpaceToLeaveAtBottom(15);
		hallModelDto.setEmptySpaceToLeaveAtLeft(15);
		hallModelDto.setEmptySpaceToLeaveAtRight(15);

		hallModelDto.setUpperDeckStallHeight(88);
		hallModelDto.setUpperDeckStallWidth(16);

		hallModelDto.setLowerDeckStallHeight(88);
		hallModelDto.setLowerDeckStallWidth(16);

		hallModelDto.setGapBetweeenAisles(10);

		hallModelDto.setTotalExitsCount(3);
		hallModelDto.setExitLength(12);

		hallModelDto.setStaffCountAtEachStall(2);
		hallModelDto.setStallUnitHeight(8);
		hallModelDto.setStallUnitWidth(8);

		hallModelDto.setPeopleCountAtEachStall(8);

		Utility.createWall(hallModelDto);
		Utility.createModel(hallModelDto);
		Utility.printHall2DMatrix(hallModelDto);
		Utility.fillStaffAtStallsIntoHallMatrix(hallModelDto);
		Utility.fillPeopleAtStallsIntoHallMatrix(hallModelDto);
		Utility.fillPeopleIntoHallMatrix(hallModelDto, 540);
		Utility.printHall2DMatrix(hallModelDto);
		Utility.fillExitsIntoHallMatrix(hallModelDto);
		Utility.writeHall2DMatrix("C:\\Users\\sai goud\\Desktop\\ModelNew.txt", hallModelDto);

	}

	//Main for creating 3000 Model and 4000 Model
	public static void main(String[] args) throws IOException {

		HallModelDTO hallModelDto = new HallModelDTO();

		hallModelDto.setTotalStallsCount(13);
		int rows = 208 + 2; // 3 extra rows because of wall
		int cols = 360;

		hallModelDto.setRows(rows);
		hallModelDto.setCols(cols);
		hallModelDto.setHallMatrix(new int[rows][cols]);

		hallModelDto.setEmptySpaceToLeaveAtTop(15 - 8);
		hallModelDto.setEmptySpaceToLeaveAtBottom(15);
		hallModelDto.setEmptySpaceToLeaveAtLeft(15);
		hallModelDto.setEmptySpaceToLeaveAtRight(15);

		hallModelDto.setUpperDeckStallHeight(88);
		hallModelDto.setUpperDeckStallWidth(16);

		hallModelDto.setLowerDeckStallHeight(88);
		hallModelDto.setLowerDeckStallWidth(16);

		hallModelDto.setGapBetweeenAisles(10);

		hallModelDto.setTotalExitsCount(3);
		hallModelDto.setExitLength(12);

		hallModelDto.setStaffCountAtEachStall(8);
		hallModelDto.setStallUnitHeight(8);
		hallModelDto.setStallUnitWidth(8);

		hallModelDto.setPeopleCountAtEachStall(0);

		Utility.createWall(hallModelDto);
		Utility.createModel(hallModelDto);
		Utility.printHall2DMatrix(hallModelDto);
		Utility.fillStaffAtStallsIntoHallMatrix(hallModelDto);
		//Utility.fillPeopleAtStallsIntoHallMatrix(hallModelDto);
		Utility.fillPeopleIntoHallMatrix(hallModelDto, 132);
		Utility.printHall2DMatrix(hallModelDto);
		Utility.fillExitsIntoHallMatrix(hallModelDto);
		Utility.writeHall2DMatrix("C:\\Users\\sai goud\\Desktop\\ModelNew.txt", hallModelDto);

	}

	
	public static void main2(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\sai goud\\Desktop\\ModelNew.txt")));
		br.readLine();
		br.readLine();
		br.readLine();
		String line = br.readLine();
		String[] splits = line.split(",");

		System.out.println("Total Characters : " + splits.length);

		String previousString = "";

		for (int i = 0; i < splits.length; i++) {

			if (previousString.equals("")) {

				System.out.println("EQ");

			}

		}

		br.close();

	}

}
