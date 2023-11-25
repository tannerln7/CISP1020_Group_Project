package Helpers;

/**
 * A class with a static method to clear the console screen
 */
public class Cls {
    /**
     * Clears the console screen
     */
    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public static void cls() {
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){ //if OS is windows
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls"); //command prompt, clear screen commands
                Process startProcess = pb.inheritIO().start(); //initialize the process
                startProcess.waitFor(); //start the process and wait for it to end
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear"); //If OS is not windows (Unix based systems (macOS, linux) are all the same)
                Process startProcess = pb.inheritIO().start(); //initialize the process
                startProcess.waitFor(); //start the process and wait for it to end
            }
        }catch(Exception e){
            System.out.println(e); //print exceptions to console
        }
    }
}