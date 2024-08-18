package com.example.intervalltimerfrontend.Service;


import com.example.intervalltimerfrontend.Model.Exercise;
import com.example.intervalltimerfrontend.Service.Response.ServiceResponse;

public interface ExerciseService {
    static String baseServiceURL = "exercise/";
    static String getAllURL = baseServiceURL + "getAll";
    static String saveExerciseURL = baseServiceURL + "newExercise";
    static String deleteExerciseURL = baseServiceURL + "delete";


    ServiceResponse<Exercise> getAll();


    ServiceResponse<Exercise> save( Exercise exercise);


    ServiceResponse<Exercise> delete( long id);
}
