package com.quicktravel.centralticket;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.secure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.PrintService;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.quicktravel.ticket_printer.PrintServiceLocator;
import com.quicktravel.ticket_printer.TicketPrintCommand;

public class Web {

  static final Logger logger = Logger.getLogger(Web.class.getName());

  @SuppressWarnings("unchecked")
  public Web(WebConfig webconfig) {

    if (webconfig.isSecure()) {
      secure(webconfig.getKeystore(), webconfig.getPassword(), null, null);
    }
    port(webconfig.getPort());

    get("/", (req, res) -> {
      return "Hello - I am your ticket printer service!";
    });

    get("/printers", (request, response) -> {
      response.header("Access-Control-Allow-Origin", "*");

      List<String> printerList = new ArrayList<String>();
      PrintServiceLocator printService = new PrintServiceLocator();
      for (PrintService p : printService.getAll()) {
        printerList.add(p.getName());
      }

      return JSONValue.toJSONString(printerList);
    });

    post("/print-tickets",  (request, response) -> {
      response.header("Access-Control-Allow-Origin", "*");

      JSONObject commandData = (JSONObject) JSONValue.parse(request.body());

      TicketPrintCommand ticketPrintCommand = new TicketPrintCommand();
      ticketPrintCommand.setPrinter(((Long) commandData.get("printer"))
          .intValue());
      ticketPrintCommand
          .setTicketPageSettingsFromMap((Map<String, String>) commandData
              .get("page_format"));
      
      // Big bad ticket data comes in... unchecked, but dealt with
      // in TicketPrintCommand -> TicketListFactory
      List<List<Map<String, Object>>> ticketData = 
          (List<List<Map<String, Object>>>) commandData.get("tickets");
      ticketPrintCommand.setTicketsFromDataList(ticketData);

      try {
        ticketPrintCommand.execute();
      } catch (Exception ex) {
        logger.log(Level.SEVERE, null, ex);
        halt(500, "Print failed: " + ex.getMessage());
      }

      return ""; // OK
    });
  }

}
