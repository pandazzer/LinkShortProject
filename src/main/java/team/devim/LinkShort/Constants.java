package team.devim.LinkShort;

public interface Constants {
    String URL = System.getenv("URL");
    String USER_NAME = System.getenv("USER_NAME");
    String STRING_PASS = System.getenv("STRING_PASS");
    int SECOND_LIVE = Integer.parseInt(System.getenv("SECONDLIVE"));
}
