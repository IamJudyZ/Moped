package edu.nyu.cs.ytz205.Assignment_5;

import java.util.Scanner;
import java.util.Random;

/**
 * This program is my submission for Assignment #5
 * @author Judy Zhang (ytz205)
 * @version 2.0 with extra credit
 *
 */

public class MopedEC {
	
	/**
	 * constructor
	 * when a moped is created, its properties are set to 
	 * 10th street, 5th ave
	 * direction: south
	 * full tank
	 */
	public MopedEC(int num) {
		if (num == 1) {
			this.setPosition(10,5);
			this.setDirection("South");
			this.setTank(full);
			int st = this.getStreet();
			int av = this.getAvenue();
			System.out.print("Thank you for jumping on the moped. ");
			System.out.print("We're currently parked outside Dr. Rossinsky DDS's office at "); 
			System.out.print(st + "th St. and " + av + " Ave, facing South. ");
			System.out.println("May I say your teeth look very clean.");
			System.out.println("What would you like to do? At any time, say 'help' for assistance.");
		}
		if (num == 2) {
			this.setPosition(6, 5);
			this.setDirection("North");
			this.setTank(full);
		}
	}

	
	
	/**
	 * properties of a moped 
	 */
	private String direction;
	private String command;
	private int commandNum = 0;
	private int tank;
	private int street;
	private int avenue;
	//create flag to start or end the drive method
	boolean flag;
	boolean turn;
		
	private static int full = 20;
	
	private static String[] acceptableCommands = {
			"go left",
			"go right",
			"straight on",
			"back up",
			"how we doin'?",
			"fill 'er up",
			"park",
			"go to Petite Abeille",
			"help"
	};	
	
	
	/**
	 * a moped starts driving
	 * two drunk mopeds also start going from the same position
	 * if they crash into the user, the program exits
	 */
	public void drive(MopedEC drunkOne, MopedEC drunkTwo) {
		Scanner scn = new Scanner(System.in);
		this.setFlag(true);
		Random rand = new Random();
		
		while (flag) {
			String userCommand = scn.nextLine();
			this.setCommand(userCommand);
			String command = this.getCommand();
			int cNum = this.getCommandNum();
			String direction = this.getDirection();
			
			if (cNum < 4) {
				this.setTurn(true);
				if (command.equals("go left")) {
					this.goLeft(direction);
				}
				else if (command.equals("go right")) {
					this.goRight(direction);
				}
				else if (command.equals("straight on")) {
					this.straightOn(direction);
				}
				else if (command.equals("back up")) {
					this.backUp(direction);
				}
				
				
				
				
				//create a number between 1-4 to get a random direction for the drunk mopeds
				int randomDirection = rand.nextInt(4) + 1;
				
				String drunkDir = drunkOne.getDirection();
				
				drunkOne.setTurn(true);
				if (randomDirection == 1) {
					drunkOne.goRight(drunkDir);
				}
				
				else if (randomDirection == 2) {
					drunkOne.goLeft(drunkDir);
				}
				else if (randomDirection == 3) {
					drunkOne.straightOn(drunkDir);
				}
				else if (randomDirection == 4) {
					drunkOne.backUp(drunkDir);
				}
				
				//if the tank starts to run out, fill it up automatically
				int tankOne = drunkOne.getTank();
				if (tankOne == 1) {
					drunkOne.fillUp();
				}
				
								
				String drunkDirTwo = drunkTwo.getDirection();
				
				drunkTwo.setTurn(true);
				if (randomDirection == 4) {
					drunkTwo.goRight(drunkDirTwo);
				}
				else if (randomDirection == 3) {
					drunkTwo.goLeft(drunkDirTwo);
				}
				else if (randomDirection == 2) {
					drunkTwo.straightOn(drunkDirTwo);
				}
				else if (randomDirection == 1) {
					drunkTwo.backUp(drunkDirTwo);
				}
				
				int tankTwo = drunkTwo.getTank();
				if (tankTwo == 1) {
					drunkTwo.fillUp();
				}

				
				
				//announce the position of all three mopeds
				this.printPosition("Your moped");
				drunkOne.printPosition("Drunk driver (1)");
				drunkTwo.printPosition("Drunk driver (2)");

				
				//if either one of the mopeds crushes into the user, the program quits by setting the flag to false
				if (drunkOne.getStreet() == this.getStreet() && drunkOne.getAvenue() == this.getAvenue()) {
					System.out.println("Drunk driver 1 crashed into you. Your moped is now unusable.");
					this.setFlag(false);
				}
				else if (drunkTwo.getStreet() == this.getStreet() && drunkTwo.getAvenue() == this.getAvenue()) {
					System.out.println("Drunk driver 2 crashed into you. Your moped is now unusable.");
					this.setFlag(false);
				}
				
			}
			else if (command.equals("park")) {
				this.park();
			}
			else if (command.equals("how we doin'?")) {
				this.howWeDoin();
			}
			else if (command.equals("fill 'er up")) {
				this.fillUp();
			}
			
			else if (command.equals("go to Petite Abeille")) {
				this.petiteAbeille();
			}
			else if (command.equals("help")) {
				this.help();
			}
			else {
				System.out.println("Incorrect instructions");
			}
			
		}

	}

	/**
	 * print the current position of the moped.
	 * print advertisement if needed
	 * quit program if tank is empty
	 */
	public void printPosition(String name) {
		int street = this.getStreet();
		int ave = this.getAvenue();
		String st = this.getEnding(street);
		String av = this.getEnding(ave);

		System.out.print(name + " is now at " + street + st + " St. and " + ave + av + " Ave, facing " + getDirection() + ". ");
		this.checkPosition();
		this.checkTank();
	}
	
	/**
	 * check level of tank
	 * if moped runs out of gas, quit program
	 */
	public void checkTank() {
		int tank = this.getTank();
		if (tank == 0) {
			System.out.println("The Moped has run out of gas and no longer drives.");
			this.setFlag(false);
		}
	}
	
	/**
	 * advertisements
	 */
	public void checkPosition() {
		if (this.getStreet() == 79 && this.getAvenue() == 8) {
			System.out.println("Did you know The American Museum of Natural History is celebrating its 150th birthday this year?");
		}
		else if (this.getStreet() == 74 && this.getAvenue() == 1) {
			System.out.println("Did you know that for more than 70 years, the Sloan Kettering Institute has set the pace for cancer science?");
		}
		else if (this.getStreet() == 12 && this.getAvenue() == 4) {
			System.out.println("Did you know The Strand has 18 Miles of new, used and rare books, and has been in business since 1927?");
		}
		else if (this.getStreet() == 3 && this.getAvenue() == 6) {
			System.out.println("Did you know the first Fayda Coffe Tea Cookies Cake store opened in 1991 by Han Chou?");
		}
		else if (this.getStreet() == 17 && this.getAvenue() == 6) {
			System.out.println("We have reached the Petite Abeille.  Enjoy your moules-frites.");
		}
		else {
			System.out.println("");
		}
	}
	
	/**
	 * turn left
	 * @param direction: current direction of moped
	 */
	public void goLeft(String direction) {
		int oldStr = this.getStreet();
		int oldAv = this.getAvenue();
		if (direction.equals("South")) {
			this.setPosition(getStreet(), getAvenue()-1);
			if (this.getTurn()) {
				this.setDirection("East");
			}
		}
		else if (direction.equals("North")) {
			this.setPosition(getStreet(), getAvenue()+1);
			if (this.getTurn()) {
				this.setDirection("West");
			}
		}
		else if (direction.equals("West")) {
			this.setPosition(getStreet()-1, getAvenue());
			if (this.getTurn()) {
				this.setDirection("South");
			}
		}
		else if (direction.equals("East")) {
			this.setPosition(getStreet()+1, getAvenue());
			if (this.getTurn()) {
				this.setDirection("North");
			}
		}
		if (oldStr != this.getStreet() || oldAv != this.getAvenue()) {
			this.setTank(getTank()-1);
		}
	}
	
	/**
	 * turn right
	 * @param direction: current direction of moped
	 */
	public void goRight(String direction) {
		int oldStr = this.getStreet();
		int oldAv = this.getAvenue();
		if (direction.equals("South")) {
			this.setPosition(getStreet(), getAvenue()+1);
			if (this.getTurn()) {
				this.setDirection("West");
			}
		}
		else if (direction.equals("North")) {
			this.setPosition(getStreet(), getAvenue()-1);
			if (this.getTurn()) {
				this.setDirection("East");
			}
		}
		else if (direction.equals("West")) {
			this.setPosition(getStreet()+1, getAvenue());
			if (this.getTurn()) {
				this.setDirection("North");
			}
		}
		else if (direction.equals("East")) {
			this.setPosition(getStreet()-1, getAvenue());
			if (this.getTurn()) {
				this.setDirection("South");
			}
		}
		if (oldStr != this.getStreet() || oldAv != this.getAvenue()) {
			this.setTank(getTank()-1);
		}
	}
	
	/**
	 * go straight
	 * @param direction: current direction of moped
	 */
	public void straightOn(String direction) {
		int oldStr = this.getStreet();
		int oldAv = this.getAvenue();

		if (direction.equals("South")) {
			this.setPosition(getStreet()-1, getAvenue());
		}
		else if (direction.equals("North")) {
			this.setPosition(getStreet()+1, getAvenue());
		}
		else if (direction.equals("West")) {
			this.setPosition(getStreet(), getAvenue()+1);
		}
		else if (direction.equals("East")) {
			this.setPosition(getStreet(), getAvenue()-1);
		}
		if (oldStr != this.getStreet() || oldAv != this.getAvenue()) {
			this.setTank(getTank()-1);
		}
	}

	/**
	 * go backwards
	 * @param direction: current direction of moped
	 */
	public void backUp(String direction) {
		int oldStr = this.getStreet();
		int oldAv = this.getAvenue();

		if (direction.equals("South")) {
			this.setPosition(getStreet()+1, getAvenue());
		}
		else if (direction.equals("North")) {
			this.setPosition(getStreet()-1, getAvenue());
		}
		else if (direction.equals("West")) {
			this.setPosition(getStreet(), getAvenue()-1);
		}
		else if (direction.equals("East")) {
			this.setPosition(getStreet(), getAvenue()+1);
		}
		if (oldStr != this.getStreet() || oldAv != this.getAvenue()) {
			this.setTank(getTank()-1);
		}
	}
	
	/**
	 * current level of gasoline in the tank, as a percentage 
	 */
	public void howWeDoin() {
		double num = this.getTank()/20.0;
		int numb = (int) (num*100);
		System.out.println("The gas tank is currently " + numb + "% full.");
	}
	
	/**
	 * fill up tank
	 */
	public void fillUp() {
		this.setTank(full);
		//System.out.println("The tank is refilled.");
	}
	
	/**
	 * park moped on sidewalk
	 */
	public void park() {
		System.out.println("The moped has been parked on the sidewalk.");
		this.setFlag(false);
	}

	/**
	 * go 'home'
	 * the moped auto-drives itself to 17th str and 6th av, and displays its current position after every move
	 */
	public void petiteAbeille() {
		//17th and 6th ave
		String direction = this.getDirection();
		int str = this.getStreet();
		int ave = this.getAvenue();
				
		int s = 17 - str;
		int a = 6 - ave;
		
		//using the flag, make sure that the moped has enough gas to move
		if (direction.equals("North") && flag) {
			if (s < 0) {
				int distance = Math.abs(s);
				for (int t = 0; t < distance; t++) {
					if (flag) {
						this.backUp(direction);
						this.printPosition("Your moped");
					}
				}
			}
			else if (s > 0) {
				for (int t = 0; t < s; t++) {
					if (flag) {
						this.straightOn(direction);
						this.printPosition("Your moped");
					}
				}
			}
			
			
			if (a < 0 && flag) {
				this.goRight(direction);
				this.printPosition("Your moped");
				int diff = Math.abs(a);
				String newDirection = this.getDirection();
				for (int z = 0; z < diff-1; z++) {
					if (flag) {
						this.straightOn(newDirection);
						this.printPosition("Your moped");
					}
				}
			}
			else if (a > 0 && flag) {
				this.goLeft(direction);
				this.printPosition("Your moped");
				String newDirection = this.getDirection();
				for (int z = 0; z < a-1; z++) {
					if (flag) {
						this.straightOn(newDirection);
						this.printPosition("Your moped");
					}
				}
			}
		}
		
		if (direction.equals("South") && flag) {
			if (s < 0) {
				int distance = Math.abs(s);
				for (int t = 0; t < distance; t++) {
					if (flag) {
						this.straightOn(direction);
						this.printPosition("Your moped");
					}
				}
			}
			else if (s > 0) {
				for (int t = 0; t < s; t++) {
					if (flag) {
						this.backUp(direction);
						this.printPosition("Your moped");
					}
				}
			}
			
			
			if (a < 0 && flag) {
				this.goLeft(direction);
				this.printPosition("Your moped");
				int diff = Math.abs(a);
				String newDirection = this.getDirection();
				for (int z = 0; z < diff-1; z++) {
					if (flag) {
						this.straightOn(newDirection);
						this.printPosition("Your moped");
					}
				}
			}
			else if (a > 0 && flag) {
				this.goRight(direction);
				this.printPosition("Your moped");
				String newDirection = this.getDirection();
				for (int z = 0; z < a-1; z++) {
					if (flag) {
						this.straightOn(newDirection);
						this.printPosition("Your moped");
					}
				}
			}
		}
		
		if (direction.equals("West") && flag) {
			if (s < 0) {
				this.goLeft(direction);
				this.printPosition("Your moped");
				this.petiteAbeille();
			}
			else if (s > 0) {
				this.goRight(direction);
				this.printPosition("Your moped");
				this.petiteAbeille();
			}			
		}
		
		if (direction.equals("East") && flag) {
			if (s < 0) {
				this.goRight(direction);
				this.printPosition("Your moped");
				this.petiteAbeille();				
			}
			else if (s > 0) {
				this.goLeft(direction);
				this.printPosition("Your moped");
				this.petiteAbeille();
			}			
		}
	}
	
	/**
	 * displays the list of acceptable commands
	 */
	public void help() {
		System.out.println("\nThe program accepts the following commands:");
		for (int b = 0; b < acceptableCommands.length; b++) {
			System.out.println(acceptableCommands[b]);
		}
		System.out.println("");
	}
	
	
	
	
	//getters
	public int getStreet() {
		return this.street;
	}
	 
	public int getAvenue() {
		return this.avenue;
	}
	 
	public String getDirection() {
		return this.direction;
	}
	 
	public String getCommand() {
		return this.command;
	}

	public int getTank() {
		return this.tank;
	}
	
	public int getCommandNum() {
		return this.commandNum;
	}
	
	/**
	 * find the appropriate word ending for the numbers
	 * @param number
	 * @return the appropriate word ending
	 */
	public String getEnding(int number) {
		if (number % 10 == 1 && number != 11) {
			return "st";
		}
		else if (number % 10 == 2 && number != 12) {
			return "nd";
		}
		else if (number % 10 == 3 && number != 13) {
			return "rd";
		}
		else {
			return "th";
		}
	}
	 
	public boolean getTurn() {
		return this.turn;
	}
	 
	 
	 
	//setters
	/**
	 * set flag
	 * @param value: boolean value of true or false
	 */
	public void setFlag(boolean value) {
		this.flag = value;
	}
	
	/**
	 * set street and avenue
	 * @param street: has to be between 1 and 200
	 * @param avenue: has to be between 1 and 10
	 */
	public void setPosition(int street, int avenue) {
		if (street < 201 && street > 0) {
			this.street = street;
		}
		/**
		else {
			System.out.println("End of area. Please choose another command.");
			this.setTurn(false);
		}
		*/
		if (avenue < 11 && avenue > 0) {
			this.avenue = avenue;
		}
		/**
		else {
			System.out.println("End of area. Please choose another command.");
			this.setTurn(false);
		}
		*/
	}
	
	/**
	 * reset command property to nothing in every round
	 * @param command: check if it is in the list of acceptable commands
	 */
	public void setCommand(String command) {
		this.command = "";
		this.commandNum = 10;
		for (int i = 0; i < acceptableCommands.length; i++) {
			if (acceptableCommands[i].equals(command)) {
				this.command = command;
				this.commandNum = i;
			}
		}
	}
	
	/**
	 * set direction
	 * @param direction current direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * set tank
	 * @param tank
	 */
	public void setTank(int tank) {
		this.tank = tank;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
}
