import java.util.ArrayList;
import java.util.Scanner;

public class hangman {

    public static void main(String[] args) {
        // Variabler
        Scanner input = new Scanner(System.in);

        // Räknar antalet felgissningar
        int wrongGuesses = 0;
        // Max antal felgissningar innan man förlorar
        int maxGuesses = 10;
        //ArrayList som lagrar de bokstäver spelaren använt
        ArrayList<String> usedLetters = new ArrayList<>();
        // 'word' tilldelas ett framslumpat ord, anropar toUpperCase för att
        // göra alla bokstäver stora
        String word = getWord().toUpperCase();
        // I en loop som körs så många gånger som det finns bokstäver i 'word'
        // läggs ett understreck till i strängen 'maskedWord'
        String maskedWord = "";
        for (int i = 0; i < word.length(); i++) {
            maskedWord = maskedWord + "_";
        }

        // Lista får att lagra bokstäver som användaren gissat på
        ArrayList<String> guessedLetters = new ArrayList<>();

        // Spelet startar
        System.out.println("Hanging Man\n");
        System.out.println("Hemligt ord: ");
        System.out.println(maskedWord + "\n");
        // while loop som körs så länge användaren inte uppnåt max antal felgissningar
        while (maxGuesses > wrongGuesses) {
            // Användaren matar in en bokstav
            System.out.print("Gissa bokstav: ");
            String letter = input.next().toUpperCase();
            // Anropa här en metod som kontrollerar att inmatningen i 'letter' endast är
            // ett tecken lång, och det tecknet är en bokstav som inte gissats förut
            if(isValid(letter)&&notUsed(letter,usedLetters)) {
                // Kontrollera om 'word' innehåller bokstaven som användaren gissat på
                if (word.contains(letter)) {
                    System.out.println(letter + " finns med!");
                    maskedWord = swapUnderScores(word, letter, maskedWord);
                }
                // Om 'word' inte innehåller bokstaven ökar 'wrongGuesses' värde med ett
                else {
                    System.out.println("Fel! "+(maxGuesses-wrongGuesses-1)+" försök kvar!");
                    wrongGuesses++;
                }

                usedLetters.add(letter);

                System.out.println(maskedWord);

                if (maskedWord.equals(word)) {
                    System.out.println("\nDu hade rätt, ordet var " + word);
                    break;
                }
            }
        }
        //skrivs ut om while-loopens villkor uppfyllts
        if (wrongGuesses >= 10){
            System.out.println("\n +---+\n" +
                    "  |   |\n" +
                    "  O   |\n" +
                    " /|\\  |\n" +
                    " / \\  |\n" +
                    "      |\n" +
                    "=========''']");
            System.out.println("GAME OVER");
        }
    }

    public static String getWord() {

        ArrayList<String> ord = new ArrayList<String>();
        ord.add("Padel");
        ord.add("Fotboll");
        ord.add("Programmering");
        ord.add("Basket");
        ord.add("Java");
        ord.add("Tennis");

        int b = (int) (Math.random() * ord.size());

        return ord.get(b);
    }

    /*
        Kontrollerar om en bokstav finns med i givet ord, byter ut understrecken i
        det maskerade ordet mot denna bokstav och returnerar strängen.
        */
    public static String swapUnderScores(String word, String guessedLetter,
                                         String maskedWord) {
        // Loopa igenom bokstäverna i 'word'
        for (int i = 0; i < word.length(); i++) {
            // Tilldelar 'c' bokstaven på plats 'i' i 'word'
            String c = Character.toString(word.charAt(i));
            // Om c är samma som bokstaven användaren gissat på byts dessa ut i
            // strängen
            if (c.equals(guessedLetter)) {
                maskedWord = maskedWord.substring(0, i) + guessedLetter +
                        maskedWord.substring(i + 1, maskedWord.length());
            }
        }
        return maskedWord;
    }
    /*
    Kontrollerar att den mottagna strängen endast är en bokstav och
    endast ett tecken långt, i annat fall skriv lämpligt felmeddelande ut.
     */
    public static boolean isValid(String letter){
        if(letter.length()>1 ){
            System.out.println("Skriv endast en bokstav");
            return false;
        } else if (letter.equals("Å")|| letter.equals("Ä")|| letter.equals("Ö")) {
            System.out.println(" ÅÄÖ är ej giltiga, ange en bokstav från A - Z");
            return false;
        }
        else if(!Character.isLetter(letter.charAt(0))) {
            System.out.println("Endast bokstäver är tillåtna");
            return false;
        }
        else
            return true;
    }
    /*
    Kontrollerar att den mottagna strängen inte använts förut
    genom att jämföra den med innehållet i arraylistan
     */
    public static boolean notUsed(String letter,ArrayList<String> usedLetters){
        for (int i = 0; i < usedLetters.size(); i++) {
            if (usedLetters.get(i).equalsIgnoreCase(letter)) {
                System.out.println("Bokstaven har redan använts");
                return false;
            }
        }
        return true;
    }
}
