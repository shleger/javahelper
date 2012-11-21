package ru.saa.basic.client;

/**
 * Created with IntelliJ IDEA.
 * User: shleger
 * Date: 21.11.12
 * Time: 18:16
 * To change this template use File | Settings | File Templates.
 */
public class Template {
    public static String REQUEST_HTML_TMPL = "<h1>Web Application Starter Project</h1>\n"
            + "<table align=\"center\">\n"
            + "  <tr>\n"
            + "    <td colspan=\"2\" style=\"font-weight:bold;\">Please enter your name:</td>\n"
            + "  </tr>\n"
            + "  <tr>\n"
            + "    <td id=\"nameFieldContainer\"></td>\n"
            + "    <td id=\"sendButtonContainer\"></td>\n"
            + "  </tr>\n"
            + "  <tr>\n"
            + "    <td colspan=\"2\" style=\"color:red;\" id=\"errorLabelContainer\"></td>\n"
            + "  </tr>\n" + "</table>\n";


    public static String RESPONSE_HTML_TMPL = "<h1>Remote Procedure Call</h1>\n"
            + "<table align=\"center\">\n" + "  <tr>\n"
            + "    <td style=\"font-weight:bold;\">Sending name to server:</td>\n"
            + "  </tr>\n" + "  <tr>\n"
            + "    <td id=\"textToServerContainer\"></td>\n" + "  </tr>\n"
            + "  <tr>\n"
            + "    <td style=\"font-weight:bold;\">Server replies:</td>\n"
            + "  </tr>\n" + "  <tr>\n"
            + "    <td id=\"serverResponseContainer\"></td>\n" + "  </tr>\n"
            + "  <tr>\n" + "    <td id=\"closeButton\"></td>\n" + "  </tr>\n"
            + "</table>\n";
}
