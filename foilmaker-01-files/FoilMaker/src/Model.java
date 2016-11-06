//Model



public class Model {


    //private String gameToken;           //Users game token that is assigned when logged in
    private String userUsername;        //Username the player submits
    private String userPassword;        //Password the player submits
    private String gameToken;






    /*
        Getter and setter methods
    */
    public void setGameToken(String token){
        this.gameToken = token;
    }

    public String getGameToken(){
        return this.gameToken;
    }

    public void setUserUsername(String username){
        this.userUsername = username;
    }

    public void setUserPassword(String password){
        this.userPassword = password;
    }

    public String getUserUsername(){ return this.userUsername;}

    public String getUserPassword() { return this.userPassword;}
















}
