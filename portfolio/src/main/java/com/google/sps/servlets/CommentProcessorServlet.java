package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.sps.data.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/** 
* Servlet that processes comment. TODO: Improve formatting of response output
* doPost requests the parameter value from "comment-input" and appends it to commentList and redirects back to the same page
* doGET converts the commentList to json and writes it to the response
*/
@WebServlet("/comment")
public final class CommentProcessorServlet extends HttpServlet {

  private final Gson gson = new Gson();

  // Datastore stuff
  DatastoreService datastore =  DatastoreServiceFactory.getDatastoreService();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String comment = request.getParameter("comment-input");

    if (comment != null && !comment.isEmpty()) {
        Entity commentEntity = new Entity("Comment");
        commentEntity.setProperty("text", comment);
        commentEntity.setProperty("timestamp", System.currentTimeMillis());
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(commentEntity);
    }
    response.sendRedirect("/");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      long id = entity.getKey().getId();
      String text = (String) entity.getProperty("text");
      long timestamp = (long) entity.getProperty("timestamp");

      Comment comment = new Comment(id, text, timestamp);
      comments.add(comment);
    }
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));
  }
}
