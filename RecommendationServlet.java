package com.jobRecommendation.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobRecommendation.job.entity.Item;
import com.jobRecommendation.job.entity.ResultResponse;
import com.jobRecommendation.job.recommendation.Recommendation;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecommendationServlet", urlPatterns = {"/recommendation"})
public class RecommendationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }
        String userId = request.getParameter("user_id");
        String category = request.getParameter("category");


        Recommendation recommendation = new Recommendation();
        List<Item> items = recommendation.recommendItems(userId, category);
        mapper.writeValue(response.getWriter(), items);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
