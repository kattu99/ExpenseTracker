package com.example.textclassification;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextClassifier {

    DocumentCategorizerME classifier;

    public TextClassifier() {
        try {
            classifier = DocumentCategorizer();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DocumentCategorizerME DocumentCategorizer() throws IOException {

        File trainingData = new File("Path to doccat.bin model file");
        String classificationModelFilePath = trainingData.getAbsolutePath();
        DocumentCategorizerME classificationME = new DocumentCategorizerME(new DoccatModel(new FileInputStream(
                classificationModelFilePath)));
        return classificationME;
    }

    public String predict(String text) {
        String documentContent = text;
        double[] classDistribution = classifier.categorize(documentContent.replaceAll("[^A-Za-z]", " ").split(" "));

        return classifier.getBestCategory(classDistribution);
    }
}
