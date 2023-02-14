package com.jeanfrancoisron.budget.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ExpenseDto {
    private Long id;

    @NotBlank(message = "Please describe the expense")
    private String expenseName;
    @NotNull(message = "Amount cannot be blank")
    @DecimalMin(value = "0.01")
    private BigDecimal amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Transaction date cannot be in the future")
    private LocalDate transactionDate;
}
