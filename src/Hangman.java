import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	int lives = 10;
	JFrame f = new JFrame();
	JPanel p = new JPanel();
	JLabel guessLabel = new JLabel();
	JLabel livesLabel = new JLabel();
	JLabel solvedLabel = new JLabel();
	JLabel wordLabel = new JLabel();
	static Stack<String> wordStack = new Stack<>(); // Contains all the words from "instructions.txt"

	String currentWord = wordStack.pop(); // Word that the user will try to guess
	int numChar = currentWord.length(); // This is the number of characters (useful when using "_")
	String hiddenWord = "";

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

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(guessLabel);
		p.add(livesLabel);
		p.add(solvedLabel);
		p.add(wordLabel);

		f.setVisible(true);
		f.pack();
		f.setSize(600, 600);

		System.out.println("" + currentWord);
		for (int i = 0; i < numChar; i++) {
			hiddenWord += "_ ";

		}

		guessLabel.setText("Guess a letter.");
		wordLabel.setText(hiddenWord);
		livesLabel.setText("You have");
		wordLabel.setText("You have solved");

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char keyPressed = e.getKeyChar();
		String newHiddenWord = ""; 

		if (currentWord.contains(Character.toString(keyPressed)) == true) {
			for (int i = 0; i < numChar; i++) {
				if(currentWord.charAt(i) == keyPressed) {
					newHiddenWord+=keyPressed; 
					l.set(newHiddenWord);
				}
			}

		} else {
			l.set(newHiddenWord);
			lives--;
		}
	}

}
