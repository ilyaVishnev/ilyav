package SQLLite;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Logger;

public class ConvertXSQT {

    StoreXML storeXML = new StoreXML();
    private static Logger log1 = Logger.getLogger(ConvertXSQT.class.getName());

    public void convert(File source, File dest, File scheme) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(scheme);
            Transformer transformer = transformerFactory.newTransformer(xslt);
            Source origin = new StreamSource(source);
            transformer.transform(origin, new StreamResult(dest));
        } catch (TransformerException ex) {
            log1.info(ex.getMessage());
        }
    }

    public int getSum(File second) {
        int sum = 0;
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new FileInputStream(second));
            org.jdom.Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.getChildren("entry");
            for (org.jdom.Element element : elements) {
                sum += Integer.parseInt(element.getAttributeValue("field"));
            }

        } catch (Exception ex) {
            log1.info(ex.getMessage());
        }
        return sum;
    }
}
