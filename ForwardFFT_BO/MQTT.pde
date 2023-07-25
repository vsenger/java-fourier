String turnOnLamp[] = {"/usr/local/bin/aws", "iot-data", "publish", "--topic", "control/lamp",  "--cli-binary-format", "raw-in-base64-out", "--payload", "1"}; 
String turnOffLamp[] = {"/usr/local/bin/aws", "iot-data", "publish", "--topic", "control/lamp",  "--cli-binary-format", "raw-in-base64-out", "--payload", "0"}; 
void turnOnLamp1() {
  try {
    //Process p = exec("ls");
    //Process p = exec("/usr/local/bin/aws iot-data publish --topic control/lamp --cli-binary-format raw-in-base64-out --payload 1");
    Process p = exec(turnOnLamp);
    InputStream is = p.getInputStream();
    Scanner s = new Scanner(is).useDelimiter("\\n");
    while(s.hasNext()) {
      String out = s.next();
      System.out.println(out);
      
    }
    int result = p.waitFor();
    println("the process returned " + result);
  } catch (Exception e) {
    e.printStackTrace();
  }

}
void turnOffLamp1() {
  try {
    //Process p = exec("ls");
    //Process p = exec("/usr/local/bin/aws iot-data publish --topic control/lamp --cli-binary-format raw-in-base64-out --payload 1");
    Process p = exec(turnOffLamp);
    InputStream is = p.getInputStream();
    Scanner s = new Scanner(is).useDelimiter("\\n");
    while(s.hasNext()) {
      String out = s.next();
      System.out.println(out);
      
    }
    int result = p.waitFor();
    println("the process returned " + result);
  } catch (Exception e) {
    e.printStackTrace();
  }

}
