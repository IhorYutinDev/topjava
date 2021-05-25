package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.mealDao.MealRepository;
import ru.javawebinar.topjava.mealDao.InMemoryMealRepository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealServlet extends HttpServlet {
    private MealRepository mealRepository;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    public MealServlet() {
        mealRepository = new InMemoryMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = "";

        if (action != null) {
            int id;
            switch (action) {
                case "delete":
                    id = Integer.parseInt(request.getParameter("mealId"));
                    mealRepository.delete(id);
                    forward = LIST_MEALS;
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("mealId"));
                    Meal meal = mealRepository.getById(id);
                    request.setAttribute("meal", meal);
                    forward = INSERT_OR_EDIT;
                    break;
                case "add":
                    forward = INSERT_OR_EDIT;
            }
        } else {
            forward = LIST_MEALS;
        }

        request.setAttribute("meals", MealsUtil.getMealToList());
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        meal.setDescription(request.getParameter("description"));

        String mealId = request.getParameter("mealId");

        if (mealId == null || mealId.isEmpty()) {
            meal.setDateTime(LocalDateTime.now());
            mealRepository.add(meal);
        } else {
            meal.setDateTime(LocalDateTime.parse(request.getParameter("date"), DateTimeFormatter
                                .ofPattern("yyyy-MM-dd HH:mm")));
            mealRepository.update(Integer.parseInt(mealId), meal);
        }
        String forward = LIST_MEALS;
        request.setAttribute("meals", MealsUtil.getMealToList());
        request.getRequestDispatcher(forward).forward(request, response);
    }
}
