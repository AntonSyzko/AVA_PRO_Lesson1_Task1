package XML;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Ыг on 19.01.2016.
 * Написать парсер для Yahoo Finance в режиме XML
 (format=xml).
 */
public class YahooDeparse {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        writeToFile();
        writeToConsole();

    }


public static void writeToConsole() throws ParserConfigurationException, IOException, SAXException {
    String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
            "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

    Document document = documentBuilder.parse(request);
    org.w3c.dom.Element root = document.getDocumentElement();

    NodeList nodeList = root.getChildNodes();

    for (int i = 0; i < nodeList.getLength(); i++) {
        org.w3c.dom.Node node = nodeList.item(i);

        if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
            org.w3c.dom.Element element = (org.w3c.dom.Element) node;

            System.out.println("Name: " + element.getElementsByTagName("Name").item(0)
                    .getChildNodes().item(0).getNodeValue());

            System.out.println("Rate: " + element.getElementsByTagName("Rate").item(0)
                    .getChildNodes().item(0).getNodeValue());

            System.out.println("----------------------");
        }
    }
}


public static void writeToFile() {
    String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
            "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

    try{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(request);

        TransformerFactory trfa = TransformerFactory.newInstance();
        Transformer transformer = trfa.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource dso = new DOMSource(document);
        StreamResult res = new StreamResult(new File("C:\\Users\\Ыг\\Desktop\\JAVA\\Random\\src\\main\\java\\JSON\\YahooXMLResult.xml"));
        transformer.transform(dso, res);
        System.out.println(" file saved ");

    } catch (Exception e){
        e.getCause();
    }

}




    public static String deparseFromURL(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            URL myUrl = new URL(url);
            HttpURLConnection http = (HttpURLConnection) myUrl.openConnection();

            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
                char[] buffer = new char[1000000];

                int r = 0;
                do{
                    if((r=br.read(buffer))>0){
                        sb.append(new String(buffer, 0,r));
                    }

                }while (r>0);

            }finally {
                http.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
       }

        return sb.toString();
    }
}

