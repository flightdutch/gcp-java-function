package com.example;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;
import java.io.IOException;

public class App implements HttpFunction {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        BufferedWriter writer = response.getWriter();
        writer.write("Hello from Java App deployed via GitHub Actions to GCP Cloud Run Functions!");
    }
}
