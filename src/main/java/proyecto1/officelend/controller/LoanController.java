package proyecto1.officelend.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import proyecto1.officelend.entity.Loan;
import proyecto1.officelend.service.LoanService;

@CrossOrigin(origins = "*")
@Tag(name = "Loans", description = "API for managing loans")
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    @Operation(summary = "Get all loans", description = "Returns a list of loans")
    public List<Loan> get() {
        return loanService.getLoans();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a loan by ID", description = "Searches for a loan by its ID")
    public Loan getById(@PathVariable int id) {
        return loanService.getLoanById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Create a new loan", description = "Adds a new loan")
    public Loan add(@RequestBody Loan loan) {
        return loanService.registerLoan(loan);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a loan", description = "Modifies an existing loan")
    public Loan update(@PathVariable int id, @RequestBody Loan loan) {
        return loanService.updateLoan(id, loan);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a loan", description = "Removes a loan from the database")
    public void delete(@PathVariable int id) {
        loanService.deleteLoan(id);
    }
}
