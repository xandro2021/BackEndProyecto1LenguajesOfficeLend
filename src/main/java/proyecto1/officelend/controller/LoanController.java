package proyecto1.officelend.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import proyecto1.officelend.entity.Loan;
import proyecto1.officelend.service.LoanService;

@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Loans", description = "API for managing loans")
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all loans", description = "Returns a list of loans")
    public List<Loan> get() {
        return loanService.getLoans();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get a loan by ID", description = "Searches for a loan by its ID")
    public Loan getById(@PathVariable int id) {
        return loanService.getLoanById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Create a new loan", description = "Adds a new loan")
    public Loan add(@RequestBody Loan loan) {
        return loanService.registerLoan(loan);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a loan", description = "Modifies an existing loan")
    public Loan update(@PathVariable int id, @RequestBody Loan loan) {
        return loanService.updateLoan(id, loan);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a loan", description = "Removes a loan from the database")
    public void delete(@PathVariable int id) {
        loanService.deleteLoan(id);
    }
}
