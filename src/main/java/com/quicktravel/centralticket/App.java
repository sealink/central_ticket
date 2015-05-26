package com.quicktravel.centralticket;

import spark.Spark;

public class App {

  public static void main(String[] args) {
    start(args);
  }

  static void start(String[] args) {
    int port = 1337;
    String keystore = null;
    String password = null;

    if (args.length >= 1) {
      try {
        port = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("Could not parse port from first arg: " + args[0]);
        System.exit(1);
      }
    }

    if (args.length >= 3) {
      keystore = args[1];
      password = args[2];
    }
    WebConfig webconfig = new WebConfig(port, keystore, password);
    new Web(webconfig);
  }

  static void stop(String[] args) {
    System.out.println("Stopping");
    Spark.awaitInitialization();
    System.out.println("finished awaiting");
    Spark.stop();
    System.out.println("finished stop...");
  }

}
