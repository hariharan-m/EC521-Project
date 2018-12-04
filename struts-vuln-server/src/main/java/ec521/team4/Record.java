package ec521.team4;

import java.math.BigDecimal;

public class Record {
    private String name;
    private BigDecimal order;

    public Record(String name, BigDecimal order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getOrder() {
        return order;
    }
}
