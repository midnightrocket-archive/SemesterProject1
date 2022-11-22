package com.example.semester1.core.Classes;

import java.util.ArrayList;

import com.example.semester1.core.Interfaces.Aliasable;


// https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
// https://docs.oracle.com/javase/tutorial/java//generics/types.html
// Using a generic bounded type, which requires objects to implement the Aliasable interface.
public class ReferenceContainer<E extends Aliasable> extends ArrayList<E> {

    public E getByAlias(String alias) {
        for (E i : this) {
            if (i.hasAlias(alias)) {
                return i;
            }
        }
        return null;
    }

    public boolean containsByAlias(String alias) {
        return this.getByAlias(alias) != null;
    }

    public boolean removeByAlias(String alias) {
        return this.remove(this.getByAlias(alias));
    }
}