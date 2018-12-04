package ec521.team4;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.Collection;

public class RecordsController implements ModelDriven<Object> {
    private Object model;
    private String name;
    private Collection<Record> allRecords;
    private RecordsService recordsService = new RecordsService();

    public Object getModel() {
        return  model;
    }

    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders index() {
        model = recordsService.getAll();
        this.allRecords = recordsService.getAll();
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders create() {
        Record newRecord = (Record)model;
        recordsService.addUserRecord(newRecord.getName(), newRecord);
        return new DefaultHttpHeaders("success").setLocation(newRecord.getName());
    }

    public String getName() {
        return name;
    }

}
