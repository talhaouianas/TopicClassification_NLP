module com.example.topicmodling_v2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.topicmodling_v2 to javafx.fxml;
    exports com.example.topicmodling_v2;
}