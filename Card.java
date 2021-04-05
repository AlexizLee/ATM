import java.util.Hashtable;

public class Card {
    public int cardNum;
    private String pin;
    public Hashtable<Integer,Account> accounts;

    public Card(int num_in,String pin_in,Hashtable<Integer,Account> accounts_in){
        cardNum=num_in;
        pin=pin_in;
        accounts = accounts_in;
    }

    public int getCardNum(){
        return cardNum;
    }

    private String getPin(){
        return pin;
    }

    public Hashtable<Integer,Account> getAccounts(){
        return accounts;
    }

    public void setCardNum(int num){
        cardNum = num;
    }

    public void setPin(String p){
        pin = p;
    }

    public void setAccounts(Hashtable<Integer,Account> acc){
        accounts = acc;
    }

    public void addAccount(Account acc){
        accounts.put(acc.getAccountNum(),acc);
    }

    public boolean checkPin(String p){
        return p.equals(getPin())?true:false;
    }
}
