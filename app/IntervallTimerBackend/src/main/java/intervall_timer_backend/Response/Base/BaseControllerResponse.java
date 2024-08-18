package intervall_timer_backend.Response.Base;

import intervall_timer_backend.Response.ResponseType;

import java.util.List;

public class BaseControllerResponse<T> {
    public ResponseType responseType = ResponseType.INITIALIZED;
    public String message = null;
    public T singleResponse = null;
    public List<T> listResponse = null;
}
