package intervall_timer_backend.Repository;

import intervall_timer_backend.DataAcessModel.ExerciseDA;
import org.springframework.data.repository.CrudRepository;

public interface IExerciseRepository extends CrudRepository<ExerciseDA, Long> {
}
