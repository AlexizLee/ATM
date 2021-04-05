public class Account {
    public int accountNum;
    public int balance;
    public String accountName;

    public Account(int an,int bl,String ana){
        accountNum = an;
        balance = bl;
        accountName = ana;
    }

    public int getAccountNum(){
        return accountNum;
    }
    public int getBalance(){
        return balance;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setAccountNum(int acc_num){
         accountNum = acc_num;
    }
    public void setBalance(int n_balance){
        balance = n_balance;
    }

    public void setAccountName(String name){
        accountName = name;
    }
}
