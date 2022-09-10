package com.hanwul.kbscbackend.file.mock;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public final class ProcessUtils {
    private ProcessUtils(){
    }

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isRunningPort(int port) throws IOException{
        return isRunning(executeGrepProcessCommand(port));
    }

    public static int findAvailabelRandomPort() throws IOException{
        for(int port = 10000; port<= 65535; port++){
            Process process = executeGrepProcessCommand(port);
            if(!isRunning(process)){
                return port;
            }
        }
        throw new IllegalArgumentException("사용 가능한 포트를 찾을 수 없습니다. (1000~65535");
    }

    private static Process executeGrepProcessCommand(int port) throws IOException{
        if(isWindows()){
            String command = String.format("netstat -nao | find \"LISTEN\" | find \"%d\"", port);
            String[] shell = {"cmd.exe", "/y", "/c", command};
            return Runtime.getRuntime().exec(shell);
        }
        String command = String.format("netstat -nat | grep LISTEN|grep %d", port);
        String[] shell = {"/bin/sh", "-c", command};
        return Runtime.getRuntime().exec(shell);
    }

    private static boolean isWindows(){
        return OS.contains("win");
    }

    private static boolean isRunning(Process process){
        String line;
        StringBuilder pidInfo = new StringBuilder();
        try(BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            while((line = input.readLine()) != null){
                pidInfo.append(line);
            }
        }catch(Exception e){
            throw new IllegalArgumentException("사용 가능한 포트를 찾는 중 에러가 발생했습니다.");
        }
        return !StringUtils.isEmpty(pidInfo.toString());
    }
}
