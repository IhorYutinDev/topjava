package ru.javawebinar.topjava.mealDao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealRepository {
    boolean delete(int id);
    List<MealTo> getAllMealTo();
    void add(Meal meal);
    boolean update(int id, Meal meal);
    Meal getById(int id);
}
