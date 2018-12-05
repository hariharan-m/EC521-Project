package ec521.team4;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.Collection;

@Results({
        @Result(name="success", type="redirectAction", params = {"actionName" , "records"})
})
public class RecordsController implements ModelDriven<Object> {
    private Object model;
    private String name;
    private RecordsService recordsService = new RecordsService();

    public Object getModel() {
        return model;
    }

    public HttpHeaders show() {
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders index() {
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders editNew() {
        model = new Record(null, null);
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        Record newRecord = (Record)model;
        recordsService.addUserRecord(newRecord.getName(), newRecord);
        return new DefaultHttpHeaders("success").setLocation(newRecord.getName());
    }

    public Collection<String> getAllCustomers() {
        return recordsService.getUsers();
    }

    public String getName() {
        return name;
    }

    public void setName(Object value) {
        this.name = value.toString();
        model = recordsService.get(this.name);
    }

}
