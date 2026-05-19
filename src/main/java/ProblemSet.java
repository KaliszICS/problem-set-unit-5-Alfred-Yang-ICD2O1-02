/**
        * File: Problem Set Unit 5
        * Author: Alfred Yang
        * Date Created: May 14th, 2026
        * Date Last Modified: May 19th 2026
        */
import java.util.Scanner;
import java.util.ArrayList;
public class ProblemSet {
	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) {
		System.out.println("Welcome to the Text Analyzer.");
		System.out.print("Please enter a sentence or paragraph: ");
		String userInput;
		int totalSpaces = 0, totalVowels = 0;
		userInput = input.nextLine();
		ArrayList<String> filteredArray = filteringArray(userInput);
		
		for (int i = 0; i < userInput.length(); i++){
			if(userInput.charAt(i) == ' '){
				totalSpaces++;
			}
			if("aeiou".contains(userInput.substring(i, i+1))){
				totalVowels++;
			}
		}
		System.out.println("Total Characters: " + userInput.length());
		System.out.println("Total Words: " + filteredArray.size());
		System.out.println("Total Vowel: " + totalVowels);
		System.out.println("Total Spaces: " + totalSpaces);
		System.out.println("\nWord Frequency:\n\n" + frequencyOfWord(filteredArray));
		System.out.println("Longest Word: " + longestWord(filteredArray).toString().replaceAll("[\\[\\]]", ""));
		System.out.println("Shortest Word: " + shortestWord(filteredArray).toString().replaceAll("[\\[\\]]", ""));
		System.out.println("Average Word Length: " + averageWordLength(filteredArray));
		System.out.println("Number of Sentences: " + numberOfSentences(userInput));
		System.out.println("Unique Words: " + uniqueWords(filteredArray));
	}
//----------------------------------Fuctions Below---------------------------------------------------------------
	public static ArrayList<String> longestWord(ArrayList<String> filteredArray){
		ArrayList<String> longestWords = new ArrayList<String>();
		for (int i = 0; i < filteredArray.size(); i++){
			//Replaces an array when theres a new longest
			if (!ignoredWords(filteredArray.get(i)) && (longestWords.size() == 0 || filteredArray.get(i).length() > longestWords.get(0).length())){
				longestWords.clear();
				longestWords.add(filteredArray.get(i));
			}
			// Adds a word to an array if it's the same length as the longest
			else if(!ignoredWords(filteredArray.get(i)) && (longestWords.size() == 0 || filteredArray.get(i).length() == longestWords.get(0).length() && !longestWords.contains(filteredArray.get(i)))){
				longestWords.add(filteredArray.get(i));
			}
		}
		return longestWords;
	}

	public static ArrayList<String> shortestWord(ArrayList<String> filteredArray){
		ArrayList<String> shortestWords = new ArrayList<String>();
		for (int i = 0; i < filteredArray.size(); i++){
			//Replaces an array when theres a new shortest

			if (!ignoredWords(filteredArray.get(i)) && (shortestWords.size() == 0 || filteredArray.get(i).length() < shortestWords.get(0).length())){
					shortestWords.clear();
					shortestWords.add(filteredArray.get(i));
				}
			// Adds a word to an array if it's the same length as the shortest
			else if(!ignoredWords(filteredArray.get(i)) && (shortestWords.size() == 0 || filteredArray.get(i).length() == shortestWords.get(0).length() && !shortestWords.contains(filteredArray.get(i)))){
					shortestWords.add(filteredArray.get(i));
				}
			}
		return shortestWords;
	}

	public static String frequencyOfWord(ArrayList<String> filteredArray){
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> validArray = new ArrayList<String>();
		//Making a new arraylist without the "what" "and" "a" "an" "is"
		for (int i = 0; i < filteredArray.size(); i++){
			if (!ignoredWords(filteredArray.get(i))){
				validArray.add(filteredArray.get(i));
			}
		}
		//Checking if the word appears multiple times
		for (int i = 0; i < validArray.size(); i++){
			if (words.contains(validArray.get(i))){
				int indexRepeatWord = words.indexOf(validArray.get(i));
				frequency.set(indexRepeatWord, frequency.get(indexRepeatWord) + 1);
			}
			else{
				words.add(validArray.get(i));
				frequency.add(1);
			}
		}
		String wordAndFrequency = "";
		for(int i = 0; i < words.size(); i++){
			//Making Every Array into a String then adding it into a string
			String wordsString = words.get(i).toString().replaceAll("[\\[\\],]", "");
			String frequencyString = frequency.get(i).toString().replaceAll("[\\[\\],]", "");
			wordAndFrequency += wordsString + " - " + frequencyString + "\n";
		}
		return wordAndFrequency;
	}

	public static boolean ignoredWords(String arraySection){
		String[] regects = new String[]{"this", "and", "an", "a", "is"};
		for (int i = 0; i < regects.length; i++){
			if (arraySection.toLowerCase().equals(regects[i])){
				return true;
			}
		}
		return false;
	}

	public static double averageWordLength(ArrayList<String> filteredArray){
		double wordLengthAvg = 0;
		int i = 0;
		if (filteredArray.size() == 0){
			return 0;
	}
		for (i = 0; i < filteredArray.size(); i++){
			wordLengthAvg += filteredArray.get(i).length();
	}
	wordLengthAvg = wordLengthAvg/i;
	return wordLengthAvg;
}

	public static int uniqueWords(ArrayList<String> filteredArray){
		ArrayList<String> words = new ArrayList<String>();
		int uniqueWords = 0;
		for (int i = 0; i < filteredArray.size(); i++){
			if (!words.contains(filteredArray.get(i))){
				words.add(filteredArray.get(i));
				uniqueWords++;
			}
	}
	return uniqueWords;
}

	public static boolean letterInSentence(String sentence){
		if (sentence.replaceAll("[a-z0-9]", "").length() == sentence.length()){
				return false;
			}
	return true;
}
	public static int numberOfSentences (String userInput){
		int numberOfSentences = 0;
		String[] sentenceCheck = userInput.split("[.!?]");
		for (int i = 0; i < sentenceCheck.length; i++){
			if (sentenceCheck[i].length() != 0 && letterInSentence(sentenceCheck[i])){
				numberOfSentences++;
			}
		}
		return numberOfSentences;
	}

	public static ArrayList<String> filteringArray(String userInput){
		userInput = userInput.replaceAll("[,.!?]", " ");
		String[] userArray = userInput.split(" ");
		ArrayList<String> filteredArray = new ArrayList<String>();
		for (int i = 0; i < userArray.length; i++){
			if (!userArray[i].equals("")){
				filteredArray.add(userArray[i]);
			}
		}
		return filteredArray;
	}
}