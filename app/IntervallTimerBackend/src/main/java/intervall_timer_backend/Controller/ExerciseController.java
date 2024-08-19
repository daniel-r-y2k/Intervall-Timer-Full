package intervall_timer_backend.Controller;

import intervall_timer_backend.Controller.Base.IBaseController;
import intervall_timer_backend.DataTransferModel.ExerciseDT;
import intervall_timer_backend.Logic.ExerciseLogic;
import intervall_timer_backend.Response.Base.BaseControllerResponse;
import intervall_timer_backend.Response.ResponseType;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "exercise")
public class ExerciseController implements IBaseController<ExerciseDT> {
    private final ExerciseLogic exerciseLogic;

    @Autowired
    public ExerciseController(ExerciseLogic exerciseLogic) {
        this.exerciseLogic = exerciseLogic;
    }

    @PostMapping("get/{id}")
    public BaseControllerResponse<ExerciseDT> get(@PathParam("id") long id) {
        BaseControllerResponse<ExerciseDT> response = new BaseControllerResponse<>();
        try{
            response.singleResponse = this.exerciseLogic.get(id);
        }
        catch(Exception e) {
            response.message = e.getMessage();
            response.responseType = ResponseType.FAIL;
        }
        finally{
            if(response.responseType == ResponseType.INITIALIZED) {
                response.responseType = ResponseType.SUCCESSFUL;
                response.message = "Erfolgreich einzelne Übung geladen";
            }
        }
        return response;
    }

    @GetMapping("getAll")
    public BaseControllerResponse<ExerciseDT> getAll() {
        BaseControllerResponse<ExerciseDT> response = new BaseControllerResponse<>();
        try{
            response.listResponse = this.exerciseLogic.getAll();
        }
        catch(Exception e) {
            response.message = e.getMessage();
            response.responseType = ResponseType.FAIL;
        }
        finally{
            if(response.responseType == ResponseType.INITIALIZED) {
                response.responseType = ResponseType.SUCCESSFUL;
                response.message = "Erfolgreich Liste von Übungen geladen";
            }
        }
        return response;
    }

    @PostMapping("newExercise")
    public BaseControllerResponse<ExerciseDT> save(@RequestBody ExerciseDT exerciseDT) {
        BaseControllerResponse<ExerciseDT> response = new BaseControllerResponse<>();
        try{
            response.singleResponse = this.exerciseLogic.save(exerciseDT);
        }
        catch(Exception e) {
            response.message = e.getMessage();
            response.responseType = ResponseType.FAIL;
        }
        finally{
            if(response.responseType == ResponseType.INITIALIZED) {
                response.responseType = ResponseType.SUCCESSFUL;
                response.message = "Erfolgreich gespeichert";
            }
        }
        return response;
    }

    @PostMapping("delete")
    public BaseControllerResponse<ExerciseDT> delete(@RequestBody long id) {
        BaseControllerResponse<ExerciseDT> response = new BaseControllerResponse<>();
        try{
            this.exerciseLogic.delete(id);
        }
        catch(Exception e) {
            response.message = e.getMessage();
            response.responseType = ResponseType.FAIL;
        }
        finally{
            if(response.responseType == ResponseType.INITIALIZED) {
                response.responseType = ResponseType.SUCCESSFUL;
                response.message = "Erfolgreich gelöscht";
            }
        }
        return response;
    }
}
