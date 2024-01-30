package it.unicam.cs.ids.localplatform.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("localhost:8080")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the value of the text input from the form
        String userInput = request.getParameter("textInput");

        // Your Java logic with the user input
        // For now, let's just print it to the console
        System.out.println("User input: " + userInput);

        // You can send a response back to the client if needed
        response.getWriter().write("User input received: " + userInput);
    }
}
