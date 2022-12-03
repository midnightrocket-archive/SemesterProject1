package com.example.semester1.core.Classes;

import com.example.semester1.core.Interfaces.Aliasable;

public abstract class AliasableImplementation implements Aliasable {
    protected String id;
    protected String displayName;

    public AliasableImplementation(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean hasAlias(String alias) {
        alias = alias.toLowerCase().trim();
        return alias.equals(this.id.toLowerCase()) || alias.equals(this.displayName.toLowerCase());
    }
}