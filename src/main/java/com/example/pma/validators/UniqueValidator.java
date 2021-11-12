package com.example.pma.validators;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Entered validation method..");
        Employee emp = empRepo.findByEmail(value);

        if(emp != null)
            return false;
        else
            return true;
    }


}
