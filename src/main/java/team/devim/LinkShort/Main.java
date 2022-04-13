package team.devim.LinkShort;

import java.sql.Connection;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main(String[] args) {

        RequestProcess requestProcess = new RequestProcess();

        post("/cut", (req, res) -> requestProcess.getCutUrl(req.body()));
        get("/key/:key", (req, res) -> requestProcess.keyActive(req.params(":key")));
        get("/:key", (req, res) -> {
            res.redirect(requestProcess.getForwarding(req.params(":key")));
            return null;
        });

    }

}
