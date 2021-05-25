package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.mealDao.MealBaseDao;
import ru.javawebinar.topjava.mealDao.MealDaoInMem;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MealServlet extends HttpServlet {
    private MealBaseDao mealBaseDao;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    public MealServlet() {
        mealBaseDao = new MealDaoInMem();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String forward = "";

        if (action != null) {
            int id = Integer.parseInt(request.getParameter("mealId"));
            switch (action) {
                case "delete":
                    mealBaseDao.delete(id);
                    forward = LIST_MEALS;
                    break;
                case "edit":
                    Meal meal = mealBaseDao.getById(id);
                    request.setAttribute("meal", meal);
                    forward = INSERT_OR_EDIT;
                    break;
            }
        } else {
            forward = LIST_MEALS;
        }

        request.setAttribute("meals", MealsUtil.getMealToList());
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
