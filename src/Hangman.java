import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman {
	int lives;
	JFrame f = new JFrame();
	JPanel p = new JPanel();
	JLabel guessLabel = new JLabel();
	JLabel livesLabel = new JLabel();
	JLabel solvedLabel = new JLabel();
	JLabel wordLabel = new JLabel();
	static Stack<String> wordStack = new Stack<>(); //Static can 

	public static void main(String[] args) {
		String numOfWordsString = JOptionPane
				.showInputDialog("Welcome to Hangman! How many rounds would you like to play?");
		int numOfWordsInt = Integer.parseInt(numOfWordsString); // Changes the number string into an number int
		ArrayList<String> wordList = new ArrayList<>(numOfWordsInt);

		// This will read each word from the "instructions.txt" and adds the words into
		// "wordList"
		try {
			Scanner sc = new Scanner(new File("src/dictionary.txt"));
			for (int i = 0; i < numOfWordsInt; i++) {
				wordList.add(sc.next());
				System.out.println(wordList.get(i));
			}
			sc.close();

			// For errors
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// The words will be in a random order
		Random ran = new Random();

		while (wordList.size() > 0) {
			int ranNum = ran.nextInt(wordList.size());
			wordStack.push(wordList.remove(ranNum));
			System.out.println(ranNum);

		}

		Hangman hangman = new Hangman();
	}



	// Constructor for my UI
	Hangman() {

		f.add(p);
		p.add(guessLabel);
		p.add(livesLabel);
		p.add(solvedLabel);
		p.add(wordLabel);

		f.pack();
		f.setVisible(true);

		guessLabel.setText("Guess a letter.");
		livesLabel.setText("You have ");
		wordLabel.setText("You have solved ");

		String pop = wordStack.pop();
		int popChar = 4;
		String blankLine = "";

		for (int j = 0; j < popChar; j++) {
			blankLine += "_ ";
		}
		wordLabel.setText("" + blankLine);
		System.out.println(blankLine);
	}
}
