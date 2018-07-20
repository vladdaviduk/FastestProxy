package com.core.groovy

import com.collection.Proxy

class Parse
{
    def file = new File(getClass().getResource('/proxy.txt').toURI())

    ArrayList<Proxy> socket = new ArrayList<>()

    ArrayList<Proxy> parseProxy(){

        file.eachLine{
            String line -> String host = line.substring(0, line.indexOf(":"))
            int port = Integer.parseInt(line.substring(line.indexOf(":"), line.indexOf("#")).replaceAll(":", ""))
                Proxy proxy = new Proxy()
                proxy.setHost(host)
                proxy.setPort(port)
                proxy.setPingSuccess(false)
                proxy.setProxyInuse(false)
            socket.add(proxy)
        }

       return socket
    }
}
