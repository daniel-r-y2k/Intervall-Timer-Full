package intervall_timer_backend.Logic;

import intervall_timer_backend.DataAcessModel.ExerciseDA;
import intervall_timer_backend.DataTransferModel.ExerciseDT;
import intervall_timer_backend.Logic.Base.IBaseLogic;
import intervall_timer_backend.Repository.IExerciseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseLogic implements IBaseLogic<ExerciseDT> {

    @Autowired
    private IExerciseRepository exerciseRepository;

    @PersistenceContext
    private EntityManager em;

    public ExerciseLogic(IExerciseRepository exerciseRepository, EntityManager em) {
        this.exerciseRepository = exerciseRepository;
        this.em = em;
    }

    /**
     * Converts an Iterable-List of Exercises from the DB to a List of ExerciseDT´s
     * @param allExercises Iterable-List of Exercises from the DB
     * @return List<ExerciseDT>
     * */
    private List<ExerciseDT> convertExercisesToDTList(Iterable<ExerciseDA> allExercises) {
        List<ExerciseDT> result = new ArrayList<>();
        for (ExerciseDA exercise: allExercises) {
            ExerciseDT exerciseDT = this.convertExerciseToDT(exercise);
            result.add(exerciseDT);
        }
        return result;
    }
    /**
     * Converts the Exercise object from the Database to an ExerciseDT
     * @param exercise Exercise Object from the database
     * @return ExerciseDT
     * */
    private ExerciseDT convertExerciseToDT(ExerciseDA exercise) {
        return new ExerciseDT(
                exercise.getId(),
                exercise.getName(),
                exercise.getRepCount(),
                exercise.getLapTime(),
                exercise.getLapBreakTime(),
                exercise.getStartCountdown()
        );
    }

    /**
     * Converts an ExerciseDT to the Exercise object from the DB
     * @param exerciseDT ExerciseDT
     * @return Exercise
     * */
    private ExerciseDA convertDTToExercise(ExerciseDT exerciseDT) {
        ExerciseDA dataAccessExercise = new ExerciseDA();
        dataAccessExercise.setId(exerciseDT.getId());
        dataAccessExercise.setName(exerciseDT.getName());
        dataAccessExercise.setRepCount(exerciseDT.getRepCount());
        dataAccessExercise.setLapTime(exerciseDT.getLapTime());
        dataAccessExercise.setLapBreakTime(exerciseDT.getLapBreakTime());
        dataAccessExercise.setStartCountdown(exerciseDT.getStartCountdown());
        return dataAccessExercise;
    }

    public ExerciseDT get(long id) {
        ExerciseDT response = new ExerciseDT();
        java.util.Optional<ExerciseDA> exercise = exerciseRepository.findById(id);
        if(exercise.isPresent()) {
            response = this.convertExerciseToDT(exercise.get());
        }
        return response;
    }

    public List<ExerciseDT> getAll() {
        Iterable<ExerciseDA> allExercises = this.exerciseRepository.findAll();
        return this.convertExercisesToDTList(allExercises);
    }


    public ExerciseDT save(ExerciseDT exerciseDT) {
        ExerciseDA exercise = convertDTToExercise(exerciseDT);
        ExerciseDA exerciseFromDB = this.exerciseRepository.save(exercise);
        return this.convertExerciseToDT(exerciseFromDB);
    }

    public void delete(long id) {
        this.exerciseRepository.deleteById(id);
    }
}
