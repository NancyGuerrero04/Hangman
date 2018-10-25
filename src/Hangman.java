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
	int solvedWords = 0;
	JFrame f = new JFrame();
	JPanel p = new JPanel();
	JLabel guessLabel = new JLabel();
	JLabel livesLabel = new JLabel();
	JLabel solvedLabel = new JLabel();
	JLabel wordLabel = new JLabel();
	JLabel hiddenWordLabel = new JLabel();
	static Stack<String> wordStack = new Stack<>(); // Contains all the words from "instructions.txt"
	int numChar;// This is the number of characters (useful when using "_")
	String currentWord = "";
	String hiddenWord = "";
	String numOfRounds;
	String numOfWords;
	int numOfRoundsInt;

	public static void main(String[] args) {
	
		

		Hangman hangman = new Hangman();
	}

	// Constructor for my UI
	Hangman() {
		
		numOfRounds = JOptionPane
				.showInputDialog("Welcome to Hangman! How many rounds would you like to play?");
		
		numOfRoundsInt = Integer.parseInt(numOfRounds); // Changes the number string into an number int
		ArrayList<String> wordList = new ArrayList<>(numOfRoundsInt);

		// This will read each word from the "instructions.txt" and adds the words into
		// "wordList"
		try {
			Scanner sc = new Scanner(new File("src/dictionary.txt"));
			while (sc.hasNext()) {
				wordList.add(sc.next());

			}
			sc.close();

			// For errors
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// The words will be in a random order
		Random ran = new Random();

		for (int i = 0; i < numOfRoundsInt; i++) {
			int ranNum = ran.nextInt(wordList.size());
			wordStack.push(wordList.remove(ranNum));
			// System.out.println(ranNum);

		}
		
		f.add(p);

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(guessLabel);
		p.add(livesLabel);
		p.add(solvedLabel);
		p.add(wordLabel);
		p.add(hiddenWordLabel);
		f.addKeyListener(this);

		f.setVisible(true);
		f.pack();
		f.setSize(600, 600);

		

		newGame();
	}

	void gameOver() {
		if (lives == 0) {
			JOptionPane.showMessageDialog(null, "GAME OVER!");
			f.hide();
		}
	}

	void newGame() {
		lives = 0;
		solvedWords = 0;
		currentWord = wordStack.pop();
		numChar = currentWord.length();
		guessLabel.setText("Guess a letter.");
		for (int i = 0; i < numChar; i++) {
			hiddenWord += "_ ";

		}
		hiddenWordLabel.setText(hiddenWord);
		livesLabel.setText("You have 10 lives left");
		wordLabel.setText("You have solved 0 words");
		System.out.println("" + currentWord);
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
				if (currentWord.charAt(i) == keyPressed) {
					newHiddenWord += keyPressed + " ";
					// l.set(newHiddenWord);
				} else {
					char previousChar = hiddenWord.charAt(i * 2);
					newHiddenWord += previousChar + " ";
				}

			}
			hiddenWord = newHiddenWord;
			hiddenWordLabel.setText(newHiddenWord);
			if (newHiddenWord.contains("_") == false) {

				JOptionPane.showMessageDialog(null, "CORRECT!");
				wordStack.pop();
				solvedWords++;
				if(numOfRoundsInt==solvedWords) {
					
					JOptionPane.showMessageDialog(null, "CONGRATUALATIONS! You have completed the rounds!");
					newGame();
					
				}
			}

		} else {
			// l.set(newHiddenWord);

			lives--;
			livesLabel.setText("You have " + lives + " left");
			wordLabel.setText("You have solved "+solvedWords+" words");
			gameOver();

		}
	}

}
