package ec521.team4;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.Console;
import java.io.File;
import java.util.Collection;

@Results({
        @Result(name="success", type="redirectAction", params = {"actionName" , "records"}),
        @Result(name="uploadSuccess", type="redirectAction", params={"actionName", "records/create"})
})
public class RecordsController implements ModelDriven<Object> {
    private Object model;
    private String name;
    private RecordsService recordsService = new RecordsService();

    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;

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
        if (newRecord != null) {
            recordsService.addUserRecord(newRecord.getName(), newRecord);
            return new DefaultHttpHeaders("success").setLocation(newRecord.getName());
        }
        return new DefaultHttpHeaders("success");
    }

    public String execute() {
        if (this.fileUpload != null) {
            System.out.println("*** GOT A FILE ***\n");
            System.out.println(fileUpload.getName());
        }
        return "uploadSuccess";
    }

    public String upload() {
        return "upload";
    }

    public File getFileUpload(File fileUpload) {
        System.out.println("***Getting fileUpload***\n");
        return this.fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        System.out.println("***Setting fileUpload***\n");
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        System.out.println("***Getting fileUploadContentType***\n");
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        System.out.println("***Setting fileUploadContentType***\n");
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        System.out.println("***Getting fileUploadName***\n");
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        System.out.println(String.format("***Setting fileUploadName to %s***\n", fileUploadFileName));
        this.fileUploadFileName = fileUploadFileName;
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
