package ec521.team4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordsService {
    private Map<String, ArrayList<Record>> userRecords = new HashMap<>();

    public RecordsService(){
        ArrayList<Record> records1 = new ArrayList<>();
        records1.add(new Record("Spenser", new BigDecimal(110.01)));
        records1.add(new Record("Spenser", new BigDecimal(42)));
        records1.add(new Record("Spenser", new BigDecimal(999.99)));
        records1.add(new Record("Spenser", new BigDecimal(19.99)));

        userRecords.put("Spenser", records1);

        ArrayList<Record> records2 = new ArrayList<>();
        records2.add(new Record("Hackerman", new BigDecimal(.01)));
        records2.add(new Record("Hackerman", new BigDecimal(.1)));
        records2.add(new Record("Hackerman", new BigDecimal(1)));
        records2.add(new Record("Hackerman", new BigDecimal(10)));
        records2.add(new Record("Hackerman", new BigDecimal(100)));
        records2.add(new Record("Hackerman", new BigDecimal(1000)));

        userRecords.put("Hackerman", records2);
    }

    public void addUserRecord(String user, Record record) {
        if (userRecords.containsKey(user))
            userRecords.get(user).add(record);
        else {
            userRecords.put(user, new ArrayList<Record>());
            userRecords.get(user).add(record);
        }
    }

    public List<Record> get(String user) {
        return new ArrayList(userRecords.get(user));
    }

    public List<Record> getAll() {
        ArrayList<Record> allRecords = new ArrayList<>();
        for (ArrayList<Record> value: userRecords.values()) {
            allRecords.addAll(value);
        }
        return allRecords;
    }
}
