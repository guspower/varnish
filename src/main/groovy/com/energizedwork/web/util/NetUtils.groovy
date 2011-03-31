package com.energizedwork.web.util


class NetUtils {

    static int findFreePort(int preferred) {
        int result

        try {
            result = findPort(preferred)
        } catch(IOException io) {
        } finally {
            if(!result) { result = findFreePort() }
        }

        result
    }

    static int findFreePort() {
        findPort 0
    }

    private static int findPort(int port) {
        int result

        def socket = new ServerSocket(port)
        socket.reuseAddress = true
        result = socket.localPort
        socket.close()

        result
    }

}
