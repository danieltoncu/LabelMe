
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.*;

public class Categorize {

    public void trainModel() {
        DoccatModel model = null;

        InputStream dataIn = null;
        try {
            dataIn = new FileInputStream("model.train");
            ObjectStream<String> lineStream
                    = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            model = DocumentCategorizerME.train("en", sampleStream);
        } catch (IOException e) {
            // Failed to read or parse training data, training failed
            e.printStackTrace();
        } finally {
            if (dataIn != null) {
                try {
                    dataIn.close();
                } catch (IOException e) {
                    // Not an issue, training already finished.
                    // The exception should be logged and investigated
                    // if part of a production system.
                    e.printStackTrace();
                }
            }
        }
        OutputStream modelOut = null;
        try {
            modelOut = new BufferedOutputStream(new FileOutputStream("model.bin"));
            model.serialize(modelOut);
        } catch (IOException e) {
            // Failed to save model
            e.printStackTrace();
        } finally {
            if (modelOut != null) {
                try {
                    modelOut.close();
                } catch (IOException e) {
                    // Failed to correctly save model.
                    // Written model might be invalid.
                    e.printStackTrace();
                }
            }
        }
    }

    public String getCategory(String inputText) {
        InputStream is = null;
        try {
            is = new FileInputStream("model.bin");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DoccatModel m = null;
        try {
            m = new DoccatModel(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DocumentCategorizerME myCategorizer = new DocumentCategorizerME(m);
        double[] outcomes = new double[10000];
        outcomes = myCategorizer.categorize(inputText);
        String category = myCategorizer.getBestCategory(outcomes);
        XML xml = new XML();
        xml.addMessage(category, inputText);
        return category;
    }
}
