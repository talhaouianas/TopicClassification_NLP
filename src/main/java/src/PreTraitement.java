package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PreTraitement {

      /*
    public static void main(String[] args) throws IOException {



        List<String> StopWords = stringIntoList(readFileIntoString("Ressources/stop-words-list.txt"));
        System.out.println(StopWords);


        String lines = PreTraitement.readFileIntoString("Ressources/culture/document_1.txt");
        lines = normalize(lines);
        lines = remove_StopWords(lines,StopWords);
        System.out.println(lines);


        String r = readDirectoryIntoString("Ressources/culture");
        System.out.println("******************"+r);


       List<String> r = getAllWordsFromDirectory("Ressources/Topics/culture");
        for (String x:r) {System.out.println(x);}


        List<Word> result = getWordsFromDirectoryWithFrequency("Ressources/stop-words-list.txt");
        System.out.println(result.size());
        for (Word word:result) { System.out.println(word.Word+"  "+word.Occurrence+" "+word.Frequence); }

        String Words =  remove_StopWords(readFileIntoString("Ressources/stop-words-list.txt"));
        System.out.println(Words+" "+Words.length());

         }
*/

    public static List<Word> getWordsFromDirectoryWithFrequency(String path,double MinimalFrequence) throws IOException{
        System.out.println( "*************************** getWordsFromDirectory **********************************\n");
        List<String> mots = getAllWordsFromDirectory(path);

          List<Word>  result = new ArrayList<Word>();

        for (int i = 0; i < mots.size(); i++) {

            //if already exist increment occurence
            int indice =existInWords(mots.get(i),result);
            if( indice >= 0){
                result.get(indice).Occurrence++;
            }
            else{
                Word word = new Word();
                word.Word=mots.get(i);
                word.Occurrence=1;
                result.add(word);
            }
        }
        //Sort Words from Most frequent to less frequent
        Collections.sort(result,new SortWords());


        //initialise frequency for each word
        int total=0;
        for (Word w:result) {total += w.Occurrence;}
        System.out.println("****************************************   total words : "+total+"     ****************************************\n\n\n");
        for (Word w:result) { w.Frequence = w.Occurrence / total;}


        //remeove Words with frequence < MinimalFrequence

        List<Word>  result2 = new ArrayList<Word>();
        for (Word w:result) {
            if(w.Frequence > MinimalFrequence)result2.add(w);
        }

        for (Word word:result2) { System.out.println(word.Word+"  "+word.Occurrence+" "+word.Frequence); }

        System.out.println("\n\n\n");

        return result2;

    }
    public static List<String> getAllWordsFromDirectory(String path) throws IOException{
        System.out.println( "*************************** getAllWordsFromDirectory **********************************");
        String text =readDirectoryIntoString(path);

        //it's necessary to remove_StopWords then normalize !!!!!!!!!!!!!!!!!!!!

        text = remove_StopWords(text);

        text = normalize(text);

        List<String>  result1 = stringIntoList(text);

        return result1;
    }



    public static String readDirectoryIntoString(String path)throws IOException {
        System.out.println( "*************************** readDirectoryIntoString **********************************");
        String result="";
        //Creating a File object for directory
        File directoryPath = new File(path);

        if(directoryPath.isDirectory()) {
            //List of all files and directories
            File filesList[] = directoryPath.listFiles();

            for (File file : filesList) {
                String chemin = file.getPath();
                // System.out.println("File path: "+chemin+"\n");

                result += "\n" + PreTraitement.readFileIntoString(chemin);

            }

        }
        else result += readFileIntoString(path);


        return result;
    }
    public static String readFileIntoString(String path) throws IOException {
        // System.out.println( "*************************** readFileIntoString **********************************");
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }





    public static List<String> stringIntoList(String text) throws IOException{
        System.out.println( "*************************** stringIntoList **********************************");
        List<String> List = new ArrayList<String>(Arrays.asList(text.split("\\s+")));

        return List;
    }
    public static String normalize(String text){
        System.out.println( "*************************** normalize **********************************");
        //remove Punctuation Numbers latin char
                                // text.replaceAll("\\p{Punct}", "");text.replaceAll("\\p{M}", "");
        text = text.replaceAll("[0-9]","");
        text = text.replaceAll("[A-Z]","");
        text = text.replaceAll("[a-z]","");
        text = text.replaceAll("(\\@)?(\\%)?(\\_)?(\\–)?(\\[)?(\\*)?(\\/)?(\\,)?(\\—)?(\\])?(\\꞉)?(\\…)?(\\»)?(\\«)?(\\')?(\\))?(\\()?(؟)?(،)?(؛)?(\\.)?(\")?(-)?(!)?(:)?(ǃ)?", "");

        // remove tab and spaces+ to 1space
        text = text.replaceAll("\\s+", " ");


        //Remove honorific sign
        text = text.replaceAll("\u0610", "");//ARABIC SIGN SALLALLAHOU ALAYHE WA SALLAM
        text = text.replaceAll("\u0611", "");//ARABIC SIGN ALAYHE ASSALLAM
        text = text.replaceAll("\u0612", "");//ARABIC SIGN RAHMATULLAH ALAYHE
        text = text.replaceAll("\u0613", "");//ARABIC SIGN RADI ALLAHOU ANHU
        text = text.replaceAll("\u0614", "");//ARABIC SIGN TAKHALLUS

        //Remove koranic anotation
        text = text.replaceAll("\u0615", "");//ARABIC SMALL HIGH TAH
        text = text.replaceAll("\u0616", "");//ARABIC SMALL HIGH LIGATURE ALEF WITH LAM WITH YEH
        text = text.replaceAll("\u0617", "");//ARABIC SMALL HIGH ZAIN
        text = text.replaceAll("\u0618", "");//ARABIC SMALL FATHA
        text = text.replaceAll("\u0619", "");//ARABIC SMALL DAMMA
        text = text.replaceAll("\u061A", "");//ARABIC SMALL KASRA
        text = text.replaceAll("\u06D6", "");//ARABIC SMALL HIGH LIGATURE SAD WITH LAM WITH ALEF MAKSURA
        text = text.replaceAll("\u06D7", "");//ARABIC SMALL HIGH LIGATURE QAF WITH LAM WITH ALEF MAKSURA
        text = text.replaceAll("\u06D8", "");//ARABIC SMALL HIGH MEEM INITIAL FORM
        text = text.replaceAll("\u06D9", "");//ARABIC SMALL HIGH LAM ALEF
        text = text.replaceAll("\u06DA", "");//ARABIC SMALL HIGH JEEM
        text = text.replaceAll("\u06DB", "");//ARABIC SMALL HIGH THREE DOTS
        text = text.replaceAll("\u06DC", "");//ARABIC SMALL HIGH SEEN
        text = text.replaceAll("\u06DD", "");//ARABIC END OF AYAH
        text = text.replaceAll("\u06DE", "");//ARABIC START OF RUB EL HIZB
        text = text.replaceAll("\u06DF", "");//ARABIC SMALL HIGH ROUNDED ZERO
        text = text.replaceAll("\u06E0", "");//ARABIC SMALL HIGH UPRIGHT RECTANGULAR ZERO
        text = text.replaceAll("\u06E1", "");//ARABIC SMALL HIGH DOTLESS HEAD OF KHAH
        text = text.replaceAll("\u06E2", "");//ARABIC SMALL HIGH MEEM ISOLATED FORM
        text = text.replaceAll("\u06E3", "");//ARABIC SMALL LOW SEEN
        text = text.replaceAll("\u06E4", "");//ARABIC SMALL HIGH MADDA
        text = text.replaceAll("\u06E5", "");//ARABIC SMALL WAW
        text = text.replaceAll("\u06E6", "");//ARABIC SMALL YEH
        text = text.replaceAll("\u06E7", "");//ARABIC SMALL HIGH YEH
        text = text.replaceAll("\u06E8", "");//ARABIC SMALL HIGH NOON
        text = text.replaceAll("\u06E9", "");//ARABIC PLACE OF SAJDAH
        text = text.replaceAll("\u06EA", "");//ARABIC EMPTY CENTRE LOW STOP
        text = text.replaceAll("\u06EB", "");//ARABIC EMPTY CENTRE HIGH STOP
        text = text.replaceAll("\u06EC", "");//ARABIC ROUNDED HIGH STOP WITH FILLED CENTRE
        text = text.replaceAll("\u06ED", "");//ARABIC SMALL LOW MEEM

        //Remove tatweel
        text = text.replaceAll("\u0640", "");

        //Remove tashkeel
        text = text.replaceAll("\u064B", "");//ARABIC FATHATAN
        text = text.replaceAll("\u064C", "");//ARABIC DAMMATAN
        text = text.replaceAll("\u064D", "");//ARABIC KASRATAN
        text = text.replaceAll("\u064E", "");//ARABIC FATHA
        text = text.replaceAll("\u064F", "");//ARABIC DAMMA
        text = text.replaceAll("\u0650", "");//ARABIC KASRA
        text = text.replaceAll("\u0651", "");//ARABIC SHADDA
        text = text.replaceAll("\u0652", "");//ARABIC SUKUN
        text = text.replaceAll("\u0653", "");//ARABIC MADDAH ABOVE
        text = text.replaceAll("\u0654", "");//ARABIC HAMZA ABOVE
        text = text.replaceAll("\u0655", "");//ARABIC HAMZA BELOW
        text = text.replaceAll("\u0656", "");//ARABIC SUBSCRIPT ALEF
        text = text.replaceAll("\u0657", "");//ARABIC INVERTED DAMMA
        text = text.replaceAll("\u0658", "");//ARABIC MARK NOON GHUNNA
        text = text.replaceAll("\u0659", "");//ARABIC ZWARAKAY
        text = text.replaceAll("\u065A", "");//ARABIC VOWEL SIGN SMALL V ABOVE
        text = text.replaceAll("\u065B", "");//ARABIC VOWEL SIGN INVERTED SMALL V ABOVE
        text = text.replaceAll("\u065C", "");//ARABIC VOWEL SIGN DOT BELOW
        text = text.replaceAll("\u065D", "");//ARABIC REVERSED DAMMA
        text = text.replaceAll("\u065E", "");//ARABIC FATHA WITH TWO DOTS
        text = text.replaceAll("\u065F", "");//ARABIC WAVY HAMZA BELOW
        text = text.replaceAll("\u0670", "");//ARABIC LETTER SUPERSCRIPT ALEF

        return text;
    }
    public static String remove_StopWords(String text) throws IOException {
        System.out.println( "*************************** remove_StopWords **********************************");

        List<String> StopWords = stringIntoList(readFileIntoString("src/main/resources/stop-words-list.txt"));
        List<String> textWords = stringIntoList(text);
        String CleanedText ="";


        for(String word : textWords) {
            if(!StopWords.contains(word)) {
               CleanedText += word+" ";
            }
        }


        return CleanedText ;
/*
        String[] allWords = text.split("\\s+");

        StringBuilder builder = new StringBuilder();

        for(String word : allWords) {
            if(!StopWords.contains(word)) {
                builder.append(word);
                builder.append(' ');
            }
        }

        String result = builder.toString().trim();
*/
    }
    public static int existInWords(String mot, List<Word> words) throws IOException{
        // System.out.println( "*************************** existInWords **********************************");
        int index = -1;
        for (int i = 0; i < words.size(); i++) {
            if(words.get(i).Word.equals(mot)){index = i;break;}
        }

        return index;

    }

}
