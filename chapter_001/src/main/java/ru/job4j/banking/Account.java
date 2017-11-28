package ru.job4j.banking;

import java.util.*;

public class Account {
    public double value;
    public String requisites;

    public Account(Integer value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    @Override
    public int hashCode() {
        return this.requisites.hashCode() + (int) this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (requisites != other.requisites)
            return false;
        if (value != other.value)
            return false;
        return true;
    }

    public String toString() {
        return "реквизиты: " + this.requisites + " кол-во денег: " + this.value;
    }
}
