package com.company;

public class Main {

    public static void main(String[] args) {
	    // Подключаем самодельные правила политики безопастности
        System.setProperty("java.security.policy", "src/MyApp.policy");

        // включаем политику безопастности
        System.setSecurityManager(new SecurityManager());
        // например можно зайти в библиотеку виртуальной машины, найти Security
        // и файле java отключить
        // permission java.util.PropertyPermission "os.name", "read"

        // выводим название ОС (это можно заблокировать
        System.out.println(System.getProperty("os.name"));
    }
}
