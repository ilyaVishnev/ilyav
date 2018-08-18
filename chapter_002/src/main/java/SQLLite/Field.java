package SQLLite;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "field")
public class Field {
    private int value;

    public Field() {
    }

    public Field(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
