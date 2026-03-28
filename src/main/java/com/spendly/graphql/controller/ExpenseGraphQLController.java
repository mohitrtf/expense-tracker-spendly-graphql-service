package com.spendly.graphql.controller;

import com.spendly.graphql.model.Expense;
import com.spendly.graphql.model.ExpenseInput;
import com.spendly.graphql.model.ExpenseSummary;
import com.spendly.graphql.service.ExpenseService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import graphql.schema.DataFetchingEnvironment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ExpenseGraphQLController {

    private final ExpenseService service;

    public ExpenseGraphQLController(ExpenseService service) {
        this.service = service;
    }

    private String getAuthToken(DataFetchingEnvironment env) {
        // Header key is case-insensitive
        return env.getGraphQlContext()
                .get("Authorization");
    }

    @QueryMapping
    public Flux<Expense> expenses(
            @Argument String category,
            @Argument String startDate,
            @Argument String endDate,
            DataFetchingEnvironment env) {

        String token = env.getGraphQlContext().get("Authorization");

        return service.getFilteredExpenses(category, startDate, endDate, token);
    }

    @QueryMapping
    public Mono<Expense> expenseById(@Argument Long id, DataFetchingEnvironment env) {
        String token = getAuthToken(env);
        return service.getById(id, token);
    }

    @MutationMapping
    public Mono<Expense> createExpense(@Argument ExpenseInput input, DataFetchingEnvironment env) {
        String token = getAuthToken(env);
        return service.create(map(input), token);
    }

    @MutationMapping
    public Mono<Expense> updateExpense(@Argument Long id, @Argument ExpenseInput input, DataFetchingEnvironment env) {
        String token = getAuthToken(env);
        return service.update(id, map(input), token);
    }

    @MutationMapping
    public Mono<Boolean> deleteExpense(@Argument Long id, DataFetchingEnvironment env) {
        String token = getAuthToken(env);
        return service.delete(id, token);
    }

    @QueryMapping
    public Mono<ExpenseSummary> expenseSummary(DataFetchingEnvironment env) {
        String token = env.getGraphQlContext().get("Authorization");
        return service.getSummary(token);
    }

    private Expense map(ExpenseInput input) {
        Expense e = new Expense();
        e.setTitle(input.getTitle());
        e.setDescription(input.getDescription());
        e.setAmount(input.getAmount());
        e.setDate(input.getDate());
        e.setCategory(input.getCategory());
        e.setPaymentMethod(input.getPaymentMethod());
        return e;
    }


}