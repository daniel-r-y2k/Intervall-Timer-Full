package intervall_timer_backend.Controller.Base;

import intervall_timer_backend.Response.Base.BaseControllerResponse;

public interface IBaseController<T> {
    BaseControllerResponse get(long id);
    BaseControllerResponse getAll();
    BaseControllerResponse save(T dto);
    BaseControllerResponse delete(long id);
}
