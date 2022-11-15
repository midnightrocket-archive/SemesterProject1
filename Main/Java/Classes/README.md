# How `ReferenceContainer` works

## What the `ReferenceContainer` provides
This class provides an easier way to search and retrieve elements by an Alias. 
In this case search and retrieve Items or Appliances by either its `id` or `displayName`.
For this purpose an interface [`Aliasable`](../Interfaces/Aliasable.java) have been created. 
> To see what `Aliasable` does, look at its declaration in file [Main/Java/Interfaces/Aliasable.java](../Interfaces/Aliasable.java)  
> And look at an implementation in [Main/Java/Classes/Item.java](Item.java)

`ReferenceContainer` provides two methods
- `getByAlias(String alias)` This returns the first item which has the provided alias. 
- `containsByAlias(String alias)` This return `true` if the Container has any item with the given alias. 

## How it works
The `ReferenceContainer` class is a subclass of `ArrayList`. 
`ArrayList` like any other class, can be extended. 
This means that a lot of work has already been done. 
We don't have to implement logic to be able to hold an array of object, 
as this is provided by extending `ArrayList`. 

To properly understand how `ReferenceContainer` works, 
we need to understand a new concept called **[Generic Types](https://docs.oracle.com/javase/tutorial/java/generics/types.html)**.

Generic types allows developers to declare classes which takes any type of object. 
When an object is provided, then the code knows, that it should work with that type going forward. 

### Let's look at an example.

We all know how to use `ArrayList`. 
```java
ArrayList<Item> myItems = new ArrayList<Item>();
```
The `<Item>` part, is where the type is provided to the ArrayList.  
When we construct the `ArrayList`, we tell the `ArrayList` that it should hold, and work with `Item` objects. 

To see how this works, we have to look at how `ArrayList` is defined.  
If we look at a simplified version of the `ArrayList` class, we get the following:

```java
public class ArrayList<E> {
    //     ⬇︎    and   ⬆︎ are the type variable
    public E get(int index) {
        return elementData(index);
    }
}
```

> To get the full code, look at the reference implementation [here](http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/util/ArrayList.java)

We can observe that the `ArrayList` uses the generic type syntax `<...>`.  
The `E` inside `<E>`, is simply a variable name. (*To be more specific, it is a type variable*).  
When we pass `<Item>` to the `ArrayList` constructor. E.g. `new ArrayList<Item>()`. 
Then the compiler knows, it should use `Item` all the places where `E` occurs.  

So when we write the following code: 
```java
ArrayList<Item> myItems = new ArrayList<Item>();
```
Then the Java compiler *"Transforms"* the underlying code into: 
```java
public class ArrayList<Item> {
    //      ⬇︎ Changed from E
    public Item get(int index) {
        return elementData(index);
    }
}
```
> Note that this is just a markup to better understand the concept.

Notice that the compiler now knows, that the `get()` method should return an `Item` object.  

### Looking at the `ReferenceContainer` code
Let's have a look at the `ReferenceContainer` code:
```java
public class ReferenceContainer<E extends Aliasable> extends ArrayList<E> {
    //                              ⬆︎ What!?
    
    public E getByAlias(String alias) { ... }

    public boolean containsByAlias(String alias) { ... }

    public boolean removeByAlias(String alias) { ... }
}
```
> The file can be found at [Main/Java/Classes/ReferenceContainer.java](ReferenceContainer.java)

First of all we have some unexplained syntax `ReferenceContainer<E extends Aliasable>`.  
Now there is this keyword `extends` inside the generic type declaration. 
This is a [Bounded Generic Type](https://docs.oracle.com/javase/tutorial/java/generics/bounded.html). 
What this means is that we restrict the allowed types that `ReferenceContainer` can hold.  
In essence it means, that any type provided, when constructing a `ReferenceContain<E>`, 
must pass a `E instanceof Aliasable` test. 
In our case it means that `ReferenceContainer` can only hold objects, which implements the `Aliasable` interface. 

> Even though objects which implements an interface, does not inherit from that interface, 
> the objects can still be considered an instance of the given interface.  

> **Q**: _Why is this keyword named `extends`, and not some other less confusing term?_   
> **A**: Because Java

> **Note**  
> We require objects to implement the `Aliasable` interface, so we know that they have the method `hasAlias()`.  
> This is used by `ReferenceContainer` to retrieve objects by their alias. 

If we ignore the part of the bounded generic type, it becomes easier to understand what the rest of the code does.
Let's have a look:  
```java
public class ReferenceContainer<E ... > extends ArrayList<E> {
    //                            ⬆︎ ︎hidden for simplicity
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
```

When we create a `ReferenceContainer` then with an object type like `Item`, 
then the compiler knows it should use `Item` in all the places where `E` occurs.   
So when we write: 
```java
ReferenceContainer<Item> myItems = new ReferenceContainer<Item>();
```
The compiler *transforms* the underlying code into:
```java
public class ReferenceContainer<Item ... > extends ArrayList<Item> {
    // Item class passed in here ⬆︎     So it got changed here ⬆︎ 

    //      ⬇︎ And here
    public Item getByAlias(String alias) {
        
        //    ⬇︎ And here
        for (Item i : this) {
            if (i.hasAlias(alias)) {
                return i;
            }
        }
        return null;
    }

    public boolean containsByAlias(String alias) { ... }

    public boolean removeByAlias(String alias) { ... }
}
```

> Note that this is just a markup to better understand the concept.

Notice that the compiler knows that: 
- It should pass Item to `ArrayList<Item>`. 
- `getByAlias()` should return an `Item` object. 
- The for loop should iterate with `Item` objects. 



**But if we instead wanted to hold `Appliance` objects, e.g:** 
```java
ReferenceContainer<Appliance> myAppliances = new ReferenceContainer<Appliance>();
```
The compiler *"transforms"* the underlying code into:
```java
public class ReferenceContainer<Appliance ... > extends ArrayList<Appliance> {
//  Appliance class passed in here ⬆︎         So it got changed here ⬆︎ 

    //        ⬇︎ And here
    public Appliance getByAlias(String alias) {

        //      ⬇︎ And here
        for (Appliance i : this) {
            if (i.hasAlias(alias)) {
                return i;
            }
        }
        return null;
    }

    public boolean containsByAlias(String alias) { ... }
    
    public boolean removeByAlias(String alias) { ... }
}
```


Notice that the compiler knows that:
- It should pass Appliance to `ArrayList<Appliance>`.
- `getByAlias()` should return an `Appliance` object.
- The for loop should iterate with `Appliance` objects.



## How `ReferenceContainer` is used
Because `ReferenceContainer` provides useful method for finding objects, 
like an item with a certain name, the `Inventory` class can be simplified.

Let's have a look at the code. 
```java
//                   ⬇︎ Does not accept generic type
public class Inventory extends ReferenceContainer<Item> {
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "Your inventory is empty.";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Item item : this) {
            stringBuilder.append(String.format(" - %s\n", item.displayName()));
        }

        return stringBuilder.toString();
    }
}
```
This is truly all that is needed for `Inventory`.  
We can see that `Inventory` does not accept a generic type, 
instead it extends a `ReferenceContainer<Item>`, which is a `ReferenceContainer` that contains `Item` objects. 

So constructing an `Inventory` is very simple:
```java
Inventory myInventory = new Inventory();
```
You don't need to specify the type of objects it holds, because `Inventory` is defined to only hold `Item` objects. 

When you need to add an item to inventory, you can just do:
```java
myInventory.add(myItem);
```
Because `add()` is defined in `ArrayList` class. Which is a super class of `Inventory`.  
And if you want to retrieve an item with a name, like "shoe", just do:
```java
myInventory.getByAlias("shoes");
```
Because `getByAlias()` is defined in `ReferenceContainer`, which is a super class of `Inventory`.  
And `Inventory` finally has defined its own `toString()`
```java
myInventory.toString();
```