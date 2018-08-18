package SQLLite;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "entries")
@XmlRootElement
public class Entries {

    StoreSQL storeSQL = new StoreSQL();
    private List<Field> entry = storeSQL.getListFields();

    public Entries() {
    }

    public Entries(List<Field> values) {
        this.entry = values;
    }

    @XmlElement(name = "entry")
    public List<Field> getEntry() {
        return entry;
    }

    public void setEntry(List<Field> values) {
        this.entry = values;
    }
}




