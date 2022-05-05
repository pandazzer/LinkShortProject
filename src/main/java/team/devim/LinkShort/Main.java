package team.devim.LinkShort;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.aplication();
    }

    private void aplication(){
        RequestProcess requestProcess = new RequestProcess();
        URLService urlService = new URLService();

        post("/cut", (req, res) -> urlService.getCutUrl(req.body()));
        get("/key/:key", (req, res) -> requestProcess.dao.keyActive(req.params(":key")));
        get("/:key", (req, res) -> {
            String result = requestProcess.getForwarding(req.params(":key"));
            if (!result.equals("Ссылка больше не активна или не записана")){
                res.redirect(requestProcess.getForwarding(req.params(":key")));
            }else return result;
            return null;
        });
    }

}
