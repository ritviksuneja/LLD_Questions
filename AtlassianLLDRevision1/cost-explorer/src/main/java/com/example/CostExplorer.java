package com.example;

// CostExplorer:

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

//     -customersToSubscriptionMap: Map<Customer, List<Subscription>>
//     +fetchCostReport(Customer customer)

public class CostExplorer {
    private List<Customer> customers;

    public CostExplorer(){
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public CostReport fetchCostReport(Customer customer, LocalDate date){
        //find the customer first
        //find the list of subscriptions
        //each subscription has a plan. so calculate monthly price for each month via a function and add all of them to the cost report.
        //calculate yearly price via a function and assign it to the costreport object.

        CostReport report = new CostReport();
        YearMonth month = YearMonth.of(date.getYear(), date.getMonthValue());

        int monthVal = month.getMonthValue();

        List<Subscription> subscriptions = customer.getSubscriptions();

        for(int i = 1; i <= 12; i++){
            double monthlyCost = 0.0;
            for(Subscription subscription : subscriptions){
                monthlyCost = monthlyCost + fetchMonthlyCost(subscription, YearMonth.of(month.getYear(), i), date);
            }
            report.addMonthlyCost(YearMonth.of(month.getYear(), i), monthlyCost); 
        }

        double yearlyCost = 0.0;
        for(Subscription subscription : subscriptions){
            yearlyCost = yearlyCost + fetchYearlyCost(subscription, date);
        }

        report.addYearlyCost(yearlyCost);
        
        return report;
    }

    private double fetchYearlyCost(Subscription subscription, LocalDate date) {
        Plan plan = subscription.getPlan();
        LocalDate startDate = subscription.getStartDate();
        LocalDate endDate = subscription.getEndDate();

        if(startDate.getYear() > date.getYear() || endDate.getYear() < date.getYear()){
            return 0.0;
        }
        else if(startDate.getYear() < date.getYear() && endDate.getYear() > date.getYear()){
            return plan.getMonthlyPrice()*12;
        }
        else{
            double result = 0.0;
            if(startDate.getYear() == date.getYear()){
                
            }
        }
    }

    private Double fetchMonthlyCost(Subscription subscription, YearMonth month, LocalDate date){
        Plan plan = subscription.getPlan();
        LocalDate startDate = subscription.getStartDate();
        LocalDate endDate = subscription.getEndDate();

        if(startDate.getYear() > month.getYear() || endDate.getYear() < month.getYear()){
            return 0.0;
        }
        else if(startDate.getYear()==month.getYear() && startDate.getMonthValue() > month.getMonthValue()){
            return 0.0;
        }
        else if(startDate.getYear() == month.getYear() && startDate.getMonthValue() == month.getMonthValue()){
            return plan.getMonthlyPrice() * date.getDayOfMonth()/30.0;
        }

        return plan.getMonthlyPrice();
    }
}
