package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String operation = request.getParameter("operation");

        double result = 0;

        try {
            double number1 = Double.parseDouble(num1);
            double number2 = Double.parseDouble(num2);

            switch (operation) {
                case "add":
                    result = number1 + number2;
                    break;
                case "subtract":
                    result = number1 - number2;
                    break;
                case "multiply":
                    result = number1 * number2;
                    break;
                case "divide":
                    if (number2 != 0) {
                        result = number1 / number2;
                    } else {
                        displayError(pw, "Error: Division by zero is not allowed.");
                        return;
                    }
                    break;
                default:
                    displayError(pw, "Error: Invalid operation.");
                    return;
            }
        } catch (NumberFormatException e) {
            displayError(pw, "Error: Invalid input. Please enter valid numbers.");
            return;
        }

        pw.print("<html>");
        pw.print("<head>");
        pw.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        pw.print("<style>");
        pw.print(".result-container {");
        pw.print("    background: linear-gradient(135deg, #71b7e6, #9b59b6);");
        pw.print("    padding: 60px;");
        pw.print("    border-radius: 20px;");
        pw.print("    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.3);");
        pw.print("    text-align: center;");
        pw.print("    color: white;");
        pw.print("    max-width: 600px;");
        pw.print("}");
        pw.print(".result-text {");
        pw.print("    font-size: 5em;");
        pw.print("    font-weight: bold;");
        pw.print("    animation: fadeIn 1.5s ease-in-out;");
        pw.print("}");
        pw.print("@keyframes fadeIn {");
        pw.print("    from { opacity: 0; transform: translateY(-20px); }");
        pw.print("    to { opacity: 1; transform: translateY(0); }");
        pw.print("}");
        pw.print("</style>");
        pw.print("</head>");
        pw.print("<body>");
        pw.print("<div class='container mt-5 d-flex justify-content-center align-items-center' style='height: 100vh;'>");
        pw.print("<div class='result-container'>");
        pw.print("<h1>Calculator Result</h1>");
        pw.print("<p class='result-text'>Result: " + result + "</p>");
        pw.print("</div>");
        pw.print("</div>");
        pw.print("<script src='https://code.jquery.com/jquery-3.5.1.slim.min.js'></script>");
        pw.print("<script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js'></script>");
        pw.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js'></script>");
        pw.print("</body>");
        pw.print("</html>");
    }

    private void displayError(PrintWriter pw, String message) {
        pw.print("<html><body>");
        pw.print("<div class='container mt-5 text-center'>");
        pw.print("<div class='alert alert-danger' role='alert'>");
        pw.print(message);
        pw.print("</div>");
        pw.print("</div>");
        pw.print("</body></html>");
    }
}
