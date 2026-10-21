package com.codevictims.bayt.maintenance.service;
import org.springframework.stereotype.Service;
@Service
public class AiAssistant {
 public record Suggestion(String summary, String category) {}
 public record Result(String status, Suggestion suggestion) {}
 public boolean available() { return false; }
 public Result suggest(String description, String language) { return new Result("UNAVAILABLE", null); }
}
