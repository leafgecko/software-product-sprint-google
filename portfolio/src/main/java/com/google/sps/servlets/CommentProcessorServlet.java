package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
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

  private final List<String> commentList = new ArrayList<String>();
  private final Gson gson = new Gson(); 

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String comment = request.getParameter("comment-input");
    if (comment != null && !comment.isEmpty()) {
       commentList.add(comment); 
    }
    response.sendRedirect("/");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = gson.toJson(commentList);
    response.setContentType("text/html;");
    response.getWriter().println(json);
  }
}
