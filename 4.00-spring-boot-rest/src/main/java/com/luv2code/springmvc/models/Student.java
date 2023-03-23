package com.luv2code.springmvc.models;

import org.springframework.stereotype.Component;

@Component
public interface Student {
   String studentInformation();
   String getFullName();
}