package src;

import java.util.Set;
import java.util.HashSet;

public class GuessVowel implements WheelOfFortunePlayer{
    public static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
    public Set<Character> guessedLetters;
    public String id;
    private int index;
    public GuessVowel(String id) {
        this.id = id;
        guessedLetters = new HashSet<>();
        reset();
    }

    @Override
    public char nextGuess() {
        while (index < VOWELS.length) {
            char vowel = VOWELS[index++];
            if (!guessedLetters.contains(vowel)) {
                guessedLetters.add(vowel);
                return vowel;}
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if ("aeiou".indexOf(c) == -1 && !guessedLetters.contains(c)) {
                guessedLetters.add(c);
                return c;}
        }
        return ' ';}

    @Override
    public String playerId() {
        return id;
    }
    @Override
    public void reset() {
        guessedLetters.clear();
        index = 0;}
}
