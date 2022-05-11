package com.sora.n4bank.repository;

import com.sora.n4bank.model.RegisterLoan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegisterLoanDAO {
    List<RegisterLoan> getList(int status);

    RegisterLoan get(UUID loanId);

    void insert(RegisterLoan registerLoan);

    void update(RegisterLoan registerLoan);

    void updateStatus(UUID loanId, int status);

    void updatePayInterest(RegisterLoan registerLoan);

    void delete(RegisterLoan registerLoan);
}
