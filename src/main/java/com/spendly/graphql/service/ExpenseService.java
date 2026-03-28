package com.spendly.graphql.service;

import com.spendly.graphql.model.Expense;
import com.spendly.graphql.model.ExpenseSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ExpenseService {

    private final WebClient.Builder webClientBuilder;

    public ExpenseService(WebClient.Builder builder) {
        this.webClientBuilder = builder;
    }

    private WebClient client(String token) {
        return webClientBuilder
                .baseUrl("http://localhost:8080/api/expenses")
                .defaultHeader("Authorization", token)
                .build();
    }

    public Flux<Expense> getAll(String token) {
        return client(token).get()
                .retrieve()
                .bodyToFlux(Expense.class);
    }

    public Mono<Expense> getById(Long id, String token) {
        return client(token).get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Expense.class);
    }

    public Mono<Expense> create(Expense expense, String token) {
        return client(token).post()
                .bodyValue(expense)
                .retrieve()
                .bodyToMono(Expense.class);
    }

    public Mono<Expense> update(Long id, Expense expense, String token) {
        return client(token).put()
                .uri("/{id}", id)
                .bodyValue(expense)
                .retrieve()
                .bodyToMono(Expense.class);
    }

    public Mono<Boolean> delete(Long id, String token) {
        return client(token).delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .map(response -> true);
    }

    public Flux<Expense> getFilteredExpenses(String category, String startDate, String endDate, String token) {

        return client(token).get()
                .uri(uriBuilder -> uriBuilder
                        .queryParamIfPresent("category", Optional.ofNullable(category))
                        .queryParamIfPresent("startDate", Optional.ofNullable(startDate))
                        .queryParamIfPresent("endDate", Optional.ofNullable(endDate))
                        .build())
                .retrieve()
                .bodyToFlux(Expense.class);
    }

    public Mono<ExpenseSummary> getSummary(String token) {
        return client(token).get()
                .uri("/summary")
                .retrieve()
                .bodyToMono(ExpenseSummary.class);
    }
}