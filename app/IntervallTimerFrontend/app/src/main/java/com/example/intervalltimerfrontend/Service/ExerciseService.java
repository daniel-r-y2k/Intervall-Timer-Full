package com.example.intervalltimerfrontend.Service;


import com.example.intervalltimerfrontend.Model.Exercise;
import com.example.intervalltimerfrontend.Service.Response.ServiceResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ExerciseService {
    static String baseServiceURL = "exercise/";
    static String getAllURL = baseServiceURL + "getAll";
    static String saveExerciseURL = baseServiceURL + "newExercise";
    static String deleteExerciseURL = baseServiceURL + "delete";



    @GET(getAllURL)
    ServiceResponse<Exercise> getAll();

    @POST(saveExerciseURL)
    ServiceResponse<Exercise> save( Exercise exercise);

    @POST(deleteExerciseURL)
    ServiceResponse<Exercise> delete( long id);
}
