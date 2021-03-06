// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

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
* Servlet that returns the pokemonList
* DataServlet initializes pokemonList and gson
* init() appends hardcoded values to pokemonList
* doGet() converts pokemonList to json and writes it to the response
*/
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  private final List<String> pokemonList = new ArrayList<String>();
  private final Gson gson = new Gson(); 

  @Override
  public void init() {
      pokemonList.add("Totodile");
      pokemonList.add("Treecko");
      pokemonList.add("Lucario");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json = gson.toJson(pokemonList);
    response.setContentType("text/html;");
    response.getWriter().println(json);
  }
}
