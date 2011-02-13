package com.energizedwork.web.cache.varnish

class VarnishRequest {

    def backend = new VarnishRequestSide(backend:true)
    def client = new VarnishRequestSide()

    boolean isCacheMiss() { backend['txURL'] }
    boolean isCacheHit() { !cacheMiss }

    boolean validate() {
        cacheMiss ? backend.xid == client.xid : true
    }

    String toString() {
        "VarnishRequest:\n\tclient:$client ${cacheMiss ? "\n\tbackend:$backend": '\tCACHE HIT'}"
    }

}

