import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.*;
import model.*;
import util.DBConnection;

public class Main {
        public static void main(String[] args) {
                try (Connection conn = DBConnection.getConnection()) {
                        CustomerDAO customerDAO = new CustomerDAO();
                        Customer customer = new Customer(
                                        0,
                                        "John Doe",
                                        "john@example.com",
                                        "1234567890");
                        customer.setAge(19);
                        customer.setAddress("123 Main Street");
                        customer.setDateOfBirth(LocalDate.of(2004, 1, 1));
                        customer.setRegistrationDate(LocalDate.of(2023, 1, 1));
                        int customerId = customerDAO.addCustomer(customer);
                        System.out.println("DEBUG customerId = " + customerId);
                        if (customerId <= 0) {
                                System.err.println(" Customer insert failed (id <= 0). Aborting.");
                                return;
                        }
                        PolicyTypeDAO policyTypeDAO = new PolicyTypeDAO();
                        PolicyType policyType = new PolicyType(
                                        0,
                                        "Life Insurance",
                                        "Provides coverage for death benefit and savings",
                                        1000);
                        int policyTypeId = policyTypeDAO.addPolicyType(policyType);
                        System.out.println(policyTypeId);
                        // 3. Insert Policy
                        PolicyDAO policyDAO = new PolicyDAO();
                        Policy policy = new Policy(
                                        0,
                                        customerId,
                                        policyType.getPolicyName(), 
                                        Date.valueOf("2023-01-01"),
                                        Date.valueOf("2024-01-01"),
                                        12000.0,
                                        "Active");
                        int policyId = policyDAO.addPolicy(policy);
                        
                        System.out.println("DEBUG policyId = " + policyId);
                        if (policyId <= 0) {
                                System.err.println(" Policy insert failed (id <= 0). Aborting.");
                                return;
                        }
                        CoverageDAO coverageDAO = new CoverageDAO();
                        Coverage coverage = new Coverage(
                                        0,
                                        policyId,
                                        "Accidental Coverage",
                                        500000.0,
                                        "Full Coverage");
                        coverageDAO.insertCoverage(coverage);
                        PaymentDAO paymentDAO = new PaymentDAO();
                        Payment payment = new Payment(
                                        0,
                                        policyId,
                                        Date.valueOf("2023-01-02"),
                                        1000.0,
                                        "Online",
                                        "TXN12345678");
                        paymentDAO.addPayment(payment);
                        ClaimDAO claimDAO = new ClaimDAO();
                        Claim claim = new Claim(
                                        0,
                                        policyId,
                                        LocalDate.of(2023, 1, 1),
                                        LocalDate.of(2023, 1, 5),
                                        LocalDate.of(2023, 1, 10),
                                        LocalDate.of(2023, 1, 15),
                                        50000.0,
                                        45000.0,
                                        "Approved",
                                        "Accident");
                        claimDAO.addClaim(claim);
                        EndorsementDAO endorsementDAO = new EndorsementDAO();
                        Endorsement endorsement = new Endorsement(
                                        0,
                                        policyId,
                                        "coverage_amount",
                                        "increase",
                                        50000.00,
                                        75000.00,
                                        "Increased coverage amount after request",
                                        Date.valueOf("2023-09-15"));
                        endorsementDAO.insertEndorsement(endorsement);

                        CancellationDAO cancellationDAO = new CancellationDAO();
                        Cancellation cancellation = new Cancellation(
                                        0,
                                        policyId,
                                        Date.valueOf("2023-03-01"),
                                        "Customer Request");
                        cancellationDAO.insertCancellation(cancellation);

                        RenewalDAO renewalDAO = new RenewalDAO(conn);
                        Renewal renewal = new Renewal(
                                        0,
                                        policyId,
                                        Date.valueOf("2024-01-01"),
                                        12000.0,
                                        Date.valueOf("2025-01-01"),
                                        7,
                                        3000);
                        renewalDAO.addRenewal(renewal);

                        RiskAssessmentDAO riskDAO = new RiskAssessmentDAO();
                        RiskAssessment risk = new RiskAssessment(
                                        0,
                                        customerId,
                                        "Low",
                                        "Healthy lifestyle, non-smoker");
                        riskDAO.addRiskAssessment(risk);

                        System.out.println(" All test inserts completed successfully!");
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
}
