package SQLLite;

import javafx.application.Application;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreXML {
    private File target;
    private static Logger log1 = Logger.getLogger(StoreXML.class.getName());

    public StoreXML() {
    }

    public File getTarget() {
        return target;
    }

    public void setTarget(File target) {
        this.target = target;
    }

    public void save(List<Field> list) {
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Entries(), target);
        } catch (JAXBException exception) {
            log1.info("marshallExample threw JAXBException");
        }
    }
}
