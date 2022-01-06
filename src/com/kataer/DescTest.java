package com.kataer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescTest {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Account account = new Account("Account" + i, i);
            accounts.add(account);
        }
        count(accounts, 30);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i));
        }
    }

    public static void count(List<Account> accounts, int num) {
        if (accounts == null || accounts.size() == 0) {
            return;
        }
        Map<String, Integer> changeMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int money = accounts.get(i).getMoney();
            if (money >= num) {
                accounts.get(i).setMoney(money - num);
                changeMap.put(accounts.get(i).getName(), num);
                break;
            } else {
                accounts.get(i).setMoney(0);
                changeMap.put(accounts.get(i).getName(), money);
                num = num - money;
            }
        }
        for (Map.Entry<String, Integer> entry : changeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static class Account {
        private String name;
        private Integer money;

        public Account(String name, Integer money) {
            this.name = name;
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMoney() {
            return money;
        }

        public void setMoney(Integer money) {
            this.money = money;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "name='" + name + '\'' +
                    ", money=" + money +
                    '}';
        }
    }
}
