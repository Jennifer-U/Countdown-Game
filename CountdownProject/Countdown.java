package CountdownProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Countdown {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<String> myList = new ArrayList<>();
		Scanner wordFile = new Scanner(new File("CountdownProject/words.txt"));

		// added elements in scanned document into ArrayList above
		while (wordFile.hasNextLine()) {
			myList.add(wordFile.next());
		}

		int round = 0;
		String[] vowels = { "A", "I", "E", "O", "U" };
		String[] consonants = { "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V",
				"W", "X", "Y", "Z" };
		int numVowels = 0;

		String player1 = "Player 1, ";
		String player1Word = "";
		int player1Score = 0;

		String player2 = "Player 2, ";
		String player2Word = "";
		int player2Score = 0;

		String letters = "";

		Random random = new Random(); // created random object
		Scanner input = new Scanner(System.in);
		
		while (round < 11) // only 10 rounds allowed
		{

			for (int i = 1; i < 11; i++) {
				System.out.println();
				System.out.println("Round " + i);
				System.out.print("How many vowels would you like (3-5 inclusive)? ");

				numVowels = input.nextInt();
				while (numVowels < 3 || numVowels > 5) {
					System.out.println("Invalid selection! How many vowels would you like (3-5 inclusive)?");
					numVowels = input.nextInt();
				}

				// create length 9 word by pulling random vowels and consonants and putting in empty string 
				if (letters.length() < 10) { 
					// getting random vowel of length that the user inputed
					for (int j = 0; j < vowels.length; j++) {
						if (j < numVowels) {
							letters += vowels[random.nextInt(vowels.length)];
						}
					}
					// Getting random consonant concatenated to vowels until new word is length 9
					for (int f = 0; f < consonants.length - 1; f++) {
						if (f > numVowels && f < 10) {
							letters += consonants[random.nextInt(consonants.length - 1)];
						}
					}

					System.out.println("The letters are: " + letters.toUpperCase());

					// Ask player 1 and then player 2 for their words
					System.out.print(player1 + "what is your word? ");
					input = new Scanner(System.in);
					player1Word = input.next().toUpperCase();

					System.out.print(player2 + "what is your word? ");
					input = new Scanner(System.in);
					player2Word = input.next().toUpperCase();

					/*
					   Created 2 temporary lists with the random letters in variable letters These
					   temporary list are manipulated later to confirm if duplicate characters are
					   present when compare each player words and the random letters
					 */

					ArrayList<String> tempLetters = new ArrayList<>();
					for (int l = 0; l < letters.length(); l++) { // This for loop fills my array
						tempLetters.add(Character.toString(letters.charAt(l)));	
					}

					ArrayList<String> tempLetters2 = new ArrayList<>();
					for (int w = 0; w < letters.length(); w++) {
						tempLetters2.add(Character.toString(letters.charAt(w)));
						// System.out.println(Character.toString(letters.charAt(w))); //check
					}
					
					//   Created 2 Strings-later filled w player letters that confirmed to be made from random letters  
					String player1ValidWord = "";
					String player2ValidWord = "";

					/*
					   FOR PLAYER 1 WORD checked the following conditions for player 1 word: 
					   1) word is valid if it appears in words.txt and can 
					   2) can be formed from the randomly-generated letters condition A:
					   3)If a word uses a letter more than once, it must appear that many
					   times in the randomly-generated letters.
					   
					   - if invalid words,reason why displayed.
					 */

					if (myList.contains(player1Word)) {
						// FIRST CHECK IF PLAYER WORD LENGTH IS LESS THAN OR EQUAL TO LETTERS
						// the following IF statement checks that player2Word does not go beyond letter
						if (player1Word.length() <= letters.length()) {
							// checking is element in tempLetters has element in word
							for (int f = 0; f < player1Word.length(); f++) {
								// Character.toString--gives result as string instead of char
								String stringP1Word = Character.toString(player1Word.charAt(f));
								if (tempLetters.contains(stringP1Word)) {
									tempLetters.remove(stringP1Word); // NEEDED TO CHECK FOR DUPLICATES
									player1ValidWord += (stringP1Word); // adds elements to valid word String
								} else {
									player1ValidWord = "";
									System.out.println(player1Word + " cannot be made with the letters");
									break;
									// break here stops print statement from printing the amount of times of the loop
								}
							}
						} else if (player1Word.length() > letters.length()) { 
							System.out.println(player1Word + " cannot be made with the letters");
						}
					} else {
						System.out.println(player1Word + " is not a word!");
					}
					
					// FOR PLAYER 2 WORD checked same conditions as listed above
					if (myList.contains(player2Word)) {
						if (player2Word.length() <= letters.length()) {
							for (int z = 0; z < player2Word.length(); z++) {
								String stringP2Word = Character.toString(player2Word.charAt(z));
								if (tempLetters2.contains(stringP2Word)) {
									tempLetters2.remove(stringP2Word); // NEEDED TO CHECK FOR DUPLICATES
									player2ValidWord += stringP2Word; // adds elements to valid word array
								} else {
									player2ValidWord = "";
									System.out.println(player2Word + " cannot be made with the letters");
									break; 
								}
							}
						} else if (player2Word.length() > letters.length()) { 
							System.out.println(player2Word + " cannot be made with the letters");
						}
					} else {
						System.out.println(player2Word + " is not a word!");
					}
					
					// LONGEST WORD SECTION
					ArrayList<String> validWords = new ArrayList<>();

					//CHECKING if word myList ARE VALID-have same letters as random letters 
					for (int d = 0; d < myList.size(); d++) { // iterating through words in myList
						String listWord = myList.get(d); // gets the element(the word) at the specified position in this list
						
						if (listWord.length() < 10) {
							boolean matchFlag = true; 
							for ( int s = 0; s < listWord.length(); s++) { // iterating through each word letter

								
								char wordLetter = listWord.charAt(s); //CHAR OF LETTER
									if (letters.indexOf(wordLetter) == -1) { //-1 WOULD BE RESULT  OF INDEXOF IF CHARACTER IS NOT PRESENT
										matchFlag = false;
										break;								
									}
							} 
							// Checking for duplicate letters -counting letters making sure count of ListWord letters is <= RandomWord letters
							for ( int s = 0; s < listWord.length(); s++) {
								char currentLetter = listWord.charAt(s);
								int rLcount=0;
								int lWcount=0;
								for ( int a = 0; a < listWord.length(); a++) {
									if(listWord.charAt(a) == currentLetter)  {
										lWcount++;	
									}
								}
								for ( int a = 0; a < letters.length(); a++) {
									if(letters.charAt(a) == currentLetter)  {
										rLcount++;	
									}
								}
								
								
								if(lWcount > rLcount)  {
									matchFlag = false;
									break;
								}		
							}
							// if flag is on/true means that word is valid
							if (matchFlag) {  
								validWords.add(listWord); 
							}
						}
					}
					//CHECKING FOR LONGEST LENGTH of validWords and adding to longestValid
					int longest = 0;
					List<String> longestValid = new ArrayList<>();

					// GET longest length out of all the valid words
					for (String word : validWords) {
						int length = word.length();

						if (length > longest) {
							longest = length;
						}
					} // ADD longest length to longestValid
					for( String word : validWords) {

						if(word.length() == longest) {
							longestValid.add(word);
						}
					}
					System.out.println("The best available words are " + longestValid); 

					// SCORE ALLOCATION
					// If BOTH players have valid words (valid word arrays are not empty) then:
					// 1)the player with the longer word scores.
					// 2)if both players have valid words of equal length, both players score.
					// The number of points scored is equal to the size of the word UNLESS
					// it has all nine letters; in that case, the player scores 18 points.

					if (player1ValidWord.length() != 0 && player2ValidWord.length() != 0) {

						if (player1ValidWord.length() > player2ValidWord.length()) {
							// If statement here checks if valid word is nine letters
							// if so, that player scores 18 points.
							if (player1ValidWord.length() == 9) {
								player1Score += 18;
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							} else {
								//// if valid word is NOT equal to nine letters
								// number of points scored is equal to the size of the word
								player1Score += player1ValidWord.length();
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							}
						} else if (player2ValidWord.length() > player1ValidWord.length()) {
							// If statement here checks if valid word is nine letters
							// if so, that player scores 18 points.
							if (player2ValidWord.length() == 9) {
								player2Score += 18;
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							} else {
								//// if valid word is NOT equal to nine letters
								// number of points scored is equal to the size of the word
								player2Score += player2ValidWord.length();
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							}

						} else { // if TIE
							// If statement here checks if BOTH Players valid words are nine letters each
							// if so, that BOTH player scores 18 points.
							if (player1ValidWord.length() == 9 && player2ValidWord.length() == 9) {
								player1Score += 18;
								player2Score += 18;
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							} else {
								//// if BOTH valid word are NOT equal to nine letters each
								// number of points scored is equal to the size of the word each
								player1Score += player1ValidWord.length();
								player2Score += player2ValidWord.length();
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							}

						}

						// If only ONE player has a valid word, that player scores.
					} else if (player1ValidWord.length() != 0 || player2ValidWord.length() != 0) {
						if (player1ValidWord.length() > player2ValidWord.length()) {
							// If statement here checks if valid word is nine letters
							// if so, that player scores 18 points.
							if (player1ValidWord.length() == 9) {
								player1Score += 18;
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							} else {
								//// if valid word is NOT equal to nine letters
								// number of points scored is equal to the size of the word
								player1Score += player1ValidWord.length();
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							}
						} else {
							// If statement below checks if valid word is nine letters
							// if so, that player scores 18 points.
							if (player2ValidWord.length() == 9) {
								player2Score += 18;
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							} else {
								//// if valid word is NOT equal to nine letters
								// number of points scored is equal to the size of the word
								player2Score += player2ValidWord.length();
								System.out.println("Player 1: " + player1Score);
								System.out.println("Player 2: " + player2Score);
							}
						}
					}
					round++; // goes to next round
					
					// blank variables once new round begins
					letters = "";
					player1Word = "";
					player2Word = "";
					tempLetters.clear();
					tempLetters2.clear();
					player1ValidWord = "";
					player2ValidWord = "";
					longestValid.clear();
					validWords.clear();	
				}
			}
		break;	
		}
	
		//COMPARES SCORES
		//player with highest score wins unless it is a tie than both win
		if (player1Score > player2Score) {
			System.out.println();
			System.out.println("Player 1 wins!");
		} else if (player2Score > player1Score) {
			System.out.println();
			System.out.println("Player 2 wins!");
		} else {
			System.out.println();
			System.out.println("It's a tie! Both players win!");
		}

	}
}

