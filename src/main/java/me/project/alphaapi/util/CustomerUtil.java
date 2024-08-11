package me.project.alphaapi.util;

import me.project.alphaapi.constants.Constant;
import me.project.alphaapi.entity.Customer;
import me.project.alphaapi.exception.NotFoundException;
import me.project.alphaapi.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerUtil.class);

    @Autowired
    private CustomerRepo customerRepo;

    public Customer getCurrentCustomer() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LOGGER.info("\n\nAuthentication: {}\n", authentication);

            if (authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();

                if (principal instanceof UserDetails) {
                    Optional<Customer> customer = customerRepo.findByEmail(((UserDetails) principal).getUsername());
                    return customer.orElseThrow(() -> new NotFoundException("Customer not found with the given username"));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error: {}", e.getMessage(), e);
        }

        return null;
    }

    public Boolean checkEmailForm(String email) {
        Pattern pattern = Pattern.compile(Constant.EMAIL_PATTERN);

        if (email == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
