package intervall_timer_backend.Logic.Base;

import intervall_timer_backend.DataTransferModel.ExerciseDT;

import java.util.List;

public interface IBaseLogic<T> {
    T get(long id);
    List<T> getAll();
    T save(T DT);
    void delete(long id);
}
