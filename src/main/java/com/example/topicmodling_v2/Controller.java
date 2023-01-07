package com.example.topicmodling_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import src.*;

public class Controller {

    @FXML
    TextField MinimalFrequence;
    @FXML
    TextField PathOfDocumentToClassify;
    @FXML
    TextField PathOfTopicsDirectory;

    @FXML
    TextArea area1;
    @FXML
    TextArea area2;
    @FXML
    TextArea area3;
    @FXML
    TextArea area4;

    public void run(ActionEvent event) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> RUN <<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        TopicClassification TC = new TopicClassification();

        //initialiser MinimalFrequence
        TC.MinimalFrequence = Double.parseDouble(MinimalFrequence.getText());
        //Classification
        TC.Classification(PathOfDocumentToClassify.getText(),PathOfTopicsDirectory.getText());


        //Affichage Resultats
        area1.setText(TC.Text_ClassificationResult);
        area2.setText(TC.Text_Similarity);
        area3.setText(TC.Text_DocumentBOW);
        area4.setText(TC.Text_TopicsBOWs);
    }
}
