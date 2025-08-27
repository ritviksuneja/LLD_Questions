package com.example;

public class Customer {
    private final String id;
    private final String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // public CostReport getCostReport(LocalDate date, String customerId){
    //     List<Subscription> subscriptions = customerSubscriptions.get(customerId);
        
    //     Map<YearMonth, Double> monthlyPrice = new HashMap<>();
    //     Double yearlyPrice = 0.0;

        
    //     for(int m = 1; m <= 12; m++){
    //         Double value = 0.0;
    //         for(Subscription subscription : subscriptions){
    //             value += calculateMonthlyPrice(date, m, subscription);
    //         }

    //         monthlyPrice.put(YearMonth.of(date.getYear(), m), value);
    //     }
    // }

    // private Double calculateMonthlyPrice(LocalDate date, int month, Subscription subs){
    //     int year = date.getYear();

    //     LocalDate startDate = subs.getStartDate();
    //     LocalDate endDate = subs.getEndDate();

    //     if(startDate.isAfter(date) || endDate.isBefore(date)){
    //         return 0.0;
    //     }

    //     if(startDate.getYear() == year && startDate.getMonthValue() == month){
    //         return (YearMonth.of(year, month).lengthOfMonth() - startDate.getDayOfMonth()) * subs.getPlan().getMonthlyPrice() / YearMonth.of(year, month).lengthOfMonth();
    //     }

    //     if(endDate.getYear() == year && endDate.getMonthValue() == month && endDate.isBefore(date)){
    //         return (endDate.getDayOfMonth()) * subs.getPlan().getMonthlyPrice() / YearMonth.of(year, month).lengthOfMonth();
    //     }

    //     // if(endDate.getYear() == year && endDate.getMonthValue() == month && endDate.isAfter(date)){
    //     //     return (endDate.getDayOfMonth()) / subs.getPlan().getMonthlyPrice();
    //     // }

    //     return subs.getPlan().getMonthlyPrice();
}
