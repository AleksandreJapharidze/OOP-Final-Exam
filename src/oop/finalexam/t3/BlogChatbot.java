package oop.finalexam.t3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BlogChatbot {
    private static final String BASE_URL = "https://max.ge/final/t3/38491627/index.php";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Pattern QUOTE_PATTERN = Pattern.compile("\"");

    public static void main(String[] args) {
        System.out.println("Welcome to BlogBot!");
        runMainLoop();
        SCANNER.close();
    }

    private static void runMainLoop() {
        while (true) {
            displayMenu();
            String choice = SCANNER.nextLine().trim();

            switch (choice) {
                case "1" -> getAllBlogs();
                case "2" -> createBlog();
                case "3" -> viewStats();
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nChoose an action:");
        System.out.println("1. View all blog posts");
        System.out.println("2. Create a new blog post");
        System.out.println("3. View site statistics");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }

    private static void getAllBlogs() {
        try {
            HttpRequest request = createGetRequest(BASE_URL + "?api=blogs");
            HttpResponse<String> response = sendRequest(request);

            System.out.println("\n--- Blog Posts ---");
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("Error fetching blogs: " + e.getMessage());
        }
    }

    private static void createBlog() {
        try {
            System.out.print("Enter blog title: ");
            String title = SCANNER.nextLine();
            System.out.print("Enter blog content: ");
            String content = SCANNER.nextLine();
            System.out.print("Enter author name: ");
            String author = SCANNER.nextLine();

            String json = createBlogJson(title, content, author);
            HttpRequest request = createPostRequest(BASE_URL + "?api=blogs", json);
            HttpResponse<String> response = sendRequest(request);

            System.out.println("\n--- Server Response ---");
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("Error creating blog: " + e.getMessage());
        }
    }

    private static void viewStats() {
        try {
            HttpRequest request = createGetRequest(BASE_URL + "?api=stats");
            HttpResponse<String> response = sendRequest(request);

            System.out.println("\n--- Site Statistics ---");
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("Error fetching statistics: " + e.getMessage());
        }
    }

    private static HttpRequest createGetRequest(String url) throws Exception {
        return HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
    }

    private static HttpRequest createPostRequest(String url, String jsonBody) throws Exception {
        return HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
    }

    private static HttpResponse<String> sendRequest(HttpRequest request) throws Exception {
        return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static String createBlogJson(String title, String content, String author) {
        return String.format(
                "{\"title\":\"%s\", \"content\":\"%s\", \"author\":\"%s\"}",
                escapeJson(title), escapeJson(content), escapeJson(author)
        );
    }

    private static String escapeJson(String text) {
        return QUOTE_PATTERN.matcher(text).replaceAll("\\\\\"");
    }
}