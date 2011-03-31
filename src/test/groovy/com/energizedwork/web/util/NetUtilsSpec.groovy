package com.energizedwork.web.util

import spock.lang.Specification

class NetUtilsSpec extends Specification {

    ServerSocket socket

    def cleanup() {
        try { socket?.close() } catch(IOException io) {}
        socket = null
    }

    def 'can find random free port'() {
        expect: 'a non-zero port'
            NetUtils.findFreePort()
    }

    def 'can determine whether preferred port is free'() {
        expect: 'to get a preferred port'
            12345 == NetUtils.findFreePort(12345)
    }

    def 'can try to get preferred port but will back off to random if in use'() {
        given: 'a preferred port that is already in use'
            int preferred = 12345
            def socket = new ServerSocket(12345)

        when: 'we ask for a free port'
            int actual = NetUtils.findFreePort(12345)

        then: 'we get a different port'
            actual != preferred
            actual
    }

}
