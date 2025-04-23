# 🎯 State Design Pattern - Deep Dive with Vending Machine Example

## 🔁 What is the State Design Pattern?

The **State Pattern** is a behavioral design pattern that lets an object change its behavior when its internal state changes — **without using if-else or switch statements**.

> 📦 **Definition (GoF)**  
> “Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.”

---

## 🎯 Use Cases

The State Pattern is ideal when:
- You have an object that behaves differently based on internal state
- You find yourself using many if-else or switch statements to manage state
- You want to encapsulate state-specific behavior
- The number of states might grow or change in the future

---

## ✅ Real-World Examples

| Domain           | Possible States                                 |
|------------------|--------------------------------------------------|
| Vending Machine  | Idle, HasMoney, Dispensing, OutOfStock          |
| Text Editor      | Editing, ReadOnly, Saving                        |
| TCP Connection   | Listening, Connecting, Connected, Closed        |
| Game Character   | Standing, Walking, Jumping, Attacking           |
| Elevator         | MovingUp, MovingDown, Idle, Maintenance         |

---

## 😬 Without State Pattern (Anti-Pattern Style)

```java
class VendingMachine {
    enum State { IDLE, HAS_MONEY, DISPENSING }
    private State state = State.IDLE;
    private double balance = 0;

    public void insertMoney(double amount) {
        if (state == State.IDLE || state == State.HAS_MONEY) {
            balance += amount;
            state = State.HAS_MONEY;
            System.out.println("Inserted: " + amount);
        } else {
            System.out.println("Can't insert money now");
        }
    }

    public void selectItem(String code) {
        if (state == State.HAS_MONEY) {
            System.out.println("Dispensing item: " + code);
            state = State.DISPENSING;
        } else {
            System.out.println("Insert money first");
        }
    }

    public void dispense() {
        if (state == State.DISPENSING) {
            System.out.println("Item dispensed");
            state = State.IDLE;
        }
    }
}


//❌ Problems:
//Lots of if-else based on state
//Hard to read & test
//Adding new states (e.g., Maintenance) is messy
//Breaks Open/Closed Principle


//Refactored using State Pattern

//Interfaces and classes
// 1. State Interface
interface State {
    void insertMoney(double amount);
    void selectItem(String code);
    void dispense();
}

// 2. Context
class VendingMachine {
    private State idleState;
    private State hasMoneyState;
    private State dispensingState;
    private State currentState;

    double balance = 0;

    public VendingMachine() {
        this.idleState = new IdleState(this);
        this.hasMoneyState = new HasMoneyState(this);
        this.dispensingState = new DispensingState(this);
        this.currentState = idleState;
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getIdleState() { return idleState; }
    public State getHasMoneyState() { return hasMoneyState; }
    public State getDispensingState() { return dispensingState; }

    public void insertMoney(double amt) {
        currentState.insertMoney(amt);
    }

    public void selectItem(String code) {
        currentState.selectItem(code);
    }

    public void dispense() {
        currentState.dispense();
    }
}


//concrete states
class IdleState implements State {
    private VendingMachine machine;

    public IdleState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertMoney(double amount) {
        machine.balance += amount;
        System.out.println("Money inserted: " + amount);
        machine.setState(machine.getHasMoneyState());
    }

    public void selectItem(String code) {
        System.out.println("Insert money first");
    }

    public void dispense() {
        System.out.println("Insert money first");
    }
}

class HasMoneyState implements State {
    private VendingMachine machine;

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertMoney(double amount) {
        machine.balance += amount;
        System.out.println("Additional money inserted: " + amount);
    }

    public void selectItem(String code) {
        System.out.println("Item " + code + " selected");
        machine.setState(machine.getDispensingState());
        machine.dispense();
    }

    public void dispense() {
        System.out.println("Please select item first");
    }
}

class DispensingState implements State {
    private VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
    }

    public void insertMoney(double amount) {
        System.out.println("Please wait, dispensing in progress");
    }

    public void selectItem(String code) {
        System.out.println("Dispensing in progress");
    }

    public void dispense() {
        System.out.println("Item dispensed. Returning to idle.");
        machine.balance = 0;
        machine.setState(machine.getIdleState());
    }
}

//resulting behaviour
//VendingMachine vm = new VendingMachine();
//vm.selectItem("Coke");             // ❌ Insert money first
//vm.insertMoney(50);                // ✅ Inserted
//vm.insertMoney(20);                // ✅ Additional
//vm.selectItem("Coke");             // ✅ Dispensing