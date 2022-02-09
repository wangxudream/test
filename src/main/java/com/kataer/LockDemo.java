package com.kataer;

public class LockDemo {

    private static Object obj = new Object();

    public static void main(String[] args) {
        Account from = new Account("from", 10000);
        Account to = new Account("to", 10000);
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                changeSafe(from, to, 1);
//                changeUnSafe(from, to, 1);
            }
        });

        Thread threadB = new Thread(() -> {
            changeSafe(to, from, 1);
//            changeUnSafe(to, from, 1);
        });

        threadA.start();
        threadB.start();
    }


    //交换参数顺序会造成死锁
    public static boolean changeUnSafe(Account from, Account to, Integer money) {
        synchronized (from) {
            synchronized (to) {
                int f = from.getMoney();
                int t = to.getMoney();
                from.setMoney(f - money);
                to.setMoney(t + money);
            }
        }
        return true;
    }


    public static boolean changeSafe(Account from, Account to, Integer money) {
        //通过hash值确保A,B或B,A获取锁的顺序是一致的
        int fromCode = System.identityHashCode(from);
        int toCode = System.identityHashCode(to);
        //保证获取获取锁的顺序一致
        if (fromCode > toCode) {
            synchronized (from) {
                synchronized (to) {
                    int f = from.getMoney();
                    int t = to.getMoney();
                    from.setMoney(f - money);
                    to.setMoney(t + money);
                }
            }
        } else if (fromCode < toCode) {
            synchronized (to) {
                synchronized (from) {
                    int f = from.getMoney();
                    int t = to.getMoney();
                    from.setMoney(f - money);
                    to.setMoney(t + money);
                }
            }
        } else {
            //hash冲突的情况,先竞争同一把锁
            synchronized (obj) {
                synchronized (from) {
                    synchronized (to) {
                        int f = from.getMoney();
                        int t = to.getMoney();
                        from.setMoney(f - money);
                        to.setMoney(t + money);
                    }
                }
            }
        }

        return true;
    }


    private static class Account {
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
    }
}
