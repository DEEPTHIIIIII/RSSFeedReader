import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class rss {
    
    public static String rssfeed(String yurl)
    {
        String title=null;String link="";
       try{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        URL url = new URL(yurl);
        DocumentBuilder dom = factory.newDocumentBuilder();
        Document doc = dom.parse(url.openStream());
        NodeList nList = doc.getElementsByTagName("item");
        Node root = nList.item(0);
        Element firstElement = (Element) root;
        title=firstElement.getElementsByTagName("title").item(0).getTextContent();
        System.out.println("Number of Items: " + nList.getLength());
        System.out.println(title);
        link=firstElement.getElementsByTagName("link").item(0).getTextContent();
        System.out.println(link);
       }
       catch(Exception e)
       {
        System.out.println("exception found"+e);
       }
       return title;
    }
}


