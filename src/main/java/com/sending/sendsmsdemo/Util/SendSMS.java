package com.sending.sendsmsdemo.Util;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;





@Component
public class SendSMS {

        public String sendSms(String numbers,String message) {
            try {
                // Construct data
                String apiKey = "apikey=" + "La08pKAwS5s-eRnDbtnWckIw8iC7fFwvs5uOb7Iy0b";
//                String apiKey = "apikey=" + "h5MxsDronIk-qe09rqiIa2Ke1qlimU38gA7hhDiuS8";
                 message = "&message=" + message;
                String sender = "&sender=" + "TXTLCL";
                 numbers = "&numbers=" + numbers;

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                String data = apiKey + numbers + message + sender;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
                rd.close();

                return stringBuffer.toString();
            } catch (Exception e) {
                System.out.println("Error SMS "+e);
                return "Error "+e;
            }
        }
    }

