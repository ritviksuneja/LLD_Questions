package com.example;

// CostExplorer:

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

import com.sun.jdi.Locatable;

//     -subscriptions: List<Subscription>
//     +addSubscription(Subscription): void
//     -createMonthlyReport(): void
//     -createYearlyReport(): void
//     +createCostReport(): CostReport

public class CostExplorer {
    private Map<String, List<Subscription>> customerSubscriptions;
    private Map<String, Customer> customerMap;

    public CostExplorer(){
        this.customerSubscriptions = new HashMap<>();
        this.customerMap = new HashMap<>();
    }

    public void addCustomer(Customer c){
        if(customerMap.containsKey(c.getId())){
            throw new DuplicateCustomerException("The customer with id '" + c.getId() + "'already exist.");
        }

        customerMap.put(c.getId(), c);
    }

    public void addSubscription(String customerId, Subscription subs){
        if(customerSubscriptions.containsKey(customerId) && customerSubscriptions.get(customerId).contains(subs)){
            throw new DuplicateSubscriptionException("The subscription already exists for the customer");
        }

        if(!customerSubscriptions.containsKey(customerId)){
            customerSubscriptions.put(customerId, new ArrayList<>());
        }

        customerSubscriptions.get(customerId).add(subs);
    }

    public CostReport getCostReport(LocalDate date, String customerId){
        List<Subscription> subscriptions = customerSubscriptions.get(customerId);

        for(int m = 1; m <= 12; m++){
            Double monthlyPrice = 0.0;

            for(Subscription sub : subscriptions){
                monthlyPrice += calculateMonthlyPrice(sub, date, m);
            }
        }
    }

    private Double calculateMonthlyPrice(Subscription sub, LocalDate date, int month){
        LocalDate endDate = sub.getEndDate();
        if(endDate == null || endDate.getYear() > date.getYear()){
            endDate = LocalDate.of(date.getYear(), 12, 31);
        }

        LocalDate startDate = sub.getStartDate();
        if(startDate.getYear() < date.getYear()){
            startDate = LocalDate.of(date.getYear(), 1, 1);
        }

        if(startDate.isAfter(date) || endDate.isBefore(date)){
            return 0.0;
        }

        YearMonth currMonth = YearMonth.of(date.getYear(), month);

        Double dailyPrice = sub.getPlan().getMonthlyPrice() / currMonth.lengthOfMonth();

        //let's calculate #active days per month
        //start date's ym is same as the date's ym
        //end date's ym is same as the date's ym
        //int activeDays = currMonth.lengthOfMonth();
        int activeDays;
        int totalDays;

        if(currMonth.getMonthValue() < date.getMonthValue()){
            return 0.0;
        }

        //startdate = 12, date = 13
        if(startDate.getMonthValue() == date.getMonthValue()){
            if(endDate.getMonthValue() == date.getMonthValue()){
                activeDays = endDate.getDayOfMonth() - date.getDayOfMonth() + 1;
                totalDays = endDate.getDayOfMonth() - startDate.getDayOfMonth() + 1;
                return dailyPrice * activeDays;
            }
            activeDays = currMonth.lengthOfMonth() - date.getDayOfMonth() + 1;
            return dailyPrice * activeDays;
        }

        if(endDate.getMonthValue() == date.getMonthValue()){
            activeDays = endDate.getDayOfMonth() - date.getDayOfMonth() + 1;
            return dailyPrice * activeDays;
        }

        return sub.getPlan().getMonthlyPrice();
    }
}
