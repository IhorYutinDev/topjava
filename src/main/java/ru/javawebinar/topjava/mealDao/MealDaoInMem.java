package ru.javawebinar.topjava.mealDao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMem implements MealBaseDao {
    private static final AtomicInteger counter = new AtomicInteger(MealsUtil.meals.size());

    @Override
    public boolean delete(int id) {
        return MealsUtil.meals.removeIf(meal -> meal.getId() == id);
    }

    @Override
    public List<MealTo> getAllMealTo() {
        return MealsUtil.getMealToList();
    }

    @Override
    public void add(Meal meal) {
        meal.setId(counter.incrementAndGet());
        MealsUtil.meals.add(meal);
    }

    @Override
    public boolean update(int id, Meal meal) {
        Meal mealToBeUpdated = getById(id);
        if (mealToBeUpdated == null) {
            return false;
        }
        mealToBeUpdated.setDescription(meal.getDescription());
        mealToBeUpdated.setDateTime(meal.getDateTime());
        mealToBeUpdated.setCalories(meal.getCalories());
        return true;
    }

    @Override
    public Meal getById(int id) {
        return MealsUtil.meals.stream().filter(meal -> meal.getId() == id).findAny().orElse(null);
    }
}
