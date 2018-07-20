package com.core.groovy

class CheckProxy {

    double downloadAndPing(String host, int port, String URLName) throws IOException {


        if(ping(host, port) != 0) {

            InetSocketAddress socket = new InetSocketAddress(host, port)

            Proxy proxy = new Proxy(Proxy.Type.HTTP, socket)
            URL url = new URL(URLName)

            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection(proxy)

            try {
                httpConnection.setConnectTimeout(1000)
                httpConnection.connect();
                System.out.println(URLName + " загружен")
                return 1
            } catch (Exception e) {
                e.printStackTrace()
                return 0
            }
        }else{
            return 0
        }
    }

    long ping(String host, int port) {
        long ping
        try {
            Socket socket = new Socket()

            long start = new GregorianCalendar().getTimeInMillis();
            socket.connect(new InetSocketAddress(host, port), 3000)
            long stop = new GregorianCalendar().getTimeInMillis()

            ping = stop - start

        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Провал")
            return 0
        }

        if(ping < 50) {
            System.out.println("\nСоединение успешно. Хост " + host + " пинг " + ping + " мс")
//            return 1;
            return ping
        }
        else if(ping > 50 && ping < 3000){
            System.out.println("\nСоединение успешно. Хост " + host + " пинг " + ping + " мс")

//            return Math.round((1.0 - (1.0 / (3000.0 / ping))) * 100.0) / 100.0;
            return ping

        }
    }
}
