package com.example.interceptormiddleware02.interceptors;

import com.example.interceptormiddleware02.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {
    private final List<Month> months;

    public MonthInterceptor() {
        months = new ArrayList<>();
        months.add(new Month(1, "January", "Gennaio", "Januar"));
        months.add(new Month(2, "February", "Febbraio", "Februar"));
        months.add(new Month(3, "March", "Marzo", "März"));
        months.add(new Month(4, "April", "Aprile", "April"));
        months.add(new Month(5, "May", "Maggio", "Mai"));
        months.add(new Month(6, "June", "Giugno", "Juni"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURL().toString().contains("/root")){
            return true;
        }
        String monthNumberHeader = request.getHeader("monthNumber");
        if (monthNumberHeader == null || monthNumberHeader.isEmpty()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "monthNumber header is missing or empty");
        }

        assert monthNumberHeader != null;
        int monthNumber = Integer.parseInt(monthNumberHeader);
        Optional<Month> optionalMonth = months.stream().filter(month -> month.getMonthNumber() == monthNumber).findFirst();

        if (optionalMonth.isPresent()) {
            request.setAttribute("month", optionalMonth.get());
        }else {
            Month emptyMonth = new Month();
            emptyMonth.setEnglishName("nope");
            emptyMonth.setItalianName("nope");
            emptyMonth.setGermanName("nope");
            request.setAttribute("month", emptyMonth);
        }

        return true;
    }
}
