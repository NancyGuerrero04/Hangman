import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Hangman {
	public static void main(String[] args) {
		String numOfWordsString = JOptionPane
				.showInputDialog("Welcome to Hangman! How many rounds would you like to play?");
		int numOfWordsInt = Integer.parseInt(numOfWordsString); // Changes the number string into an number int
		ArrayList<String> wordList = new ArrayList<>(numOfWordsInt);
		Stack<String> wordStack = new Stack<>();

		try {
			Scanner sc = new Scanner(new File("src/dictionary.txt"));
			for (int i = 0; i < numOfWordsInt; i++) {
				wordList.add(sc.next());
				System.out.println(wordList.get(i));
			}
			sc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Random ran = new Random();
		
		while(wordList.size()>0) {
			int ranNum = ran.nextInt(wordList.size());
			wordStack.push(wordList.remove(ranNum));
			System.out.println(ranNum);
		}
	}
}
