package ec521.team4;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({
        @Result(name="success", type="redirectAction", params = {"actionName" , "records"})
})
public class IndexController {
    @Action("/")
    public String index() {
        return "success";
    }
}
