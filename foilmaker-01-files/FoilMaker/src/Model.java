//Model



public class Model {


    //private String gameToken;           //Users game token that is assigned when logged in
    private String userUsername;        //Username the player submits
    private String userPassword;        //Password the player submits
    private String userToken;
    private String gameToken;
    private double userScore;
    private String rightAnswer;

    private String participants;
    private String wordGuess;






    /*
        Getter and setter methods
    */
    public void setUserToken(String token){
        this.userToken = token;
    }

    public String getUserToken(){
        return this.userToken;
    }

    public void setGameToken(String token){ this.gameToken = token;}

    public String getGameToken(){ return this.gameToken;}

    public void setUserUsername(String username){
        this.userUsername = username;
    }

    public void setUserPassword(String password){
        this.userPassword = password;
    }

    public String getUserUsername(){ return this.userUsername;}

    public String getUserPassword() { return this.userPassword;}

    public void setUserScore(double score){this.userScore = score;}

    public double getUserScore(){ return this.userScore;}


    public void addParticipant(String participant){
        this.participants += participant;
    }

    public String getParticipants(){
        return this.participants;
    }

    public void setWordGuess(String guess) { this.wordGuess = guess;}

    public String getWordGuess(){
        return this.wordGuess.substring(this.wordGuess.indexOf("-"), this.wordGuess.lastIndexOf("-"));
    }

    public String getRightAnswer(){
        return this.rightAnswer;
    }

    public void setRightAnswer(String message){
        this.rightAnswer = message;
    }















}
