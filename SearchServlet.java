package com.jobRecommendation.job.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobRecommendation.job.db.MySQLConnection;
import com.jobRecommendation.job.entity.Item;
import com.jobRecommendation.job.entity.ResultResponse;
import com.jobRecommendation.job.external.MuseClient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }

        String userId = request.getParameter("user_id");
        String category = request.getParameter("category");

        MySQLConnection connection = new MySQLConnection();
        Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);
        connection.close();

        MuseClient client = new MuseClient();
        List<Item> items = client.search(category);


        for (Item item : items) {
            item.setFavorite(favoritedItemIds.contains(item.getId()));
        }

        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), items);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

