package com.company;


import java.util.*;

class BankAccounts{
    private int accountNo;
    private String name;
    private String accountType;
    private String date;
    private double balance;
    private String address;


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccountType(String accountType) {
        List<String> acctypes = new ArrayList<>();
        acctypes.add("saving");
        acctypes.add("loan");
        acctypes.add("current");
        if(acctypes.contains(accountType)){
            this.accountType = accountType;
        }else{
            Scanner sc = new Scanner(System.in);
            System.out.println("wrong account type. choose between saving, loan and current");
            this.accountType = sc.next();
        }
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }
}

class SortByAddress implements Comparator<BankAccounts> {
    public int compare(BankAccounts a, BankAccounts b)
    {
        return a.getAddress().compareTo(b.getAddress());
    }
}

class SortByAccountId implements Comparator<BankAccounts> {
    public int compare(BankAccounts a, BankAccounts b)
    {
        int ar = a.getAccountNo();
        int br = b.getAccountNo();
        return ar-br;
    }
}

class AccountRecord{
    private ArrayList<BankAccounts> records;
    Scanner sc = new Scanner(System.in);
    AccountRecord(){
        records = new ArrayList<BankAccounts>();
    }
    public void pushback(BankAccounts user){
        records.add(user);
    }

    public int getAccountNo(){
        System.out.println("Enter account number: ");
        int id = sc.nextInt();
        int index = -1;
        for (int i = 0; i<records.size(); i++){
            if(records.get(i).getAccountNo()==id){
                index = i;
                break;
            }
        }
        return index;


    }

    public void updateDetails(){
        int index = getAccountNo();
        if(index == -1){
            System.out.println("User not found");
        }else {
            System.out.println("Following options are available");
            System.out.println("1. Change Name");
            System.out.println("2. Change Address");
            System.out.println("Enter your choice (1/2)");
            int updateChoice = sc.nextInt();
            if(updateChoice==1){
                System.out.println("Enter new Name: ");
                String name = sc.nextLine();
                records.get(index).setName(name);
                System.out.println("Updated Successfully");
            }else if(updateChoice==2){
                System.out.println("Enter new Address");
                String address = sc.nextLine();
                records.get(index).setAddress(address);
                System.out.println("Updated Successfully");
            }
        }
    }

    public void deleteUser(){
        int index = getAccountNo();
        if(index == -1){
            System.out.println("User not found");
        }else{
            records.remove(index);
            System.out.println("Account detail deleted successfully");
        }
    }

    public int displayMenu(){
        System.out.println("Following display options are available");
        System.out.println("--------------------------------");
        System.out.println("1. User details based on id");
        System.out.println("2. User detail based on Name");
        System.out.println("3. All user details who have non-zero balance");
        System.out.println("4. User sorted based on address");
        System.out.println("5. User sorted based on account id");
        System.out.println("Select option (1/2/3/4/5) ");
        int choice = sc.nextInt();
        return choice;
    }

    public void displayUserDetails(){
        int choice = displayMenu();
        switch (choice){
            case 1:
                int id = getAccountNo();
                if(id == -1){
                    System.out.println("User not found");
                }else{
                    System.out.println(records.get(id));
                }
                break;
            case 2:
                System.out.println("Enter user name");
                String name = sc.nextLine();
                for (int i= 0; i<records.size();i++){
                    if(records.get(i).getName() == name){
                        System.out.println(records.get(i));
                    }
                }
                break;
            case 3:
                for (int i = 0; i<records.size();i++){
                    if(records.get(i).getBalance()>0){
                        System.out.println(records.get(i));
                    }
                }
                break;
            case 4:
                if(records.isEmpty()){
                    System.out.println("There are no Account record in the portal.");
                }else {
                    Collections.sort(records, new SortByAddress());
                    for (int i = 0; i<records.size();i++){
                        System.out.println(records.get(i));
                    }
                }
                break;
            case 5:
                if(records.isEmpty()){
                    System.out.println("There are no Account record in the portal.");
                }else{
                    Collections.sort(records, new SortByAccountId());
                    for (int i = 0; i<records.size();i++){
                        System.out.println(records.get(i));
                    }
                }
                    break;
            default:
                System.out.println("Not a valid input");
        }

    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here

        int flag=0;

        AccountRecord accountRecord = new AccountRecord();
        Scanner sc = new Scanner(System.in);

        while(true){
            Introduction();
            int selectedOption = getSelectedOption();
            switch (selectedOption){
                case 1: BankAccounts user = getAccountDetails();
                    accountRecord.pushback(user);
                    break;
                case 2:
                    accountRecord.updateDetails();
                    break;
                case 3: System.out.println("Enter account number: ");
                    int accountNo= sc.nextInt();
                    accountRecord.deleteUser();
                    break;
                case 4: accountRecord.displayUserDetails();
                    break;
                default: flag=1;
                    System.out.println("Exiting the operation as per user input.");
                    System.out.println("--------------------------------------------------------");
                    break;
            }
            if(flag==1) break;
        }

    }

    private static BankAccounts getAccountDetails(){
        Scanner sc= new Scanner(System.in);
        BankAccounts user = new BankAccounts();
        System.out.println("Enter account number");
        int accNo = sc.nextInt();
        user.setAccountNo(accNo);
        System.out.println("Enter your name");
        String name = sc.nextLine();
        user.setName(name);
        System.out.println("Type of account i.e saving, current or loan");
        String type = sc.nextLine();
        user.setAccountType(type);
        System.out.println("Date of birth");
        String dob = sc.nextLine();
        user.setDate(dob);
        System.out.println("Enter amount to be deposit");
        double amount = sc.nextDouble();
        user.setBalance(amount);
        System.out.println("Enter address");
        String address = sc.nextLine();
        user.setAddress(address);

        return user;
    }

    public static void Introduction(){
        System.out.println("--------------------------------------------------------");
        System.out.println(" *** Welcome to Banking Application ***");
        System.out.println("--------------------------------------------------------");
        System.out.println("Following operations are supported by the portal:");
        System.out.println("1. Create bank user");
        System.out.println("2. Update details");
        System.out.println("3. Delete user details");
        System.out.println("4. Display user details");
        System.out.println("5. Exit");
    }

    public static int getSelectedOption(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter your choice (1/2/3/4/5) : ");
        int selectedOption = sc.nextInt();
        System.out.println("--------------------------------------------------------");
        return selectedOption;
    }
}
