package com.example.semester1.core.Interfaces;

public interface Aliasable {
    /**
     * Returns true if object has the given alias
     * E.g. This could be if the alias matches the objects id or displayName
     *
     * @param alias
     * @return hasAlias
     */
    boolean hasAlias(String alias);
}