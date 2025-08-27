package com.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//     -customerSubs: Map<String, List<Subscription>>
//     -customers: List<Customer>
//     +addCustomer(Customer customer)
//     +addSubscription(String customerId, Subscription sub)
//     +getCostReport(): CostReport

public class CostExplorer {
    private final Map<String, List<Subscription>> customerSubs;
    private final Map<String, Customer> customers;

    public CostExplorer(){
        customerSubs = new HashMap<>();
        customers = new HashMap<>();
    }

    public void addCustomer(Customer customer){
        customers.put(customer.getId(), customer);
    }

    public void addSubscription(String customerId, Subscription sub){
        if(!customerSubs.containsKey(customerId)){
            customerSubs.put(customerId, new ArrayList<>());
        }

        customerSubs.get(customerId).add(sub);
    }

    public CostReport getCostReport(String customerId, LocalDate reportDate){
        List<Subscription> subs = customerSubs.get(customerId);

        Double yearlyCost = 0.0;
        Map<YearMonth, Double> monthlyCost = new HashMap<>();

        for(int month = 1; month <= 12; month++){
            Double currMonthCost = 0.0;

            for(Subscription sub : subs){
                currMonthCost += calculateMonthlyCost(sub, month, reportDate);
            }

            yearlyCost += currMonthCost;
            monthlyCost.put(YearMonth.of(reportDate.getYear(), month), currMonthCost);
        }

        return new CostReport(monthlyCost, yearlyCost);
    }

    //step1: calculate currMonth, startDate, endDate
    //step2: adjust endDate if null
    //step3: check if startDate and endDate are out of bounds of currMonth
    //step4: create activeStart - set it at som and activeEnd - set it at eom
    //step5: now reduce activeStart and activeEnd by comparing startDate and endDate with currMonth's 1st and 31st respectively.
    //step6: now further reduce activeStart by comparing it with reportDate. if it is before report date, we should update it. otherwise, active date should stay the same.
    //step7: calculate active days.
    //step8: if active days are greater than 0, we calculate the cost. otherwise, we return 0.
    // private Double calculateMonthlyCost(Subscription sub, int month, LocalDate reportDate){
    //     YearMonth currMonth = YearMonth.of(reportDate.getYear(), month);
    //     LocalDate startDate = sub.getStartTime();
    //     LocalDate endDate = sub.getEndTime();

    //     if(endDate == null){
    //         endDate = LocalDate.of(9999, 12, 31);
    //     }

    //     if(endDate.isBefore(currMonth.atDay(1)) || startDate.isAfter(currMonth.atEndOfMonth())){
    //         return 0.0;
    //     }

    //     LocalDate activeStart = currMonth.atDay(1);
    //     LocalDate activeEnd = currMonth.atDay(currMonth.lengthOfMonth());
    //     Double dailyCost = sub.getPlan().getMonthlyPrice()/currMonth.lengthOfMonth();

    //     if(startDate.isAfter(currMonth.atDay(1))){
    //         activeStart = startDate;
    //     }

    //     if(endDate.isBefore(currMonth.atEndOfMonth())){
    //         activeEnd = endDate;
    //     }

    //     if (currMonth.equals(YearMonth.from(reportDate)) && activeStart.isBefore(reportDate)) {
    //         activeStart = reportDate;
    //     }

    //     int activeDays = activeEnd.getDayOfMonth() - activeStart.getDayOfMonth() + 1;

    //     if(activeDays > 0){
    //         return activeDays * dailyCost;
    //     }

    //     return 0.0;
    // }

    private Double calculateMonthlyCost(Subscription sub, int month, LocalDate reportDate){
        YearMonth currMonth = YearMonth.of(reportDate.getYear(), month);
        LocalDate startDate = sub.getStartTime();
        LocalDate endDate = sub.getEndTime();

        if(endDate == null){
            endDate = LocalDate.of(9999, 12, 31);
        }

        if(endDate.isBefore(currMonth.atDay(1)) || startDate.isAfter(currMonth.atEndOfMonth())){
            return 0.0;
        }

        LocalDate activeStart = currMonth.atDay(1);
        LocalDate activeEnd = currMonth.atEndOfMonth();
        Double dailyCost = sub.getPlan().getMonthlyPrice() / currMonth.lengthOfMonth();

        if(startDate.isAfter(currMonth.atDay(1))){
            activeStart = startDate;
        }

        if(endDate.isBefore(currMonth.atEndOfMonth())){
            activeEnd = endDate;
        }

        if(currMonth == YearMonth.from(sub.getStartTime()) && activeStart.isBefore(reportDate)){
            activeStart = reportDate;
        }

        int activeDays = activeEnd.getDayOfMonth() - activeStart.getDayOfMonth() + 1;
        
        if(activeDays <= 0){
            return 0.0;
        }

        return activeDays * dailyCost;
    }
}
