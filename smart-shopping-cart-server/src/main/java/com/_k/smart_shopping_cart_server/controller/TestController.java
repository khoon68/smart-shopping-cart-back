package com._k.smart_shopping_cart_server.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {
    // GET 요청 처리
    @GetMapping("/greet")
    public String greet() {
        return "Hello from Spring Boot!";
    }

    // POST 요청 처리
    @PostMapping("/data")
    public String receiveData(@RequestBody Map<String, Object> payload) {
        System.out.println("Received data: " + payload);
        return "Data received: " + payload.get("message");
    }

    @GetMapping("/ip")
    public String getServerAddress(HttpServletRequest request) {
        String scheme = request.getScheme(); // http 또는 https
        int serverPort = request.getServerPort(); // 포트 번호
        String serverIp;

        try {
            // 서버 IP 주소 가져오기
            InetAddress inetAddress = InetAddress.getLocalHost();
            serverIp = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            serverIp = "Unknown";
        }

        // 서버의 전체 주소 반환
        return String.format("%s://%s:%d", scheme, serverIp, serverPort);
    }

    @GetMapping("/hotspot-ip")
    public String getHotspotServerAddress(HttpServletRequest request) {
        String scheme = request.getScheme(); // http 또는 https
        int serverPort = request.getServerPort(); // 포트 번호
        String serverIp = "Unknown";

        try {
            // 네트워크 인터페이스를 순회하며, 유효한 IP 주소를 가져옴
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                // 네트워크 인터페이스가 활성화되었고, 루프백이 아닐 때
                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inetAddresses.nextElement();

                        // IPv4 주소만 가져옴 (IPv6 주소 제외)
                        if (inetAddress instanceof Inet4Address) {
                            serverIp = inetAddress.getHostAddress();
                            break;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // 서버의 전체 주소 반환
        return String.format("%s://%s:%d", scheme, serverIp, serverPort);
    }
}
