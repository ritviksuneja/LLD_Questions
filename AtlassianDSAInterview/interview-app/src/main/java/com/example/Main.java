package com.example;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Node be = new Node("BE", Collections.emptyList(), List.of("A", "B"));
        Node fe = new Node("FE", Collections.emptyList(), List.of("C", "D"));
        Node hr = new Node("HR", Collections.emptyList(), List.of("E", "F"));
        Node eng = new Node("Eng", List.of(be, fe), List.of("X", "Y"));
        Node company = new Node("Company", List.of(eng, hr), List.of("Z"));

        Organization org = new Organization(company);

        Node lca = org.getCommonGroupForEmployees(company, Set.of("A", "C"));
        System.out.println(lca.getId());
    }
}