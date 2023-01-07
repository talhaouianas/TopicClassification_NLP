package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopicClassification {

    public static double MinimalFrequence = 0.001;

    public static String Text_ClassificationResult = "";
    public static String Text_Similarity = "";
    public static String Text_DocumentBOW = "";
    public static String Text_TopicsBOWs = "";


    public static String Classification(String PathOfInputDocument ,String PathOfTopicsDirectory) throws IOException {

        List<List<Word>> BagOfWords = getBagOfWordsForAllTopics(PathOfTopicsDirectory,MinimalFrequence);



        //----------------Affichage
        Text_TopicsBOWs = "         Bag Of Words For All Topics :  \n\n";
        for (List<Word> list: BagOfWords) {
            Text_TopicsBOWs += "\n      BOW for Topic : " +Dictionary.dictionary[BagOfWords.indexOf(list)]+"\n";
            for (Word w:list) {
                Text_TopicsBOWs +="Word : "+w.Word+"  Frequence : "+w.Frequence+"  Occurence : "+w.Occurrence+"\n";
            }

        }
//----------------



        List<Word> WordsOfInput= getVocabularyFromDirectory(PathOfInputDocument,MinimalFrequence);




        //----------------Affichage
        Text_DocumentBOW = "      BOW for Input Document :\n";
        for (Word w:WordsOfInput) {
            Text_DocumentBOW +="Word : "+w.Word+"  Frequence : "+w.Frequence+"  Occurence : "+w.Occurrence+"\n";
        }
//----------------


        double max = 0;
        int index=0;

        int i=0;
        for (List<Word> WordsOfTopic:BagOfWords) {
            Text_Similarity += "\n_______________ CosineSimilarity for Topic : "+Dictionary.dictionary[i]+" _____________________\n";
            double temp = CosineSimilarity(WordsOfInput,WordsOfTopic);
            if(max<temp){max=temp;index=i;}

            i++;
        }

        Text_ClassificationResult = Dictionary.dictionary[index];

return Dictionary.dictionary[index];

    }
    public static double CosineSimilarity(List<Word> WordsOfInput,List<Word> WordsOfTopic) throws IOException {
        String text="";

        double Product = 0;
        double magnitudeWordsOfInput = 0 ;
        double magnitudeWordsOfTopic = 0 ;

//calculate the dot product of the vectors:
        for (Word word:WordsOfInput) {
            int index =PreTraitement.existInWords(word.Word,WordsOfTopic);
            if( index >= 0 ){
                text +="WordsOfInput: "+word.Word+"   freq: "+word.Frequence+"\t WordsOfTopic: "+WordsOfTopic.get(index).Word+"   freq: "+WordsOfTopic.get(index).Frequence+"\n";

                Product += word.Frequence * WordsOfTopic.get(index).Frequence;
            }
        }


//calculate the magnitude of the vectors:
        double sum=0;
        for (Word word:WordsOfInput) { sum += word.Frequence * word.Frequence;}
        magnitudeWordsOfInput = Math.sqrt(sum);

        sum=0;
        for (Word word:WordsOfTopic) { sum += word.Frequence* word.Frequence;}
        magnitudeWordsOfTopic = Math.sqrt(sum);


//Finally, cosine similarity can be calculated by dividing the dot product by the magnitude

        double result = Product  / (magnitudeWordsOfInput * magnitudeWordsOfTopic);

        text += "_______________ CosineSimilarity = "+result+" _____________________\n\n";

        Text_Similarity += text;

    return result;
    }

    public static List<List<Word>> getBagOfWordsForAllTopics(String PathOfTopicsDirectory,double MinimalFrequence) throws IOException {

        List<List<Word>> BagOfWords = new ArrayList<List<Word>>();


        System.out.println( "-------------------------- setBagOfWords --------------------------------\n\n\n");
        //Creating a File object for directory
        File directoryPath = new File(PathOfTopicsDirectory);

        //List of all directories
        File filesList[] = directoryPath.listFiles();

        for(File file : filesList) {
            String chemin = file.getPath();
            System.out.println("-------------------------- directory : "+chemin+"--------------------------");
           List<Word> result = getVocabularyFromDirectory(chemin,MinimalFrequence);

           BagOfWords.add(result);
        }

        System.out.println("\n\n\n______________________BagOfWords________________");
        for (int i = 0; i < BagOfWords.size(); i++) {
            System.out.println("BOW for Topic "+i+" has : "+BagOfWords.get(i).size()+" Words without repetition");}
        System.out.println( "----------------------------------------------------------\n\n\n");

        return BagOfWords;
    }

    public static List<Word> getVocabularyFromDirectory(String path,double MinimalFrequence) throws IOException{
        System.out.println( "*************************** getVocabularyFromDirectory **********************************");
        List<Word> result = PreTraitement.getWordsFromDirectoryWithFrequency(path,MinimalFrequence);

        return result;
    }


}
