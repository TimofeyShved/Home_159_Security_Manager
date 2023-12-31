package com.company;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

public class Main {

    public static void main(String[] args) throws LoginException {
	    // Подключаем самодельные правила политики безопастности
        System.setProperty("java.security.policy", "src/MyApp.policy");
        System.setProperty("java.security.auth.login.config", "src/jaas.config");

        // включаем политику безопастности
        System.setSecurityManager(new SecurityManager());
        // например можно зайти в библиотеку виртуальной машины, найти Security
        // и файле java отключить
        // permission java.util.PropertyPermission "os.name", "read"

        // выводим название ОС (это можно заблокировать)
        System.out.println(System.getProperty("os.name"));

        // так же можно авторизоваться через настройки, выдать себе права
        LoginContext loginContext = new LoginContext("Login1");
        loginContext.login();
        Subject subject = loginContext.getSubject();
        System.out.println("Авторизован");

        // И выполнить нужный нам код
        Subject.doAsPrivileged(
                subject,
                new PrivilegedAction<Object>() {
                    @Override
                    public Object run() {

                        // Например этот код
                        System.out.println(System.getProperty("os.name"));

                        return null;
                    }
                },
                null);

        loginContext.logout();

    }
}
